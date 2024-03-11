package avs.recruitment.elevators.services;

import avs.recruitment.elevators.models.Elevator;
import avs.recruitment.elevators.models.NextFloor;
import avs.recruitment.elevators.models.enums.Direction;
import avs.recruitment.elevators.repositories.ElevatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorServiceTest {

    private final ElevatorRepository elevatorRepository = new ElevatorRepository();
    private final ElevatorService elevatorService = new ElevatorService(elevatorRepository);

    Queue<NextFloor> testQ;
    private Elevator elevator;

    @BeforeEach
    public void setUp() {
        testQ = new ArrayDeque<>();
        elevator = Elevator.builder()
                .elevatorId(-1)
                .currentFloor(-1)
                .destinationFloor(-1)
                .currentDirection(Direction.WAITING)
                .floorsQueue(testQ)
                .build();
    }

    @Test
    void addElevator() {
        assertEquals(0, elevatorRepository.getElevators().size());
        elevatorService.addElevator();
        assertEquals(1, elevatorRepository.getElevators().size());
    }

    @Test
    void maxNumberOfElevators() {
        for (int i = 0; i < elevatorRepository.getMaxNumberOfElevators(); i += 1) {
            elevatorService.addElevator();
        }
        assertEquals(16, elevatorRepository.getElevators().size());
        assertThrowsExactly(IllegalStateException.class, elevatorService::addElevator);
        assertEquals(16, elevatorRepository.getElevators().size());
    }

    @Test
    void deleteElevator() {
        elevatorService.addElevator();
        elevatorService.addElevator();
        elevatorService.addElevator();

        Set<Integer> indexes = new HashSet<>(elevatorRepository.getElevators().keySet());
        indexes.forEach(elevatorService::deleteElevator);
        indexes.forEach(id -> assertFalse(elevatorRepository.getElevators().containsKey(id)));
        assertTrue(elevatorRepository.getElevators().isEmpty());
    }

    @Test
    void update() {
        NextFloor currentStep = new NextFloor(5, Direction.UP);
        NextFloor nextStep = new NextFloor(8, Direction.UP);
        testQ.add(currentStep);
        testQ.add(nextStep);


        elevatorService.update(elevator);
        assertEquals(5, elevator.getCurrentFloor());
        assertEquals(8, elevator.getDestinationFloor());
        assertEquals(Direction.UP, elevator.getCurrentDirection());

        elevatorService.update(elevator);
        assertEquals(8, elevator.getCurrentFloor());
        assertEquals(8, elevator.getDestinationFloor());
        assertEquals(Direction.WAITING, elevator.getCurrentDirection());
    }

    @Test
    void status() {
        elevatorService.addElevator();
        elevatorService.addElevator();

        assertFalse(elevatorService.status().isEmpty());
        assertEquals(2, elevatorService.status().size());
    }


    @Test
    void selectDestination() {
        elevatorRepository.getElevators().put(elevator.getElevatorId(), elevator);
        elevatorService.selectDestination(elevator.getElevatorId(), 5);
        assertEquals(Direction.UP, elevator.getFloorsQueue().peek().direction());

        elevatorService.step();

        elevatorService.selectDestination(elevator.getElevatorId(), 2);
        assertEquals(Direction.DOWN, elevator.getFloorsQueue().peek().direction());
    }

}
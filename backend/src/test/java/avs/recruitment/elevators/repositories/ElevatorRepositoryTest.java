package avs.recruitment.elevators.repositories;

import avs.recruitment.elevators.models.Elevator;
import avs.recruitment.elevators.models.NextFloor;
import avs.recruitment.elevators.models.enums.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorRepositoryTest {

    private final ElevatorRepository elevatorRepository = new ElevatorRepository();

    Queue<NextFloor> testQ;
    private Elevator elevator1;
    private Elevator elevator2;
    private Elevator elevator3;


    @BeforeEach
    public void setUp() {
        testQ = new ArrayDeque<>();
        elevator1 = Elevator.builder()
                .elevatorId(1)
                .currentFloor(3)
                .destinationFloor(1)
                .currentDirection(Direction.DOWN)
                .floorsQueue(new ArrayDeque<>())
                .build();

        elevator2 = Elevator.builder()
                .elevatorId(2)
                .currentFloor(5)
                .destinationFloor(5)
                .currentDirection(Direction.WAITING)
                .floorsQueue(new ArrayDeque<>())
                .build();

        elevator3 = Elevator.builder()
                .elevatorId(3)
                .currentFloor(7)
                .destinationFloor(9)
                .currentDirection(Direction.UP)
                .floorsQueue(new ArrayDeque<>())
                .build();
    }

    @Test
    void getNextElevator() {
        elevatorRepository.getElevators().put(elevator1.getElevatorId(), elevator1);
        elevatorRepository.getElevators().put(elevator2.getElevatorId(), elevator2);
        elevatorRepository.getElevators().put(elevator3.getElevatorId(), elevator3);

        assertEquals(elevatorRepository.getNextElevator(4, Direction.UP), elevator2);
        assertEquals(elevatorRepository.getNextElevator(8, Direction.UP), elevator3);
        assertEquals(elevatorRepository.getNextElevator(6, Direction.UP), elevator2);
        assertEquals(elevatorRepository.getNextElevator(4, Direction.DOWN), elevator1);
    }

}
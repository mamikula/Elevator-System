package avs.recruitment.elevators.services;

import avs.recruitment.elevators.models.Elevator;
import avs.recruitment.elevators.models.NextFloor;
import avs.recruitment.elevators.models.Status;
import avs.recruitment.elevators.models.enums.Direction;
import avs.recruitment.elevators.repositories.ElevatorRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class ElevatorService implements ElevatorSystem {
    private final ElevatorRepository elevatorRepository;

    public void addElevator() {
        elevatorRepository.addElevator();
    }

    public void deleteElevator(int elevatorId) {
        elevatorRepository.deleteElevator(elevatorId);
    }

    @Override
    public void update(Elevator elevator) {
        NextFloor currentStep = elevator.getFloorsQueue().poll();
        NextFloor nextStep = elevator.getFloorsQueue().peek();
        if (currentStep != null) {
            elevator.setCurrentFloor(currentStep.floorNumber());
            if (nextStep != null) {
                elevator.setDestinationFloor(nextStep.floorNumber());
                elevator.setCurrentDirection(nextStep.direction());
            } else elevator.setCurrentDirection(Direction.WAITING);
        }
    }

    @Override
    public List<Status> status() {
        return elevatorRepository.getElevators().entrySet().stream()
                .map(elevatorEntry -> new Status(
                        elevatorEntry.getKey(),
                        elevatorEntry.getValue().getCurrentFloor(),
                        elevatorEntry.getValue().getDestinationFloor()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void pickUp(int currentFloor, Direction direction) {
        elevatorRepository.getNextElevator(currentFloor, direction)
                .getFloorsQueue()
                .add(new NextFloor(currentFloor, direction));
    }

    public void selectDestination(int elevatorId, int destinationFloor) {
        Elevator elevator = elevatorRepository.getElevators().get(elevatorId);
        Queue<NextFloor> elevatorFQ = elevator.getFloorsQueue();

        if (elevator.getCurrentFloor() > destinationFloor)
            elevatorFQ.add(new NextFloor(destinationFloor, Direction.DOWN));

        else elevatorFQ.add(new NextFloor(destinationFloor, Direction.UP));
    }

    @Override
    public void step() {
        elevatorRepository.getElevators().values().forEach(this::update);
    }

}

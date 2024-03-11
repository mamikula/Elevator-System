package avs.recruitment.elevators.repositories;


import avs.recruitment.elevators.models.Elevator;
import avs.recruitment.elevators.models.enums.Direction;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

@Repository
@Getter
public class ElevatorRepository {
    private final HashMap<Integer, Elevator> elevators = new HashMap<>();
    private final int maxNumberOfElevators = 16;

    public void deleteElevator(int elevatorId) {
        elevators.remove(elevatorId);
    }
    public void addElevator() {
        int availableIndex = findAvailableIndex();
        elevators.put(
                availableIndex,
                Elevator.buildElevator(availableIndex));
    }
    private int findAvailableIndex() throws IllegalStateException{
        for (int i = 0; i < maxNumberOfElevators; i++) {
            if (elevators.get(i) == null) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

    public Elevator getNextElevator(int currentFloor, Direction direction) {
        Optional<Elevator> closestElevator = elevators.values().stream()
                .filter(elevator -> elevator.getCurrentDirection().equals(direction) || elevator.getCurrentDirection().equals(Direction.WAITING))
                .min(Comparator.comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - currentFloor)));

        return closestElevator.orElseGet(() -> elevators.values().stream()
                .min(Comparator.comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - currentFloor))).get());
    }
}

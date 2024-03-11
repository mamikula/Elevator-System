package avs.recruitment.elevators.services;

import avs.recruitment.elevators.models.Elevator;
import avs.recruitment.elevators.models.Status;
import avs.recruitment.elevators.models.enums.Direction;

import java.util.List;

public interface ElevatorSystem {
    void pickUp(int currentFloor, Direction direction);

    void step();

    List<Status> status();

    void update(Elevator elevator);

}

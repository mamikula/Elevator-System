package avs.recruitment.elevators.models;

import avs.recruitment.elevators.models.enums.Direction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Queue;

@Getter
@Setter
@Builder
public class Elevator{
    private int elevatorId;
    private int currentFloor;
    private int destinationFloor;
    private Direction currentDirection;
    private Queue<NextFloor> floorsQueue;

    public static Elevator buildElevator(int elevatorId){
        return Elevator.builder()
                .elevatorId(elevatorId)
                .currentFloor(0)
                .destinationFloor(0)
                .currentDirection(Direction.WAITING)
                .floorsQueue(new ArrayDeque<>())
                .build();
    }

}

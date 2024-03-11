package avs.recruitment.elevators.controllers;

import avs.recruitment.elevators.models.enums.Direction;
import avs.recruitment.elevators.services.ElevatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/elevators")
@RequiredArgsConstructor
public class ElevatorController {
    private final ElevatorService elevatorService;


    @GetMapping("/add-elevator")
    public ResponseEntity<?> addElevator() {
        try {
            elevatorService.addElevator();
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-elevator/{elevatorId}")
    public ResponseEntity<Void> deleteElevator(@PathVariable int elevatorId) {
        elevatorService.deleteElevator(elevatorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getStatus() {
        return new ResponseEntity<>(elevatorService.status(), HttpStatus.OK);
    }

    @GetMapping("/pick-up")
    public ResponseEntity<Void> pickUp(@RequestParam int currentFloor, @RequestParam Direction direction) {
        elevatorService.pickUp(currentFloor, direction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/select-destination")
    public ResponseEntity<Void> selectDestination(@RequestParam int elevatorId, @RequestParam int destinationFloor) {
        elevatorService.selectDestination(elevatorId, destinationFloor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/step")
    public ResponseEntity<Void> step() {
        elevatorService.step();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

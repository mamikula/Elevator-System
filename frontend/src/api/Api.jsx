export async function addElevator() {
    await fetch(`http://localhost:8080/elevators/add-elevator`);
}

export async function deleteElevator(elevatorId) {
    console.log(elevatorId)
    await fetch(`http://localhost:8080/elevators/delete-elevator/${elevatorId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
          }
    });
}

export async function getStatus() {
    const response = await fetch(`http://localhost:8080/elevators/status`);
    return await response.json();
}

export async function pickUp(currentFloor, direction) {
    await fetch(`http://localhost:8080/elevators/pick-up?currentFloor=${currentFloor}&direction=${direction}`);
}

export async function selectDestination(elevatorId, destinationFloor) {
    await fetch(`http://localhost:8080/elevators/select-destination?elevatorId=${elevatorId}&destinationFloor=${destinationFloor}`);
}

export async function step() {
    await fetch(`http://localhost:8080/elevators/step`);
}
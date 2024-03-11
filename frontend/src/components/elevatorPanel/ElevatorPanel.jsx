import React from 'react';
import { addElevator, deleteElevator } from '../../api/Api';
import './ElevatorPanel.css'

export default function ElevatorPanel( {ElevatorPanelProps} ) {
  
  return (
    <div>
      <h2>Elevator Panel</h2>
      <div>
        <h3>Add New Elevator</h3>
        <button className='Panel-button' onClick={addElevator} disabled={ElevatorPanelProps.length===16}>Add Elevator</button>
      </div>
      <div>
        <h3>Elevators List</h3>
        <div>
          {ElevatorPanelProps.map(elevator => (
            <div className='List-container' key={elevator.elevatorId}>
              <span>Floor: {elevator.currentFloor}</span>
              <span>DestinationFloor: {elevator.destinationFloor}</span>
              <button className='Panel-button' onClick={() => deleteElevator(elevator.elevatorId)}>Delete elevator {elevator.elevatorId + 1}</button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

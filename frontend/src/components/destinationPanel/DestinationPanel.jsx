import React from 'react'
import { selectDestination } from '../../api/Api';
import './DestinationPanel.css'

function DestinationPanel(destinationPanelProps) {
    const { elevatorId } = destinationPanelProps;
  return (
    <div><div>
    <h3>Choose Destination Floor for Elevator {elevatorId + 1}</h3>
    <div className='Buttons-panel'>
      {[...Array(10)].map((_, floor) => (
        <button className='Destination-button' key={floor} onClick={() => selectDestination(elevatorId, floor)}>
          Floor {floor}
        </button>
      ))}
    </div>
  </div>
</div>
  )
}

export default DestinationPanel
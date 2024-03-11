import React from 'react'
import './Elevator.css'
import Popup from 'reactjs-popup'
import DestinationPanel from '../destinationPanel/DestinationPanel'

function Elevator( elevatorProps ) {
  const { elevatorId } = elevatorProps;
  return (
      <Popup trigger=
        {<button className='elevator'> {elevatorId + 1} </button>} position="right center">

        <DestinationPanel elevatorId={elevatorId}/>

      </Popup>
      
  )
}

export default Elevator 
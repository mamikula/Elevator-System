import React from 'react';
import './Column.css'
import Elevator from '../elevator/Elevator';



function Column( {columnProps} ) {
    const floorHeightPercentage = 10;
    const { elevatorId, currentFloor, destinationFloor } = columnProps;
    const elevatorPosition = {bottom: `${currentFloor * floorHeightPercentage}%`,};
    
  return (
    <div className='column' >
        {Array.from({ length: 10 }).map((_, index) => (
        <div key={index} className="floor">
        </div>
      ))}
<div style={elevatorPosition} className="elevator-position">
        <Elevator elevatorId={elevatorId}/>
      </div>
  

    </div>
  )
}

export default Column
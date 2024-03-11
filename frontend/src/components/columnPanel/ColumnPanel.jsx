import React from 'react';
import { pickUp } from '../../api/Api';
import './ColumnPanel.css';
import Direction from '../enums/Directions';

function ColumnPanel( columnPanelProps ) {
  const { currentFloor } = columnPanelProps;

  return (
    <div className="column-panel">
      <div className="button-container">
        {currentFloor !== 9 && (
          <button onClick={() => pickUp(currentFloor, Direction.UP)} className="call-button">⭡</button>
        )}
        {currentFloor !== 0 && (
          <button onClick={() => pickUp(currentFloor, Direction.DOWN)} className="call-button">⭣</button>
        )}
      </div>
    </div>
  );
}

export default ColumnPanel;

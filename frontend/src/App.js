import './App.css';
import Column from './components/column/Column.jsx';
import { useEffect, useState } from 'react';
import './api/Api.jsx'
import { getStatus, step } from './api/Api.jsx';
import ElevatorPanel from './components/elevatorPanel/ElevatorPanel.jsx';
import ColumnPanel from './components/columnPanel/ColumnPanel.jsx';


function App() {
  const [statuses, setStatuses] = useState([])

useEffect(() => {
  updateStatus()
}, [statuses]);

  function updateStatus() {
    getStatus().then((statuses) => {
      const mappedStatuses = statuses.map((status) => ({
        elevatorId: status.elevatorId,
        currentFloor: status.currentFloor,
        destinationFloor: status.destinationFloor,
      }));
      setStatuses(mappedStatuses)
    });
  }
  

  return (
    <div className="App">
      <div className='Column-container'>
      {statuses.map((status, index) => (
        <Column key={index} columnProps={status} />
      ))}
      </div>
      
      <div className='Column-panel'>
        {Array.from({ length: 10 }).map((_, index) => (
          <div key={index}>
            <ColumnPanel currentFloor={10 - index - 1} /> 
          </div>
        ))}
      </div>

      <div className='Elevator-panel'>
        <ElevatorPanel ElevatorPanelProps={statuses}/>
      </div>

      <button className='Step-button' onClick={() => {step().then(() => updateStatus()) }}>Step</button>

    </div>

  );
}

export default App;

var React = require('react');
var ReactDOM = require('react-dom');
var LineChart = require("react-chartjs").Line;
const chartData = [
      {name: 'Page A', uv: 4000, pv: 2400, amt: 2400},
      {name: 'Page B', uv: 3000, pv: 1398, amt: 2210},
      {name: 'Page C', uv: 2000, pv: 9800, amt: 2290},
      {name: 'Page D', uv: 2780, pv: 3908, amt: 2000},
      {name: 'Page E', uv: 1890, pv: 4800, amt: 2181},
      {name: 'Page F', uv: 2390, pv: 3800, amt: 2500},
      {name: 'Page G', uv: 3490, pv: 4300, amt: 2100},
];

const chartOptions = {
    scales: {
      yAxes: [{
        ticks: {
          reverse: false
        }
      }]
    }
  }

export default class Line extends React.Component {
  constructor() {
    super();
  }

    render() {
      return (
        <div>
        <div class="row"> 
        <h1>Chartjs</h1>
        <div class="col-md-12">  
          <div class="col-md-6">   
            <LineChart data={chartData} options={chartOptions} width="600" height="250"/>
          </div>
        </div>
        </div> 
        </div>
      );
    }
}
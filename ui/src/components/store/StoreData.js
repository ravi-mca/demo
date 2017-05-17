
var React = require('react');
var ReactDOM = require('react-dom');
import {LineGraph, BarGraph, AreaGraph, PieChart, ScatterPlot} from 'react-d3-responsive';
// import rd3r from '../../lib-compiled';
// import rd3r from '../../script-compiled';
import ChartData from '../data.js';
import {Pie, BarChart, Bar, LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';
//import LineChartJs from './Line.js';
const barData = [
      {name: 'Page A', uv: 4000, pv: 2400, amt: 2400},
      {name: 'Page B', uv: 3000, pv: 1398, amt: 2210},
      {name: 'Page C', uv: 2000, pv: 9800, amt: 2290},
      {name: 'Page D', uv: 2780, pv: 3908, amt: 2000},
      {name: 'Page E', uv: 1890, pv: 4800, amt: 2181},
      {name: 'Page F', uv: 2390, pv: 3800, amt: 2500},
      {name: 'Page G', uv: 3490, pv: 4300, amt: 2100},
];

const data = [
       {
      "name":"02-11-2016",
      "count":120
    },
    {
      "name":"02-12-2016",
      "count":200
    },
    {
      "name":"02-13-2016",
      "count":150
    },
    {
      "name":"02-14-2016",
      "count":496
    },
    {
      "name":"02-15-2016",
      "count":140
    },
    {
      "name":"02-16-2016",
      "count":380
    },
    {
      "name":"02-17-2016",
      "count":100
    }
];
export default class StoreData extends React.Component {
  constructor() {
    super();
  }

  render() {
    return (
      <div>
      <div class="row"> 
      <h1>React Charts</h1>
      <div class="col-md-12">  
        <div class="col-md-6">   
          <LineChart width={600} height={300} data={data}
              margin={{top: 5, right: 30, left: 20, bottom: 5}}>
           <XAxis dataKey="name"/>
           <YAxis/>
           <CartesianGrid strokeDasharray="3 3"/>
           <Tooltip/>
           <Legend />
           <Line type="monotone" dataKey="count" stroke="#8884d8" activeDot={{r: 8}}/>
          </LineChart>
        </div>
        <div class="col-md-6">
          <BarChart width={600} height={300} data={data}
            margin={{top: 5, right: 30, left: 20, bottom: 5}}>
           <XAxis dataKey="name"/>
           <YAxis/>
           <CartesianGrid strokeDasharray="3 3"/>
           <Tooltip/>
           <Legend />
           <Bar dataKey="count" fill="#82ca9d" />
          </BarChart>
        </div>
        <div class="col-md-6">
         <BarChart width={600} height={300} data={barData}
            margin={{top: 5, right: 30, left: 20, bottom: 5}}>
           <XAxis dataKey="name"/>
           <YAxis/>
           <CartesianGrid strokeDasharray="3 3"/>
           <Tooltip/>
           <Legend />
           <Bar dataKey="pv" fill="#8884d8" />
           <Bar dataKey="uv" fill="#82ca9d" />
          </BarChart>
        );
        </div>
      </div>
      </div>
      <div class="row">
      <h1>D3 Charts</h1>
      <div class="col-md-12">
          <div class="col-md-6">
          <LineGraph
          title="Line Graph - 700px max width"
          width={400}
          height={300}
          chartId="custom-ID"
          chartClassName="custom-CLASS"
          xAxisLabel="X Axis Label"
          yAxisLabel="Y Axis Label"
          xDataKey="day"
          yDataKey="count"
          dateFormat="%m-%d-%Y"
          xToolTipLabel="X-TT "
          yToolTipLabel="Y-TT "
          lineType="linear"
          yMaxBuffer={50}
          data={data}/>
          </div>
          <div class="col-md-6">
            <AreaGraph
            title="Area Graph - 700px max width"
            width={700}
            height={500}
            chartId="custom-ID"
            chartClassName="custom-CLASS"
            xAxisLabel="X Axis Label"
            yAxisLabel="Y Axis Label"
            xDataKey="day"
            yDataKey="count"
            dateFormat="%m-%d-%Y"
            xToolTipLabel="X-TT"
            yToolTipLabel="Y-TT"
            lineType="linear"
            yMaxBuffer={50}
            data={data} />
           </div>
           <div class="col-md-6"> 
            <ScatterPlot
              title="Scatter Plot - Date X axis"
              xDataKey="day"
              yDataKey="count"
              dataType="date"
              trendLine={true}
              data={ChartData.scatterPlotData} />
            </div>
            <div class="col-md-6">  
              <BarGraph
                title="Bar Graph"
                xDataKey="label"
                barChartType="side"
                xAxisLabel="X Axis Label"
                yAxisLabel="Y Axis Label"
                keys={['Your Score','Month To Date','Vin Average']}
                legendValues={ChartData.soldRatiosLegend}
                data={ChartData.soldRatios} />
              </div>
              <div class="col-md-6">  
                <PieChart
                  title="Pie Chart"
                  chartId="piechart"
                  data={ChartData.pieTestData}
                  innerRadiusRatio={0}
                  labelOffset={1}
                  startAngle={0}
                  endAngle={360} />
                </div>
                <div class="col-md-6">  
                  <PieChart
                    title="Pie Chart - Different Start and End Angles"
                    chartId="piechart"
                    data={ChartData.pieTestData}
                    innerRadiusRatio={.8}
                    labelOffset={1}
                    showLabel={false}
                    legend={false}
                    startAngle={-50}
                    endAngle={154} />
                  </div>  

      
      </div>
      </div>

      </div>
    );
  }
}
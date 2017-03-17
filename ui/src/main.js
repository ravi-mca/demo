import React from "react";
import ReactDOM from "react-dom";
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import 'bootstrap/dist/css/bootstrap.css';
import Home from "./components/home/Home";
import Merchands from "./components/merchands/Merchands";
import Dataportals from "./components/Data-Portals/Dataportals";

import Layout from "./components/Layout";


const app = document.getElementById('app');

ReactDOM.render((
  <Router history = {browserHistory}>
      <Route path = "/" component = {Layout}>
         <IndexRoute  component = {Layout} />
         <Route path = "/home" component = {Home} />
         <Route path = "/merchands" component = {Merchands} />
         <Route path = "/dataportals" component = {Dataportals} />
      </Route>
   </Router>
),app);

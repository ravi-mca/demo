import React from "react";
import ReactDOM from "react-dom";

import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import 'bootstrap/dist/css/bootstrap.css';
import Admins from "./components/admins/Admins";
import Merchants from "./components/merchants/Merchants";
import Dataportal from "./components/Data-Portal/Dataportal";

import Layout from "./components/Layout";


const app = document.getElementById('app');

ReactDOM.render((
  <Router history = {browserHistory}>
      <Route path = "/" component = {Layout}>
         <IndexRoute  component = {Layout} />
         <Route path = "/admins" component = {Admins} />
         <Route path = "/merchants" component = {Merchants} />
         <Route path = "/dataportal" component = {Dataportal} />
      </Route>
   </Router>
), app);

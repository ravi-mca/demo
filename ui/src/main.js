import React from "react";
import ReactDOM from "react-dom";
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';

import 'bootstrap/dist/css/bootstrap.css';
import 'font-awesome/css/font-awesome.min.css';
import './css/login.css';

import Layout from "./components/Layout";

const app = document.getElementById('app');

ReactDOM.render((
	<Router history = {browserHistory}>
      <Route path = "/" component = {Layout}></Route>
   </Router>
),app);

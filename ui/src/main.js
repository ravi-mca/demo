import React from "react";
import ReactDOM from "react-dom";

import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';

import 'bootstrap/dist/css/bootstrap.css';
import 'font-awesome/css/font-awesome.min.css';
import './css/variable.css';
import './css/login.css';
import './css/footer.css';
import './css/header.css';
import './css/sidebar.css';
import './css/merchant.css';
import './fonts/fonts.css';

import Layout from "./components/Layout";
import Login from "./components/login/Login";
import Admins from "./components/admins/Admins";
import Dashboard from "./components/dashboard/Dashboard";
import Merchants from "./components/merchants/Merchants";
import Dataportal from "./components/Data-Portal/Dataportal";
import Account from "./components/Account/Account";


const app = document.getElementById('app');

ReactDOM.render((
  <Router history = {browserHistory}>
      <Route path = "/" component = {Layout}>
		<IndexRoute  component = {Login} />
		<Route path = "/" component = {Dashboard} >
			<IndexRoute component = {Merchants} />
			<Route path="/merchants" component={Merchants} />
			<Route path="/admins" component={Admins} />
			<Route path="/dataportal" component={Dataportal} />
			<Route path="/account" component={Account} />
		</Route>
      </Route>
   </Router>
), app);


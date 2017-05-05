import React from "react";
import ReactDOM from "react-dom";


import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';


import 'jquery/src/jquery.js';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.min.js';
import 'font-awesome/css/font-awesome.min.css';
import './css/variable.css';
import './css/login.css';
import './css/footer.css';
import './css/header.css';
import './css/media_screen.css';
import './css/sidebar.css';
import './css/merchant.css';
import './css/customer.css';
import './css/toastr.min.css';
import './fonts/fonts.css';
import 'animate.css/animate.min.css';
import './css/store.css';


import Layout from "./components/Layout";
import Login from "./components/login/Login";
import Customers from "./components/customers/Customers";
import Admins from "./components/admins/Admins";
import Dashboard from "./components/dashboard/Dashboard";
import Merchants from "./components/merchants/Merchants";
import Dataportal from "./components/data-portal/Dataportal";
import Account from "./components/account/Account";


const app = document.getElementById('app');

ReactDOM.render((
  <Router history = {browserHistory}>
      <Route path = "/" component = {Layout}>
		<IndexRoute  component = {Login} />
		<Route path = "/" component = {Dashboard} >
			<IndexRoute component = {Merchants} />
			<Route path="/customers" component={Customers} />
			<Route path="/merchants" component={Merchants} />
			<Route path="/admins" component={Admins} />
			<Route path="/dataportal" component={Dataportal} />
			<Route path="/account" component={Account} />
		</Route>
      </Route>
   </Router>
), app);


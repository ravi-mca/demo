import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router'

export default class Header extends React.Component {

    render() {
        return (
        	<div>
			<nav class="navbar navbar-inverse fixed-top">
			  <div class="row">
			    <div class="col-sm-4 col-md-4 col-lg-3 navbar-header">
			      <a href=""><div class="logo"></div></a>
			    </div>
			    <div class="col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
			    <ul class="nav navbar-nav ">
			      <li><Link to="/admins" activeClassName="active">ADMINS</Link></li>
			      <li><Link to="/merchants" activeClassName="active">MERCHANTS</Link></li>
			      <li><Link to="/dataportal" activeClassName="active">DATA PORTAL</Link></li> 
			    </ul>
			    </div>
			    <div>
			    <ul class="pull-nav">
			    	<li class="dropdown">
				        <a class="dropdown-toggle" data-toggle="dropdown">ACCOUNT</a>
				        <ul class="dropdown-menu">
				          <li><a href="">Logout</a></li>
				        </ul>
				    </li>
			    </ul>
			    </div>
			  </div>
			</nav>	
          	</div>	
        );
    }
}


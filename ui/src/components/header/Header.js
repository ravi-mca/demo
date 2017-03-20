import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router'

export default class Header extends React.Component {

    render() {
        return (
        	<div>
			<nav class="navbar navbar-inverse fixed-top">
			  <div class="row">
			    <div class="col-sm-3 navbar-header">
			      <a href=""><div class="logo"></div></a>
			    </div>
			    <div class="col-md-offset-6 col-sm-offset-6">
			    <ul class="nav navbar-nav ">
			      <li><Link to="/home" activeClassName="active">Home</Link></li>
			      <li><Link to="/merchands" activeClassName="active">Merchands</Link></li>
			      <li><Link to="/dataportals" activeClassName="active">Data Portal</Link></li> 
			    </ul>
			    </div>
			    <div>
			    <ul class="pull-nav">
			    	<li class="dropdown">
				        <a class="dropdown-toggle" data-toggle="dropdown">Account
				        <span class="caret"></span></a>
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


import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import Background from '../../images/favendo-logo.png';
import $ from 'jquery';

export default class Header extends React.Component {

	 componentDidMount() {
        if($("#customers a").hasClass("active")) {
        	$("#admins").hide();
        	$("#merchants").hide();
        	$("#dataportal").hide();
        } else {
        	$("#customers").hide();
        }
    }
    
    render() {
        return (
        	<div>
			<nav class="navbar navbar-inverse fixed-top">
			  <div class="row">
			    <div class="col-sm-4 col-md-4 col-lg-3 navbar-header">
			      <div class="headerLogo"> <img src={ Background }/></div>
			    </div>
			    <div class="col-sm-5 col-md-5 col-lg-5">
			    <ul class="nav navbar-nav ">
			      <li id="customers"><Link to="/customers" activeClassName="active" class="text-white">CUSTOMERS</Link></li>
			      <li id="admins"><Link to="/admins" activeClassName="active" class="text-white">ADMINS</Link></li>
			      <li id="merchants"><Link to="/merchants" activeClassName="active" class="text-white">MERCHANTS</Link></li>
			      <li id="dataportal"><Link to="/dataportal" activeClassName="active" class="text-white">DATA PORTAL</Link></li> 
			    </ul>
			    </div>
			    <div>
			    <ul class="pull-nav">
			    	<li><Link to="/account" activeClassName="active" class="text-white">ACCOUNT</Link></li> 
			    </ul>
			    </div>
			  </div>
			</nav>
          	</div>
        );
    }
}

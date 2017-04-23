import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import Background from '../../images/favendo-logo.png';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'React-Bootstrap';
import $ from 'jquery';

export default class Header extends React.Component {
	constructor(props) {
	    super();

	    
	    //this.toggleChange = this.toggleChange.bind(this);
	}

    componentDidMount() {
        $( "nav div" ).removeClass( "container" );
        
	    $(".navbar-toggle").html("<span></span>");
        $(".navbar-toggle").html("<span class='fa fa-bars'></span>"); 
    }

    /*toggleChange() {

        if($(".navbar-collapse").hasClass( "collapse in" )) {
       		$(".navbar-toggle").html("<span class='fa fa-bars'></span>");
       		$("li a").removeAttr("style"); 
        } else {
        	$(".navbar-toggle").html("<span class='fa fa-close'></span>");
        }

        $("li a:active").css("background-color", "#292e35"); 
    }*/
    render() {
        return (
        	<div>
			 <Navbar inverse collapseOnSelect>
    <Navbar.Header class="headerLogo col-lg-3">
       <img src={ Background }/>
      <Navbar.Toggle/>
    </Navbar.Header>
    <Navbar.Collapse >
      <Nav class="nav navbar-nav ml-300">
	     <NavItem eventKey={1} onClick={this.toggleChange}><Link to="/admins" activeClassName="active" class="text-white">ADMINS</Link></NavItem>
	      <NavItem eventKey={2} onClick={this.toggleChange} ><Link to="/merchants" activeClassName="active" class="text-white">MERCHANTS</Link></NavItem>
	      <NavItem eventKey={3} onClick={this.toggleChange}><Link to="/dataportal" activeClassName="active" class="text-white">DATA PORTAL</Link></NavItem>
	   </Nav>
      <Nav pullRight>
       <NavItem eventKey={4} onClick={this.toggleChange}><Link to="/account" activeClassName="active" class="text-white">ACCOUNT</Link> </NavItem>
      </Nav>
    </Navbar.Collapse>
  </Navbar>
          	</div>
        );
    }
}


/*

 <NavItem eventKey={1} onClick={this.toggleChange}><Link to="/admins" activeClassName="active" class="text-white">ADMINS</Link></NavItem>
	      <NavItem eventKey={2} onClick={this.toggleChange}><Link to="/merchants" activeClassName="active" class="text-white">MERCHANTS</Link></NavItem>
	      <NavItem eventKey={3} onClick={this.toggleChange}><Link to="/dataportal" activeClassName="active" class="text-white">DATA PORTAL</Link></NavItem>
import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import Background from '../../images/favendo-logo.png';

export default class Header extends React.Component {

    render() {
        return (
        	<div>
			<nav class="navbar navbar-inverse fixed-top">
			  <div class="row">
			    <div class="col-sm-4 col-md-4 col-lg-3 navbar-header">
			      <div class="headerLogo"> <img src={ Background }/></div>
			    </div>
			    <div class="col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
			    <ul class="nav navbar-nav ">
			      <li><Link to="/admins" activeClassName="active" class="text-white">ADMINS</Link></li>
			      <li><Link to="/merchants" activeClassName="active" class="text-white">MERCHANTS</Link></li>
			      <li><Link to="/dataportal" activeClassName="active" class="text-white">DATA PORTAL</Link></li> 
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
}*/





import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import Background from '../../images/favendo-logo.png';

export default class Header extends React.Component {
	constructor(props) {
	    super();

	    
	    this.toggleChange = this.toggleChange.bind(this);
	}

    componentDidMount() {
       /* $( "nav div" ).removeClass( "container" );
        
	    $(".navbar-toggle").html("<span></span>");
        $(".navbar-toggle").html("<span class='fa fa-bars'></span>"); */
    }

    toggleChange() {

        if($(".navbar-toggle").hasClass( "collapsed" )) {
       		$(".navbar-toggle").html("<span class='fa fa-bars'></span>");
       		$("li a").removeAttr("style"); 
        } else {
        	$(".navbar-toggle").html("<span class='fa fa-close'></span>");
        }

        $("li a:active").css("background-color", "#292e35"); 
    }
    render() {
        return (
        	<div>
			<nav class="navbar navbar-inverse fixed-top">
			  <div class="row">
			    <div class="col-sm-12 col-sx-12 col-md-4 col-lg-3 navbar-header">
			    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle" onClick={this.toggleChange}>
	                <span class="sr-only">Toggle navigation</span>
	                <span class="fa fa-bars"></span>
	            </button>
			      <div class="headerLogo"> <img src={ Background }/></div>
			    </div>

			    <div id="navbarCollapse" class="collapse navbar-collapse">
			    <div class="col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
			    <ul class="nav navbar-nav ">
			      <li><Link to="/admins" activeClassName="active" class="text-white">ADMINS</Link></li>
			      <li><Link to="/merchants" activeClassName="active" class="text-white">MERCHANTS</Link></li>
			      <li><Link to="/dataportal" activeClassName="active" class="text-white">DATA PORTAL</Link></li> 
			    </ul>
			    </div>
			    <div>
			    <ul class="nav navbar-nav pull-nav">
			    	<li><Link to="/account" activeClassName="active" class="text-white">ACCOUNT</Link></li> 
			    </ul>
			    </div>
			  </div>
			  </div>
			</nav>
          	</div>
        );
    }
}


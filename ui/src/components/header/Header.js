import React from 'react';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import Background from '../../images/favendo-logo.png';

export default class Header extends React.Component {
	constructor(props) {
	    super();

        this.state = {showIcon:false};
	    this.toggleNav = this.toggleNav.bind(this);
	    this.toggleSidebar = this.toggleSidebar.bind(this);
	}

    componentDidMount() {
        this.setToggle($('.nav .active').text().toLowerCase());
    }

    toggleSidebar() {
        $('.side-icon').toggleClass('fa-angle-right fa-angle-left');
        $('.row-offcanvas').toggleClass('active');
    }

    setToggle(name,val) {
        if(name == 'merchants') {
            this.setState({showIcon:true});
        } else {
            this.setState({showIcon:false});
        }
    }

    toggleNav() {
        $(".nav-icon").toggleClass('fa-bars fa-close');
        $("li a:active").css("background-color", "#292e35"); 
    }

    render() {
        return (
        	<div>
			    <nav class="navbar navbar-inverse fixed-top">
			        <div class="row">
			            <div class="col-sm-12 col-sx-12 col-md-4 col-lg-3 navbar-header">
			                <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle" onClick={this.toggleNav}>
	                            <span class="sr-only">Toggle navigation</span>
	                            <i class="fa fa-bars nav-icon"></i>
	                        </button>
	                        <button type="button" class="navbar-toggle pull-left" data-toggle="offcanvas" onClick={this.toggleSidebar} className={this.state.showIcon ? ' navbar-toggle pull-left' : ' hidden navbar-toggle pull-left' } >
	                           <i class="fa fa-angle-right side-icon"></i>
	                        </button>
			                <div class="headerLogo"> <img src={ Background }/></div>
			            </div>
			            <div id="navbarCollapse" class="collapse navbar-collapse">
			                <div class="col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
			                    <ul class="nav navbar-nav">
			                        <li onClick={this.setToggle.bind(this,'admins')}><Link to="/admins" activeClassName="active" class="text-white">ADMINS</Link></li>
			                        <li onClick={this.setToggle.bind(this,'merchants')}><Link to="/merchants" activeClassName="active" class="text-white">MERCHANTS</Link></li>
			                        <li onClick={this.setToggle.bind(this,'dataportal')}><Link to="/dataportal" activeClassName="active" class="text-white">DATA PORTAL</Link></li> 
			                   </ul>
			                </div>
			                <div>
			                    <ul class="nav navbar-nav pull-nav">
			    	                <li onClick={this.setToggle.bind(this,'account')}><Link to="/account" activeClassName="active" class="text-white">ACCOUNT</Link></li> 
			                    </ul>
			                </div>
			            </div>
			       </div>
		        </nav>
          	</div>
        );
    }
}


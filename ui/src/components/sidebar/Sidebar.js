import React from "react";
import  'react-bootstrap';
import ReactDOM from "react-dom";

import Service from "../Service";
import Config from "../../index.config";

import $ from 'jquery';


export default class Sidebar extends React.Component {
    constructor(props) {
        super();
        this.state = {
            selected :'',
            merchantList: []
        };
    }

    componentDidMount() {
        this.getListOfMerchant();
    }

    componentDidUpdate() {
        if(this.state.selected == '') {
            $('#menu-content li').first().addClass('activeList');
            $('#menu-content li' ).first().trigger('click');
        }
    }

    getListOfMerchant() {
        var reqData = Service.buildRequestdata(Config.getMerchantList, 'GET');
        Service.executeRequest(reqData, function(response) {
            this.setState({merchantList: response});
        }.bind(this), function(xhr, status, err) {
                console.log(err);
        }.bind(this));
    }

    setFilter(filter,user) {
        this.setState({selected  : filter});
        this.props.onSelectList(user);
    }

    isActive(value) {
        if(this.state.selected !== '') {
            $('#menu-content li').first().addClass('list-nav');
            return ((value===this.state.selected) ?'activeList':'list-nav');
        }
    }

    render() {
        let showList =  this.state.merchantList.map(function(user, i) {
            return (
                <li  key={i} className={this.isActive(user.firstname)} onClick={this.setFilter.bind(this, user.firstname,user)}>
                <div class="list-padding">{user.firstname} </div></li>
           );
        }, this);

    return (
        <div class="nav-side-menu">
            <div class="input-group pad-15">
                <input type="text" class="form-control" placeholder="Search" />
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
            <div class="menu-list">
                <ul id="menu-content">
                    {showList}
                </ul>
            </div>
        </div>
    );
  }
}

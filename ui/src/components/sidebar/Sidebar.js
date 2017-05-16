import React from "react";
import  'react-bootstrap';
import ReactDOM from "react-dom";
import Service from "../Service";
import Config from "../../index.config";
import CreateCustomers from "../customers/CreateCustomers";
import CreateMerchants from "../merchants/CreateMerchants";

import $ from 'jquery';
import SearchInput, {createFilter} from 'react-search-input';

const KEYS_TO_FILTERS = ['name','accountName','accountNo'];
var token;
var tokenObj;

export default class Sidebar extends React.Component {
    constructor(props) {
        super();
        this.state = {
            selected :'',
            selectedTerm:'',
            list: []
        };

        this.searchUpdated = this.searchUpdated.bind(this);
        this.getList = this.getList.bind(this);
    }

    componentDidMount() {
        this.getList();
    }

    componentDidUpdate() {
        if(this.state.selected == '') {
            $('#menu-content li').first().addClass('activeList');
            $('#menu-content li' ).first().trigger('click');
        }
    }

    getList(info) {

        token = Service.getToken();
        if(token) {
            tokenObj = JSON.parse(token);        
            if(tokenObj.roles[0].role == "ROLE_ADMIN") {
               var APIUrl = Config.customerAPIPath;
            } else {
                var APIUrl = Config.merchantAPIPath;
            }

            var requestData = {
                url: APIUrl,
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json'
            };

            var reqData = Service.buildRequestdata(requestData);
            Service.executeRequest(reqData, function(response) {

               this.setState({list: response});
               if( (info !== undefined) && (info !== null) ) {
                    
                    if(tokenObj.roles[0].role == "ROLE_ADMIN") {
                        this.setState({selected  : info.name});
                    } else {
                        this.setState({selected  : info.accountName});
                    }
               } else {
                $('#menu-content li').first().addClass('activeList');
                $('#menu-content li' ).first().trigger('click');
               }

               if(this.state.list == undefined) {
                    this.props.onSelectList(this.state.list);
               }

            }.bind(this), function(xhr, status, err) {
                if(xhr.status == 401) {
                    Service.setInvalidSession('invalidSession');
                }
               console.log(err);
            }.bind(this));
        }
        }

    setFilter(filter,user) {        
        this.setState({selected  : filter});
        this.props.onSelectList(user);
    }

    isActive(value) {
        if(this.state.selected !== '') {
            $('#menu-content li').first().addClass('list-nav');
            return ((value==this.state.selected) ?'activeList':'list-nav');
        }
    }

    searchUpdated (term) {
        this.setState({selectedTerm: term});
    }

    render() {
        let createAccount;
        let showList;
        if(token) {
            if(tokenObj.roles[0].role == "ROLE_ADMIN") { 
                createAccount = (
                    <CreateCustomers onUpdateList={this.getList}/>
                );
            } else {
                createAccount = (
                   <CreateMerchants onUpdateList={this.getList}/>
                );
            }
        }   
         if(this.state.list) {
             const filteredList = this.state.list.filter(createFilter(this.state.selectedTerm, KEYS_TO_FILTERS));
             showList =  filteredList.map(function(user, i) {
            if(tokenObj.roles[0].role == "ROLE_ADMIN") {
                return (
                    <li  key={i} className={this.isActive(user.name)} onClick={this.setFilter.bind(this, user.name,user)}>
                    <div class="list-padding">{user.name} </div></li>
                ); 
            } else {
                return (
                    <li  key={i} className={this.isActive(user.accountName)} onClick={this.setFilter.bind(this, user.accountName,user)}>
                    <div class="list-padding">{user.accountName} </div></li>
                );
            }
        }, this);
         }

    return (
         <div class="nav-side-menu">
           <div class="right-inner-addon">
                <i class="fa fa-search login-font"></i>
                    <SearchInput class="search-box" placeholder="Search" onChange={this.searchUpdated} />
            </div>
            <div class="menu-list">
                <ul id="menu-content">
                    {showList}
                </ul>
            </div>
            <div>
                {createAccount}
            </div>
        </div>
    );
  }
}

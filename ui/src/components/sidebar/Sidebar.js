import React from "react";
import  'react-bootstrap';
import ReactDOM from "react-dom";
import Service from "../Service";
import Config from "../../index.config";
import CreateCustomers from "../Customers/CreateCustomers";

import $ from 'jquery';
import SearchInput, {createFilter} from 'react-search-input';

const KEYS_TO_FILTERS = ['accountName','accountNo'];
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

            }.bind(this), function(xhr, status, err) {
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
        const filteredList = this.state.list.filter(createFilter(this.state.selectedTerm, KEYS_TO_FILTERS));
        let showList =  filteredList.map(function(user, i) {
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

    return (
        <div>
           <div class="right-inner-addon">
                <i class="fa fa-search"></i>
                    <SearchInput class="search-box" placeholder="Search" onChange={this.searchUpdated} />
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

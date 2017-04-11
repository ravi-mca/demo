import React from "react";
import  'react-bootstrap';
import ReactDOM from "react-dom";
import Service from "../Service";
import Config from "../../index.config";
import CreateMerchants from "../merchants/CreateMerchants";

import $ from 'jquery';
import SearchInput, {createFilter} from 'react-search-input';

const KEYS_TO_FILTERS = ['firstname','accountNo'];

export default class Sidebar extends React.Component {
    constructor(props) {
        super();
        this.state = {
            selected :'',
            selectedTerm:'',
            merchantList: []
        };

        this.searchUpdated = this.searchUpdated.bind(this);
        this.getListOfMerchant = this.getListOfMerchant.bind(this);
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

    getListOfMerchant(info) {
        var requestData = {
            url: Config.merchantAPIPath,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json'
        };

        var reqData = Service.buildRequestdata(requestData);
        Service.executeRequest(reqData, function(response) {
           this.setState({merchantList: response});

           if( (info !== undefined) && (info !== null) ) {
               this.setState({selected  : info.editFirstName});
           }

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
            return ((value==this.state.selected) ?'activeList':'list-nav');
        }
    }

    searchUpdated (term) {
        this.setState({selectedTerm: term});
    }

    render() {
        const filteredList = this.state.merchantList.filter(createFilter(this.state.selectedTerm, KEYS_TO_FILTERS));
        let showList =  filteredList.map(function(user, i) {
            return (
                <li  key={i} className={this.isActive(user.firstName)} onClick={this.setFilter.bind(this, user.firstName,user)}>
                <div class="list-padding">{user.firstName} </div></li>
           );
        }, this);

    return (
        <div class="nav-side-menu">
           <div class="right-inner-addon pad-15">
                <i class="fa fa-search"></i>
                    <SearchInput class="search-box" placeholder="Search" onChange={this.searchUpdated} />
            </div>
            <div class="menu-list">
                <ul id="menu-content">
                    {showList}
                </ul>
            </div>
            <div>
               <CreateMerchants onUpdateList={this.getListOfMerchant}/>
            </div>
        </div>
    );
  }
}

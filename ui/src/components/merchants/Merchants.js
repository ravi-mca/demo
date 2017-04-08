import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

import CreateMerchants from "./CreateMerchants";
import EditMerchants from "./EditMerchants";
import Sidebar from '../sidebar/Sidebar';
import AddStore from '../store/AddStore';
import Service from "../Service";
import Config from "../../index.config";
import EditStore from "../store/EditStore";

export default class Merchants extends React.Component {
    constructor(props) {
	    super(props);
	    this.state = {
            selected :'',
            userInfo:'',
            storeInfo: '',
            merchantList: []
        };
        this.getMerchantAccounts = this.getMerchantAccounts.bind(this);
        this.setSelectList = this.setSelectList.bind(this);
    }

    setSelectList(userInfo) {
        this.setState({
            userInfo: userInfo
        });

        this.getStorsInfo(userInfo.userId);
    }

    getStorsInfo(merchantId) {

    	var requestData = {
			url: Config.getStoreInfo+merchantId,
			type: 'GET',
			dataType: 'JSON',
			contentType: 'application/json'
		};
        var reqData = Service.buildRequestdata(requestData);
        Service.executeRequest(reqData, function(response) {

        	if(response.length > 1){
        		response.push({"id":0,"storeId":"0","name":"All"});
        	}
            this.setState({storeInfo: response});
            this.sortSelect('#selectStore', 'text', 'asc');
        }.bind(this), function(xhr, status, err) {
           console.log(err);
           var response = [{"id":null,"storeId":"0","name":"No Stores"}];

           this.setState({storeInfo: response});

        }.bind(this));
    }

    //, attr, order

	 sortSelect(select, attr, order) {
	    if(attr === 'text'){
	        if(order === 'asc'){
	            $(select).html($(select).children('option').sort(function (x, y) {
	                return $(x).text().toUpperCase() < $(y).text().toUpperCase() ? -1 : 1;
	            }));
	            $(select).get(0).selectedIndex = 0;
	            e.preventDefault();
	        }// end asc
	        if(order === 'desc'){
	            $(select).html($(select).children('option').sort(function (y, x) {
	                return $(x).text().toUpperCase() < $(y).text().toUpperCase() ? -1 : 1;
	            }));
	            $(select).get(0).selectedIndex = 0;
	            e.preventDefault();
	        }// end desc
	    }

	}

    getMerchantAccounts(info) {

		this.refs.child.getListOfMerchant(info);

		var accountNo = info.accountNo;
    	var reqData = Service.buildRequestdata(Config.getMerchant+accountNo, 'GET');
        Service.executeRequest(reqData, function(response) {
           this.setState({userInfo: response});
        }.bind(this), function(xhr, status, err) {
           console.log(err);           
          
        }.bind(this));
    }

  	render() {
		let showAccountInfo;
		let showStoreInfo;
		var stores = this.state.storeInfo;
		if(this.state.userInfo) {
			showAccountInfo = (
				<div class="col-md-12 mt-10 mb-20 acc-border no-padding">
					<div class="col-md-6 col-xs-6 auto-div no-padding">
						<div class="acc-heading">{this.state.userInfo.firstName} </div>
						<div class="acc-info">Account#: {this.state.userInfo.accountNo}</div>
						<div class="acc-info">{this.state.userInfo.phone}</div>
						<div class="acc-info mb-20">{this.state.userInfo.email}</div>
					</div>
				<EditMerchants ref="editMerchantChild" data={this.state.userInfo} onUpdateAccount={this.getMerchantAccounts} />				
				</div>
	  		)
 		}

 		if(this.state.storeInfo) {
 			console.log("test", stores.length);
	 		showStoreInfo = (
	 			<div class="row">
	 			<div class="btn-padding">
	 			<Col sm={4} class="col-padding">
	 			<select className="form-control" id="selectStore">	 			
				{
					stores.map(function (store) {
        				return <option value={store.id } key={store.id}>{store.name}</option>;
    				}) 
    			}
	 			</select>
	 			</Col>
					<div class="col-md-6 col-xs-6">
						<button type="button" class="btn btn-primary storebtn" onClick={this.addStore}>
							<i class="fa fa-plus"> Add Store</i>
						</button>
					</div>
				</div>	 			
	 			</div>
	 		)
 		}

	return (
		<div>
			<Sidebar ref="child" onSelectList={this.setSelectList} />

			<div class="dashboard-container" id="main">
			   	{showAccountInfo}
			   	{showStoreInfo}
			   	<AddStore data={this.state.userInfo.userId}/>
	            <EditStore/>
	        </div>
		</div>
	);
  }
}

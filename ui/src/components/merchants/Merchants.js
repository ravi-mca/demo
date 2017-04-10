import React from "react";
import  'react-bootstrap';
import Tabs from 'react-bootstrap/lib/Tabs';
import Tab from 'react-bootstrap/lib/Tab';
import TabContainer from 'react-bootstrap/lib/TabContainer';
import Row from 'react-bootstrap/lib/Row';
import NavItem from 'react-bootstrap/lib/NavItem';
import Col from 'react-bootstrap/lib/Col';
import Nav from 'react-bootstrap/lib/Nav';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

import CreateMerchants from "./CreateMerchants";
import EditMerchants from "./EditMerchants";
import Sidebar from '../sidebar/Sidebar';
import StoreInfo from '../store/StoreInfo';
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
            merchantList: [],
            showStoreId: '',
            storeDetails: '',
        };
        this.getMerchantAccounts = this.getMerchantAccounts.bind(this);
        this.setSelectList = this.setSelectList.bind(this);
        this.getStore = this.getStore.bind(this);
        this.getStoresInfo = this.getStoresInfo.bind(this);
    }

    setSelectList(userInfo) {
        this.setState({
            userInfo: userInfo
        });

        this.getStoresInfo(userInfo.userId);
        this.showStoreInfo();
    }

    getStore(){
    	this.setState({
    		showStoreId : $('#selectStore').find(':selected').val()
    	});

    	this.getStoreInfo($('#selectStore').find(':selected').val());
    	this.showStoreInfo();
    }

    showStoreInfo() {
    	if(($('#selectStore').find(':selected').val() != 0) && ($('#selectStore').find(':selected').val() != "")) {
    		$("#showSelectedStoreId").show();
    	} else {
    		$("#showSelectedStoreId").hide();
    	}
    }

    getStoreInfo(storeId) {

    	var requestData = {
			url: Config.getStoreInfo+storeId,
			type: 'GET',
			dataType: 'JSON',
			contentType: 'application/json'
		};
        var reqData = Service.buildRequestdata(requestData);
        Service.executeRequest(reqData, function(response) {
        	response.id = this.state.showStoreId;
        	response.userId = this.state.userInfo.userId; 
            this.setState({storeDetails: response});            
        }.bind(this), function(xhr, status, err) {
           console.log(err);
           this.setState({storeDetails: ""});
        }.bind(this));
    }

    getStoresInfo(merchantId) {

    	var requestData = {
			url: Config.getStoresInfo+merchantId,
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
            this.setState({storeId: $('#selectStore').find(':selected').val()});
        	this.getStore();           
        }.bind(this), function(xhr, status, err) {
           console.log(err);
           var response = [{"id":"","storeId":"0","name":"No Stores"}];
           this.setState({storeInfo: response});
           this.getStore();

        }.bind(this));
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
		let showStoreTabs;
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
	  		);

	  		showStoreTabs = (
				<Tab.Container id="storeInfo" defaultActiveKey="storeInfoTab" >
    				<Row className="clearfix">
      					<Col sm={12}>
       						<Nav bsStyle="pills">
          						<NavItem eventKey="storeInfoTab" class="pad-right-10">STORE INFO</NavItem>
          						<NavItem eventKey="storeDataTab" disabled>STORE DATA</NavItem>
        					</Nav>
      					</Col>
      					<Col sm={12} class="no-padding">
        					<Tab.Content animation class="mt-20">
          						<Tab.Pane eventKey="storeInfoTab">
           							<StoreInfo data={this.state.storeList}/>
          						</Tab.Pane>
          						<Tab.Pane eventKey="storeDataTab">
          						</Tab.Pane>
        					</Tab.Content>
      					</Col>
    				</Row>
  				</Tab.Container>
	  		)
 		}

 		if(this.state.storeInfo) {
	 		showStoreInfo = (
	 			<div class="row">
	 			<div class="btn-padding">
	 			<Col sm={3} class="col-padding">
	 			<select className="form-control selectedFont" onClick={this.getStore} id="selectStore">	 			
				{
					stores.map(function (store) {

        				return <option value={store.id } data={store} key={store.id}>{store.name}</option>;
    				}) 
    			}
	 			</select>
	 			</Col>
				<AddStore data={this.state.userInfo.userId}/>		
				<div class="row" >
				<div class="col-md-12 col-xs-12 pad-top-10 storeMargin" id="showSelectedStoreId">
	              <div class="col-md-4 col-xs-4 auto-div ">
	              <span>Store #: {this.state.storeDetails.storeId}</span>  
	              </div>
	              <EditStore ref="editStoreChild" data={this.state.storeDetails} onUpdateStore= {this.getStoresInfo}/>
	            </div>
	            </div>		   
				</div>	 			
	 			</div>
	 		)
 		}

	return (
		<div>
			<Sidebar ref= "child" onSelectList={this.setSelectList} />
			<div class="dashboard-container" id="main">
			   	{showAccountInfo}
			   	{showStoreInfo}	
	            {showStoreTabs}
	        </div>
		</div>
	);
  }
}

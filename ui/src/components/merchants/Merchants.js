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
            disableTabs: true,
            defaultActiveKey: "storeInfoTab"
        };
        this.getMerchantAccounts = this.getMerchantAccounts.bind(this);
        this.setSelectList = this.setSelectList.bind(this);
        this.getStore = this.getStore.bind(this);
        this.getStoresInfo = this.getStoresInfo.bind(this);
        this.setActiveTabs = this.setActiveTabs.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
    }

	handleSelect(key) { }

    setSelectList(userInfo) {
        this.setState({
            userInfo: userInfo
        });

        this.getStoresInfo(userInfo.userId);
        this.showStoreInfo();
    }

    getStore() {
    	let selectedStoreVal = $('#selectStore').find(':selected').val();
    	this.setState({
    		showStoreId : selectedStoreVal
    	});

    	// storeInfo child method call
        this.refs.storeInfoChild.getStoreInfo(selectedStoreVal);

    	this.getStoreInfo(selectedStoreVal);
    	this.showStoreInfo();
    	this.setActiveTabs();
    }

    showStoreInfo() {
    	let selectedStoreVal = $('#selectStore').find(':selected').val();
    	if((selectedStoreVal != 0) && (selectedStoreVal != -1)) {
    		$("#showSelectedStoreId").show();
    	} else {
    		$("#showSelectedStoreId").hide();
    	}
    }

    //Set active tabs based on selected dropdown stores
    setActiveTabs() {
    	let selectedStoreVal = $('#selectStore').find(':selected').val();
    	if(selectedStoreVal == 0) {
    		this.setState({
    			disableTabs: false,
    			defaultActiveKey: "storeDataTab"
    		});
    	} else if(selectedStoreVal == -1) {
    		$("#storeInfo").hide();
    	} else {
    		$("#storeInfo").show();
    		this.setState({
    			disableTabs: true,
    			defaultActiveKey: "storeInfoTab"
    		});
    	}
    }

    getStoreInfo(storeId) {
		if((storeId != 0) && (storeId != -1)) {
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
    }

    getStoresInfo(merchantId) {

       let selectedStoreVal = $('#selectStore').find(':selected').val();

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

            $("#selectStore").html($("#selectStore option").sort(function (a, b) {
                return a.text == b.text ? 0 : a.text < b.text ? -1 : 1
            }))
           this.setState({storeId: selectedStoreVal});
           $('#selectStore option:eq(1)').prop('selected', true);
           this.getStore();

       }.bind(this), function(xhr, status, err) {
          console.log(err);
          var response = [{"id":-1,"storeId":"","name":"No Stores"}];
          this.setState({storeInfo: response});
          this.getStore();

       }.bind(this));
   }

    getMerchantAccounts(info) {
    	this.refs.child.getListOfMerchant(info);
    	var accountNo = info.accountNo;

    	var requestData = {
    		url: Config.getMerchant+accountNo,
    		type: 'GET',
    		dataType: 'JSON',
    		contentType: 'application/json'
    	};
        var reqData = Service.buildRequestdata(requestData);
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
        var i = 0;
		var stores = this.state.storeInfo;
		if(this.state.userInfo) {
			showAccountInfo = (
				<div class="col-md-12 mt-25 mb-20 acc-border no-padding">
					<div class="col-md-6 col-xs-6 auto-div no-padding">
						<div class="acc-heading">{this.state.userInfo.firstName} </div>
						<div>
                            <span class="acc-labels">Account#: </span>
                            <span class="acc-info">{this.state.userInfo.accountNo}</span>
                        </div>
						<div>
                            <span class="acc-labels">Contact: </span>
                            <span class="acc-info">{this.state.userInfo.phone}</span>
                        </div>
						<div class="mb-20">
                            <span class="acc-labels">Email: </span>
                            <span class="acc-info">{this.state.userInfo.email}</span>
                        </div>
					</div>
					<EditMerchants ref="editMerchantChild" data={this.state.userInfo} onUpdateAccount={this.getMerchantAccounts} />				
				</div>
	  		);

	  		showStoreTabs = (
				<Tab.Container id="storeInfo" onSelect={this.handleSelect} activeKey={this.state.defaultActiveKey} >
    				<Row className="clearfix">
      					<Col sm={12}>
       						<Nav bsStyle="pills">
          						<NavItem eventKey="storeInfoTab"  class="pad-right-10" disabled={!this.state.disableTabs}>STORE INFO</NavItem>
          						<NavItem eventKey="storeDataTab"   disabled={this.state.disableTabs}>STORE DATA</NavItem>
        					</Nav>
      					</Col>
      					<Col sm={12} class="no-padding">
        					<Tab.Content animation class="mt-20">
          						<Tab.Pane eventKey="storeInfoTab">
           							<StoreInfo ref="storeInfoChild"/>
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
                                        i++;
        								return <option value={store.id } data={store} key={i}>{store.name}</option>;
    								})
    							}
	 						</select>
	 					</Col>
						<AddStore data={this.state.userInfo.userId} onUpdateStore= {this.getStoresInfo}/>
						<div class="row" >
							<div class="col-md-12 col-xs-12 pad-top-10 storeMargin" id="showSelectedStoreId">
	              				<div class="col-md-4 col-xs-4 auto-div pad-bottom-10">
                                    <span class="acc-labels">Store#: </span>
                                    <span class="acc-info">{this.state.storeDetails.storeId}</span>
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

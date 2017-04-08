import React from "react";
import  'react-bootstrap';
import Tabs from 'react-bootstrap/lib/Tabs';
import Tab from 'react-bootstrap/lib/Tab';
import TabContainer from 'react-bootstrap/lib/TabContainer';
import Row from 'react-bootstrap/lib/Row';
import NavItem from 'react-bootstrap/lib/NavItem';
import Col from 'react-bootstrap/lib/Col';
import Nav from 'react-bootstrap/lib/Nav';

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
            merchantList: []
        };
        this.getMerchantAccounts = this.getMerchantAccounts.bind(this);
        this.setSelectList = this.setSelectList.bind(this);

    }

    setSelectList(userInfo) {
        this.setState({
            userInfo: userInfo
        });
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

	return (
		<div>
			<Sidebar ref= "child" onSelectList={this.setSelectList} />
			<div class="dashboard-container" id="main">
			   	{showAccountInfo}
			   	<AddStore data={this.state.userInfo.userId}/>
	            <EditStore/>
	            {showStoreTabs}
	        </div>
		</div>
	);
  }
}

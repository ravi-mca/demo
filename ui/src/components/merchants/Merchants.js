import React from "react";
import  'react-bootstrap';

import CreateMerchants from "./CreateMerchants";
import EditMerchants from "./EditMerchants";
import Sidebar from '../sidebar/Sidebar';
import Service from "../Service";
import Config from "../../index.config";

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

    getMerchantAccounts() {
		this.props.updateInfo();
    }

  render() {
	let showAccountInfo;
	if(this.state.userInfo) {
		showAccountInfo = (
			<div class="col-md-12 mt-10 mb-20 acc-border no-padding">
				<div class="col-md-6 col-xs-6 auto-div no-padding">
					<div class="acc-heading">{this.state.userInfo.firstname} </div>
					<div class="acc-info">Account#: {this.state.userInfo.accountNo}</div>
					<div class="acc-info">{this.state.userInfo.phone}</div>
					<div class="acc-info mb-20">{this.state.userInfo.username}</div>
				</div>

				<EditMerchants data={this.state.userInfo} onUpdateAccount={this.getMerchantAccounts}/>				
			</div>
	  	)
 	}

	  return (
		<div>
		<Sidebar ref= "child" onSelectList={this.setSelectList} />
			<div class="dashboard-container" id="main">
			   {showAccountInfo}
			</div>
		</div>
	);
  }
}

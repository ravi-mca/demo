import React from "react";
import  'react-bootstrap';

import CreateMerchants from "./CreateMerchants";
import EditMerchants from "./EditMerchants";
import Service from "../Service";
import Config from "../../index.config";

export default class Merchants extends React.Component {
    constructor(props) {
	    super(props);
	    this.state = {
            selected :'',
            merchantList: []
        };
        this.getMerchantAccounts = this.getMerchantAccounts.bind(this);
    }

    getMerchantAccounts(){
		var reqData = Service.buildRequestdata(Config.getMerchantList, 'GET');
	    Service.executeRequest(reqData, function(response) {

	    	console.log(response);
	        this.setState({merchantList: response});
	    }.bind(this), function(xhr, status, err) {
	            console.log(err);
	    }.bind(this));
    }

  render() {
	let showAccountInfo;
	if(this.props.data) {
		showAccountInfo = (
			<div class="col-md-12 mt-10 mb-20 acc-border no-padding">
				<div class="col-md-6 col-xs-6 auto-div no-padding">
					<div class="acc-heading">{this.props.data.firstname} </div>
					<div class="acc-info">Account#: {this.props.data.accountNo}</div>
					<div class="acc-info">{this.props.data.phone}</div>
					<div class="acc-info mb-20">{this.props.data.username}</div>
				</div>

				<EditMerchants data={this.props.data} onUpdateAccount={this.getMerchantAccounts}/>				
			</div>
	  	)
 	}

	  return (
		<div>
			<div class="dashboard-container" id="main">
			   {showAccountInfo}
			</div>
		</div>
	);
  }
}

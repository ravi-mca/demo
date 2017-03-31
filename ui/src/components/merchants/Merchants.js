import React from "react";
import  'react-bootstrap';

import CreateMerchants from "./CreateMerchants";
import Service from "../Service";

export default class Merchants extends React.Component {
    constructor(props) {
	    super(props);
    }

  render() {
	let showAccountInfo;
	if(this.props.data) {
		showAccountInfo = (
			<div class="col-md-12 mt-10 mb-20 acc-border">
				<div class="col-md-6 col-xs-6 auto-div no-padding">
					<div class="acc-heading">{this.props.data.firstname} </div>
					<div class="acc-info">Account#: {this.props.data.accountNo}</div>
					<div class="acc-info">{this.props.data.phone}</div>
					<div class="acc-info mb-20">{this.props.data.username}</div>
				</div>
				<div class="col-md-6 col-xs-6">
					<i class="fa fa-pencil"></i>
				</div>
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

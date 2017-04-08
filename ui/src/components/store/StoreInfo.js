import React from 'react';
import  'react-bootstrap';
import Service from "../Service";
import Config from "../../index.config";


export default class StoreInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            storeList: ''
        };
    }

    componentDidMount() {
        this.getStoreInfo();
    }

     getStoreInfo() {
        var reqData = Service.buildRequestdata(Config.getStoreInfo+26, 'GET');
        Service.executeRequest(reqData, function(response) {
            this.setState({storeList:response});
        }.bind(this), function(xhr, status, err) {
           console.log(err);
        }.bind(this));
    }

    render() {

        return (
        	<div class="acc-info">
                <div class="col-md-12 mb-10 no-padding">
            	    <div class="col-md-3 col-xs-6">Store Name</div>
            	    <div class="col-md-3 col-xs-6">{this.state.storeList.name}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Nickname</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.nickName}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Manager/POC</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.managerOrPOC}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Phone Number</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.phone}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Street Address</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.street}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store City</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.city}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store State</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.state}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Country</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.country}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Zip Code</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.zipCode}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Controller Number</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.controllerNumber}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Controller Placement</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.controllerPlacement}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Category</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.category}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Subcategory</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.subCategory}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">Store Price Category</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.priceCategory}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6">POS System</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.posSystem}</div>
                </div>
                <div class="col-md-12 mb-20 no-padding">
                    <div class="col-md-3 col-xs-6">Storecast Admin Name</div>
                    <div class="col-md-3 col-xs-6">{this.state.storeList.storecastAdminName}</div>
                </div>
          	</div>
        );
    }
}


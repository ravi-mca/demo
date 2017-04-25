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

        this.getStoreInfo = this.getStoreInfo.bind(this);
    }

     getStoreInfo(id) {
        var requestData = {
                url: Config.storeAPIPath + id,
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json'
            };

        var reqData = Service.buildRequestdata(requestData);
        Service.executeRequest(reqData, function(response) {
            if(this._mounted) {
                this.setState({storeList:response});
            }
        }.bind(this), function(xhr, status, err) {
        }.bind(this));
    }

    render() {

        return (
        	<div class="info-store">
                <div class="col-md-12 mb-10 no-padding">
            	    <div class="col-md-3 col-xs-6 font-semibold">Store Name</div>
            	    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.name}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Nickname</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.nickName}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Manager/POC</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.managerOrPOC}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Phone Number</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.phone}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Street Address</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.street}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store City</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.city}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store State</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.state}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Country</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.country}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Zip Code</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.zipCode}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Controller Number</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.controllerNumber}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Controller Placement</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.controllerPlacement}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Category</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.category}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Subcategory</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.subCategory}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Store Price Category</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.priceCategory}</div>
                </div>
                <div class="col-md-12 mb-10 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">POS System</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.posSystem}</div>
                </div>
                <div class="col-md-12 mb-20 no-padding">
                    <div class="col-md-3 col-xs-6 font-semibold">Storecast Admin Name</div>
                    <div class="col-md-3 col-xs-6 font-regular">{this.state.storeList.storecastAdminName}</div>
                </div>
          	</div>
        );
    }
}


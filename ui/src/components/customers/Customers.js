import React from "react";
import  'react-bootstrap';

import $ from 'jquery';
import Service from "../Service";
import Config from "../../index.config";
import Sidebar from '../sidebar/Sidebar';
import CreateCustomers from "../customers/CreateCustomers";
import EditCustomers from "../customers/EditCustomers";

export default class Customers extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
        userInfo : ""
      };

      this.setSelectList = this.setSelectList.bind(this);
      this.getCustomerAccount = this.getCustomerAccount.bind(this);
  }

  setSelectList(userInfo) {
    if(userInfo) {      
      userInfo.deleteName = userInfo.name;
      userInfo.successAlert = Config.successAlert.deleteCustomer; 
      userInfo.APIUrl = Config.customerAPIPath + '/' + userInfo.customerId;
      userInfo.deleteMessage = "Customer";
      userInfo.info = userInfo;
    }    
    this.setState({
        userInfo: userInfo
    });
  }

    getCustomerAccount(cusData) {
        this.refs.child.getList(cusData);
        var updatedAccountInfo  = {};
        if(cusData){           
            updatedAccountInfo.name = cusData.editCustomerName;
            updatedAccountInfo.firstName = cusData.editCustomerFirstName;
            updatedAccountInfo.lastName = cusData.editCustomerLastName;
            updatedAccountInfo.phone = cusData.editCustomerPhone;
            updatedAccountInfo.email = cusData.editCustomerEmail;
            updatedAccountInfo.country = cusData.editCustomerCountry;
            updatedAccountInfo.state = cusData.editCustomerState;
            updatedAccountInfo.city = cusData.editCustomerCity;
            updatedAccountInfo.street = cusData.editCustomerStreet;
            updatedAccountInfo.zipcode = cusData.editCustomerZipcode;
            updatedAccountInfo.customerId = cusData.info.customerId; 
            updatedAccountInfo.deleteName = cusData.editCustomerName;
            updatedAccountInfo.successAlert = Config.successAlert.deleteCustomer;
            updatedAccountInfo.APIUrl = Config.customerAPIPath + '/' + cusData.info.customerId;
            updatedAccountInfo.deleteMessage = "customer";
            updatedAccountInfo.info = cusData;
        }

        this.setState({
            userInfo: updatedAccountInfo
        });
    }

  render() {

    let showAccountInfo;
    let showAdminInfo;
    if(this.state.userInfo) {
            showAccountInfo = (
              <div class="row">
                <div class="col-md-12 mt-25 mb-20 acc-border no-padding">
                    <div class="col-md-6 col-xs-6 auto-div no-padding">
                        <div class="acc-heading">{this.state.userInfo.name}</div>
                        <div>
                            <i class="fa fa-phone mr-10" aria-hidden="true"></i>
                            <span class="acc-info">{this.state.userInfo.phone}</span>
                        </div>
                        <div class="mb-20">
                            <i class="fa fa-envelope mr-10" aria-hidden="true"></i>
                            <span class="acc-info">{this.state.userInfo.email}</span>
                        </div>
                    </div>
                        <EditCustomers data={this.state.userInfo} onUpdateCustomer={this.getCustomerAccount} />
                 </div>
            </div>
            );

            showAdminInfo = (
              <div class="row">
                <div class="col-md-9 pad-right-50 pad-left-0"><h1 class="admin-h1">Admin Users</h1>
                    <div class="row tbl">
                      <div class="col-md-12 tbl-head">
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          First Name
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          Last Name
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          Email ID
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          Action
                        </div>
                      </div>
                      </div>
                      <div class="row tbl">
                      <div class="col-md-12 tbl-body">
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          {this.state.userInfo.firstName}
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          {this.state.userInfo.lastName}
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          {this.state.userInfo.email}
                        </div>
                        <div class="col-xs-3 col-sm-3 col-md-3">
                          <i class="fa fa-pencil pointer"></i> 
                            <span class="m-5"> | </span>
                          <i class="fa fa-trash pointer"></i> 
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="pull-right mr-15">
                      <button type="button" disabled class="btn btn-primary admin-btn disabled">                        
                          + Add Admin  
                      </button>
                      </div>
                    </div>  
                  </div>
                </div>
              );
        }

    return (
      <div class="merchant-dashboard">
            <div class="row row-offcanvas row-offcanvas-left">
                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                    <div class="list-group">
                        <Sidebar ref= "child" onSelectList={this.setSelectList} />
                    </div>
                </div>
                <div>
                    {showAccountInfo}
                    {showAdminInfo}
                </div>
            </div>
        </div>
    );
  }
}

import React from "react";
import  'react-bootstrap';

import $ from 'jquery';
import Service from "../Service";
import Config from "../../index.config";
import Sidebar from '../sidebar/Sidebar';
import CreateCustomers from "../customers/CreateCustomers";

export default class Customers extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
      };
      this.setSelectList = this.setSelectList.bind(this);
  }

  setSelectList(userInfo) {
        userInfo.deleteName = userInfo.accountName;
        userInfo.successAlert = Config.successAlert.deleteMerchant;
        userInfo.APIUrl = Config.deleteAPIPath+ userInfo.merchantId;
        userInfo.deleteMessage = "Customer";
        userInfo.info = userInfo;
        this.setState({
            userInfo: userInfo
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
                    <div class="mr-90">
                    <div class="col-xs-8">
                      <div class="pull-right">
                          <button type="button" class="btn info-btn btn-sm">
                              <i class="fa fa-pencil pointer login-font" ></i>
                          </button>
                          <button type="button" class="btn info-btn btn-sm">
                              <i class="fa fa-trash login-font pointer"></i>    
                          </button>
                      </div>
                    </div>
                  </div>                   
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
                    <div class="pull-right">
                    <button type="button" disabled class="btn btn-primary admin-btn disabled">                        
                        + Add Admin  
                    </button>
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

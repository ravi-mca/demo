import React from "react";
import  'react-bootstrap';

import $ from 'jquery';
import Service from "../Service";
import Config from "../../index.config";
import Sidebar from '../sidebar/Sidebar';
import CreateCustomers from "../Customers/CreateCustomers";

export default class Customers extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
      };
      this.getCustomerList = this.getCustomerList.bind(this);
  }

  getCustomerList(info) {
    this.refs.child.getList(info);
  }

  render() {
    return (
      <div>
        <div class="nav-side-menu">
        <Sidebar ref= "child" onSelectList={this.setSelectList} />
        <div>
        <CreateCustomers onUpdateList={this.getCustomerList}/>
        </div>
        </div>
        <div class="dashboard-container" id="main">
          <div class="col-md-12 mt-10 mb-20 no-padding">
            <div class="col-md-6 col-xs-6 auto-div no-padding">
              <div class="acc-heading"></div>
              <div>
                 <span class="acc-labels"></span>
                 <span class="acc-info"></span>
              </div>
            </div>
          </div>  
        </div>
       </div> 
    );
  }
}

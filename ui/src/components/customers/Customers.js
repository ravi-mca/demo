import React from "react";
import  'react-bootstrap';

import $ from 'jquery';
import Service from "../Service";
import Config from "../../index.config";

export default class Customers extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
      };
  }

  render() {
    return (
        <div>
        <div class="nav-side-menu">
           <div class="right-inner-addon pad-15">
            </div>
            <div class="menu-list">
                <ul id="menu-content">
                </ul>
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

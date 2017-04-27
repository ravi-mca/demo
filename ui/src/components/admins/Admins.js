import React from "react";
import  'react-bootstrap';
import Service from "../Service";
import Config from "../../index.config";

export default class Admins extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
        adminIfo : ''
      };
       this.getAdminDetails = this.getAdminDetails.bind(this);
  }

  componentDidMount() {
        this.getAdminDetails();
  }

  getAdminDetails() {
      var requestData = {
        url: Config.adminAPIPath,
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json'
      };
      var reqData = Service.buildRequestdata(requestData);
      Service.executeRequest(reqData, function(response) {
         this.setState({adminIfo: response});
      }.bind(this), function(xhr, status, err) {
          console.log(err);
      }.bind(this));
  }

  render() {
    return (
        <div>
            <div class="merchant-dashboard">
                <div class="row row-offcanvas row-offcanvas-left">
                    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                        <div class="list-group">
                            <div class="nav-side-menu">
                                <div class="right-inner-addon pad-15"></div>
                                <div class="menu-list">
                                    <ul id="menu-content"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-6 auto-div no-padding">
                        <div class="acc-heading">{this.state.adminIfo.firstName}</div>
                        <div>
                            <span class="acc-labels">Username: </span>
                            <span class="acc-info">{this.state.adminIfo.username}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
  }
}

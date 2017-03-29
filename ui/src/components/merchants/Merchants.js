import React from "react";
import  'react-bootstrap';


import CreateMerchants from "./CreateMerchants";
import Service from "../Service";

export default class Merchants extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
         <div class="dashboard-container" id="main">
          <div class="row content">  
          <h1>Merchants</h1>
          <div><CreateMerchants/></div>
          </div>
          <div className="modal fade">
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 className="modal-title">Modal title</h4>
                </div>
                <div className="modal-body">
                  <p>One fine body&hellip;</p>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                  <button type="button" className="btn btn-primary">Save changes</button>
                </div>
              </div>
            </div>
          </div>
          </div>

    );
  }
}

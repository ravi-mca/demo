import React from "react";
import 'react-bootstrap';

export default class Dashboard extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
      <div class="col-sm-3 col-md-3 col-lg-3 sidenav">
      <div class="row content">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search" />
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
      <div>
      <ul class="nav nav-pills nav-stacked">
        <li class=""><a href="">Merchant 1</a></li>
        <li><a href="">Merchant 2</a></li>
        <li><a href="">Merchant 3</a></li>
        <li><a href="">Merchant 4</a></li>
      </ul>
      </div>
      </div>  
      </div>
    );
  }
}

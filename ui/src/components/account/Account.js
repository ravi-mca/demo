import React from "react";
import Reflux from "reflux";
import  'react-bootstrap';

import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';

import Login from "../login/Login";
import Service from "../Service";
import Store from '../../Store';
import ControlActions from '../../ControlActions';

export default class Account extends React.Component {
  constructor() {
    super();

  }

    handleClick () {
        //ControlActions.logout();
        Service.deleteToken();
        browserHistory.push('/');
    }

  render() {
    return (
      <div>
      <div class="dashboard-container" id="main">
            <div class="font-18 mt-lg-20">
                <Link to="" onClick={ this.handleClick } class="text-red">Signout</Link>
            </div>
        </div>
      </div>
    );
  }
}

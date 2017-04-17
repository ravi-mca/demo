import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';

var ReactToastr = require("react-toastr");
var {ToastContainer} = ReactToastr; // This is a React Element.
// For Non ES6...
// var ToastContainer = ReactToastr.ToastContainer;
var ToastMessageFactory = React.createFactory(ReactToastr.ToastMessage.animation);


export default class AlertMessage extends React.Component {
    constructor(props) {
    super(props);
    this.successAlert = this.successAlert.bind(this);
    this.errorAlert = this.errorAlert.bind(this);    
  }

  successAlert (message) {
    this.refs.container.success(
      "",
      message, {
      timeOut: 2000,
      extendedTimeOut: 1000
    });
  }

  errorAlert (message) {
    this.refs.container.error(
      "",
      message, {
      timeOut: 2000,
      extendedTimeOut: 1000
    });
  }

  render() {  
   return (        
        <div>
          <ToastContainer ref="container"
                            toastMessageFactory={ToastMessageFactory}
                            className="toast-top-right" />
        </div>  
    );
  }
}

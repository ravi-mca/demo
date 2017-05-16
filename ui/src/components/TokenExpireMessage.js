import React from "react";
import  'react-bootstrap';
import $ from 'jquery';
import Service from "./Service";

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
import Validation, {Validator} from 'rc-form-validation';

var ReactToastr = require("react-toastr");
var {ToastContainer} = ReactToastr;
var ToastMessageFactory = React.createFactory(ReactToastr.ToastMessage.animation);


export default class TokenExpireMessage extends React.Component {
    constructor(props) {
        super(props);
        this.errorAlert = this.errorAlert.bind(this);
    }

    errorAlert() {
        this.refs.container.error(
            "",
            "Token expired. Please login again", {
            showAnimation: `fadeIn`,
            hideAnimation: `fadeOut`,
            timeOut: 30000,
            extendedTimeOut: 1000,
            closeButton: true
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

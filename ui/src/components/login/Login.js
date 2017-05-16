import React from "react";
import Moment from 'react-moment';
import $ from 'jquery';

import Service from "../Service";
import Config from "../../index.config";
import Background from '../../images/favendo-logo.png';

import Store from '../../Store';
import ControlActions from '../../ControlActions';
import TokenExpireMessage from "../TokenExpireMessage";

import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';

export default class Login extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            showResults: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.checkTokenTimeout = this.checkTokenTimeout.bind(this);
    }

    componentDidMount() {
        this.checkTokenTimeout();
    }

    handleChange(e) {
        e.target.classList.add('active');

        this.setState({
            [e.target.name]: e.target.value
        });

        this.showInputError(e.target.name);
    }

    handleSubmit(e) {
        e.preventDefault();

        if (this.showFormErrors()) {
            var requestData = {
                url: Config.login,
                type: 'POST',
                data: this.state,
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded'
            };

            var reqData = Service.buildRequestdata(requestData);

            Service.executeRequest(reqData, function(data) {
                Service.setToken(data);
                this.setState({ showResults: false });

                if(data.roles[0].role == "ROLE_ADMIN") {
                    browserHistory.push('/customers');
                }else {
                    browserHistory.push('/merchants');
                }
            }.bind(this), function(xhr, status, err) {
               this.setState({ showResults: true });
                  console.log('err',err);
            }.bind(this));
        }
    }

    showFormErrors() {
        const inputs = document.querySelectorAll('input');
        let isFormValid = true;

        inputs.forEach(input => {
            input.classList.add('active');
            const isInputValid = this.showInputError(input.name);
            if (!isInputValid) {
                isFormValid = false;
            }
        });

        return isFormValid;
    }

    showInputError(refName) {
        const validity = this.refs[refName].validity;
        const label = refName.charAt(0).toUpperCase() + refName.substr(1).toLowerCase();
        const error = document.getElementById(`${refName}Error`);
        const isPassword = refName.indexOf('password') !== -1;

        if (!validity.valid) {
            this.setState({ showResults: false });
            if (validity.valueMissing) {
                error.textContent = `${label} is a required field`;
            } else if (validity.typeMismatch) {
                error.textContent = `${label} should be a valid email address`;
            } else if (isPassword && validity.patternMismatch) {
                error.textContent = `${label} should be longer than 4 chars`;
            }
            return false;
        }

        error.textContent = '';
        return true;
    }

    checkTokenTimeout() {
        if(Service.getSessionInfo() == "invalidSession") {
            this.refs.tokenMessageChild.errorAlert();
            Service.deleteInvalidSession();
        }
    }

    render() {
        return (
            <div>
            <div class="login-content">
                <div class="logo mb-10"> <img src={ Background }/></div>
                <form class="form-horizontal" noValidate>
                <div class="error">
                    {this.state.showResults ? 'Invalid username or password' : ''}
                </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-user login-font"></i>
                            </div>
                            <input type="email" name="username" ref="username"
                            class="form-control input-lg font-16 text-gray-dark" placeholder="Username"
                            value={ this.state.username }
                            onChange={ this.handleChange } required />
                        </div>
                        <div class="error" id="usernameError" />
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-lock login-font"></i>
                            </div>
                            <input type="password" name="password" class="form-control input-lg font-16 text-gray-dark"
                                placeholder="Password"
                                ref="password"
                                value={ this.state.password }
                                onChange={ this.handleChange }
                                pattern=".{5,}" required/>
                        </div>
                        <div class="error" id="passwordError" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-lg full-width font-18 font-bold" onClick={ this.handleSubmit }>LOGIN</button>
                    </div>
                    <div class="form-group font-18 text-gray">
                        <div class="col-md-6 col-xs-6 no-padding">
                            <span class="">Forgot password?</span>
                        </div>
                        <div class="col-md-6 col-xs-6 no-padding">
                            <span class="pull-right">Create an account</span>
                        </div>
                    </div>
                </form>
                 <div class="fixed-bottom">
                        <div class="col-md-6">
                          <div class="col-md-4 col-xs-6 col-sm-6 login-footer">
                          <p>© <Moment format="YYYY"/> Aussio, Inc</p>
                          </div>
                          <div class="col-md-4 col-xs-6 col-sm-6">
                                <a href="www.storecast-io.com/terms-of-services" target="_blank" class="text-white">Terms of Service</a>
                          </div>
                        </div>
                    </div>
               </div>
               <div>
                    <TokenExpireMessage ref="tokenMessageChild"/>
                </div>
            </div>
        );
    }
}

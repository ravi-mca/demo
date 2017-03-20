import React from "react";
import $ from 'jquery';

export default class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
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

        console.log('component state', this.state);

        if (!this.showFormErrors()) {
            console.log('form is invalid: do not submit');
        } else {
            console.log('form is valid: submit');
             $.ajax({
                type: 'POST',
                url: "http://localhost:8080/storecast/api/admin/login",
                dataType: 'json',
                ContentType: 'application/x-www-form-urlencoded',
                data: this.state,
                cache: false,
            success: function(data) {
            console.log('status',data);
        }.bind(this),
            error: function(xhr, status, err) {
                console.log('err',status);
        }.bind(this)
    });
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

    render() {
        return (
            <div class="login-content">
                <div class="logo"></div>
                <form class="form-horizontal" noValidate>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-user"></i>
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
                                <i class="fa fa-lock"></i>
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
                        <button type="submit" class="btn btn-primary btn-lg full-width font-18" onClick={ this.handleSubmit }>LOGIN</button>
                    </div>
                    <div class="form-group font-18 text-gray">
                    <div class="col-md-6">
                        <span class=""> Forgot Password? </span>
                    </div>
                    <div class="col-md-6">
                        <span class="pull-right"> Create an account </span>
                    </div>
                </div>
                </form>
            </div>
        );
    }
}

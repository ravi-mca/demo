import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';

import Service from "../Service";
import Config from "../../index.config";
import AlertMessage from "../AlertMessage";
import DeletePopUp from "../DeletePopUp";

var ReactToastr = require("react-toastr");
var {ToastContainer} = ReactToastr; // This is a React Element.
// For Non ES6...
// var ToastContainer = ReactToastr.ToastContainer;
var ToastMessageFactory = React.createFactory(ReactToastr.ToastMessage.animation);

import {
  Modal,
  ModalHeader,
  ModalTitle,
  ModalClose,
  ModalBody,
  ModalFooter
} from 'react-modal-bootstrap';

export default class EditCustomers extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            editCustomerFirstName: '',
            editCustomerLastName: '',
            editCustomerName: '',
            editCustomerEmail: '',
            editCustomerPhone: '',
            editCustomerCountry: '',
            editCustomerStreet: '',
            editCustomerCity: '',
            editCustomerState: '',
            editCustomerZipcode: '',
            deleteName: this.props.data.name,
            deleteMessage: "customer",
            info: this.props.data,
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.editCustomerForm = this.editCustomerForm.bind(this);
    }

    openModal = () => {
        this.setState({
            isOpen: true
        });
    };

    hideModal = () => {
        this.setState({
            isOpen: false,
            editCustomerFirstName: '',
            editCustomerLastName: '',
            editCustomerName: '',
            editCustomerEmail: '',
            editCustomerPhone: '',
            editCustomerCountry: '',
            editCustomerStreet: '',
            editCustomerCity: '',
            editCustomerState: '',
            editCustomerZipcode: ''
        });

        $("#editCustomerFirstNameError").html("");
        $("#editCustomerLastNameError").html("");
        $("#editCustomerNameError").html("");
        $("#editCustomerEmailError").html("");
        $("#editCustomerPhoneError").html("");
        $("#editCustomerCountryError").html("");
        $("#editCustomerStreetError").html("");
        $("#editCustomerCityError").html("");
        $("#editCustomerStateError").html("");
        $("#editCustomerZipcodeError").html("");
        $("input").removeClass("active");
    };

    handleChange(e) {
        e.target.classList.add('active');
        this.setState({
            [e.target.name]: e.target.value
        });
        this.showInputError(e.target.name);
        this.setState({
            isOpen: true
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        let isFormValid = true;

        if (this.checkFormValidation()) {
            var customerId = this.props.data.customerId;

            var newCustomerData = {};
            newCustomerData.name = this.state.editCustomerName;
            newCustomerData.firstName = this.state.editCustomerFirstName;
            newCustomerData.lastName = this.state.editCustomerLastName;
            newCustomerData.phone = this.state.editCustomerPhone;
            newCustomerData.email = this.state.editCustomerEmail;
            newCustomerData.country = this.state.editCustomerCountry;
            newCustomerData.state = this.state.editCustomerState;
            newCustomerData.city = this.state.editCustomerCity;
            newCustomerData.street = this.state.editCustomerStreet;
            newCustomerData.zipcode = this.state.editCustomerZipcode;

            var requestData = {
                url: Config.customerAPIPath+ '/' + customerId,
                type: 'PUT',
                data: JSON.stringify(newCustomerData),
                dataType: 'text',
                contentType: 'application/json'
            };

            var reqData = Service.buildRequestdata(requestData);

            Service.executeRequest(reqData, function(data) {
                this.props.onUpdateCustomer(this.state);
                this.refs.alertMessageChild.successAlert("Customer updated successfully.");
                this.hideModal();
            }.bind(this), function(xhr, status, err) {
                this.refs.alertMessageChild.errorAlert("Something is wrong.");
                var statusObj = xhr;
                var obj = JSON.parse(xhr.responseText);

                if(obj["error_description"] == "Customer with name already exist. Please provide different name.") {
                    const editCustomerNameError = document.getElementById(`editCustomerNameError`);
                    editCustomerNameError.textContent = obj["error_description"];
                } else if(obj["error_description"] == "User with email already exist. Please provide different email.") {
                    const editCustomerEmailError = document.getElementById(`editCustomerEmailError`);
                    editCustomerEmailError.textContent = obj["error_description"];
                }

                isFormValid = false;
            }.bind(this));
        }
    }

    checkFormValidation() {
        const inputs = document.querySelectorAll('#editCustomerPanel input');
        let isFormValid = true;
        inputs.forEach(input => {
            input.classList.add('active');
            const isInputValid = this.showInputError(input.name);

            if (!isInputValid && isFormValid ) {
                isFormValid = false;
            }
        });
        return isFormValid;
    }

    showInputError(refName) {
        var isControlValid = true;
        const validity = this.refs[refName].validity;
        const label = document.getElementById(`${refName}Label`).textContent;
        const error = document.getElementById(`${refName}Error`);

        const validEmail = refName.indexOf('editCustomerEmail') !== -1;
        const isNumber = refName.indexOf('editCustomerPhone') !== -1;
        const isZipCodeNumber = refName.indexOf('editCustomerZipcode') !== -1;

        error.textContent = '';

        if (!validity.valid) {
            isControlValid = false;
            if (validity.valueMissing) {
                error.textContent = `Please enter ${label}`;
            } else if (validEmail && validity.patternMismatch) {
                error.textContent = `Please enter valid ${label} address`;
            } else if (isNumber && validity.patternMismatch) {
                error.textContent = `Please enter valid number`;
            } else if (isZipCodeNumber && validity.patternMismatch) {
                error.textContent = `Please enter valid zipcode`;
            }
        }
        return isControlValid;
    }

    editCustomerForm() {
        this.setState({
            isOpen: true,
            editCustomerFirstName: this.props.data.firstName,
            editCustomerLastName: this.props.data.lastName,
            editCustomerName:this.props.data.name,
            editCustomerEmail: this.props.data.email,
            editCustomerPhone: this.props.data.phone,
            editCustomerCountry: this.props.data.country,
            editCustomerStreet: this.props.data.street,
            editCustomerCity: this.props.data.city,
            editCustomerState: this.props.data.state,
            editCustomerZipcode: this.props.data.zipcode
        });
    }

  render() {
    return (
        <div id="editCustomerPanel">
            <div class="col-md-6 col-xs-4">
                <div class="pull-right">
                    <div class="col-md-6 col-xs-6 no-padding">
                        <button type="button" class="btn info-btn btn-sm" onClick={this.editCustomerForm}>
                            <i class="fa fa-pencil pointer login-font"></i>
                        </button>
                    </div>
                    <div class="col-md-6 col-xs-6 no-padding">
                        <DeletePopUp data={this.props.data} onUpdate={this.onUpdateMerchantAccount} setDeleteButton="true"/>
                    </div>
                </div>
            </div>
            <div>
              <AlertMessage ref="alertMessageChild"/>
            </div>
            <Modal isOpen={this.state.isOpen}>
                <div class="modal-header">
                    <h4 class="modal-title font-20">Edit Customer</h4>
                </div>
                <form id="editCustomer" method="post" noValidate onSubmit="">
                    <ModalBody>
                        <FormGroup row>
                            <Label id="editCustomerNameLabel" class="form-label" for="editCustomerName" sm={3}> Customer Name </Label><span></span>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerName"
                                        ref="editCustomerName"
                                        value={ this.state.editCustomerName }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerNameError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerFirstNameLabel" class="form-label" for="editCustomerFirstName" sm={3}> First Name </Label><span></span>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerFirstName"
                                        ref="editCustomerFirstName"
                                        value={ this.state.editCustomerFirstName }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerFirstNameError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerLastNameLabel" class="form-label" for="editCustomerLastName" sm={3}> Last Name </Label><span></span>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerLastName"
                                        ref="editCustomerLastName"
                                        value={ this.state.editCustomerLastName }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerLastNameError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerEmailLabel" class="form-label" for="editCustomerEmail" sm={3}>Email </Label>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerEmail"
                                        ref="editCustomerEmail"
                                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                                        value={ this.state.editCustomerEmail }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerEmailError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerPhoneLabel" class="form-label" for="editCustomerPhone" sm={3}> Phone </Label>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerPhone"
                                        ref="editCustomerPhone"
                                        value={ this.state.editCustomerPhone }
                                        onChange={ this.handleChange }
                                        pattern="[0-9]{6,10}"
                                        required />
                                <div className="error" id="editCustomerPhoneError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerCountryLabel" class="form-label" for="editCustomerCountry" sm={3}> Country </Label>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerCountry"
                                        ref="editCustomerCountry"
                                        value={ this.state.editCustomerCountry }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerCountryError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerCityLabel" class="form-label" for="editCustomerCity" sm={3}> City </Label>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerCity"
                                        ref="editCustomerCity"
                                        value={ this.state.editCustomerCity }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerCityError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerStateLabel" class="form-label" for="editCustomerState" sm={3}> State </Label>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerState"
                                        ref="editCustomerState"
                                        value={ this.state.editCustomerState }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerStateError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerStreetLabel" class="form-label" for="editCustomerStreet" sm={3}> Street </Label>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerStreet"
                                        ref="editCustomerStreet"
                                        value={ this.state.editCustomerStreet }
                                        onChange={ this.handleChange }
                                        required />
                                <div className="error" id="editCustomerStreetError" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label id="editCustomerZipcodeLabel" class="form-label" for="editCustomerZipcode" sm={3}> Zip Code </Label><span></span>
                            <Col sm={8} class="col-padding">
                                <input className="form-control"
                                        type="text"
                                        name="editCustomerZipcode"
                                        ref="editCustomerZipcode"
                                        value={ this.state.editCustomerZipcode }
                                        onChange={ this.handleChange }
                                        pattern="[0-9]{4,6}"
                                        required />
                                <div className="error" id="editCustomerZipcodeError" />
                            </Col>
                        </FormGroup>
                    </ModalBody>
                    <div>
                        <hr class="modal-footer-hr"/>
                        <ModalFooter>
                            <button type="submit" class="btn btn-blue" onClick={ this.handleSubmit }>
                                Save Customer Account
                            </button>
                            <button type="button" className='btn btn-blue' onClick={this.hideModal} formNoValidate>
                                Cancel
                            </button>
                        </ModalFooter>
                    </div>
                </form>
            </Modal>
      </div>
    );
  }
}

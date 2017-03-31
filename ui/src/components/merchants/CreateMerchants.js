import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';

import Service from "../Service";
import Config from "../../index.config";

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

export default class MerchantForm extends React.Component {
    constructor(props) {
    super(props);

    this.state = {
      firstName: '',
      lastName: '',      
      email: '',      
      phone: '',
      accountName: '',
    };
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  addAlert = this.addAlert.bind(this);
  clearAlert = this.clearAlert.bind(this);

  addAlert() {
    this.refs.container.success(`hi! Now is ${new Date()}`, `///title\\\\\\`, {
      closeButton: true,
    });
  }

  clearAlert() {
    this.refs.container.clear();
  }


  openModal = () => {
    this.setState({
      isOpen: true
    });
  };

  hideModal = () => {
    this.setState({
      isOpen: false, 
      firstName: '',
      lastName: '',      
      email: '',      
      phone: '',
      accountName: '',
    }); 
    $("#firstNameError").html("");
    $("#lastNameError").html("");
    $("#emailError").html("");
    $("#phoneError").html("");
    $("#accountNameError").html("");
    $("input").removeClass("active");


  };

  handleChange(e) {
    e.target.classList.add('active');
    
    this.setState({
      [e.target.name]: e.target.value
    });
    
    this.showInputError(e.target.name);
  }

  handleSubmit(e) {    
    e.preventDefault();
    
    //console.log('component state', JSON.stringify(this.state));
    
    if (!this.showFormErrors()) {
      console.log('form is invalid: do not submit');
    } else {
      console.log('form is valid: submit');

      Service.createMerchant(Config.createMerchant,this.state, function(data) {
       
        this.successAlert();        
        this.hideModal();
       
      }.bind(this), function(xhr, status, err) {
        
        this.openModal();
        this.errorAlert();
      }.bind(this));
    }
  }

  successAlert () {
    this.refs.container.success(
      "Success!!",
      "Merchant created successfully.", {
      timeOut: 5000,
      extendedTimeOut: 1000
    });
  }

  errorAlert () {
    this.refs.container.error(
      "Error!!",
      "Something is wrong.", {
      timeOut: 5000,
      extendedTimeOut: 1000
    });
  }
  
  showFormErrors() {
    const inputs = document.querySelectorAll('#panel input');
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
    const label = document.getElementById(`${refName}Label`).textContent;
    const error = document.getElementById(`${refName}Error`);

    const validEmail = refName.indexOf('email') !== -1;
  
   
    const isNumber = refName.indexOf('phone') !== -1;
    //console.log(isNumber);
     error.textContent = '';

        if (!validity.valid) {
            this.setState({ showResults: false });
            if (validity.valueMissing) {
                error.textContent = `Please enter ${label}`;
            } else if (validEmail && validity.patternMismatch) {
              console.log("Email");
              error.textContent = `Please enter valid ${label} address`;
            } else if (isNumber && validity.patternMismatch) {
              console.log("number");
              error.textContent = `Please enter valid number eg. (+1-999-999-9999)`;
            } else {
              console.log("number");
            }
          }
   
    return true;
  }

  render() {
    return (
      <div id="panel">
            <div class="btn-padding">
              <button className='btn bottom-btn btn-block' onClick={this.openModal}>
              <i class="fa fa-user-plus"> </i>  New Merchant Account
              </button>
            </div>            
            <div>
              <ToastContainer ref="container"
                              toastMessageFactory={ToastMessageFactory}
                              className="toast-top-right" />
            </div>           
            <Modal isOpen={this.state.isOpen}>
             
              <div class="modal-header">
                <h4 class="modal-title font-20">Create New Merchant</h4>
              </div>
              <form id="myform" method="post" noValidate>
              <ModalBody>
                <FormGroup row>
                  <Label id="firstNameLabel" class="form-label" for="firstName" sm={3}>First Name </Label><span></span>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="firstName"
                    name="firstName"
                    ref="firstName"
                    value={ this.state.firstName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="firstNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="lastNameLabel" class="form-label" for="lastName" sm={3}>Last Name </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="lastName"
                    name="lastName"
                    ref="lastName"
                    value={ this.state.lastName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="lastNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="accountNameLabel" class="form-label" for="accountName" sm={3}>Account Name </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="accountName"
                    name="accountName"
                    ref="accountName"
                    value={ this.state.accountName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="accountNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="phoneLabel" class="form-label" for="phone" sm={3}>Phone </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="phone"
                    ref="phone"
                    value={ this.state.phone } 
                    onChange={ this.handleChange }
                    pattern="[0-9]{6,10}"
                    required />
                  <div className="error" id="phoneError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="emailLabel" class="form-label" for="email" sm={3}>Email </Label>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="email"
                    name="email"
                    ref="email"
                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                    value={ this.state.email } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="emailError" />
                  </Col>
                </FormGroup>
              </ModalBody>
              <div>
              <hr class="modal-footer-hr"/>
              <ModalFooter>
                <button type="submit" class="btn btn-blue" onClick={ this.handleSubmit }>
                  Save Merchant Account
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

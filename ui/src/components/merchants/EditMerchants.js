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

export default class EditMerchant extends React.Component {
    constructor(props) {
    super(props);
    this.state = {
      firstName: this.props.data.firstname,
      lastName: this.props.data.lastname,      
      email: this.props.data.username,      
      phone: this.props.data.phone,
      accountNo: this.props.data.accountNo,
      accountName: this.props.data.accountName,
    };
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.showEditForm = this.showEditForm.bind(this);
  }

  openModal = () => {
    this.setState({
      isOpen: true,
    });
  };

  hideModal = () => {
    this.setState({
      isOpen: false, 
      firstName: this.props.data.firstname,
      lastName: this.props.data.lastname,      
      email: this.props.data.username,      
      phone: this.props.data.phone,
      accountNo: this.props.data.accountNo,
      accountName: this.props.data.accountName,
    }); 
    $("#editFirstNameError").html("");
    $("#editLastNameError").html("");
    $("#editEmailError").html("");
    $("#editPhoneError").html("");
    $("#editAccountNameError").html("");
    $("input").removeClass("active");
  };

  handleChange(e) {
    
    e.target.classList.add('active');
    
    this.setState({
      [e.target.name]: e.target.value

    });

    this.showInputError(e.target.name);    

  }

  updateMerchantState(info){

     this.setState({
      firstName: info.firstName,
      lastName: info.lastName,      
      email: info.email,      
      phone: info.phone,
      accountName: info.accountName,
      accountNo: info.accountNo,
    }); 
  }

  handleSubmit(e) {    
    e.preventDefault();   
    let isFormValid = true;
    var merchantId = this.props.data.userId;

    if (this.showFormErrors()) {

      Service.editMerchant(Config.editMerchant+merchantId,this.state, function(data) {
       
        this.props.onUpdateAccount(this.state);  
        this.successAlert();        
        this.hideModal();

      }.bind(this), function(xhr, status, err) {
      
        this.errorAlert();
        var statusObj = xhr;
        var obj=JSON.parse(xhr.responseText);

        const error = document.getElementById(`emailError`);
        const accountNameError = document.getElementById(`accountNameError`);

        if(obj["error_description"] == "Merchant with email address already exist. Please provide different email address.") {

          error.textContent = `Merchant with email address already exist`;          
         
         } else if(obj["error_description"] == "Merchant with account name already exist. Please provide different account name.") {
          
          accountNameError.textContent = `Merchant with account name already exist`;
        }

        isFormValid = false;

      }.bind(this));
    }
  }

  successAlert () {
    this.refs.container.success(
      "Success!!",
      "Merchant Updated successfully.", {
      timeOut: 2000,
      extendedTimeOut: 1000
    });
  }

  errorAlert () {
    this.refs.container.error(
      "Error!!",
      "Something is wrong.", {
      timeOut: 2000,
      extendedTimeOut: 1000
    });
  }
  
  showFormErrors() {
    
    const inputs = document.querySelectorAll('#panel input');

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

  showEditForm() {

    this.setState({
      isOpen: true,
      firstName: this.props.data.firstname,
      lastName: this.props.data.lastname,      
      email: this.props.data.username,      
      phone: this.props.data.phone,
      accountNo: this.props.data.accountNo,
      accountName: this.props.data.accountName,
    });   
  }
  
  showInputError(refName) {
   
    var isControlValid = true;
    const validity = this.refs[refName].validity;
    const label = document.getElementById(`${refName}Label`).textContent;
    const error = document.getElementById(`${refName}Error`);

    const validEmail = refName.indexOf('email') !== -1;     
    const isNumber = refName.indexOf('phone') !== -1;

     error.textContent = '';

        if (!validity.valid) {
          
          isControlValid = false;            
          this.setState({ showResults: false });  

          if (validity.valueMissing) {
             
            error.textContent = `Please enter ${label}`;
          } else if (validEmail && validity.patternMismatch) {
            
            error.textContent = `Please enter valid ${label} address`;
          } else if (isNumber && validity.patternMismatch) {
          
            error.textContent = `Please enter valid number`;
          }
        }
   
    return isControlValid;
  }

  render() {
    return (
      <div id="panel">
            <div class="btn-padding">
            <div class="col-md-6 col-xs-6">
              <i class="fa fa-pencil" onClick={this.showEditForm}></i>  
            </div>
            </div>            
            <div>
              <ToastContainer ref="container"
                              toastMessageFactory={ToastMessageFactory}
                              className="toast-top-right" />
            </div>           
            <Modal isOpen={this.state.isOpen}>
              <div class="modal-header">
                <h4 class="modal-title font-20">Edit New Merchant</h4>
              </div>
              <form id="editMerchant" method="post" noValidate onSubmit="">
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
                    value={this.state.lastName} 
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
                  Save Merchant Update
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

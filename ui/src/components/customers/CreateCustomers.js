import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';

import Service from "../Service";
import Config from "../../index.config";
import AlertMessage from "../AlertMessage";


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

export default class CreateCustomers extends React.Component {
    constructor(props) {
    super(props);

    this.state = {
      customerFirstName: '',
      customerLastName: '', 
      customerName: '',     
      customerEmail: '',      
      customerPhone: '',
      customerCountry: '',
      customerStreet: '',      
      customerCity: '',      
      customerState: '',
      customerZipcode: '',
    };
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  openModal = () => {
    this.setState({
      isOpen: true
    });
  };

  hideModal = () => {
    this.setState({
      isOpen: false, 
      customerFirstName: '',
      customerLastName: '', 
      customerName: '',     
      customerEmail: '',      
      customerPhone: '',
      customerCountry: '',
      customerStreet: '',      
      customerCity: '',      
      customerState: '',
      customerZipcode: '',
    }); 
    $("#customerFirstNameError").html("");
    $("#customerLastNameError").html("");
    $("#customerNameError").html("");
    $("#customerEmailError").html("");
    $("#customerPhoneError").html("");
    $("#customerCountryError").html("");
    $("#customerStreetError").html("");
    $("#customerCityError").html("");
    $("#customerStateError").html("");
    $("#customerZipcodeError").html("");
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

      var newCustomerData = {};
      newCustomerData.name = this.state.customerName;
      newCustomerData.firstName = this.state.customerFirstName;
      newCustomerData.lastName = this.state.customerLastName;
      newCustomerData.phone = this.state.customerPhone;
      newCustomerData.email = this.state.customerEmail;
      newCustomerData.country = this.state.customerCountry;
      newCustomerData.state = this.state.customerState;
      newCustomerData.city = this.state.customerCity;
      newCustomerData.street = this.state.customerStreet;      
      newCustomerData.zipcode = this.state.customerZipcode;
      
      var requestData = {
            url: Config.customerAPIPath,
            type: 'POST',
            data: JSON.stringify(newCustomerData),
            dataType: 'text',
            contentType: 'application/json'
        };

      var reqData = Service.buildRequestdata(requestData);

      Service.executeRequest(reqData, function(data) {
        this.refs.alertMessageChild.successAlert("Customer created successfully.");         
        this.hideModal();   
        this.props.onUpdateList(); 
      }.bind(this), function(xhr, status, err) {
        console.log("error", err);
        this.refs.alertMessageChild.errorAlert("Something is wrong."); 
        var statusObj = xhr;
        var obj=JSON.parse(xhr.responseText);

        if(obj["error_description"] == "Customer with name already exist. Please provide different name.") {
         const customerNameError = document.getElementById(`customerNameError`);
          customerNameError.textContent = obj["error_description"]; 
        
        } else if(obj["error_description"] == "User with email already exist. Please provide different email.") {
         const customerEmailError = document.getElementById(`customerEmailError`);
          customerEmailError.textContent = obj["error_description"];
        }

        isFormValid = false;
      }.bind(this));
    }
  }
  
  checkFormValidation() {
    
    const inputs = document.querySelectorAll('#customerPanel input');

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

    const validEmail = refName.indexOf('customerEmail') !== -1;     
    const isNumber = refName.indexOf('customerPhone') !== -1;
    const isZipCodeNumber = refName.indexOf('customerZipcode') !== -1;

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
          } else if (isZipCodeNumber && validity.patternMismatch) {
          
            error.textContent = `Please enter valid zipcode`;
          }
        }
   
    return isControlValid;
  }

  render() {
    return (
      <div id="customerPanel">
            <div class="marchant-btn">
              <button className='btn bottom-btn btn-block btn-lg' onClick={this.openModal}>
              <i class="fa fa-user margin-right-11"> </i>   New Customer Account
              </button>
            </div>            
            <div>
              <AlertMessage ref="alertMessageChild"/>
            </div>           
            <Modal isOpen={this.state.isOpen}>
              <div class="modal-header">
                <h4 class="modal-title font-20">Create New Customer</h4>
              </div>
              <form id="myform" method="post" noValidate onSubmit="">
              <ModalBody>
              <FormGroup row>
                  <Label id="customerNameLabel" class="form-label" for="customerName" sm={3}> Customer Name </Label><span></span>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerName"
                    ref="customerName"
                    value={ this.state.customerName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerNameError" />
                  </Col>
                </FormGroup>
               <FormGroup row>
                  <Label id="customerFirstNameLabel" class="form-label" for="customerFirstName" sm={3}> First Name </Label><span></span>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerFirstName"
                    ref="customerFirstName"
                    value={ this.state.customerFirstName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerFirstNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerLastNameLabel" class="form-label" for="customerLastName" sm={3}> Last Name </Label><span></span>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerLastName"
                    ref="customerLastName"
                    value={ this.state.customerLastName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerLastNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerEmailLabel" class="form-label" for="customerEmail" sm={3}>Email </Label>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerEmail"
                    ref="customerEmail"
                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                    value={ this.state.customerEmail } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerEmailError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerPhoneLabel" class="form-label" for="customerPhone" sm={3}> Phone </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerPhone"
                    ref="customerPhone"
                    value={ this.state.customerPhone } 
                    onChange={ this.handleChange }
                    pattern="[0-9]{6,10}"
                    required />
                  <div className="error" id="customerPhoneError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerCountryLabel" class="form-label" for="customerCountry" sm={3}> Country </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerCountry"
                    ref="customerCountry"
                    value={ this.state.customerCountry } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerCountryError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerCityLabel" class="form-label" for="customerCity" sm={3}> City </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerCity"
                    ref="customerCity"
                    value={ this.state.customerCity } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerCityError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerStateLabel" class="form-label" for="customerState" sm={3}> State </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerState"
                    ref="customerState"
                    value={ this.state.customerState } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerStateError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerStreetLabel" class="form-label" for="customerStreet" sm={3}> Street </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerStreet"
                    ref="customerStreet"
                    value={ this.state.customerStreet } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="customerStreetError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="customerZipcodeLabel" class="form-label" for="customerZipcode" sm={3}> Zip Code </Label><span></span>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="customerZipcode"
                    ref="customerZipcode"
                    value={ this.state.customerZipcode } 
                    onChange={ this.handleChange }
                    pattern="[0-9]{4,6}"
                    required />
                  <div className="error" id="customerZipcodeError" />
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

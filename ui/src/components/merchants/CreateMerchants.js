import React from "react";
import  'react-bootstrap';
import $ from 'jquery';
import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';

import CreateMerchants from "./CreateMerchants";
import Service from "../Service";
import Config from "../../index.config";

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
     // isOpen: false,
     //isSubOpen: false
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
      isOpen: false
    });
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
    
    console.log('component state', JSON.stringify(this.state));
    
    if (!this.showFormErrors()) {
      console.log('form is invalid: do not submit');
    } else {
      console.log('form is valid: submit');

      Service.createMerchant(Config.createMerchant,this.state, function(data) {
      
        console.log('status',data);
      }.bind(this), function(xhr, status, err) {
        
        this.setState({ showResults: true });
        console.log('err', err);
      }.bind(this));
    }
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
   
    const isNumber = refName.indexOf('phone') !== -1;
    //console.log(isNumber);
     error.textContent = '';

        if (!validity.valid) {
            this.setState({ showResults: false });
            if (validity.valueMissing) {
                error.textContent = `${label} is a required field`;
            } else if (isNumber && validity.patternMismatch) {
              console.log("number");
              error.textContent = `${label} should be longer than 10 number`;
            } else {
              console.log("number");
            }
          }
   
    return true;
  }

  render() {
    return (
      <div className='layout-page' id="panel">
        <main className='layout-main'>
          <div className='container'>
            <button className='btn btn-primary' onClick={this.openModal}>
              New Merchant Account
            </button>
            <Modal isOpen={this.state.isOpen} onRequestHide={this.hideModal}>
              <div class="modal-header">
                <h4 class="modal-title">Create New Merchant</h4>
              </div>
              <form method="post" noValidate>
              <ModalBody>
                <FormGroup row>
                  <Label id="firstNameLabel" for="firstName" sm={3}>First Name : </Label>
                   <Col sm={8}>
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
                  <Label id="lastNameLabel" for="lastName" sm={3}>Last Name : </Label>
                   <Col sm={8}>
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
                  <Label id="accountNameLabel" for="accountName" sm={3}>Account # : </Label>
                   <Col sm={8}>
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
                  <Label id="phoneLabel" for="phone" sm={3}>Phone # : </Label>
                   <Col sm={8}>
                     <input className="form-control"
                    type="text"
                    name="phone"
                    ref="phone"
                    value={ this.state.phone } 
                    onChange={ this.handleChange }
                    pattern=".{6,10}"
                    required />
                  <div className="error" id="phoneError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="emailLabel" sm={3}>Email : </Label>
                  <Col sm={8}>
                     <input className="form-control"
                    type="email"
                    name="email"
                    ref="email"
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
                <button type="submit" class="btn btn-primary" onClick={ this.handleSubmit }>
                  Save Merchant Account
                </button>
                <button type="button" className='btn btn-primary' onClick={this.hideModal}>
                  Cancel
                </button>
              </ModalFooter>
              </div>
              </form>
            </Modal>
          </div>
        </main>
      </div>
    );
  }
}

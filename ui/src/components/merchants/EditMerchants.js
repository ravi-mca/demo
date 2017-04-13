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

export default class EditMerchant extends React.Component {
    constructor(props) {
    super(props);
    this.state = {
      editFirstName: this.props.data.firstName,
      editLastName: this.props.data.lastName,      
      editEmail: this.props.data.email,      
      editPhone: this.props.data.phone,
      editAccountName: this.props.data.accountName,
      accountNo: this.props.data.accountNo,
    };
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.showEditForm = this.showEditForm.bind(this);
    this.onUpdateMerchantAccount = this.onUpdateMerchantAccount.bind(this);
    
  }

  openModal = () => {
    this.setState({
      isOpen: true,
      editFirstName: this.props.data.firstname,
      editLastName: this.props.data.lastname,      
      editEmail: this.props.data.username,      
      editPhone: this.props.data.phone,
      editAccountName: this.props.data.accountName,
      accountNo: this.props.data.accountNo,
    }); 
    $("#editFirstNameError").html("");
    $("#editLastNameError").html("");
    $("#editEmailError").html("");
    $("#editPhoneError").html("");
    $("#editAccountNameError").html("");
    $("input").removeClass("active");
  };

  hideModal = () => {
    this.setState({
      isOpen: false, 
      editFirstName: this.props.data.firstName,
      editLastName: this.props.data.lastName,      
      editEmail: this.props.data.email,      
      editPhone: this.props.data.phone,
      editAccountName: this.props.data.accountName,
      accountNo: this.props.data.accountNo,
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
    if (this.showFormErrors()) {
    var merchantId = this.props.data.userId;

    var newData = {};
    newData.firstName = this.state.editFirstName;
    newData.lastName = this.state.editLastName;
    newData.phone = this.state.editPhone;
    newData.accountName = this.state.editAccountName;
    newData.email = this.state.editEmail;
    if (this.showFormErrors()) {

      var requestData = {
            url: Config.merchantAPIPath+'/'+ merchantId,
            type: 'PUT',
            data: JSON.stringify(newData),
            dataType: 'text',
            contentType: 'application/json'
      };

      var reqData = Service.buildRequestdata(requestData);

      Service.executeRequest(reqData, function(data) {
        this.props.onUpdateAccount(this.state);  
        this.refs.alertMessageChild.successAlert("Merchant Updated successfully.");         
        this.hideModal();
      }.bind(this), function(xhr, status, err) {
        this.refs.alertMessageChild.errorAlert("Something is wrong."); 
        var statusObj = xhr;
        var obj=JSON.parse(xhr.responseText);

        const editMerchantNameError = document.getElementById(`editFirstNameError`);
        const editEmailError = document.getElementById(`editEmailError`);
        const editAccountNameError = document.getElementById(`editAccountNameError`);

        if(obj["error_description"] == "Merchant with first name already exist. Please provide different first name.") {
          
          editMerchantNameError.textContent = `Merchant with first name already exist`; 
        
        }else if(obj["error_description"] == "Merchant with email address already exist. Please provide different email address.") {

          editEmailError.textContent = `Merchant with email address already exist`;          
         
         } else if(obj["error_description"] == "Merchant with account name already exist. Please provide different account name.") {
          
          editAccountNameError.textContent = `Merchant with account name already exist`;
        }

        isFormValid = false;
      }.bind(this));
    }
  }
  }
  
  showFormErrors() {
    
    const inputs = document.querySelectorAll('#editpanel input');

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

  onUpdateMerchantAccount() {
    this.props.onUpdateAccount();
  }

  showEditForm() {

    this.setState({
      isOpen: true,
      editFirstName: this.props.data.firstName,
      editLastName: this.props.data.lastName,      
      editEmail: this.props.data.email,      
      editPhone: this.props.data.phone,
      editAccountName: this.props.data.accountName,
      accountNo: this.props.data.accountNo,
    });   
  }
  
  showInputError(refName) {
   
    var isControlValid = true;
    const validity = this.refs[refName].validity;
    const label = document.getElementById(`${refName}Label`).textContent;
    const error = document.getElementById(`${refName}Error`);

    const validEmail = refName.indexOf('editEmail') !== -1;     
    const isNumber = refName.indexOf('editPhone') !== -1;

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
      <div id="editpanel">
            <div class="btn-padding">
            <div class="col-md-2 col-xs-2">
              <div class="col-md-1 col-xs-1">
                <i class="fa fa-pencil" onClick={this.showEditForm}></i>  
              </div> 
              <div class="col-md-1 col-xs-1 pad-left-9 pad-right-0">
                <span>|</span>
              </div>
              <div class="col-md-1 col-xs-1">              
                <DeletePopUp data={this.props.data} onUpdateAccount={this.onUpdateMerchantAccount}/>
              </div>
             
            </div>
            </div>            
            <div>
              <AlertMessage ref="alertMessageChild"/>
            </div>          
            <Modal isOpen={this.state.isOpen}>
              <div class="modal-header">
                <h4 class="modal-title font-20">Edit New Merchant</h4>
              </div>
              <form id="editMerchant" method="post" noValidate>
              <ModalBody>
                <FormGroup row>
                  <Label id="editFirstNameLabel" class="form-label" for="editFirstName" sm={3}>First Name </Label><span></span>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="editFirstName"
                    name="editFirstName"
                    ref="editFirstName"
                    value={ this.state.editFirstName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editFirstNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editLastNameLabel" class="form-label" for="editLastName" sm={3}>Last Name </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="editLastName"
                    name="editLastName"
                    ref="editLastName"
                    value={this.state.editLastName} 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editLastNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editAccountNameLabel" class="form-label" for="editAccountName" sm={3}>Account Name </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="editAccountName"
                    name="editAccountName"
                    ref="editAccountName"
                    value={ this.state.editAccountName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editAccountNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editPhoneLabel" class="form-label" for="editPhone" sm={3}>Phone </Label>
                   <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editPhone"
                    ref="editPhone"
                    value={ this.state.editPhone } 
                    onChange={ this.handleChange }
                    pattern="[0-9]{6,10}"
                    required />
                  <div className="error" id="editPhoneError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editEmailLabel" class="form-label" for="editEmail" sm={3}>Email </Label>
                  <Col sm={8} class="col-padding">
                     <input className="form-control"
                    type="editEmail"
                    name="editEmail"
                    ref="editEmail"
                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
                    value={ this.state.editEmail } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editEmailError" />
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

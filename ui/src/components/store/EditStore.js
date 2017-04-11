import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';
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

export default class EditStore extends React.Component {
    constructor(props) {
    super(props);
    this.state = {
      edituserId: "",
      editId: "",
      editStoreId : "",
      editName : "",
      editNickname : "",
      editManagerPoc : "",
      editPhoneNo : "",
      editCountry : "",
      editState : "",
      editCity : "",
      editZipCode : "",
      editStreetAddress : "",
      editControllerNo : "",
      editControllerPlacement : "",
      editStoreCategory : "",
      editStoreSubCategory : "",
      editPriceCategory : "",
      editPosSystem : "",
      editStoreCastAdminName : "",
    };
    
    this.handleChange = this.handleChange.bind(this);
    this.handleEditStoreSubmit = this.handleEditStoreSubmit.bind(this);
    this.showEditForm = this.showEditForm.bind(this);
    this.resetForm = this.resetForm.bind(this);
  }

  openModal = () => {
    this.setState({
      isOpen: true,
    }); 
  };

  resetForm() {
       // Clear state
    this.setState({
      edituserId: "",
      editId: "",
      editStoreId : "",
      editName : "",
      editNickname : "",
      editManagerPoc : "",
      editPhoneNo : "",
      editCountry : "",
      editState : "",
      editCity : "",
      editZipCode : "",
      editStreetAddress : "",
      editControllerNo : "",
      editControllerPlacement : "",
      editStoreCategory : "",
      editStoreSubCategory : "",
      editPriceCategory : "",
      editPosSystem : "",
      editStoreCastAdminName : "",
    }); 
   
    $("#editStoreIdError").html("");
    $("#editNameError").html("");
    $("#editNicknameError").html("");
    $("#editManagerPocError").html("");
    $("#editPhoneNoError").html("");
    $("#editCountryError").html("");
    $("#editStateError").html("");
    $("#editCityError").html("");
    $("#editStreetAddressError").html("");
    $("#editZipCodeError").html("");
    $("#editControllerNoError").html("");
    $("#editControllerPlacementError").html("");
    $("#editStoreCategoryError").html("");
    $("#editStoreSubCategoryError").html("");
    $("#editPriceCategoryError").html("");
    $("#editPosSystemError").html("");
    $("#editStoreCastAdminNameError").html("");
    $("input").removeClass("active");
   }

  hideModal = () => {
     this.setState({
      isOpen: false, 
    });
    this.resetForm();
  };

  handleChange(e) {
    
    e.target.classList.add('active');
    
    this.setState({
      [e.target.name]: e.target.value

    });
    this.showInputError(e.target.name);    
  }

  handleEditStoreSubmit(e) {    
    e.preventDefault();  

    let isFormValid = true;
    if (this.showFormErrors()) {
    var storeId = this.state.editId;

    var updateStoreData = {
    "name": this.state.editName,
    "nickName": this.state.editNickname,
    "managerOrPOC": this.state.editManagerPoc,
    "phone": this.state.editPhoneNo,
    "street": this.state.editStreetAddress,
    "city": this.state.editCity,
    "state": this.state.editState,
    "country": this.state.editCountry,
    "zipCode": this.state.editZipCode,
    "controllerNumber": this.state.editControllerNo,
    "controllerPlacement": this.state.editControllerPlacement,
    "category": this.state.editStoreCategory,
    "subCategory": this.state.editStoreSubCategory,
    "priceCategory": this.state.editPriceCategory,
    "posSystem": this.state.editPosSystem,
    "storecastAdminName": this.state.editStoreCastAdminName
  };
    
    if (this.showFormErrors()) {

      var requestData = {
            url: Config.storeAPIPath + storeId,
            type: 'PUT',
            data: JSON.stringify(updateStoreData),
            dataType: 'text',
            contentType: 'application/json'
      };

      var reqData = Service.buildRequestdata(requestData);

      Service.executeRequest(reqData, function(data) {
        this.refs.alertMessageChild.successAlert("Store updated successfully.");    
        this.props.onUpdateStore(this.state.edituserId);         
        this.hideModal();
      }.bind(this), function(xhr, status, err) {
        this.refs.alertMessageChild.errorAlert("Something is wrong."); 
        var statusObj = xhr;
        var obj=JSON.parse(xhr.responseText);
        const editNameError = document.getElementById(`editNameError`);
        const editNicknameError = document.getElementById(`editNicknameError`);
        var editNameErrorMessage = "Store with name already exist. Please provide different store name.";
        var editNicknameErrorMessage = "Store with nickname already exist. Please provide different store nickname.";

        if(obj["error_description"] == editNameErrorMessage) {
          editNameError.textContent = obj["error_description"];   
         } else if(obj["error_description"] == editNicknameErrorMessage) {
          editNicknameError.textContent = obj["error_description"];
        }

        isFormValid = false;
      }.bind(this));
    }
  }
  }
  
  showFormErrors() {
    
    const inputs = document.querySelectorAll('#editStorepanel input');

    const selects = document.querySelectorAll('#editStorepanel select');

    let isFormValid = true;
    inputs.forEach(input => {
      input.classList.add('active');
      const isInputValid = this.showInputError(input.name, "input");
      
      if (!isInputValid && isFormValid ) {
        isFormValid = false;
      }
    });

    selects.forEach(select => {
      select.classList.add('active');
      const isInputValid = this.showInputError(select.name, "select");
      
      if (!isInputValid && isFormValid ) {
        isFormValid = false;
      }
    });
    
    return isFormValid;
  }

  showEditForm() {
    this.setState({
      isOpen: true,
      edituserId:this.props.data.userId,
      editId: this.props.data.id,
      editStoreId : this.props.data.storeId,
      editName : this.props.data.name,
      editNickname : this.props.data.nickName,
      editManagerPoc : this.props.data.managerOrPOC,
      editPhoneNo : this.props.data.phone,
      editCountry : this.props.data.country,
      editState : this.props.data.state,
      editCity : this.props.data.city,
      editZipCode : this.props.data.zipCode,
      editStreetAddress : this.props.data.street,
      editControllerNo : this.props.data.controllerNumber,
      editControllerPlacement : this.props.data.controllerPlacement,
      editStoreCategory : this.props.data.category,
      editStoreSubCategory : this.props.data.subCategory,
      editPriceCategory : this.props.data.priceCategory,
      editPosSystem : this.props.data.posSystem,
      editStoreCastAdminName : this.props.data.storecastAdminName,
    });   
  }
  
  showInputError(refName, type) {
   
    var isControlValid = true;
    const validity = this.refs[refName].validity;
    const label = document.getElementById(`${refName}Label`).textContent;
    const error = document.getElementById(`${refName}Error`);
 
    const isPhoneNumber = refName.indexOf('editPhone') !== -1;
    const isZipCodeNumber = refName.indexOf('editZipCode') !== -1;

     error.textContent = '';

        if (!validity.valid) {
          
          isControlValid = false;            
          this.setState({ showResults: false });  

          if (validity.valueMissing) { 

            if(type == "select") {
              error.textContent = `Please select ${label}`;
            } else {
              error.textContent = `Please enter ${label}`;
            }
          } else if (isPhoneNumber && validity.patternMismatch) {
            error.textContent = `Please enter valid phone Number.`;
          } else if(isZipCodeNumber && validity.patternMismatch) {
            error.textContent = `Please enter valid zip code.`;
          }
        }
   
    return isControlValid;
  }

  render() {
    return (
          <div id="editStorepanel">
            <div class="col-md-6 col-xs-6 storeMargin">
            <i class="fa fa-pencil ml-10" onClick={this.showEditForm}></i>    
            </div>    
            <div>
              <AlertMessage ref="alertMessageChild"/>
            </div>     
            <Modal isOpen={this.state.isOpen}>
              <div class="modal-header">
                <h4 class="modal-title font-20">Edit Store</h4>
              </div>
              <form id="editStore" method="post" noValidate>
              <ModalBody>
                <FormGroup row>
                  <Label id="editStoreIdLabel" class="form-label" for="editStoreId" sm={4}> Store ID </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editStoreId"
                    ref="editStoreId"
                    value={ this.state.editStoreId } 
                    onChange={ this.handleChange }
                    required disabled />
                  <div className="error" id="editStoreIdError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editNameLabel" class="form-label" for="editName" sm={4}> Store Name </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editName"
                    ref="editName"
                    value={ this.state.editName } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editNameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editNicknameLabel" class="form-label" for="editNickname" sm={4}> Store Nickname </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editNickname"
                    ref="editNickname"
                    value={ this.state.editNickname } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editNicknameError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editManagerPocLabel" class="form-label" for="editManagerPoc" sm={4}> Store Manager/POC </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editManagerPoc"
                    ref="editManagerPoc"
                    value={ this.state.editManagerPoc } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editManagerPocError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editPhoneNoLabel" class="form-label" for="editPhoneNo" sm={4}> Store Phone Number </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editPhoneNo"
                    ref="editPhoneNo"
                    value={ this.state.editPhoneNo } 
                    onChange={ this.handleChange } 
                    pattern="[0-9]{6,10}"
                    required />
                  <div className="error" id="editPhoneNoError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editStreetAddressLabel" class="form-label" for="editStreetAddress" sm={4}> Store Street Address </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editStreetAddress"
                    ref="editStreetAddress"
                    value={ this.state.editStreetAddress } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editStreetAddressError" />
                  </Col>
                </FormGroup>                    
                <FormGroup row>
                  <Label id="editCityLabel" class="form-label" for="editCity" sm={4}> Store City </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editCity"
                    ref="editCity"
                    value={ this.state.editCity } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editCityError" />
                  </Col>
                </FormGroup>           
                <FormGroup row>
                  <Label id="editStateLabel" class="form-label" for="editState" sm={4}> Store State </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editState"
                    ref="editState"
                    value={ this.state.editState } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editStateError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editCountryLabel" class="form-label" for="editCountry" sm={4}> Store Country </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editCountry"
                    ref="editCountry"
                    value={ this.state.editCountry } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editCountryError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editZipCodeLabel" class="form-label" for="editZipCode" sm={4}> Store Zip Code </Label><span></span>
                  <Col sm={7} class="col-padding">
                     <input className="form-control"
                    type="text"
                    name="editZipCode"
                    ref="editZipCode"
                    value={ this.state.editZipCode } 
                    onChange={ this.handleChange }
                    pattern="[a-zA-Z0-9]{4,6}"
                    required />
                  <div className="error" id="editZipCodeError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editControllerNoLabel" class="form-label" for="editControllerNo" sm={4}> Controller Number </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <input className="form-control"
                    type="text"
                    name="editControllerNo"
                    ref="editControllerNo"
                    value={ this.state.editControllerNo } 
                    onChange={ this.handleChange }
                    required />
                  <div className="error" id="editControllerNoError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editControllerPlacementLabel" class="form-label" for="editControllerPlacement" sm={4}> Controller Placement </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <select className="form-control"
                    name="editControllerPlacement"
                    ref="editControllerPlacement"
                    value={ this.state.editControllerPlacement } 
                    onChange={ this.handleChange }
                    required > 
                    <option value=''>Select controller placement</option>
                    <option value='Back'>Back</option>
                    <option value='Middle'>Middle</option>
                    <option value='Front'>Front</option>
                    </select>
                  <div className="error" id="editControllerPlacementError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editStoreCategoryLabel" class="form-label" for="editStoreCategory" sm={4}> Store Category </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <select className="form-control"
                    name="editStoreCategory"
                    ref="editStoreCategory"
                    value={ this.state.editStoreCategory } 
                    onChange={ this.handleChange }
                    required >
                    <option value=''>Select catagory</option>
                    <option value='Fast-Casual Restaurants'>Fast-Casual Restaurants</option>
                    <option value='Fast-Food Restaurants'>Fast-Food Restaurants</option>
                    <option value='Fast-Service Restaurants'>Fast-Service Restaurants</option>
                    </select>
                  <div className="error" id="editStoreCategoryError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editStoreSubCategoryLabel" class="form-label" for="editStoreSubCategory" sm={4}> Store Subcategory </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <select className="form-control"
                    name="editStoreSubCategory"
                    ref="editStoreSubCategory"
                    value={ this.state.editStoreSubCategory } 
                    onChange={ this.handleChange }
                    required>
                    <option value=''>Select Subcategory</option>
                    <option value="Quick Foods & Cafes">Quick Foods & Cafes</option>
                    <option value="Seafood">Seafood</option>
                    <option value="Fast-casual Restaurants">Fast-casual Restaurants</option>
                    </select>
                  <div className="error" id="editStoreSubCategoryError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editPriceCategoryLabel" class="form-label" for="editPriceCategory" sm={4}> Price Category </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <select className="form-control"
                    name="editPriceCategory"
                    ref="editPriceCategory"
                    value={ this.state.editPriceCategory } 
                    onChange={ this.handleChange }
                    required>
                      <option value="">Select</option>
                      <option value="$">$</option>
                      <option value="$$">$$</option>
                      <option value="$$$">$$$</option>
                      <option value="$$$$">$$$$</option>
                    </select>
                  <div className="error" id="editPriceCategoryError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editPosSystemLabel" class="form-label" for="editPosSystem" sm={4}> POS System </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <input className="form-control"
                    type="text"
                    name="editPosSystem"
                    ref="editPosSystem"
                    value={ this.state.editPosSystem } 
                    onChange={ this.handleChange } />
                  <div className="error" id="editPosSystemError" />
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Label id="editStoreCastAdminNameLabel" class="form-label" for="editStoreCastAdminName" sm={4}> Storecast Admin </Label><span></span>
                  <Col sm={7} class="col-padding">
                    <input className="form-control"
                    type="text"
                    name="editStoreCastAdminName"
                    ref="editStoreCastAdminName"
                    value={ this.state.editStoreCastAdminName } 
                    onChange={ this.handleChange }
                     />
                  <div className="error" id="editStoreCastAdminNameError" />
                  </Col>
                </FormGroup>
              </ModalBody>
              <div>
              <hr class="modal-footer-hr"/>
              <ModalFooter>
                <button type="submit" class="btn btn-blue" onClick={ this.handleEditStoreSubmit }>
                  Save Store Update
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

import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';
import {
	Modal,
	ModalHeader,
	ModalTitle,
	ModalClose,
	ModalBody,
	ModalFooter
} from 'react-modal-bootstrap';


import Service from "../Service";
import Config from "../../index.config";

var ReactToastr = require("react-toastr");
var {ToastContainer} = ReactToastr;
var ToastMessageFactory = React.createFactory(ReactToastr.ToastMessage.animation);

export default class AddStore extends React.Component {
	constructor(props) {
		super(props);

		this.getInitialState = this.getInitialState.bind(this);
		this.addStore = this.addStore.bind(this);
		this.inputChange = this.inputChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.resetForm = this.resetForm.bind(this);
		this.getInitialState();
	}

	getInitialState() {
		this.state = {
			isOpen: false,
			storeName : "",
			nickName : "",
			managerOrPOC : "",
			storePhone : "",
			country : "",
			state : "",
			street : "",
			city : "",
			zipCode : "",
			controllerNumber : "",
			controllerPlacement : "",
			category : "",
			subCategory : "",
			priceCategory : "",
			posSystem : "",
			storecastAdminName : "",
		}
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
		this.resetForm();

	};

	resetForm() {
		// Clear state
		this.getInitialState();

	   //Clear error fields
		$("#storeNameError").html("");
		$("#nickNameError").html("");
		$("#managerOrPOCError").html("");
		$("#storePhoneError").html("");
		$("#countryError").html("");
		$("#stateError").html("");
		$("#cityError").html("");
		$("#streetError").html("");
		$("#zipCodeError").html("");
		$("#controllerNumberError").html("");
		$("#controllerPlacementError").html("");
		$("#categoryError").html("");
		$("#subCategoryError").html("");
		$("#priceCategoryError").html("");
		$("#posSystemError").html("");
		$("#storecastAdminNameError").html("");
		$("input").removeClass("active");
		$("select").removeClass("active");
	}

	inputChange(e) {
		e.target.classList.add('active');
		this.setState({
			[e.target.name]: e.target.value
		});
		this.showInputError(e.target.name);
		this.setState({isOpen: true});
	}

	handleSubmit(e) {
		e.preventDefault();
		let isFormValid = true;

		if (this.showFormErrors()) {

			var storeData = {
    			name: this.state.storeName,
    			nickName: this.state.nickName,
    			managerOrPOC: this.state.managerOrPOC,
    			phone: this.state.storePhone,
    			street: this.state.street,
    			city: this.state.city,
   				state: this.state.state,
    			country: this.state.country,
    			zipCode: this.state.zipCode,
    			controllerNumber: this.state.controllerNumber,
    			controllerPlacement: this.state.controllerPlacement,
    			category: this.state.category,
   				subCategory: this.state.subCategory,
    			priceCategory: this.state.priceCategory,
    			posSystem: this.state.posSystem,
    			storecastAdminName: this.state.storecastAdminName
  			};
			var reqData = Service.buildRequestdata(Config.addStore + this.props.data, 'POST', storeData);
			console.log('reqData',reqData);
			Service.executeRequest(reqData, function(data) {
				this.successAlert();
				this.hideModal();
			}.bind(this), function(xhr, status, err) {
				this.errorAlert();
				var statusObj = xhr;
				//var obj = JSON.parse(xhr.responseText);
				//console.log('obj',obj);
				console.log('statusObj',statusObj);


				/*const storeNameError = document.getElementById(`storeNameError`);
				const nickNameError = document.getElementById(`nickNameError`);

				if(obj["error_description"] == "Store with name already exist. Please provide different name.") {
					storeNameError.textContent = `Store name already exist`;
				} else if(obj["error_description"] == "Store with nick name already exist. Please provide different nick name.") {
					nickNameError.textContent = `Store nick name already exist`;
				}*/
				isFormValid = false;
			}.bind(this));

		}
	}


	showFormErrors() {
		const inputs = document.querySelectorAll('#storepanel input');
		const selects = document.querySelectorAll('#storepanel select');

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

	showInputError(refName, type) {
		var isControlValid = true;
		const validity = this.refs[refName].validity;
		const label = document.getElementById(`${refName}Label`).textContent;
		const error = document.getElementById(`${refName}Error`);

		const isPhoneNumber = refName.indexOf('storePhone') !== -1;
		const isZipCodeNumber = refName.indexOf('zipCode') !== -1;

		error.textContent = '';

		if (!validity.valid) {
			isControlValid = false;

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

	successAlert () {
		this.refs.container.success(
			"",
			"Merchant created successfully.", {
				timeOut: 2000,
				extendedTimeOut: 1000
		});
	}

	errorAlert () {
		this.refs.container.error(
			"",
			"Something is wrong.", {
			timeOut: 2000,
			extendedTimeOut: 1000
		});
	}

	addStore() {
		this.setState({
			isOpen: true
		});
	}

	render() {
		return (
			<div id="storepanel">
				<div class="btn-padding">
					<div class="col-md-6 col-xs-6">
						<button type="button" class="btn btn-primary storebtn" onClick={this.addStore}>
							<i class="fa fa-plus"> Add Store</i>
						</button>
					</div>
				</div>
				<div>
					<ToastContainer ref="container"
							  toastMessageFactory={ToastMessageFactory}
							  className="toast-top-right" />
				</div>
				<Modal isOpen={this.state.isOpen}>
					<div class="modal-header">
						<h4 class="modal-title font-20">New Store Set-up</h4>
					</div>
					<form id="addStoreForm" method="post" onSubmit="" noValidate>
						<ModalBody>
							<FormGroup row>
								<Label id="storeNameLabel" class="form-label" for="storeName" sm={4}>Store Name </Label>
								<Col sm={7} class="col-padding">
									<input className="form-control"
											type="text"
											name="storeName"
											ref="storeName"
											value={ this.state.storeName }
											onChange={ this.inputChange }
											required />
									<div className="error" id="storeNameError" />
								</Col>
							</FormGroup>
							<FormGroup row>
								<Label id="nickNameLabel" class="form-label" for="nickName" sm={4}>Store Nickname</Label>
								<Col sm={7} class="col-padding">
									<input className="form-control"
											type="nickName"
											name="nickName"
											ref="nickName"
											value={this.state.nickName}
											onChange={ this.inputChange }
											required />
									<div className="error" id="nickNameError" />
								</Col>
							</FormGroup>
							<FormGroup row>
								<Label id="managerOrPOCLabel" class="form-label" for="managerOrPOC" sm={4}>Store Manager/POC</Label>
								<Col sm={7} class="col-padding">
									<input className="form-control"
											type="managerOrPOC"
											name="managerOrPOC"
											ref="managerOrPOC"
											value={ this.state.managerOrPOC }
											onChange={ this.inputChange }
											required />
									<div className="error" id="managerOrPOCError" />
								</Col>
							</FormGroup>
							<FormGroup row>
								<Label id="storePhoneLabel" class="form-label" for="storePhone" sm={4}>Store Phone number</Label>
								<Col sm={7} class="col-padding">
									<input className="form-control"
										    type="text"
											name="storePhone"
											ref="storePhone"
											value={ this.state.storePhone }
											onChange={ this.inputChange }
											pattern="[0-9]{6,10}"
											required />
									<div className="error" id="storePhoneError" />
								</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="countryLabel" class="form-label" for="country" sm={4}>Store Country</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="country"
											ref="country"
											value={ this.state.country }
											onChange={ this.inputChange }
											required />
				  					<div className="error" id="countryError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="cityLabel" class="form-label" for="city" sm={4}>Store City</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="city"
											ref="city"
											value={ this.state.city }
											onChange={ this.inputChange }
											required />
				  					<div className="error" id="cityError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="streetLabel" class="form-label" for="street" sm={4}>Store Street Address</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="street"
											ref="street"
											value={ this.state.street }
											onChange={ this.inputChange }
											required />
				  					<div className="error" id="streetError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="stateLabel" class="form-label" for="state" sm={4}>Store State</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="state"
											ref="state"
											value={ this.state.state }
											onChange={ this.inputChange }
											required />
				  					<div className="error" id="stateError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				 				<Label id="zipCodeLabel" class="form-label" for="zipCode" sm={4}>Store Zip Code</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="zipCode"
											ref="zipCode"
											value={ this.state.zipCode }
											onChange={ this.inputChange }
											pattern="[a-zA-Z0-9]{5}"
											required />
				  					<div className="error" id="zipCodeError" />
				 				</Col>
							</FormGroup>
				 			<FormGroup row>
				  				<Label id="controllerNumberLabel" class="form-label" for="controllerNumber" sm={4}>Controller Number</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="controllerNumber"
											ref="controllerNumber"
											value={ this.state.controllerNumber }
											onChange={ this.inputChange }
											required />
				  					<div className="error" id="controllerNumberError" />
				  				</Col>
							</FormGroup>
				 			<FormGroup row>
				  				<Label id="controllerPlacementLabel" class="form-label" for="controllerPlacement" sm={4}>Controller Placement</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="controllerPlacement"
											ref="controllerPlacement"
											value={ this.state.controllerPlacement }
											onChange={ this.inputChange }
											required />
				  					<div className="error" id="controllerPlacementError" />
				  				</Col>
							</FormGroup>
				 			<FormGroup row>
				  				<Label id="categoryLabel" class="form-label" for="category" sm={4}>Store Categroy</Label>
				   				<Col sm={7} class="col-padding">
									<select name="category"
											ref="category"
											value={ this.state.category }
											class="form-control"
											onChange={ this.inputChange } required>
										<option value=''>Select catagory</option>
										<option value='Fast-Casual Restaurants'>Fast-Casual Restaurants</option>
										<option value='Fast-Food Restaurants'>Fast-Food Restaurants</option>
										<option value='Fast-Service Restaurants'>Fast-Service Restaurants</option>
									</select>
				  					<div className="error" id="categoryError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="subCategoryLabel" class="form-label" for="subCategory" sm={4}>Store Subcategroy</Label>
				   				<Col sm={7} class="col-padding">
									<select name="subCategory"
											ref="subCategory"
											value={ this.state.subCategory }
											class="form-control"
											onChange={ this.inputChange } required>
										<option value=''>Select Subcategory</option>
										<option value='seafood'>seafood</option>
										<option value='Vegetarian'>Vegetarian</option>
									</select>
				  					<div className="error" id="subCategoryError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="priceCategoryLabel" class="form-label" for="priceCategory" sm={4}>Price Categroy</Label>
				   				<Col sm={7} class="col-padding">
									<select name="priceCategory"
											ref="priceCategory"
											value={ this.state.priceCategory }
											class="form-control" 
											onChange={ this.inputChange } required>
										<option value=''>Select price category</option>
										<option value='$'>$</option>
										<option value='$$'>$$</option>
										<option value='$$$'>$$$</option>
									</select>
				  					<div className="error" id="priceCategoryError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				 	 			<Label id="posSystemLabel" class="form-label" for="posSystem" sm={4}>POS System</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="posSystem"
											ref="posSystem"
											value={ this.state.posSystem }
											onChange={ this.inputChange } />
					 				<div className="error" id="posSystemError" />
				  				</Col>
							</FormGroup>
							<FormGroup row>
				  				<Label id="storecastAdminNameLabel" class="form-label" for="storecastAdminName" sm={4}>Storecast Admin Name</Label>
				   				<Col sm={7} class="col-padding">
					 				<input className="form-control"
											type="text"
											name="storecastAdminName"
											ref="storecastAdminName"
											value={ this.state.adminName}
											onChange={ this.inputChange } />
									<div className="error" id="storecastAdminNameError" />
				  				</Col>
							</FormGroup>
			 			</ModalBody>
			  			<div>
			  				<hr class="modal-footer-hr"/>
			  				<ModalFooter>
								<button type="submit" class="btn btn-blue" onClick={ this.handleSubmit }>
				  					Save New Store
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

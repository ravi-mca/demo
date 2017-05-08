import React from "react";
import  'react-bootstrap';
import $ from 'jquery';

import { Col, Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Validation, {Validator} from 'rc-form-validation';

import Config from "../index.config";
import Service from "./Service";
import AlertMessage from "./AlertMessage";

import {
  Modal,
  ModalHeader,
  ModalTitle,
  ModalClose,
  ModalBody,
  ModalFooter
} from 'react-modal-bootstrap';

export default class DeletePopUp extends React.Component {
    constructor(props) {
    super(props);

     this.state = {
        isOpen: false,
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  openModal = () => {
    this.setState({
        isOpen: true,
    });
  };

  hideModal = () => {
    this.setState({
        isOpen: false,
    });
  };

  handleSubmit(e) {
    e.preventDefault();
    var requestData = {
            url: this.props.data.APIUrl,
            type: 'DELETE',
            dataType: 'text',
            contentType: 'application/json'
      };

      var reqData = Service.buildRequestdata(requestData);

      Service.executeRequest(reqData, function(data) {

        this.props.onUpdate();
        this.refs.alertMessageChild.successAlert(this.props.data.successAlert);
        this.hideModal();
      }.bind(this), function(xhr, status, err) {
        this.refs.alertMessageChild.errorAlert("Something is wrong.");
      }.bind(this));
  }

  render() {
    return (
        <div id="deletePopUpPanel">
            <button type="button" class="btn info-btn btn-sm">
                <i class="fa fa-trash login-font pointer" onClick={this.openModal}></i>
            </button>
            <div>
                <AlertMessage ref="alertMessageChild"/>
            </div>
            <Modal isOpen={this.state.isOpen}>
                <div class="modal-header">
                    <h4 class="modal-title font-20">Delete {this.props.data.deleteName}</h4>
                </div>
                <ModalBody>
                    <div class="modal-padding">Do you want to delete this {this.props.data.deleteMessage} ?</div>
                </ModalBody>
                <div>
                    <hr class="modal-footer-hr"/>
                    <ModalFooter>
                        <button type="button" class="btn btn-blue" onClick={this.handleSubmit}>Yes</button>
                        <button type="button" className='btn btn-blue' onClick={this.hideModal}>Cancel</button>
                    </ModalFooter>
                </div>
            </Modal>
        </div>
    );
  }
}

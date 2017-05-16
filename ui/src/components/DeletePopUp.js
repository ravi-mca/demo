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
        this.refs.alertMessageChild.successAlert(this.props.data.successAlert);
        this.props.onUpdate();
        this.hideModal();
      }.bind(this), function(xhr, status, err) {
        if(xhr.status == 401) {
            /* set session as invalid */
            Service.setInvalidSession('invalidSession');
        } else {
            this.refs.alertMessageChild.errorAlert("Something is wrong.");
        }
      }.bind(this));
  }

  render() {
    var showdeleteBtn;
    if(this.props.setDeleteButton == 'true') {
        showdeleteBtn = (
            <button type="button" class="btn info-btn btn-sm" onClick={this.openModal}>
                <i class="fa fa-trash login-font pointer"></i>
            </button>
        );

    } else {
        showdeleteBtn = (
                <i class="fa fa-trash login-font pointer" onClick={this.openModal}></i>
        );
    }

    return (
        <div id="deletePopUpPanel">
            {showdeleteBtn}
            <div>
                <AlertMessage ref="alertMessageChild"/>
            </div>
            <Modal isOpen={this.state.isOpen}>
                <div class="modal-header">
                    <h4 class="modal-title font-20">Delete {this.props.data.deleteName}</h4>
                </div>
                <div>                  
                    <hr class="modal-header-hr"/>
                </div>
                <ModalBody>
                    <div class="modal-padding">Are you sure you want to delete this {this.props.data.deleteMessage} ?</div>
                </ModalBody>
                <div>
                    <ModalFooter>
                        <button type="button" class="btn btn-blue btn-delete" onClick={this.handleSubmit}>Yes</button>
                        <button type="button" className='btn btn-blue btn-delete' onClick={this.hideModal}>Cancel</button>
                    </ModalFooter>
                </div>
            </Modal>
        </div>
    );
  }
}

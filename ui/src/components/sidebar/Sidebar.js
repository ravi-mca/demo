import React from "react";
import  'react-bootstrap';
import Service from "../Service";
import Config from "../../index.config";

var listOfMerchants;

export default class Sidebar extends React.Component {
    constructor() {
        super();
        this.state = {
            selected :'',
            users: [
                {
                    "userId":"1",
                    "accountNo":"A012424",
                    "firstname":"Merchant1",
                    "lastname":"Merchant1",
                    "username":"merchant1@gmail.com",
                    "phone": "9898102525"
                },
                {
                    "userId":"2",
                    "accountNo":"A012467",
                    "firstname":"Merchant2",
                    "lastname":"Merchant2",
                    "username":"merchant2@gmail.com",
                    "phone": "9898202020"
                }
            ]
    };

    this.getListOfMerchant();
  }

    getListOfMerchant() {
        Service.getListOfMerchants(Config.getMerchantList, function(data) {
                   console.log('status',data);
        }.bind(this), function(xhr, status, err) {
            this.setState({ showResults: true });
                console.log('err',err);
        }.bind(this));
    }

    setFilter(filter) {
        this.setState({selected  : filter})
    }

    isActive(value) {
        return ((value===this.state.selected) ?'activeList':'list-nav');
    }

  render() {
    let showList =  this.state.users.map(function(user, i) {
        return (
            <li  key={i} className={this.isActive(user.firstname)} onClick={this.setFilter.bind(this, user.firstname)}><div class="list-padding">{user.firstname} </div></li>
        );
    }, this);

    return (
        <div class="nav-side-menu">
            <div class="input-group pad-15">
            <input type="text" class="form-control" placeholder="Search" />
        <span class="input-group-btn">
         <button class="btn btn-default" type="button">
           <span class="glyphicon glyphicon-search"></span>
         </button>
       </span>
     </div>
    <div class="menu-list">
        <ul id="menu-content">
           {showList}
        </ul>
    </div>
</div>
    );
  }
}

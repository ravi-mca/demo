var Reflux = require('reflux');
var ControlActions = require('./ControlActions.js');
var accessToken = {};

var Store = Reflux.createStore({

	init: function() {
		this.listenTo(ControlActions.login, this.login);
    	this.listenTo(ControlActions.logout, this.logout);
    	this.listenTo(ControlActions.getAccessToken, this.getAccessToken);
	},

 	logout: function() {
        accessToken = {};
        this.trigger(accessToken);
 	},

	getAccessToken: function() {
      	return accessToken;
 	},

 	login: function(token) {
        accessToken = token;
        this.trigger(accessToken);
 	}

});
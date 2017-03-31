var Reflux = require('reflux');

//create set of actions
var ControlActions = Reflux.createActions([
   'logout',
   'login',
   'getAccessToken'
]);

//export the module
module.exports = ControlActions;

import React from "react";
import  'react-bootstrap';

import Login from "./login/Login";

export default class Layout extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
      <div class="">
        <Login/>
      </div>
    );
  }
}

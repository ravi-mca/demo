import React from "react";
import  'react-bootstrap';

import Login from "./login/Login";
import Header from './header/Header';
import Footer from './footer/Footer';

export default class Layout extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
      <div>
          {this.props.children}
      </div>
    );
  }
}

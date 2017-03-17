import React from "react";
import  'react-bootstrap';
import Footer from './footer/Footer';

export default class Layout extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
      <div>
        {this.props.children}
        <Footer/>
      </div>
    );
  }
}

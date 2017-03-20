import React from "react";
import  'react-bootstrap';
import Header from './header/Header';
import Footer from './footer/Footer';

export default class Layout extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
      <div>
        <Header/>
            {this.props.children}
        <Footer/>
>>>>>>> .theirs
      </div>
    );
  }
}

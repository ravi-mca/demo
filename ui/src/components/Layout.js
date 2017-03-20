import React from "react";
import  'react-bootstrap';
<<<<<<< .mine
import Header from './header/Header';
=======
import Footer from './footer/Footer';
>>>>>>> .theirs

export default class Layout extends React.Component {
  constructor() {
    super();
  }
  render() {
    return (
<<<<<<< .mine
      <div>
        <Header/>
            {this.props.children}
=======
      <div>
        {this.props.children}
        <Footer/>
>>>>>>> .theirs
      </div>
    );
  }
}

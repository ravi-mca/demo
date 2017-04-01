import React from "react";
import 'react-bootstrap';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';

import Header from '../header/Header';
import Footer from '../footer/Footer';
import Sidebar from '../sidebar/Sidebar';

export default class Dashboard extends React.Component {
    constructor() {
        super();
        this.state = {
            userInfo:''
        };
        this.setSelectList = this.setSelectList.bind(this);
    }

    setSelectList(userInfo) {
        this.setState({
            userInfo: userInfo
        });
    }

    render() {
        const childrenWithProps = React.Children.map(this.props.children,
            (child) => React.cloneElement(child, {
                data: this.state.userInfo
           })
        );

        return (
            <div>
                <Header/>
                <Sidebar onSelectList={this.setSelectList} />
                    <div> {childrenWithProps} </div>
                <Footer/>
            </div>
        );
    }
}

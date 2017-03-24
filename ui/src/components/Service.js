import $ from 'jquery';

import Store from '../Store';
import ControlActions from '../ControlActions';


const Service = {

    login(url,data,successHandler, errorHandler) {

        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            ContentType: 'application/x-www-form-urlencoded',
            data: data,
            cache: false,
            success: successHandler,
            error:errorHandler
        });
    },

    setToken(token) {
        localStorage.setItem("accessToken", token);
    },

    getToken() {
        return localStorage.accessToken;
    },

    deleteToken() {
        localStorage.removeItem("accessToken");
    }

};

export default Service;
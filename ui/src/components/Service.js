import $ from 'jquery';

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
    },

    getListOfMerchants(url, successHandler, errorHandler) {
        $.ajax({
            type: 'GET',
            url: url,
            dataType: 'json',
            ContentType: 'application/json',
            headers: {
                "Authorization": 'Bearer'+ localStorage.accessToken
            },
            cache: false,
            success: successHandler,
            error:errorHandler
        });
    }

};

export default Service;

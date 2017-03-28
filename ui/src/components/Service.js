import $ from 'jquery';

const Service = {

    setToken(token) {
        localStorage.setItem("accessToken", token);
    },

    getToken() {
        return localStorage.accessToken;
    },

    deleteToken() {
        localStorage.removeItem("accessToken");
    },

    setAuthHeader(headers) {
        headers = headers || {};
        if(localStorage.accessToken) {
            headers.Authorization  = 'Bearer'+' '+ localStorage.accessToken;
        }
        return headers;
    },

    buildRequestdata(url, type, data, headers) {
        var reqData = {
            type: type,
            url: url,
            dataType: 'json',
            ContentType: 'application/json'
        };

        if(data) {
            reqData.data = data;
        }

        reqData.headers = this.setAuthHeader(headers);

        return reqData;
    },

    executeRequest(reqData, successHandler, errorHandler) {
        reqData.success = successHandler,
        reqData.error = errorHandler

        $.ajax(reqData);
    }
};

export default Service;

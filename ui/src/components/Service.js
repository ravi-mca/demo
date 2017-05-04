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

    buildRequestdata(reqInfo, headers) {

        var reqData = {
            type: reqInfo.type,
            url: reqInfo.url,
            dataType: reqInfo.dataType,
            contentType: reqInfo.contentType
        };

        if(reqInfo.data) {
            reqData.data = reqInfo.data;
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

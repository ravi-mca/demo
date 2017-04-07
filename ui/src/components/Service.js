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

    createMerchant(url, data, successHandler, errorHandler) {

        delete data.isOpen;
        delete data.showResults;

        $.ajax({
            type: 'POST',
            url: url,
            dataType : "text",
            contentType: "application/json",
            data: JSON.stringify(data),
            headers: {
                'Authorization': 'Bearer '+localStorage.accessToken,
            },
            success: successHandler,
            error: errorHandler,
        });
    },

    editMerchant(url, data, successHandler, errorHandler) {

        delete data.isOpen;
        delete data.showResults;

        $.ajax({
            type: 'PUT',
            url: url,
            dataType : "text",
            contentType: "application/json",
            data: JSON.stringify(data),
            headers: {
                'Authorization': 'Bearer '+localStorage.accessToken,
            },
            success: successHandler,
            error: errorHandler,
        });
    },

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

    buildRequestdata(url, type, data, headers) {
        var reqData = {
            type: type,
            url: url,
            dataType: 'json',
            contentType: 'application/json'
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

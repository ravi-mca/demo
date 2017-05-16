import $ from 'jquery';
import { Router, Route, Link, browserHistory, IndexRoute  } from 'react-router';


const Service = {

    setToken(token) {
        localStorage.setItem("accessToken", JSON.stringify(token));
    },

    getToken() {
        return localStorage.accessToken;
    },

    setInvalidSession(sessionInfo) {
          localStorage.setItem("sessionInfo",sessionInfo);
          browserHistory.push('/');
    },

    getSessionInfo() {
        return localStorage.sessionInfo;
    },

    deleteInvalidSession() {
        localStorage.removeItem("sessionInfo");
    },

    deleteToken() {
        localStorage.removeItem("accessToken");
    },

    setAuthHeader(headers) {
        headers = headers || {};

        if(localStorage.accessToken) {
            var tokenObject = JSON.parse(localStorage.accessToken);
            headers.Authorization  = 'Bearer'+' '+ tokenObject.accessToken;
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

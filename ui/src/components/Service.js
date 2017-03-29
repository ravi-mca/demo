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

    createMerchant(url, data, successHandler, errorHandler) {
        
        $.ajax({
            type: 'POST',
            url: url,
            dataType: 'json',
            contentType: "application/json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+localStorage.accessToken,
            },
            data: JSON.stringify(data),
            cache: false,
            success: successHandler,
            error: errorHandler,
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
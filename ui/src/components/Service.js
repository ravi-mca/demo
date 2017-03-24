import $ from 'jquery';
var newToken;

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
            error:errorHandler,
        });
    },

    createMerchant(url, data, successHandler, errorHandler) {

        console.log(data);
        console.log(localStorage.accessToken);
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

    setVariable(tokn) {
       newToken = tokn;
       console.log('newToken',newToken);
   },
};

export default Service;
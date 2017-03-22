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
    }
};

export default Service;
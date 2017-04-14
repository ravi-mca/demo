var BaseUrl = 'http://localhost:8080';

const Config = {
	login : BaseUrl +'/storecast/api/admin/login',
    merchantAPIPath : BaseUrl +'/storecast/api/admin/merchant',   
    storeAPIPath:  BaseUrl + '/storecast/api/admin/store/',
	getStoresInfo: BaseUrl + '/storecast/api/admin/store/list/',
	deleteAPIPath: BaseUrl + '/storecast/api/admin/merchant/',
	successAlert : {
		deleteMerchant : "Merchant deleted successfully."
	}

};

export default Config;

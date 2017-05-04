var BaseUrl = 'http://localhost:8080';

const Config = {
	login : BaseUrl +'/storecast/api/login',
    merchantAPIPath : BaseUrl +'/storecast/api/customer/merchant',   
    storeAPIPath:  BaseUrl + '/storecast/api/customer/store/',
	getStoresInfo: BaseUrl + '/storecast/api/customer/store/list/',
	deleteAPIPath: BaseUrl + '/storecast/api/customer/merchant/',
	adminAPIPath : BaseUrl +'/storecast/api/customer/merchant/credentials',
	customerAPIPath : BaseUrl +'/storecast/api/admin/customer',
	successAlert : {
		deleteMerchant : "Merchant deleted successfully.",
		deleteStore : "Store deleted successfully."
	}
};

export default Config;

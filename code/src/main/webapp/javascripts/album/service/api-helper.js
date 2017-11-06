angular.module('album').factory('ApiHelper', function($http, consts) {

	var call0 = function(url, method, params, headers) {
		url = url || consts.baseUrl + 'api.do';
		method = method || 'POST';
		params = params || {};
		headers = headers || {};
		if (!headers['Content-Type']) {
			headers['Content-Type'] = 'application/x-www-form-urlencoded';
		}
		var defer = $q.defer();
		$http({
			method : method,
			url : url,
			params : params,
			headers : headers,
			transformRequest : function transform(data, headersGetter) {
			},
			transformResponse : function transform(data, headersGetter) {
			},
			cache : false,
			timeout : 2000
		}).success(function(data, status, headers, config) {
			if (data.code == 0) {
				defer.resolve(data);
			} else {
				defer.reject(data);
			}
		}).error(function(data, status, headers, config) {
			defer.reject(data);
		});
		return defer.promise;
	}

	return {
		call : call0
	};

});
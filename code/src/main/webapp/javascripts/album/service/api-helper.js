angular.module('album').factory('ApiHelper', function($q, $http, AppInfo, CommonSvc) {

    /**
    	总入口方法
    */
    var call0 = function(url, method, params, headers) {
        url = url || AppInfo.baseUrl + 'api.do';
        method = method || 'POST';
        params = params || {};
        headers = headers || {};
        if (!headers['Content-Type']) {
            headers['Content-Type'] = 'application/x-www-form-urlencoded';
        }
        var defer = $q.defer();
        $http({
            method: method,
            url: url,
            params: params,
            headers: headers,
            cache: false,
            timeout: 2000
        }).success(function(data, status, headers, config) {
            defer.resolve(data);
        }).error(function(data, status, headers, config) {
            defer.reject(data);
        });
        return defer.promise;
    }

    /**
		post
    */
    var post0 = function(apiKey, params) {
        var defer = $q.defer();
        params.apiKey = apiKey;
        call0(null, 'POST', params, {}).then(function(data) {
            if (data.code == 0) {
                defer.resolve(data);
            } else if (!angular.isUndefined(data.code)) {
                CommonSvc.alert('Error', data.msg).then(function() {
                    defer.reject(data);
                });
            }
        }, function(e) {
            defer.reject(e);
        });
        return defer.promise;
    }

    return {
        call: call0,
        post: post0
    };

});
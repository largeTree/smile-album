angular.module('album').factory('ApiHelper', function($rootScope, $q, $http, AppConfig, CommonSvc) {

    /**
        总入口方法
    */
    var call0 = function(url, method, params, headers) {
        url = url || AppConfig.baseUrl + 'api.do';
        method = method || 'POST';
        params = params || {};
        headers = headers || {};
        if (!headers['Content-Type']) {
            headers['Content-Type'] = 'application/x-www-form-urlencoded';
        }

        if ($rootScope.session) {
            params.sessionId = $rootScope.session.sessionId;
        }

        var defer = $q.defer();
        $http({
            method: method,
            url: url,
            params: params,
            headers: headers,
            cache: false,
            timeout: 20000
        }).success(function(data, status, headers, config) {
            defer.resolve(data);
        }).error(function(data, status, headers, config) {
            if (data == null) {
                data = {
                    code: -1,
                    msg: '请求超时'
                };
            }
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

    /**
     * 查询列表
     */
    var queryList0 = function(apiKey, params, pageNo, pageSize) {
        var defer = $q.defer();
        params.pageNo = pageNo || 0;
        params.pageSize = pageSize || AppConfig.defPageSize;
        post0(apiKey, params).then(function(data) {
            defer.resolve(data.rows);
        }, function(e) {
            CommonSvc.alert('Error', e.msg).then(function() {
                defer.reject(e);
            });
        });
        return defer.promise;
    }

    /**
     * 保存表单
     */
    var saveForm0 = function(apiKey, params, jsonParam) {
        var defer = $q.defer();
        params.jsonParam = jsonParam || {};
        post0(apiKey, params).then(function(data) {
            CommonSvc.msg('Info', data.msg);
            defer.resolve(data);
        }, function(e) {
            CommonSvc.alert('Error', e.msg).then(function() {
                defer.reject(e);
            });
        });
        return defer.promise;
    }

    return {
        call: call0,
        post: post0,
        queryList: queryList0
    };

});
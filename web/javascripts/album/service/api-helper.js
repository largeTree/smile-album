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

        var defer = $q.defer();
        $http({
            method: method,
            url: url,
            params: params,
            headers: headers,
            cache: false,
            timeout: 20000
        }).success(function(data, status, headers, config) {
            $rootScope.loading = false;
            defer.resolve(data);
        }).error(function(data, status, headers, config) {
            $rootScope.loading = false;
            if (data == null) {
                CommonSvc.alert('Error', '请求超时').then(function() {
                    defer.reject({
                        code: -1,
                        msg: '请求超时'
                    });
                });
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
                defer.reject(data);
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
            defer.resolve(data.data);
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

    /**
     * 根据主键获取bean
     */
    var queryForm0 = function(apiKey, pk, wrapper) {
        var defer = $q.defer();
        post0(apiKey, { id: pk, wrapper: wrapper }).then(function(data) {
            defer.resolve(data.data);
        }, function(e) {
            CommonSvc.alert('Error', e.msg).then(function() {
                defer.reject(e);
            });
        });
        return defer.promise;
    }

    var sendFormData0 = function(url, method, formData, headers) {
        var defer = $q.defer();
        $http({
            method: method,
            url: url,
            data: formData,
            headers: headers,
            cache: false,
            timeout: 20000
        }).success(function(data, status, headers, config) {
            defer.resolve(data);
        }).error(function(data, status, headers, config) {
            $rootScope.loading = false;
            if (data == null) {
                CommonSvc.alert('Error', '请求超时').then(function() {
                    defer.reject({
                        code: -1,
                        msg: '请求超时'
                    });
                });
            }
            defer.reject(data);
        });
        return defer.promise;
    }

    return {
        call: call0,
        post: post0,
        queryList: queryList0,
        saveForm: saveForm0,
        queryForm: queryForm0,
        sendFormData: sendFormData0
    };

});
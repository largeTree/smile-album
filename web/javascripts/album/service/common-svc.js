angular.module('album').factory('CommonSvc', function($rootScope, $q, $modal, $http) {

    var openSimpleFormModel0 = function(config) {
        var deferred = $q.defer();
        var simpleForm = $modal.open({
            templateUrl: 'tpls/common/simple-form.html',
            controller: 'SimpleFormController',
            size: 'lg',
            backdrop: false,
            keyboard: false,
            resolve: {
                config: function() {
                    return config;
                }
            }
        });
        simpleForm.result.then(function(data) {
            deferred.resolve(data);
        }, function(e) {
            deferred.reject(e);
        });
        return deferred.promise;
    }

    /**
     * 验证Jcaptche
     */
    var checkJcaptcheCode0 = function(code) {
        var deferred = $q.defer();
        $http({
            method: 'POST',
            url: $rootScope.baseUrl + '/jcaptcha/check',
            params: {
                captcha: code
            },
            headers: {
                'Content-type': 'application/x-www-form-urlencoded'
            }
        }).success(function(data, status, headers, config) {
            if (status == 200) {
                deferred.resolve(data);
            }
        });
        return deferred.promise;
    }

    /**
     * 显示一个带有确认和取消按钮的弹框
     */
    var confirm0 = function(text) {
        var deferred = $q.defer();
        var confirmModal = $modal.open({
            templateUrl: 'tpls/common/modals/confirm-modal.html',
            size: 'sm',
            backdrop: false,
            controller: 'ConfirmController',
            resolve: {
                text: function() {
                    return text;
                }
            }
        });
        confirmModal.result.then(function(action) {
            deferred.resolve(action == '1');
        });
        return deferred.promise;
    }

    /**
     * alert
     */
    var alert0 = function(title, text, btn) {
        var deferred = $q.defer();
        var confirmModal = $modal.open({
            templateUrl: 'tpls/common/modals/alert-modal.html',
            size: 'sm',
            backdrop: false,
            controller: 'AlertController',
            resolve: {
                options: function() {
                    return {
                        title: title,
                        text: text,
                        btnlabel: btn
                    };
                }
            }
        });
        confirmModal.result.then(function() {
            deferred.resolve();
        });
        return deferred.promise;
    }

    /**
     * 显示一个自动消失的消息框
     */
    var msg0 = function(title, text, delay) {
        var deferred = $q.defer();
        var confirmModal = $modal.open({
            templateUrl: 'tpls/common/modals/alert-modal.html',
            size: 'sm',
            backdrop: false,
            controller: 'MessageController',
            resolve: {
                options: function() {
                    return {
                        title: title,
                        text: text,
                        delay: delay
                    };
                }
            }
        });
        confirmModal.result.then(function() {
            deferred.resolve();
        });
        return deferred.promise;
    }

    /**
     * 设置分页数据 是指用来画分页工具栏的数据
     */
    var setPagination0 = function($scope) {
        $scope.totalPage = Math.ceil($scope.total / $scope.pageSize);
        $scope.PaginationData = new Array();

    }

    return {
        confirm: confirm0,
        alert: alert0,
        msg: msg0,
        setPagination: setPagination0,
        checkJcaptcheCode: checkJcaptcheCode0,
        openSimpleFormModel: openSimpleFormModel0
    };
});
angular.module('album').controller('RegisterController', function($rootScope, $scope, $location, CommonSvc, ApiKeyConst, ApiHelper, AppConfig) {
    // 设置页面主样式
    $rootScope.pageStyle = 'album-register-bg-color';
    $scope.duplicateLoginId = false;
    $scope.user = {};
    $scope.time = new Date().getTime();

    $scope.reGetJcaptcha = function() {
        // 获取新的验证码
        $scope.time = new Date().getTime();
    }

    /**
     * 检查
     */
    $scope.checkExists = function() {
        if ($scope.user.loginId) {
            ApiHelper.post(ApiKeyConst.checkUserExists, { loginId: $scope.user.loginId }).then(function(data) {
                $scope.duplicateLoginId = data.data.val;
            });
        }
    }

    $scope.doRegister = function() {
        CommonSvc.checkJcaptcheCode($scope.user.jcaptchaCode).then(
            function(result) {
                if (result) {
                    ApiHelper.post(ApiKeyConst.createUser, { jsonParam: JSON.stringify($scope.user) }).then(function() {
                        CommonSvc.msg('Info', '注册成功').then(function() {
                            $location.path('/');
                        });
                    });
                } else {
                    $scope.reGetJcaptcha();
                    $scope.captchaErr = true;
                }
            });
    }
});
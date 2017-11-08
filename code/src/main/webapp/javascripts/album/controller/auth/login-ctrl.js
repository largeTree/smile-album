angular.module('album').controller('LoginController', function($rootScope, $scope, $location, $cookies, CommonSvc, ApiKeyConst, ApiHelper) {
    $scope.loginError = '';

    $scope.RemberMe = $cookies.get('lq_login_RemberMe') == 'true';

    $scope.login = function() {
        ApiHelper.post(ApiKeyConst.login, { jsonParam: $scope.jsonParam }).then(function(data) {
        	
        }, function(e) {

        });
    }

});
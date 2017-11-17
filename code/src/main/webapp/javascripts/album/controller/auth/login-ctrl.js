angular.module('album').controller('LoginController', function($rootScope, $scope, $location, $cookies, CommonSvc, ApiKeyConst, ApiHelper) {
    $scope.loginError = '';
    
    $scope.RemberMe = $cookies.get('lq_login_RemberMe') == 'true';

    $scope.jsonParam ={
    		loginId:$cookies.get('lq_login_loginId'),
    		pass:$cookies.get('lq_login_pass')
    };
    
    $scope.login = function() {
        ApiHelper.post(ApiKeyConst.login, { jsonParam: $scope.jsonParam }).then(function(data) {
    		$rootScope.session = data.data;
    		$rootScope.isOnline = true;
    		setCookies();
    		CommonSvc.msg('info','登录成功',500).then(function() {
        		$location.path('/');
    		});
        }, function(e) {
        	if(e.code && e.code == -3) {
        		$scope.loginError = e.msg;
        	} else {
        		console.log(e);
        	}
        });
    }

    function setCookies() {
    	var expireDate = new Date();  
		expireDate.setTime(expireDate.getTime() + 30 * 60 * 1000);              
		$cookies.put('lq_user_session', JSON.stringify($rootScope.session), {'expires': expireDate.toUTCString()});
    	if($scope.RemberMe) {
    		$cookies.put('lq_login_RemberMe',$scope.RemberMe);
    		$cookies.put('lq_login_loginId',$scope.jsonParam.loginId);
    		$cookies.put('lq_login_pass',$scope.jsonParam.pass);
    	}
    }
    
});
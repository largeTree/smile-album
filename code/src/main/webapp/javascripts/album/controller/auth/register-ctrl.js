angular.module('album').controller(
		'RegisterController',
		function($rootScope, $scope, CommonSvc, ApiHelper,consts) {
			// 设置页面主样式
			$rootScope.pageStyle = 'album-register-bg-color';
			$scope.user = {};
			$scope.time = new Date().getTime();

			$scope.reGetJcaptcha = function() {
				// 获取新的验证码
				$scope.time = new Date().getTime();
			}

			$scope.doRegister = function() {
				CommonSvc.checkJcaptcheCode($scope.user.jcaptchaCode).then(
						function(result) {
							if (result) {
								ApiHelper.call(null, 'post', {
									jsonParam : JSON.stringify($scope.user)
								}).then(function(data) {
									alert(data);
								}, function(e) {
									alert(e);
								});
							} else {
								$scope.reGetJcaptcha();
								$scope.captchaErr = true;
							}
						});
			}
		});

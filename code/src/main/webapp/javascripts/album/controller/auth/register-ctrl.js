angular.module('album').controller('RegisterController',
		function($rootScope, $scope, CommonSvc) {
			// 设置页面主样式
			$rootScope.pageStyle = 'album-register-bg-color';
			$scope.user = {};
			$scope.time = new Date().getTime();

			$scope.reGetJcaptcha = function() {
				// 获取新的验证码
				$scope.time = new Date().getTime();
			}
			
			$scope.doRegister = function() {
				CommonSvc.checkJcaptcheCode($scope.user.jcaptchaCode).then(function(result){
					if(result) {
						
					} else {
						$scope.reGetJcaptcha();
						$scope.captchaErr = true;
					}
				});
			}
		});

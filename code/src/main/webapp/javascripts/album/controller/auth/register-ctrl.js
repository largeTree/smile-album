angular.module('album').controller('RegisterController',
		function($rootScope, $scope, CommonSvc) {
			// 设置页面主样式
			$rootScope.pageStyle = 'album-register-bg-color';
			
			$scope.user = {};
			$scope.time = new Date().getTime();
			$scope.errMsg = {
				userCode : '此帐号已被占用',
				userPassword : '密码最少6位',
				userPasswordCk : '与输入的密码不一致',
				userEmail : '邮箱格式不正确',
				userJcaptchaCode : '验证码错误'
			};
			$scope.fieldValided = {
				userCode : true,
				userPassword : true,
				userPasswordCk : true,
				userEmail : true,
				userJcaptchaCode : true
			};

			$scope.invalided = function(field) {
				return !$scope.fieldValided[field];
			}
			
			/**
			 * 验证用户名是否重复
			 */
			$scpoe.checkDuplicateCode = function(){
				
			}
			
			$scope.reGetJcaptcha = function() {
				// 获取新的验证码
				$scope.time = new Date().getTime();
			}

			$scope.doRegister = function() {
				if(!$scope.user.jcaptchaCode || $scope.user.jcaptchaCode.length == 0) {
					$scope.errMsg.userJcaptchaCode = '请输入验证码';
					$scope.fieldValided.userJcaptchaCode = false;
					return;
				}
				CommonSvc.checkJcaptcheCode($scope.user.jcaptchaCode).then(function(result){
					if(!result) {
						$scope.errMsg.userJcaptchaCode = '验证码错误';
						$scope.fieldValided.userJcaptchaCode = false;
						$scope.reGetJcaptcha();
					} else {
						$scope.fieldValided.userJcaptchaCode = true;
					}
				});
			}
			
			function checkInput() {
				
			}
			
		});

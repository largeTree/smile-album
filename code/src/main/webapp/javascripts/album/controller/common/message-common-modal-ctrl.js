angular.module('album').controller('ConfirmController',
		function($scope, $modalInstance, text) {
			$scope.text = text;
			$scope.done = function(action) {
				$modalInstance.close(action);
			}
		});

angular.module('album').controller('AlertController',
		function($scope, $modalInstance, options) {
			$scope.options = options;
			$scope.done = function() {
				$modalInstance.close();
			}
		});

angular.module('album').controller('MessageController',
		function($scope, $modalInstance, $timeout, options) {
			options.hiddenBtn = true;
			$scope.options = options;
			$scope.done = function() {
				$modalInstance.close();
			}
			if (!options.delay) {
				options.delay = 1000;
			}
			$timeout(function() {
				$modalInstance.close();
			}, options.delay);
		});
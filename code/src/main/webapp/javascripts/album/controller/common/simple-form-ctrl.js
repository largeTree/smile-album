angular.module('album').controller('SimpleFormController', function($scope, $modalInstance, ApiHelper, config) {
    $scope.config = config;
    $scope.jsonParam = {};

    $scope.submit = function() {
        ApiHelper.saveForm(config.apiKey, {}, $scope.jsonParam).then(function(data){
        	if(data.code == 0) {
        		$modalInstance.close('ok');
        	}
        });
    }

    $scope.close = function() {
        $modalInstance.close();
    }
});
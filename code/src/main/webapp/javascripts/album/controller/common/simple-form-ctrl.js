angular.module('album').controller('SimpleFormController', function($scope, $modalInstance, config, checkFunc) {
    $scope.config = config;
    $scope.jsonParam = {};

    $scope.submit = function() {
        console.info($scope.jsonParam);
    }

    $scope.close = function() {
        $modalInstance.close();
    }
});
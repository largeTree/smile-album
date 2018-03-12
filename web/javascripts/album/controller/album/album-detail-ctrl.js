angular.module('album').controller('AlbumDetailController', function($scope, $location, ApiHelper, ApiKeyConst) {
    var searchParams = $location.search();

    ApiHelper.queryForm(ApiKeyConst.getAlbumById, searchParams.pk, true).then(function(data) {
    	$scope.album = data;
    });

    $scope.waitUploadFiles = new Array();

    $scope.doUpload = function(){
    	var file = document.getElementById('file_asset').files[0];
    	$scope.waitUploadFiles.push(file);
    }

});
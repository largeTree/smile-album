angular.module('album').controller('AlbumDetailController', function($scope, $location, ApiHelper, ApiKeyConst) {
    var searchParams = $location.search();

    ApiHelper.queryForm(ApiKeyConst.getAlbumById, searchParams.pk, true).then(function(data) {
    	$scope.album = data;
    });

    

});
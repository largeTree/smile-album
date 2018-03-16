angular.module('album').controller('AlbumDetailController', function($scope, $location, $modal, ApiHelper, ApiKeyConst) {
    var searchParams = $location.search();

    ApiHelper.queryForm(ApiKeyConst.getAlbumById, searchParams.pk, true).then(function(data) {
        $scope.album = data;
    });

    $scope.showUploadModal = function() {
        var uploadModal = $modal.open({
            templateUrl: 'tpls/album/upload-modal-tpl.html',
            controller: 'AlbumUploadModalController',
            size: 'lg',
            backdrop: false,
            keyboard: false,
            resolve: {
                album: function() {
                    return {
                        id: searchParams.pk,
                        name: $scope.album.name,
                        desc: $scope.album.desc
                    };
                }
            }
        });
        uploadModal.result.then(function(data) {
            console.log(data);
        }, function(e) {
            console.log(e);
        });
    }

});
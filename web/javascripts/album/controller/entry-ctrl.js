angular.module('album').controller('EntryController', function($rootScope, $scope, ApiHelper, ApiKeyConst, AppConfig, CommonSvc, AlbumCommSvc) {
    // 设置页面主样式
    $rootScope.pageStyle = 'album-main-bg-color';

    var loadAblums = function(pageNo) {
        ApiHelper.queryList(ApiKeyConst.publicAlbum, {}).then(function(data) {
            $scope.albums = AlbumCommSvc.transfAlbumsData(data.rows);
        });
    }

    loadAblums(1);
});
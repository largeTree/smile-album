angular.module('album').controller('MyAlbumsController', function($rootScope, $scope, $location, CommonSvc, AlbumCommSvc, ApiHelper, ApiKeyConst) {
    //  $rootScope.pageStyle = 'album-register-bg-color';
    $rootScope.loading = true;
    if (!$rootScope.isOnline) {
        CommonSvc.msg('Wran', '请先登录!').then(function() {});
        $location.path('/login');
        $rootScope.loading = false;
        return;
    }

    $scope.albums = new Array();

    var loadMyAlbums = function(pageNo) {
        ApiHelper.queryList(ApiKeyConst.privateAlbum, { pageNo: pageNo }).then(function(data) {
            console.log(data);
            $scope.albums = AlbumCommSvc.transfAlbumsData(data.rows);
            $rootScope.loading = false;
        });
    }

    loadMyAlbums(1);

    $scope.showAddAlbumModal = function() {
        CommonSvc.openSimpleFormModel({
            apiKey: ApiKeyConst.createAlbum,
            title: '新增相册',
            fields: [{
                label: '相册名称',
                type: 'text',
                required: true,
                fieldName: 'name',
                placeholder: '输入相册名称'
            }, {
                label: '仅自己可见',
                type: 'checkbox',
                fieldName: 'onlySelf',
            }, {
                label: '相册描述',
                type: 'text',
                fieldName: 'desc'
            }]
        }).then(function(res) {
            if (res == 'ok') {
                loadMyAlbums(1);
            }
        });
    }

});
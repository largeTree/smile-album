angular.module('album').controller('MyAlbumsController', function($rootScope, $scope, $location, CommonSvc, ApiHelper, ApiKeyConst) {
    //  $rootScope.pageStyle = 'album-register-bg-color';
    $rootScope.loading = true;
    if (!$rootScope.isOnline) {
        CommonSvc.msg('Wran', '请先登录!', 500).then(function() {});
        $location.path('/login');
        $rootScope.loading = false;
        return;
    }

    $scope.albums = new Array();

    var loadMyAlbums = function(pageNo) {
        ApiHelper.post(ApiKeyConst.privateAlbum, {}).then(function(data) {
            console.log(data);
            var list = data.data.rows;
            var row = new Array();
            // 数据整理  每四个相册放一行
            for (var i = 0; i < list.length; i++) {
                row.push(list[i]);
                if (row.length == 4) {
                    $scope.albums.push(row);
                    row = new Array();
                }
            }
            $rootScope.loading = false;
        }, function(e) {
            console.log(e);
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
                fieldName: 'oblySelf',
            }, {
                label: '相册描述',
                type: 'text',
                fieldName: 'desc'
            }]
        });
    }

});
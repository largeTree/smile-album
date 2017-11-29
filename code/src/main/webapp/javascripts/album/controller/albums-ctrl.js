angular.module('album').controller('MyAlbumsController', function($rootScope, $scope, $location, CommonSvc) {
    //	$rootScope.pageStyle = 'album-register-bg-color';
    $rootScope.loading = true;
    if (!$rootScope.isOnline) {
        CommonSvc.msg('Wran', '请先登录!', 500).then(function() {});
        $location.path('/login');
        $rootScope.loading = false;
        return;
    }

    $scope.albums = [
        [{
            title: '相册1',
            desc: '这是第一个相册',
            cover: 'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
        }],
        [{
            title: '相册1',
            desc: '这是第一个相册',
            cover: 'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
        }],
    ];
});
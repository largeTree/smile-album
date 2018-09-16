angular.module('album').controller('AlbumUploadModalController', function($rootScope, $scope, $modalInstance, album, CommonSvc, ApiHelper, ApiKeyConst, AppConfig) {
    $scope.album = album;

    $scope.waitUploadFiles = new Array();
    $scope.waitUploadImgs = new Array();
    $scope.removeBtnSatus = new Array();
    $scope.uploadProgress = new Array();

    $scope.doUpload = function() {
        if (!hasImage()) {
            CommonSvc.msg('提示', '请先点击下方按钮选择图片');
            return;
        }
        angular.forEach($scope.waitUploadFiles, function(item, index) {
            var albumId = $scope.album.id;
            ApiHelper.post(ApiKeyConst.genAuthorization, { albumId: albumId }).then(function(data) {
                data = data.data;
                var formData = new FormData(document.getElementById('file_asset_form'));
                formData.append('policy', data.policy);
                formData.append('authorization', data.authorization);
                formData.append('file', item);

                var startTime = 0;
                var xhr = new XMLHttpRequest();
                xhr.onload = function(evt) {
                    // 请求完成方法
                    var res = JSON.parse(evt.target.responseText);
                }
                xhr.upload.onprogress = function(evt) {
                    // 进度方法
                    updateProgress(index, evt.total, evt.loaded);
                }
                xhr.upload.onloadstart = function(evt) {

                }
                xhr.open('POST', 'http://v0.api.upyun.com/qiuxs-test', true);
                startTime = new Date().getTime();
                xhr.send(formData);
            });
        });
    }

    $scope.addFileToWait = function(file) {
        // var file = input.files[0];
        $scope.waitUploadFiles.push(file);
        var index = $scope.waitUploadFiles.length - 1;
        appendImg(file, index);
        initProgress(index);
    }

    /**
     * 读取一个图片
     */
    var appendImg = function(file, index) {
        var reader = new FileReader();
        reader.onload = function() {
            $scope.$apply(function() {
                $scope.waitUploadImgs[index] = { src: reader.result };
            });
        }
        reader.readAsDataURL(file);
    }

    /**
     * 添加一个进度条
     */
    var initProgress = function(index) {
        $scope.uploadProgress[index] = {
            started: false, // 是否开始
            progress: 0 // 进度
            // speed: 0, // 速度
            // speedUnit: 'b/s', // 速度单位
            // surplus: 0 // 剩余时间
        };
    }

    /**
     * 更新进度
     */
    var updateProgress = function(index, total, loaded) {
        var progress = $scope.uploadProgress[index];
        progress.started = true;
        progress.progress = Math.round((loaded / total) * 100, 2);
        // console.log('progress.progress' + progress.progress);
        $scope.$apply();
    }

    $scope.removeImg = function(index) {
        $scope.waitUploadImgs.splice(index, 1);
        $scope.waitUploadFiles.splice(index, 1);
        $scope.removeBtnSatus.splice(index, 1);
    }

    $scope.close = function() {
        if (hasImage()) {
            CommonSvc.confirm('还有未上传的图片，确定关闭吗？').then(function(res) {
                if (res) {
                    $modalInstance.close();
                }
            });
        } else {
            $modalInstance.close();
        }
    }

    $scope.showRemoveBtn = function(index) {
        $scope.removeBtnSatus[index] = true;
    }

    $scope.hideRemoveBtn = function(index) {
        $scope.removeBtnSatus[index] = false;
    }

    var hasImage = function() {
        return $scope.waitUploadFiles.length > 0;
    }

});
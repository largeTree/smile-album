angular.module('album').controller('AlbumUploadModalController', function($rootScope, $scope, $modalInstance, album, CommonSvc, ApiHelper, ApiKeyConst, AppConfig) {
    $scope.album = album;

    $scope.waitUploadFiles = new Array();
    $scope.waitUploadImgs = new Array();
    $scope.removeBtnSatus = new Array();

    $scope.doUpload = function() {
        if (!hasImage()) {
            CommonSvc.msg('提示', '请先点击下方按钮选择图片');
            return;
        }
        angular.forEach($scope.waitUploadFiles, function(item) {
            ApiHelper.post(ApiKeyConst.genAuthorization, { albumId: $scope.album.id }).then(function(data) {
                data = data.data;
                var formData = new FormData(document.getElementById('file_asset_form'));
                formData.append('policy',data.policy);
                formData.append('authorization', data.authorization);
                formData.append('file', item);
                // var headers = {
                //     'Content-Type': 'multipart/form-data'
                // };
                // ApiHelper.sendFormData(AppConfig.UpYunDomain + '/' + data.bucket, 'post', formData, headers).then(function(data) {
                //     console.log('上传成功');
                //     console.log(data);
                // }, function(e) {
                //     console.log('上传失败');
                //     console.log(e);
                // });
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                	console.log(xhr.responseText);
                }
                xhr.onload = function(evt) {
                	// 请求完成方法
                }
                xhr.upload.onprogress = function(evt){
                	// 进度方法
                	evt.total;
                	evt.loaded
                }
                xhr.upload.onloadstart = function(evt){

                }
                xhr.open('POST','http://v0.api.upyun.com/qiuxs-test',true);
                // xhr.setRequestHeader('Content-Type','multipart/form-data');
                xhr.send(formData);
            });
        });
    }

    $scope.addFileToWait = function(input) {
        var file = input.files[0];
        $scope.waitUploadFiles.push(file);
        appendImg(file, $scope.waitUploadFiles.length - 1);
    }

    $scope.openFileSelect = function() {
        document.getElementById('file_asset').click();
    }

    /**
     * 读取一个图片
     */
    var appendImg = function(file, index) {
        var reader = new FileReader();
        reader.onload = function() {
            $scope.waitUploadImgs[index] = { src: reader.result };
            $scope.$apply();
        }
        reader.readAsDataURL(file);
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
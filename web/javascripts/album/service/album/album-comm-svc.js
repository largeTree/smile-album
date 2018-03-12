angular.module('album').factory('AlbumCommSvc', function() {

    var transfAlbumsData0 = function(origAlbums) {
        var transfedAlbums = new Array();
        var row = new Array();
        // 数据整理  每四个相册放一行
        for (var i = 0; i < origAlbums.length; i++) {
            row.push(origAlbums[i]);
            if (row.length == 4) {
                transfedAlbums.push(row);
                row = new Array();
            }
        }
        if (row.length > 0) {
            transfedAlbums.push(row);
        }
        return transfedAlbums;
    }

    return {
        transfAlbumsData: transfAlbumsData0
    }

});
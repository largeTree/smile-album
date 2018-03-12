angular.module("album").directive("smAlbumContainer", function() { 
    return { 
        restrict: 'E',
        templateUrl : 'javascripts/album/directives/album-container/album-container-tpl.html'
    };
})
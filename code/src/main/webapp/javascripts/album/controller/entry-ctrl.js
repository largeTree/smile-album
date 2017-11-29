angular.module('album').controller('EntryController', function($rootScope,$scope) {
	// 设置页面主样式
	$rootScope.pageStyle = 'album-main-bg-color';
	
	$scope.albums = [
	                 [{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	},{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	},{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	},{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	}], [{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	},{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	},{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	},{
		title:'相册1',
		desc:'这是第一个相册',
		cover:'https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2487597369,453683281&fm=173&s=F2A5B1446A7484CE0AD3BD1B0300F0C9&w=640&h=403&img.JPG'
	}],
	                 ];
});

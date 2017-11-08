angular.module('album', ['ngRoute', 'ui.bootstrap','ngCookies'])

.constant('AppInfo', {
	'baseUrl' : './'
}).constant('ApiKeyConst', {
	checkUserExists : 'lq-auth-user-check-exists',
	createUser:'lq-auth-user-create',
	login:'lq-auth-login'
}).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'tpls/entry.html',
		controller : 'EntryController'
	}).when('/reg', {
		templateUrl : 'tpls/auth/register.html',
		controller : 'RegisterController'
	}).when('/login',{
		templateUrl : 'tpls/auth/login.html',
		controller : 'LoginController'
	})
	.otherwise({
		redirectTo : '/'
	});
}).run(function($rootScope,AppInfo) {
	$rootScope.baseUrl = AppInfo.baseUrl;
	$rootScope.pageStyle = 'album-main-bg-color';
});

angular.module('album', ['ngRoute', 'ui.bootstrap'])

.constant('consts', {
	'baseUrl' : './'
}).constant('ApiKeyConst', {
}).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'tpls/entry.html',
		controller : 'EntryController'
	}).when('/reg', {
		templateUrl : 'tpls/auth/register.html',
		controller : 'RegisterController'
	}).otherwise({
		redirectTo : '/'
	});
}).run(function($rootScope) {
	$rootScope.pageStyle = 'album-main-bg-color';
});

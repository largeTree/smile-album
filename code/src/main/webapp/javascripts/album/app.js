angular.module('album', ['ngRoute', 'ui.bootstrap'])

.constant('constants', {
}).constant('ApiKeyConst', {
})
.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'tpls/entry.html',
        controller: 'EntryController'
    }).otherwise({
        redirectTo: '/'
    });
}).run(function($rootScope) {
});

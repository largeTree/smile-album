angular.module('album', ['ngRoute', 'ui.bootstrap', 'ngCookies'])

    .constant('AppConfig', {
        'baseUrl': 'http://www.qiuxs.com:8090/album/',
        'defPageSize': '15'
    }).constant('ApiKeyConst', {
        checkUserExists: 'sm-auth-user-check-exists',
        createUser: 'sm-auth-user-create',
        login: 'sm-auth-login',
        privateAlbum: 'sm-album-list-private',
        publicAlbum: 'sm-album-list-public',
        createAlbum: 'sm-album-create',
        getAlbumById: 'sm-album-getById'
    }).config(function($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'tpls/entry.html',
            controller: 'EntryController'
        }).when('/reg', {
            templateUrl: 'tpls/auth/register.html',
            controller: 'RegisterController'
        }).when('/login', {
            templateUrl: 'tpls/auth/login.html',
            controller: 'LoginController'
        }).when('/albums', {
            templateUrl: 'tpls/album/pri-albums.html',
            controller: 'MyAlbumsController'
        }).when('/album', {
            templateUrl: 'tpls/album/album-detail.html',
            controller: 'AlbumDetailController'
        }).otherwise({
            redirectTo: '/'
        });

        // 构造http拦截器
        $httpProvider.interceptors.push(function($rootScope) {
            return {
                request: function(config) {
                    if ($rootScope.session && config.params) {
                        config.params.sessionId = $rootScope.session.sessionId;
                    }
                    return config;
                },
                requestError: function(rejection) {
                    return rejection;
                },
                response: function(response) {
                    $rootScope.loading = false;
                    return response;
                },
                responseError: function(rejection) {
                    return rejection;
                }

            };
        });
    }).run(function($rootScope, $cookies, $location, AppConfig) {

        $rootScope.signOut = function() {
            delete $rootScope.session;
            $cookies.remove('lq_user_session');
            $rootScope.isOnline = false;
            $location.path('/');
        }

        $rootScope.isOnline = false;
        $rootScope.baseUrl = AppConfig.baseUrl;
        $rootScope.pageStyle = 'album-main-bg-color';
        var lastSession = $cookies.get('lq_user_session');
        if (lastSession != null) {
            $rootScope.session = JSON.parse(lastSession);
            $rootScope.isOnline = true;
        }
    });
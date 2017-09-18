var app=angular.module('nisumApp',['ngRoute','mainApp','adminApp','loginApp','ui.bootstrap']);

app.config(function($routeProvider){
	$routeProvider.otherwise({redirectTo:'/admin'});
})
.run(function ($rootScope, $window, $location) {
	$rootScope.navBarToggle=false;
	$rootScope.$on("$locationChangeStart", function (event, next, current) {
		
        $rootScope.urlChanged = $location.path();
       
        var urls=['/home','/main']
        if (urls.indexOf($rootScope.urlChanged) > -1) {
            $rootScope.navBarToggle = false;
        } else {
            $rootScope.navBarToggle = true;
        }
        })
})
var app = angular.module('nisumApp', [ 'ui.router', 'configurationsApp',
		'loginApp','questionsApp','directive.g+signin', 'LocalStorageModule' ]);

// app.config(function($routeProvider){
// $routeProvider.otherwise({redirectTo:'/login'});
// })
app.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/login');
}).run(function($rootScope, $window, $state, $location, localStorageService,$timeout) {
	$rootScope.navBarToggle = false;
	$rootScope.$on("$locationChangeStart", function(event, next, current) {

		$rootScope.urlChanged = $location.path();

		var urls = [ '/home', '/questions', '/configurations' ]
		if (urls.indexOf($rootScope.urlChanged) > -1) {
			$rootScope.navBarToggle = false;
		} else {
			$rootScope.navBarToggle = true;
		}

//		$timeout(function(){
			var profile = localStorageService.get("profile");	
			if (profile !== (undefined || null) && $rootScope.urlChanged==='/login') {
				
					$state.go('configurations');
//					if (profile.username === 'admin@gmail.com') {
//						$state.go("admin");
//					} 
//					else if (profile.username === 'user@gmail.com') {
//						$state.go("questions");
//					}
				
			} else if (profile === null) {
				$state.go('login');
			}
//		},50);
		
	})
}).controller('mainController', function($scope, localStorageService, $state) {
	var vm = this;
	vm.getProfile = function() {

		vm.profile = localStorageService.get('profile');
	}
	vm.logout = function() {

		localStorageService.remove('profile');
		var url=window.location.href;
		var navigate=url.substring(0,url.lastIndexOf("/"));
		document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue="+navigate+"/login";
		sessionStorage.clear();
	}
})
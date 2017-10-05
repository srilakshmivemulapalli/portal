var app = angular
		.module(
				'nisumApp',
				[ 'ui.router', 'configurationsApp', 'profileApp', 'loginApp',
						'questionsApp','trainingsApp', 'directive.g+signin',
						'LocalStorageModule', 'textAngular', 'am.multiselect' ])

		.config(function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise('/login');
		})
		.run(
				function($rootScope, $window, $state, $location,
						localStorageService, $timeout) {
					$rootScope.navBarToggle = false;
					$rootScope.$on("$locationChangeStart",
							function(event, next, current) {

								$rootScope.urlChanged = $location.path();

								var urls = [ '/home', '/questions',
										'/configurations', '/profile',
										'/question', '/addquestion','/trainings','/onlineTrainings','/classRoomTrainings','/myTrainings' ]
								if (urls.indexOf($rootScope.urlChanged) > -1) {
									$rootScope.navBarToggle = false;
								} else if ($rootScope.urlChanged
										.indexOf('/question/') > -1) {
									$rootScope.navBarToggle = false;
								} else {
									$rootScope.navBarToggle = true;
								}

								// $timeout(function(){
								var profile = localStorageService
										.get("profile");
								if (profile !== (undefined || null)
										&& $rootScope.urlChanged === '/login') {

									$state.go('configurations');
									// if (profile.username ===
									// 'admin@gmail.com') {
									// $state.go("admin");
									// }
									// else if (profile.username ===
									// 'user@gmail.com') {
									// $state.go("questions");
									// }

								} else if (profile === null) {
									$state.go('login');
								}
								// },50);

							})
				})
		.controller(
				'mainController',
				function($scope, $rootScope, localStorageService, $state, $http) {
					var vm = this;
					vm.redirect = function() {
						$state.go('addquestion');
					}
					vm.getProfile = function() {

						vm.profile = localStorageService.get('profile');
					}
					$http
							.get('v1/questionaries/retrieveCount')
							.then(
									function(response) {
										 $rootScope.questionCount = response.data.questionCount;
										$rootScope.userCount = response.data.userCount;
									}, function(response) {
										console.log(response);
									});
					vm.logout = function() {

						localStorageService.remove('profile');
						var url = window.location.href;
						var navigate = url.substring(0, url.lastIndexOf("/"));
						document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue="
								+ navigate + "/login";
						sessionStorage.clear();
					}
					
				})
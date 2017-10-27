var app = angular
		.module(
				'nisumApp',
				[ 'ui.router', 'configurationsApp', 'profileApp', 'loginApp',
	'questionsApp', 'trainingsApp', 'meetingApp',
						'LocalStorageModule', 'textAngular', 'am.multiselect',
						'google-signin','BlogsApp', 'ae-datetimepicker'])


		.config(function($stateProvider, $urlRouterProvider) {

			$urlRouterProvider.otherwise('/login');
		})
		.config(
				[
						'GoogleSigninProvider',
						function(GoogleSigninProvider) {
							GoogleSigninProvider
									.init({
										clientId : '167391935529-bns0200aplm1inm0qpb5ie7te1g1n50t.apps.googleusercontent.com',
										hostedDomain : 'nisum.com'
									});
						} ])

		.run(
				function($rootScope, $window, $state, $location,
						localStorageService, $timeout,commonService) {
					$rootScope.navBarToggle = false;
					console.log(commonService);
					$rootScope
							.$on(
									"$locationChangeStart",
									function(event, next, current) {

										$rootScope.urlChanged = $location
												.path();

										var urls = [ '/home', '/questions',
												'/configurations', '/profile',
												'/question', '/addquestion',
												'/trainings',
												'/onlineTrainings',
												'/classRoomTrainings',
												'/myTrainings',
												'/createTraining',
												'/editquestion','/blogs','/newBlog','/blog','/meetings'  ]
										if (urls.indexOf($rootScope.urlChanged) > -1) {
											$rootScope.navBarToggle = false;
										} else if ($rootScope.urlChanged
												.indexOf('/question/') > -1) {
											$rootScope.navBarToggle = false;
										} else {
											$rootScope.navBarToggle = true;
										}

										
										var profile = commonService.profile;
												
										if (profile !== (undefined || null)
												&& $rootScope.urlChanged === '/login') {
											if (commonService.userRoleName.toLowerCase() ===commonService.checkRoleName) {
												$timeout(
														function() {
															$state
																	.go('configurations');
														}, 0);
											} else {
												$timeout(function() {
													$state.go('questions');
												}, 0);
											}
										}else if (profile !== (undefined || null)
												&& $rootScope.urlChanged == '/configurations' &&
												commonService.userRoleName.toLowerCase()!==commonService.checkRoleName){
											$timeout(function() {
												$state.go('questions');
											}, 0)
											
										} 
										else if (profile === null) {
											$timeout(function() {
												$state.go('login');
											}, 0);

										}

									})
				})
		.controller(
				'mainController',
				function($scope, $rootScope, localStorageService, $state,
						$http, loginLogoutService, questionService,
						commonService) {
					var vm = this;
					vm.checkRoleName=commonService.checkRoleName;
					vm.redirectQuestion = function() {
						$state.go('addquestion');
					}
					vm.redirectMeeting = function() {
						$state.go('addmeeting');
					}
					vm.getProfile = function() {

						vm.profile = commonService.profile;
					}
					vm.init = function() {
						questionService
								.getQuestionsCount()
								.then(
										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$rootScope.questionCount = response.questionCount;
												$rootScope.userCount = response.userCount;
											}
										}, function(response) {
											console.log(response);
										});
					}
					vm.logout = function() {

						loginLogoutService.logout().then(function(response) {
							if (response.status) {
								$state.go('login');
							}
						})

					}

				})
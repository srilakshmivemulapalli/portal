app.service('commonService', function(localStorageService) {
	var cs={};
	
	var profile=localStorageService.get('profile');
	cs.emailId=profile?profile.emailId:null;
	cs.profile=profile? profile : null;
	cs.categoriesList=localStorageService.get('categoriesList');
	return cs;
});
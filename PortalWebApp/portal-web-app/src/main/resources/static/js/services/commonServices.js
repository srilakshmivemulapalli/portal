app.service('commonService', function(localStorageService) {
	var cs={};
	
	var profile=localStorageService.get('profile');
	cs.checkRoleName='admin';
	cs.emailId=profile?profile.emailId:null;
	cs.profile=profile? profile : null;
	cs.userRoleName= profile?profile.role.role: null;
	cs.categoriesList=localStorageService.get('categoriesList');
	cs.localStorageArr=['profile','categoriesList'];
	cs.clearAll=function(){
		cs.emailId=null;
		cs.profile=null;
		cs.userRoleName=null;
		cs.categoriesList=null;
	}
	return cs;
});
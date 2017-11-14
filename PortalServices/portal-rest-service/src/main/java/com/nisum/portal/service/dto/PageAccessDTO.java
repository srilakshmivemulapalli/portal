package com.nisum.portal.service.dto;

import java.util.List;

public class PageAccessDTO {

	private String pageName;
	private List<PageRole> roles;

	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public List<PageRole> getRoles() {
		return roles;
	}
	public void setRoles(List<PageRole> roles) {
		this.roles = roles;
	}	
	
	
}

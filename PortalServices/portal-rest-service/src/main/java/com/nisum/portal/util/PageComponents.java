package com.nisum.portal.util;

public enum PageComponents {

	QUESTIONS(1,"Questions"), 
	BLOG(2,"Blog"), 
	TRAININGS(3,"Trainings"), 
	CONFIGURATIONS(4, "Configurations"), 
	MEETINGS_ROOM(5, "Meetings Room");

	private String page;
	private int id;

	private PageComponents(int id, String page) {
		this.id = id;
		this.page = page;
	}

	public String getPageName() {
		return page;
	}
	public int getPageId() {
		return id;
	}
}

package com.nisum.portal.service.dto;

public class CourseDto {
	private Integer couserId;
	private String courseName;
	public Integer getCouserId() {
		return couserId;
	}
	public void setCouserId(Integer couserId) {
		this.couserId = couserId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + ((couserId == null) ? 0 : couserId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseDto other = (CourseDto) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (couserId == null) {
			if (other.couserId != null)
				return false;
		} else if (!couserId.equals(other.couserId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CourseDto [couserId=" + couserId + ", courseName=" + courseName + "]";
	}
	
}

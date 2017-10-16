package com.nisum.portal.data.domain;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="ProfileSetting")
public class ProfileSettings implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 public ProfileSettings() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
    @GeneratedValue
    @Column(name="profileId")
	private int profileId;
	
	
	@Column(name="categoryId")
    private int categoryId;
	
	
	public int getProfileId() {
		return profileId;
	}


	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	@Column(name="userId")
    private int userId;


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	public ProfileSettings(int categoryId,int userId){
		this.categoryId=categoryId;
		this.userId=userId;
	}
	

	/*@JsonBackReference
	@ManyToOne
    @JoinColumn(name="userId")
    private ProfileSettings user;
	
	/*@JsonBackReference
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private Set<ProfileSettings> profileSettings;
	

	public Set<ProfileSettings> getProfileSettings() {
		
		
		return profileSettings;
	}

	public void setProfileSettings(Set<ProfileSettings> profileSettings) {
		this.profileSettings = profileSettings;
	}
	/*@ManyToOne
    @JoinColumn(name="categoryId")
    private Categories category;

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}*/                                                                

	/*public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public ProfileSettings getUser() {
		return user;
	}

	public void setUser(ProfileSettings user) {
		this.user = user;
	}*/
    
	
	}
	



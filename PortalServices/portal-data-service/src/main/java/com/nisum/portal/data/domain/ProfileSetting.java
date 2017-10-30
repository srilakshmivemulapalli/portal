package com.nisum.portal.data.domain;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "ProfileSetting")
public class ProfileSetting implements Serializable {

	private static final long serialVersionUID = 1L;

	public ProfileSetting() {
	}

	@Id
	@GeneratedValue
	@Column(name = "profileId")
	private int profileId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
	private Categories categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User userId;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public Categories getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public ProfileSetting(Categories categoryId, User userId) {
		this.categoryId = categoryId;
		this.userId = userId;
	}

	/*
	 * @JsonBackReference
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="userId") private ProfileSettings user;
	 * 
	 * /*@JsonBackReference
	 * 
	 * @OneToMany(mappedBy="user",cascade = CascadeType.ALL) private
	 * Set<ProfileSettings> profileSettings;
	 * 
	 * 
	 * public Set<ProfileSettings> getProfileSettings() {
	 * 
	 * 
	 * return profileSettings; }
	 * 
	 * public void setProfileSettings(Set<ProfileSettings> profileSettings) {
	 * this.profileSettings = profileSettings; } /*@ManyToOne
	 * 
	 * @JoinColumn(name="categoryId") private Categories category;
	 * 
	 * public Categories getCategory() { return category; }
	 * 
	 * public void setCategory(Categories category) { this.category = category; }
	 */

	/*
	 * public int getProfileId() { return profileId; }
	 * 
	 * public void setProfileId(int profileId) { this.profileId = profileId; }
	 * 
	 * public int getCategoryId() { return categoryId; }
	 * 
	 * public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
	 * 
	 * public ProfileSettings getUser() { return user; }
	 * 
	 * public void setUser(ProfileSettings user) { this.user = user; }
	 */

}

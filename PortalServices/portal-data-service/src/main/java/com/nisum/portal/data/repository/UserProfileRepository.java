package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.ProfileSetting;
import com.nisum.portal.data.domain.User;

public interface UserProfileRepository extends JpaRepository<ProfileSetting, String> {

	@Transactional
	@Modifying
	@Query(value = "delete from ProfileSetting where userId = (select userId from User where emailid = :emailid)")
	int deleteProfiles(@Param("emailid") String emailid);
	
	@Query(value = "SELECT user FROM User user, ProfileSetting pf where user.userId= pf.userId and pf.categoryId=:categoryId")
	List<User> findByCategoryId(@Param("categoryId")Integer categoryId);
}

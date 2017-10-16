package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.User;





public interface UserProfileRepository extends JpaRepository<User,Integer> {
	//UserProfile findByUserId(Integer categoryId);
	//@Query("SELECT userId FROM User u WHERE u.emailid = :email")
}

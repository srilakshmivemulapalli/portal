package com.nisum.portal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.User;





public interface UserProfileRepository extends JpaRepository<User,Integer> {
	//UserProfile findByUserId(Integer categoryId);
	//@Query("SELECT userId FROM User u WHERE u.emailid = :email")
	
	@Query(value = "SELECT user FROM User user, ProfileSettings pf where user.userId= pf.userId and pf.categoryId=:categoryId")
	List<User> findByCategoryId(@Param("categoryId")Integer categoryId);
}

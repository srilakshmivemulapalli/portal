package com.nisum.portal.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nisum.portal.data.domain.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query(value = "UPDATE User u SET u.activeStatus = 'No' where u.userId = :userId")
    int deleteUser(@Param("userId") int userId);
	
	User findByEmailId(String emailId);
	
	public User findByUserId(int userId);

}

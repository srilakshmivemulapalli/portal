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
    @Query(value = "UPDATE User u SET u.isActive = 'No' where u.userId = :userId",nativeQuery = true)
    int deleteUser(@Param("userId") int userId);
}

package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nisum.portal.data.domain.User;

public interface UsersRespository extends JpaRepository<User,Integer> {

}

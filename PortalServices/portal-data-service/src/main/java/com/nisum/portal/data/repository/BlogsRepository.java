package com.nisum.portal.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.Blogs;

public interface BlogsRepository extends JpaRepository<Blogs, Integer> {
	
	Blogs findByBlogsId(Integer blogsId);
	List<Blogs> findAllByUserMailIdOrderByCreatedDateDesc(String userMailId);
	List<Blogs> findAllByOrderByCreatedDateDesc();

}

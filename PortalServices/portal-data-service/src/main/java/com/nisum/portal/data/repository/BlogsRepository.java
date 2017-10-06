package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.Blogs;

public interface BlogsRepository extends JpaRepository<Blogs, Integer> {
	
	Blogs findByBlogsId(Integer blogsId);

}

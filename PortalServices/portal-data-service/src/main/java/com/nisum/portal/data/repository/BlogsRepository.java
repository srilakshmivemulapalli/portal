package com.nisum.portal.data.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nisum.portal.data.domain.Blogs;

public interface BlogsRepository extends JpaRepository<Blogs, Integer> {
	
	Blogs findByBlogsId(int blogsId);
	List<Blogs> findAllByUserMailIdOrderByCreatedDateDesc(String userMailId);
	List<Blogs> findAllByOrderByCreatedDateDesc();
	@Query("select b from Blogs b order by b.createdDate desc")
	List<Blogs> findAllBlogsPaginationOrderByDateDesc(Pageable pageable);
	@Query("select b from Blogs b where b.userMailId =:mailId order by b.createdDate desc")
	List<Blogs> findAllBlogsPaginationByUserMailIdOrderByDateDesc(@Param("mailId") String mailId,Pageable pageable);
}

package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Blogs;

public interface BlogsDAO {
	
	List<Blogs> getAllBlogs();
	
	Blogs getBlog(Integer id);

}

package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Blogs;

public interface BlogsDAO {
	
	List<Blogs> getAllBlogs();
	
	Blogs getBlog(Integer id);
	
	void removeBlog(Integer id);
	
	Blogs updateBlog(Blogs blog);
	
	Blogs addBlog(Blogs blog);
	
	boolean blogExists(Integer id);
	
	List<Blogs> getAllBlogsByUserMailId(String userMailId);

}

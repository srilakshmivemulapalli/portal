package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.dto.ServiceStatusDto;

public interface BlogService {

	public Blogs SubmitBlog(Blogs blog);
	
	public Blogs getBlog();
	
	public List<Blogs> getAllBlogs();
	
	public ServiceStatusDto RemoveBlog();
	
	public ServiceStatusDto UpdateBlog();
	
	

}

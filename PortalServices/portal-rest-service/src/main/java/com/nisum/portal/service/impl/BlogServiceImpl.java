package com.nisum.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.BlogsDAO;
import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.ServiceStatusDto;

@Service
public class BlogServiceImpl implements BlogService{
	
	private static Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
	
	@Autowired
	private BlogsDAO blogDAO;

	@Override
	public Blogs SubmitBlog( Blogs blog) {
		return null;
		
	}

	@Override
	public Blogs getBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceStatusDto RemoveBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceStatusDto UpdateBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blogs> getAllBlogs() {
		// TODO Auto-generated method stub
		return blogDAO.getAllBlogs();
	}

}

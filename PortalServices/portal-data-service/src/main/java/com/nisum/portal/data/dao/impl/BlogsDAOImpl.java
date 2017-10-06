package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.BlogsDAO;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.data.repository.BlogsRepository;

@Configuration
public class BlogsDAOImpl implements BlogsDAO {

	private static Logger logger = LoggerFactory.getLogger(BlogsDAOImpl.class);
	@Autowired
	BlogsRepository blogsRepository;
	@Override
	public List<Blogs> getAllBlogs() {
		logger.info("BlogsDAOImpl :: getAllBlogs");
		return blogsRepository.findAll();
	}
	@Override
	public Blogs getBlog(Integer id) {
		logger.info("BlogsDAOImpl :: getBlog");
		return blogsRepository.findByBlogsId(id);
	}

}

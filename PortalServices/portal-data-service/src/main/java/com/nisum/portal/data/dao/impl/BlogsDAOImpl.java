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
	@Override
	public void removeBlog(Integer id) {
		logger.info("BlogsDAOImpl :: removeBlog");
		blogsRepository.delete(id);
	}
	@Override
	public Blogs updateBlog(Blogs blog) {
		logger.info("BlogsDAOImpl :: updateBlog");
		Blogs updatedBlog=blogsRepository.saveAndFlush(blog);
		return updatedBlog;
	}
	@Override
	public boolean blogExists(Integer id) {
		logger.info("BlogsDAOImpl :: blogExists");
		if(blogsRepository.exists(id)) {
			return true;
		}
		return false;
	}
	@Override
	public Blogs addBlog(Blogs blog) {
		logger.info("BlogsDAOImpl :: addBlog");
		Blogs addedBlog=blogsRepository.save(blog);
		return addedBlog;
	}
	@Override
	public List<Blogs> getAllBlogsByUserMailId(String userMailId) {
		logger.info("BlogsDAOImpl :: getAllBlogsByUserMailId");
		return blogsRepository.findByUserMailId(userMailId);

	}

}

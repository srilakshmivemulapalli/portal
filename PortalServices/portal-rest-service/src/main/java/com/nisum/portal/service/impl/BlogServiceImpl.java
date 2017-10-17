package com.nisum.portal.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;



import com.nisum.portal.data.dao.api.BlogsDAO;
import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.util.BlogsServiceUtil;


@Service
public class BlogServiceImpl implements BlogService{
	
	private static Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
	
	@Autowired
	private BlogsDAO blogDAO;
	
	@Value("${blogs.attachment.path}")
	private String blogsAttachmentPath;

	@Override
	public List<BlogsDTO> getAllBlogs() throws Exception {
		logger.info("BlogServiceImpl :: getAllBlogs");
		List<Blogs> blogs= blogDAO.getAllBlogs();
		List<BlogsDTO> blogsDTO=BlogsServiceUtil.convertDaoTODto(blogs);
		for(BlogsDTO blogDTO : blogsDTO) {
			String dirPath=blogDTO.getPath();
			if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
				blogDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath));
			}
		}
		return blogsDTO;
	}

	@Override
	public BlogsDTO getBlog(Integer id) throws Exception {
		logger.info("BlogServiceImpl :: getBlog");
		Blogs blog=blogDAO.getBlog(id);
		if(blog==null) {
			logger.error("BlogServiceImpl :: getBlog Error === Blog does not exist.");
			throw new BlogServiceException("No Blog found with id "+id);
		}
		BlogsDTO blogsDTO=BlogsServiceUtil.convertDaoToDtoInstance(blog);
		String dirPath=blogsDTO.getPath();
		if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
			blogsDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath));
		}
		return blogsDTO;
	}

	@Override
	public void removeBlog(Integer id,String userMailId) throws Exception {
		logger.info("BlogServiceImpl :: removeBlog");
		if(blogDAO.blogExists(id)&&(userMailId!=null)) {
			if(BlogsServiceUtil.removeBlogAttachments(blogsAttachmentPath+File.separator+userMailId+File.separator+id)) {
				logger.info("BlogServiceImpl :: removeBlog -- Blog's attachments removed.");
			}
			blogDAO.removeBlog(id);
			
		}else {
			logger.error("BlogServiceImpl :: removeBlog Error === Blog/UserMailId does not exist.");
			throw new BlogServiceException("No Blog/UserMailId found with "+id+"/"+userMailId);
		}
	}

	@Override
	public BlogsDTO updateBlog(BlogsDTO blogDTO) throws Exception {
		logger.info("BlogServiceImpl :: updateBlog");
		if(!blogDAO.blogExists(blogDTO.getBlogsId())) {
			logger.error("BlogServiceImpl :: updateBlog Error === Blog does not exist.");
			throw new BlogServiceException("No Blog found with id "+blogDTO.getBlogsId());
		}
		Blogs blog=blogDAO.getBlog(blogDTO.getBlogsId());
		Blogs updatedBlog=BlogsServiceUtil.setDtoToDao(blogDTO,blog);
		BlogsDTO updatedBlogsDTO= BlogsServiceUtil.convertDaoToDtoInstance(blogDAO.updateBlog(updatedBlog));	
		String dirPath=updatedBlogsDTO.getPath();
		if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
			updatedBlogsDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath));
		}
		return updatedBlogsDTO;
	}

	@Override
	public BlogsDTO addBlog(BlogsDTO blogDTO) throws Exception {
		logger.info("BlogServiceImpl :: addBlog");
		blogDTO.setBlogsId(0);
		Blogs blog=new Blogs();
		Timestamp createdDate=new Timestamp(System.currentTimeMillis());
		blog.setCreatedDate(createdDate);
		Blogs newBlog=BlogsServiceUtil.setDtoToDao(blogDTO, blog);
		return BlogsServiceUtil.convertDaoToDtoInstance(blogDAO.addBlog(newBlog));
	}

	@Override
	public BlogsDTO parseRequestToGetBlogsDTO(HttpServletRequest request) throws Exception {
		logger.info("BlogServiceImpl :: parseRequestToGetBlogsDTO");
		return BlogsServiceUtil.parseRequestToGetBlogsDTO(request);
	}

	@Override
	public BlogsDTO parseRequestToStoreUploads(HttpServletRequest request, String path, BlogsDTO blogsDTO) throws Exception{
		logger.info("BlogServiceImpl :: parseRequestToStoreUploads");
		return BlogsServiceUtil.parseRequestToStoreUploads(request, blogsAttachmentPath, blogsDTO);
	}

	@Override
	public String uploadAttachment(HttpServletRequest request, String path) throws Exception {
		logger.info("BlogServiceImpl :: uploadAttachment");
		return BlogsServiceUtil.parseRequestToStoreUploads(request,path);
	}

	@Override
	public Path getFile(String userMailId, String blogId, String fileName) throws Exception{
		logger.info("BlogServiceImpl :: getFile");
		String fileUrl=null;
		if((userMailId!=null)&&(blogId!=null)&&(fileName!=null)) {
			fileUrl=blogsAttachmentPath+File.separator+userMailId+File.separator+blogId+File.separator+fileName;
			File file=new File(fileUrl);
			if(file.exists()) {
				return file.toPath();
			}else {
				throw new BlogServiceException(fileName+" does't exist.");
			}
		}else {
			throw new BlogServiceException("UserMailId or BlogId or FileName are null.");
		}
	}
	
}
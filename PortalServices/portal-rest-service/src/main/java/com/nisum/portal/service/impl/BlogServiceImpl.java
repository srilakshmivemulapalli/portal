package com.nisum.portal.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	

	public String getBlogsAttachmentPath() {
		return blogsAttachmentPath;
	}


	@Override
	public List<BlogsDTO> getAllBlogs() throws Exception {
		logger.info("BlogServiceImpl :: getAllBlogs");
		List<Blogs> blogs= blogDAO.getAllBlogs();
		List<BlogsDTO> blogsDTO=BlogsServiceUtil.convertDaoTODto(blogs);
		for(BlogsDTO blogDTO : blogsDTO) {
			String dirPath=blogDTO.getPath();
			if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
				blogDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath,blogDTO.getBlogsId()));
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
			blogsDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath,blogsDTO.getBlogsId()));
		}
		return blogsDTO;
	}

	@Override
	public void removeBlog(Integer id,String userMailId) throws Exception {
		logger.info("BlogServiceImpl :: removeBlog");
		if(blogDAO.blogExists(id)&&(userMailId!=null)) {
			if(blogsAttachmentPath!=null) {
				if(BlogsServiceUtil.removeBlogAttachments(blogsAttachmentPath+File.separator+userMailId+File.separator+id)) {
					logger.info("BlogServiceImpl :: removeBlog -- Blog's attachments removed.");
				}
			}else {
				logger.error("BlogServiceImpl :: removeBlog Error === Can't found Server Location Path to store blog's attachments.");
				throw new BlogServiceException("Internal Server Error while removing Blog's attachments.");
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
		Blogs blog=new Blogs();
		
		Blogs updatedBlog=BlogsServiceUtil.setDtoToDao(blogDTO,blog);
		BlogsDTO updatedBlogsDTO= BlogsServiceUtil.convertDaoToDtoInstance(blogDAO.updateBlog(updatedBlog));	
		String dirPath=updatedBlogsDTO.getPath();
		if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
			updatedBlogsDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath,updatedBlogsDTO.getBlogsId()));
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
	public Path getFile(String userMailId, Integer blogId, String fileName) throws Exception{
		logger.info("BlogServiceImpl :: getFile");
		String fileUrl=null;
		if((userMailId!=null)&&(blogId!=null)&&(fileName!=null)) {
			fileUrl=getBlogsAttachmentPath()+File.separator+userMailId+File.separator+blogId+File.separator+fileName;
			File file=new File(fileUrl);
			if(file.exists()) {
				return file.toPath();
			}else {
				logger.error("BlogServiceImpl :: getFile Error === "+fileName+" does't exist.");
				throw new BlogServiceException(file.getName()+" does't exist.");
			}
		}else {
			logger.error("BlogServiceImpl :: getFile Error === UserMailId or BlogId or FileName are nul");
			throw new BlogServiceException("UserMailId or BlogId or FileName are null.");
		}
	}

	@Override
	public boolean removeFile(String userMailId, Integer blogId, String fileName) throws Exception {
		logger.info("BlogServiceImpl :: removeFile");
		if(blogDAO.blogExists(blogId)&&(userMailId!=null)) {
			if(BlogsServiceUtil.removeBlogAttachments(getBlogsAttachmentPath()+File.separator+userMailId+File.separator+blogId+File.separator+fileName)) {
				logger.info("BlogServiceImpl :: removeFile -- file "+fileName+" removed.");
				return true;
			}
			return false;
			
		}else {
			logger.error("BlogServiceImpl :: removeFile Error === Blog/UserMailId does not exist.");
			throw new BlogServiceException("No Blog/UserMailId found with "+blogId+"/"+userMailId);
		}
	}


	@Override
	public String uploadAttachment(MultipartFile[] file, String path) throws Exception {
		logger.info("BlogServiceImpl :: uploadAttachment");
		return BlogsServiceUtil.parseRequestToStoreUploadsUI(file, path);
	}


	@Override
	public BlogsDTO parseRequestToStoreUploads(MultipartFile[] file, String path, BlogsDTO blogsDTO) throws Exception {
		logger.info("BlogServiceImpl :: parseRequestToStoreUploads");
		//return BlogsServiceUtil.parseRequestToStoreUploads(request, getBlogsAttachmentPath(), blogsDTO);
		return BlogsServiceUtil.parseRequestToStoreUploads(file, path, blogsDTO);
	}


	@Override
	public List<BlogsDTO> getAllBlogsByEmailId(String userMailId) {
		logger.info("BlogServiceImpl :: getAllBlogsByEmailId");
		List<Blogs> blogsList=blogDAO.getAllBlogsByUserMailId(userMailId);
		List<BlogsDTO> blogsDTO=BlogsServiceUtil.convertDaoTODto(blogsList);
		for(BlogsDTO blogDTO : blogsDTO) {
			String dirPath=blogDTO.getPath();
			if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
				blogDTO.setFileNames(BlogsServiceUtil.getAllFiles(dirPath,blogDTO.getBlogsId()));
			}
		}
		return blogsDTO;	
	}


	@Override
	public BlogsDTO parseRequestToGetBlogsDTOForUpdate(HttpServletRequest request) throws Exception {
		logger.info("BlogServiceImpl :: parseRequestToGetBlogsDTOForUpdate");
		return BlogsServiceUtil.parseRequestToGetBlogsDTOForUpdate(request);
	}


	@Override
	public List<BlogsDTO> getAllBlogsPaination(Integer page, Integer size) {
		logger.info("BlogServiceImpl :: getAllBlogsPaination");
		List<Blogs> blogsList=blogDAO.getAllBlogsPagination(page, size);
		return BlogsServiceUtil.convertDaoTODto(blogsList);
	}


	@Override
	public List<BlogsDTO> getAllBlogsPainationByMailId(String mailId, Integer page, Integer size) {
		logger.info("BlogServiceImpl :: getAllBlogsPaination");
		List<Blogs> blogsList=blogDAO.getAllBlogsPaginationByMailId(mailId, page, size);
		return BlogsServiceUtil.convertDaoTODto(blogsList);
	}


	@Override
	public Long getAllBlogsCount() {
		logger.info("BlogServiceImpl :: getAllBlogsCount");
		return blogDAO.getAllBlogsCount();
	}


	@Override
	public Long getAllBlogsCountByMailId(String mailId) {
		logger.info("BlogServiceImpl :: getAllBlogsCountByMailId");
		return blogDAO.getAllBlogsCountByMailId(mailId);
	}
	
	
}
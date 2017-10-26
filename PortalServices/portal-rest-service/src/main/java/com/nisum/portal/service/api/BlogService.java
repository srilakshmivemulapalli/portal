package com.nisum.portal.service.api;

import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.nisum.portal.service.dto.BlogsDTO;


public interface BlogService {
	
	List<BlogsDTO> getAllBlogs() throws Exception;
	
	BlogsDTO getBlog(Integer id) throws Exception;;
	
	void removeBlog(Integer id,String userMailId) throws Exception;
	
	BlogsDTO updateBlog(BlogsDTO blogDTO) throws Exception;
	
	BlogsDTO addBlog(BlogsDTO blogDTO) throws Exception;
	
	BlogsDTO parseRequestToGetBlogsDTO(HttpServletRequest request) throws Exception;
	
	BlogsDTO parseRequestToStoreUploads(HttpServletRequest request,String path,BlogsDTO blogsDTO) throws Exception;
	
	String uploadAttachment(HttpServletRequest request,String path) throws Exception;
	
	Path getFile(String userMailId,Integer blogId,String fileName) throws Exception;
	
	boolean removeFile(String userMailId,Integer blogId,String fileName) throws Exception;
	
	boolean validateHttpRequestUploads(HttpServletRequest request)throws Exception;

}

package com.nisum.portal.service.api;

import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.nisum.portal.service.dto.BlogsDTO;


public interface BlogService {
	
	List<BlogsDTO> getAllBlogs() throws Exception;
	
	BlogsDTO getBlog(Integer id) throws Exception;;
	
	void removeBlog(Integer id,String userMailId) throws Exception;
	
	BlogsDTO updateBlog(BlogsDTO blogDTO) throws Exception;
	
	BlogsDTO addBlog(BlogsDTO blogDTO) throws Exception;
	
	BlogsDTO parseRequestToGetBlogsDTO(HttpServletRequest request) throws Exception;
	
	BlogsDTO parseRequestToGetBlogsDTOForUpdate(HttpServletRequest request) throws Exception;
	
	BlogsDTO parseRequestToStoreUploads(MultipartFile[] file,String path,BlogsDTO blogsDTO) throws Exception;
	
	//String uploadAttachment(HttpServletRequest request,String path) throws Exception;
	
	String uploadAttachment(MultipartFile[] file,String path) throws Exception;
	
	Path getFile(String userMailId,Integer blogId,String fileName) throws Exception;
	
	boolean removeFile(String userMailId,Integer blogId,String fileName) throws Exception;
	
	BlogsDTO convertJSONObjectToBlogsDTO(JSONObject jsonObject) throws Exception;
	
	List<BlogsDTO> getAllBlogsByEmailId(String userMailId);
	
	List<BlogsDTO> getAllBlogsPaination(Integer page,Integer size);
	
	List<BlogsDTO> getAllBlogsPainationByMailId(String mailId,Integer page,Integer size);

}

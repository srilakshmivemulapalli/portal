package com.nisum.portal.rest.api;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.nisum.portal.service.api.BlogService;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.util.BlogsServiceUtil;


/**
 * @author nisum
 */
@Controller
@RestController
@RequestMapping(value = "/v1/Blogs")
public class BlogsRestService {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsRestService.class);

	@Autowired
	BlogService blogService;
	
	@Value("${blogs.attachment.path}")
	private String blogsAttachmentPath;
	/**
	 * getAllBlogs
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Object getAllBlogs() throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogs");
		try {
			List<BlogsDTO> blogsDTO= blogService.getAllBlogs();
			for(BlogsDTO blogDTO : blogsDTO) {
				blogDTO.setPath("");
			}
			return blogsDTO;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogs Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
		
	}
	
	/**
	 * getBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/retrieve/{id}", method = RequestMethod.GET)
	public Object getBlog(@PathVariable Integer id) throws BlogServiceException {
		logger.info("BlogsRestService :: getBlog");
		try {
			BlogsDTO blogsDTO=blogService.getBlog(id);
			blogsDTO.setPath("");
			return blogsDTO;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getBlog ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * getFile
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/retrieve/getFile/{fileName}/{userMailId}/{blogId}", method = RequestMethod.GET)
	public Object getFile(@PathVariable String userMailId,@PathVariable Integer blogId,@PathVariable String fileName) {
		logger.info("BlogsRestService :: getFile");
		try {
			
			Path path = blogService.getFile(userMailId, blogId, fileName);
			byte[] document = Files.readAllBytes(path);
	        HttpHeaders header = new HttpHeaders();
	        header=BlogsServiceUtil.setFileTypeForHttpHeader(header,fileName);
	        header.set("Content-Disposition", "inline; filename=" +fileName);
	        header.setContentLength(document.length);

	        return new HttpEntity<byte[]>(document, header);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getFile Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}

	}
	
	
	/**
	 * updateBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Object updateBlog(@RequestBody BlogsDTO blogsDTO) throws BlogServiceException {
		logger.info("BlogsRestService :: updateBlog");
		try {
			// Setting DTO object fields that can not be updated.
			BlogsDTO blog=blogService.getBlog(blogsDTO.getBlogsId());
			blogsDTO.setBlogsId(blog.getBlogsId());
			blogsDTO.setCreatedDate(blog.getCreatedDate());
			blogsDTO.setPath(blog.getPath());
			blogsDTO.setUserMailId(blog.getUserMailId());
			blogsDTO.setUserId(blog.getUserId());
			// Setting completed.
			BlogsDTO updatedDTO= blogService.updateBlog(blogsDTO);
			if((updatedDTO!=null)) {
				String path=updatedDTO.getPath();
				if(path!=null) {
					updatedDTO.setPath("");
				}
			}
			return updatedDTO;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: updateBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * removeBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/remove/{userMailId}/{id}", method = RequestMethod.DELETE)
	public Object removeBlog(@PathVariable Integer id,@PathVariable String userMailId) throws BlogServiceException {
		logger.info("BlogsRestService :: removeBlog");
		try {
			blogService.removeBlog(id,userMailId);
			String resStatus="Success";
			ResponseEntity<String> response=new ResponseEntity<String>(resStatus,HttpStatus.OK);
			return response;
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: removeBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * removeFile
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/remove/file/{fileName}/{userMailId}/{blogId}", method = RequestMethod.DELETE)
	public Object removeFile(@PathVariable Integer blogId,@PathVariable String userMailId,@PathVariable String fileName) throws BlogServiceException {
		logger.info("BlogsRestService :: removeFile");
		try {
			if(blogService.removeFile(userMailId, blogId, fileName)) {
				String resStatus="Success";
				ResponseEntity<String> response=new ResponseEntity<String>(resStatus,HttpStatus.OK);
				return response;
			}else {
				throw new BlogServiceException("Error while deleting file "+fileName);
			}
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: removeFile Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * addBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value="/add/addBlog", method=RequestMethod.POST,consumes = { "multipart/form-data" })
	public @ResponseBody Object addBlog(
	        @RequestParam(value = "uploads") MultipartFile[]  file,HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: addBlog");
		try {
			String Local_FOLDER = "//Users//nisum//Desktop//test//";
			for(int i=0;i<file.length;i++)
			{
				System.out.println(file[i].getOriginalFilename());
				
				byte[] bytes = file[i].getBytes();
	            Path path = Paths.get(Local_FOLDER + file[i].getOriginalFilename());
	            System.out.println(path.toAbsolutePath());
	            Files.write(path, bytes);
				
				//file[i].transferTo(filedest);
				}
			 
				System.out.println(request.getParameter("title"));
				System.out.println(request.getParameter("description"));
				System.out.println(request.getParameter("userId"));
				System.out.println(request.getParameter("emailId"));
			return "";
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: addBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * uploadAttachment
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value="/add/uploadAttachment", method=RequestMethod.POST)
	public @ResponseBody Object uploadAttachment(@Context HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: uploadAttachment");
		try {	
			if(blogService.validateHttpRequestUploads(request)) {
				String userMailId=request.getParameter("userMailId");
				String blogId=request.getParameter("blogId");
				BlogsDTO blogDTO=blogService.getBlog(Integer.parseInt(blogId));
				String blogsMailId=blogDTO.getUserMailId();
				if((userMailId!=null)&&(blogId!=null)) {
					if((blogsMailId!=null)&&blogsMailId.equals(userMailId)) {
						String dirPath=blogService.uploadAttachment(request, blogsAttachmentPath+File.separator+userMailId+File.separator+blogId);
						if(dirPath!=null) {
							String path=blogDTO.getPath();
							if((blogDTO!=null)&&((path==null)||(!path.equals(dirPath)))) {
								blogDTO.setPath(dirPath);
								blogService.updateBlog(blogDTO);
							}
						}
						return "Uploaded File(s) Successfully!!!";
					}else {
						throw new BlogServiceException("Invalid User Email Id "+userMailId);	
					}
				}else {
					throw new BlogServiceException("UserMailId or BlogId are null.");
				}
			}else {
				throw new BlogServiceException("No uploads found.");
			}
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: uploadAttachment Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(BlogServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();

		errors.setErrorCode("Errors-Blogs");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
}

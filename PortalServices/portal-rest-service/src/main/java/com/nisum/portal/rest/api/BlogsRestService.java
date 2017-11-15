package com.nisum.portal.rest.api;


import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
			return new ResponseEntity<List<BlogsDTO>>(blogsDTO,HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogs Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	/**
	 * getAllBlogsCount
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value = "/retrieve/allBlogsCount", method = RequestMethod.GET)
	public Object getAllBlogsCount() throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogsCount");
		try {
			Long blogsCount=blogService.getAllBlogsCount();
			JSONObject jsonObj=new JSONObject();
			jsonObj.append("count", blogsCount);
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogsCount Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	/**
	 * getAllBlogsCountByUserMailID
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value = "/retrieve/MyBlogsCount/{mailId}/", method = RequestMethod.GET)
	public Object getAllBlogsCountByUserMailID(@PathVariable String mailId) throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogsCountByUserMailID");
		try {
			Long blogsCount=blogService.getAllBlogsCountByMailId(mailId);
			JSONObject jsonObj=new JSONObject();
			jsonObj.append("count", blogsCount);
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogsCountByUserMailID Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	/**
	 * getAllBlogsPagination
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value = "/retrieve/allBlogsPagination/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public Object getAllBlogsPagination(@PathVariable Integer pageNum,@PathVariable Integer pageSize) throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogsPagination");
		try {
			List<BlogsDTO> blogsDTO= blogService.getAllBlogsPaination(pageNum, pageSize);
			for(BlogsDTO blogDTO : blogsDTO) {
				blogDTO.setPath("");
			}
			return new ResponseEntity<List<BlogsDTO>>(blogsDTO,HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogsPagination Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	/**
	 * getAllBlogsPaginationByMailId
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	@RequestMapping(value = "/retrieve/allBlogsPaginationByMailId/{mailId}/{pageNum}/{pageSize}", method = RequestMethod.GET)
	public Object getAllBlogsPaginationByMailId(@PathVariable String mailId,@PathVariable Integer pageNum,@PathVariable Integer pageSize) throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogsPaginationByMailId");
		try {
			List<BlogsDTO> blogsDTO= blogService.getAllBlogsPainationByMailId(mailId, pageNum, pageSize);
			for(BlogsDTO blogDTO : blogsDTO) {
				blogDTO.setPath("");
			}
			return new ResponseEntity<List<BlogsDTO>>(blogsDTO,HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogsPaginationByMailId Error ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
			if(blogsDTO!=null) {
				blogsDTO.setPath("");
			}
			return new ResponseEntity<BlogsDTO>(blogsDTO,HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getBlog ");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}

	}
	
	
	/**
	 * updateBlogAndAttachments
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/update/updateBlogAndAttachments", method = RequestMethod.PUT,consumes = { "multipart/form-data" })
	public Object updateBlogAndAttachments(@RequestParam(value = "uploads") MultipartFile[]  files,HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: updateBlogAndAttachments");
		try {
			BlogsDTO blogsDTO=blogService.parseRequestToGetBlogsDTOForUpdate(request);
			BlogsDTO updateBlog=null;
			BlogsDTO updatedDTO=null;
			if(blogsDTO!=null) {
				Integer blogId=blogsDTO.getBlogsId();
				BlogsDTO blog=blogService.getBlog(blogId);
				// Setting DTO object fields that can not be updated.
				blogsDTO.setCreatedDate(blog.getCreatedDate());
				blogsDTO.setPath(blog.getPath());
				blogsDTO.setUserMailId(blog.getUserMailId());
				blogsDTO.setUserId(blog.getUserId());
				// Setting completed.
				
				if((files!=null)&&(files.length!=0)) {
					updateBlog=blogService.parseRequestToStoreUploads(files, blogsAttachmentPath, blogsDTO);
					if(!updateBlog.getTitle().equals(blog.getTitle())||!updateBlog.getDescription().equals(blog.getDescription())||(!updateBlog.getPath().equals(blog.getPath()))) {
						updatedDTO= blogService.updateBlog(updateBlog);
					}	
				}
				if(updateBlog==null) {
					if(!blogsDTO.getTitle().equals(blog.getTitle())||!blogsDTO.getDescription().equals(blog.getDescription())) {
						updatedDTO= blogService.updateBlog(blogsDTO);
					}
				}
				
				if((updatedDTO!=null)) {
					String path=updatedDTO.getPath();
					if(path!=null) {
						updatedDTO.setPath("");
					}
					return new ResponseEntity<BlogsDTO>(updatedDTO,HttpStatus.OK);
				}else {
					JSONObject jsonObj=new JSONObject();
					if((updateBlog==null)||(updateBlog.getPath()==null)||!updateBlog.getPath().equals(blog.getPath())) {
						jsonObj.append("result", "No modification found to update the Blog.");
						return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.BAD_REQUEST);
					}else {
						jsonObj.append("result", "File(s) uploaded successfully.");
						return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
					}
				}
				
			}else {
				throw new BlogServiceException("Unable to process request as blog object is empty :"+blogsDTO);
			}
			
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: updateBlogAndAttachments Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
			if(blogsDTO!=null) {
				BlogsDTO blog=blogService.getBlog(blogsDTO.getBlogsId());
				// Setting DTO object fields that can not be updated.
				blogsDTO.setBlogsId(blog.getBlogsId());
				blogsDTO.setCreatedDate(blog.getCreatedDate());
				blogsDTO.setPath(blog.getPath());
				blogsDTO.setUserMailId(blog.getUserMailId());
				blogsDTO.setUserId(blog.getUserId());
				// Setting completed.
				BlogsDTO updatedDTO=null;
				if(!blogsDTO.getTitle().equals(blog.getTitle())||!blogsDTO.getDescription().equals(blog.getDescription())) {
					updatedDTO= blogService.updateBlog(blogsDTO);
				}
				if((updatedDTO!=null)) {
					String path=updatedDTO.getPath();
					if(path!=null) {
						updatedDTO.setPath("");
					}
					return new ResponseEntity<BlogsDTO>(updatedDTO,HttpStatus.OK);
				}else {
					String resStatus="No modification found to update the Blog.";
					JSONObject jsonObj=new JSONObject();
					jsonObj.append("result", resStatus);
					return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.BAD_REQUEST);
				}
			}else {
				throw new BlogServiceException("Unable to process request as blog object is empty :"+blogsDTO);
			}
			
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: updateBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
			String resStatus="Blog Removed Successfully.";
			JSONObject jsonObj=new JSONObject();
			jsonObj.append("result", resStatus);
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: removeBlog Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
				String resStatus="File Removed Successfully.";
				JSONObject jsonObj=new JSONObject();
				jsonObj.append("result", resStatus);
				return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
			}else {
				throw new BlogServiceException(fileName+" does't exist.");
			}
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: removeFile Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}
	}
	
	/**
	 * addBlog
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value="/add/addBlog", method=RequestMethod.POST,consumes = { "multipart/form-data" })
	public @ResponseBody Object addBlog(@RequestParam(value = "uploads") MultipartFile[]  files,HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: addBlog");
		try {
			//blogService.validateRequestUploads(files);
			
			BlogsDTO blogsDTO=blogService.parseRequestToGetBlogsDTO(request);
			
			BlogsDTO addedBlog=blogService.addBlog(blogsDTO);
			
			BlogsDTO updateBlog=blogService.parseRequestToStoreUploads(files, blogsAttachmentPath, addedBlog);
			
			BlogsDTO updatedBlog=blogService.updateBlog(updateBlog);
			
			if(updatedBlog.getPath()!=null) {
				updatedBlog.setPath("");
			}
			
			return new ResponseEntity<BlogsDTO>(updatedBlog,HttpStatus.OK);
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
	@RequestMapping(value="/add/uploadAttachment", method=RequestMethod.POST,consumes = { "multipart/form-data" })
	public @ResponseBody Object uploadAttachment(@RequestParam(value = "uploads") MultipartFile[]  files,HttpServletRequest request) throws BlogServiceException {
		logger.info("BlogsRestService :: uploadAttachment");
		try {	
			//if(blogService.validateHttpRequestUploads(request)) {
			if((files!=null)&&(files.length!=0)) {
				String userMailId=request.getParameter("emailId");
				String blogId=request.getParameter("blogId");
				BlogsDTO blogDTO=blogService.getBlog(Integer.parseInt(blogId));
				String blogsMailId=blogDTO.getUserMailId();
				if((blogDTO!=null)&&(userMailId!=null)&&(blogId!=null)) {
					if((blogsMailId!=null)&&blogsMailId.equals(userMailId)) {
						String dirPath=blogService.uploadAttachment(files, blogsAttachmentPath+File.separator+userMailId+File.separator+blogId);
						if(dirPath!=null) {
							String path=blogDTO.getPath();
							if(((path==null)||(!path.equals(dirPath)))) {
								blogDTO.setPath(dirPath);
								blogService.updateBlog(blogDTO);
							}
						}
						return new ResponseEntity<String>("Uploaded File(s) Successfully!!!",HttpStatus.OK);
					}else {
						throw new BlogServiceException("Invalid User Email Id "+userMailId);	
					}
				}else {
					throw new BlogServiceException("User MailId or BlogId are null.");
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
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
		}
	}
	
	/**
	 * getAllBlogsByUserMailId
	 * 
	 * @return
	 * @throws BlogServiceException
	 */
	
	@RequestMapping(value = "/retrieve/getAllBlogsByUserMailId/{userMailId}/", method = RequestMethod.GET)
	public Object getAllBlogsByUserMailId(@PathVariable String userMailId) throws BlogServiceException {
		logger.info("BlogsRestService :: getAllBlogsByUserMailId");
		try {
			List<BlogsDTO> blogsDTO= blogService.getAllBlogsByEmailId(userMailId);
			for(BlogsDTO blogDTO : blogsDTO) {
				blogDTO.setPath("");
			}
			return new ResponseEntity<List<BlogsDTO>>(blogsDTO,HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("BlogsRestService :: getAllBlogsByUserMailId Error");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Blogs");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.BAD_GATEWAY);
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
		return new ResponseEntity<Errors>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

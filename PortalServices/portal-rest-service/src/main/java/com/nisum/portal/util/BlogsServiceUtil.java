package com.nisum.portal.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.nisum.portal.data.domain.Blogs;

import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.exception.BlogServiceException;

public class BlogsServiceUtil {
	
	private static Logger logger = LoggerFactory.getLogger(BlogsServiceUtil.class);
	
	
	public static List<BlogsDTO> convertDaoTODto(List<Blogs> blogsList) {
		logger.info("BlogsServiceUtil :: convertDaoTODto");
		List<BlogsDTO> blogsDTOs = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(blogsList)) {
			for (Blogs blogs : blogsList) {
				BlogsDTO blogsDTO = convertDaoToDtoInstance(blogs);
				blogsDTOs.add(blogsDTO);
			}
		}
		return blogsDTOs;

	}
	
	public static BlogsDTO convertDaoToDtoInstance(Blogs blog) {
		logger.info("BlogsServiceUtil :: convertDaoToDtoInstance");
		BlogsDTO blogsDTO=null;
		if(blog!=null) {
			blogsDTO=new BlogsDTO();
			blogsDTO.setBlogsId(blog.getBlogsId());
			blogsDTO.setTitle(blog.getTitle());
			blogsDTO.setUserId(blog.getUserId());
			blogsDTO.setCreatedDate(blog.getCreatedDate());
			blogsDTO.setDescription(blog.getDescription());
			blogsDTO.setPath(blog.getPath());
			blogsDTO.setUserMailId(blog.getUserMailId());
		}
		return blogsDTO;
	}
	
	public static Blogs setDtoToDao(BlogsDTO blogsDTO, Blogs blog)throws Exception {
		logger.info("BlogsServiceUtil :: setDtoToDao");
		if((blogsDTO!=null)&&(blog!=null)) {
			if(blogsDTO.getBlogsId()!=0) {
				blog.setBlogsId((blogsDTO.getBlogsId()));
			}
			if(blogsDTO.getTitle()!=null) {
				blog.setTitle(blogsDTO.getTitle());
			}
			if(blogsDTO.getUserId()!=0) {
				blog.setUserId(blogsDTO.getUserId());
			}
			if(blogsDTO.getCreatedDate()!=null) {
				blog.setCreatedDate(blogsDTO.getCreatedDate());
			}
			if(blogsDTO.getDescription()!=null) {
				blog.setDescription(blogsDTO.getDescription());
			}
			if(blogsDTO.getPath()!=null) {
				blog.setPath(blogsDTO.getPath());
			}
			if(blogsDTO.getUserMailId()!=null) {
				blog.setUserMailId(blogsDTO.getUserMailId());
			}
			return blog;
		}else {
			logger.error("BlogsServiceUtil :: setDtoToDao Error");
			throw new BlogServiceException("Unable to set DTO object to DAO object as BlogsDTO or Blogs is null");
		}
	}
	
	public static BlogsDTO parseRequestToGetBlogsDTO(HttpServletRequest request) throws Exception{
		logger.info("BlogsServiceUtil :: parseRequestToGetBlogsDTO");
		
		validateHttpRequestForUploads(request);
		
		String title=request.getParameter("title");
		if(title==null) {
			logger.error("BlogsServiceUtil :: parseRequestToGetBlogsDTO Error ==== Missing Blog title.");
			throw new BlogServiceException("Missing Blog title.");
		}
		String description=request.getParameter("description");
		if(description==null) {
			logger.error("BlogsServiceUtil :: parseRequestToGetBlogsDTO Error ==== Missing Blog description.");
			throw new BlogServiceException("Missing Blog description.");
		}
		String userId=request.getParameter("userId");
		if(userId==null) {
			logger.error("BlogsServiceUtil :: parseRequestToGetBlogsDTO Error ==== Missing  UserId.");
			throw new BlogServiceException("Missing  UserId.");
		}
		String userMailId=request.getParameter("userMailId");
		if(userMailId==null) {
			logger.error("BlogsServiceUtil :: parseRequestToGetBlogsDTO Error ==== Missing User Mail ID.");
			throw new BlogServiceException("Missing User Mail ID.");
		}
		
		
		BlogsDTO blogsDTO=new BlogsDTO();
		blogsDTO.setTitle(title);
		blogsDTO.setDescription(description);
		blogsDTO.setUserId(Integer.parseInt(userId));
		blogsDTO.setUserMailId(userMailId);
		return blogsDTO;
	}
	
	public static String parseRequestToStoreUploads(HttpServletRequest request,String dirPath) throws Exception{
		logger.info("BlogsServiceUtil :: parseRequestToUploadFiles");
		validateHttpRequestForUploads(request);
		if(dirPath!=null) {
			File dir=new File(dirPath);
			if(!dir.exists()) {
				if(dir.mkdirs()) {
					logger.info("BlogsServiceUtil :: parseRequestToStoreUploads -- directory "+dirPath+" created.");
				}else {
					logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- error while creating directory "+dirPath);
					throw new BlogServiceException("error while creating directory "+dirPath);
				}
			}
		}else {
			logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- Directory Path is null");
			throw new BlogServiceException(" Directory Path is null ");
		}
		
		// Get all uploaded files
		List<Part> fileParts = request.getParts().stream().filter(part -> "uploads".equals(part.getName())).collect(Collectors.toList()); 
		
		// Store uploaded files into server
		if(fileParts!=null) {
			for (Part filePart : fileParts) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				InputStream fileContent = filePart.getInputStream();
				
				String[] fileNameParts=(String[]) fileName.split("\\.");
				if((fileNameParts!=null)&&(fileNameParts.length==2)) {
					String newFileName=fileNameParts[0]+"_"+getCurrentTimeAsString("ddMMMYYYY_hh_mm_ss_a");
					newFileName=newFileName+"."+fileNameParts[1];
					if(dirPath!=null) {
						String newFilePath=dirPath+File.separator+newFileName;
						File newFile=new File(newFilePath);
						if(newFile.createNewFile()) {
							logger.info("BlogsServiceUtil :: parseRequestToStoreUploads -- file "+newFilePath+" created.");
							OutputStream outStream=new FileOutputStream(newFile);
							IOUtils.copy(fileContent, outStream);
						}else {
							logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- Error while creating file "+newFileName);
							throw new BlogServiceException(" Error while creating file "+newFileName);
						}
						
					}else {
						logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- Error while creating file "+newFileName);
						throw new BlogServiceException(" Error while creating file "+newFileName);
					}
				}else {
					logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- Error while creating file ==== File Name "+fileName+" contains \".\" symbol.");
					throw new BlogServiceException(" Error while creating file ==== File Name "+fileName+" contains \".\" symbol.");
				}
			}
		}else {
			logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- No Uploads Found.");
			return null;
		}
		
		return dirPath;
	}
	
	public static BlogsDTO parseRequestToStoreUploads(HttpServletRequest request,String dirPath,BlogsDTO blogsDTO) throws Exception{
		logger.info("BlogsServiceUtil :: parseRequestToStoreUploads");
		
		// Compose directory path to store files
		String userMailId=blogsDTO.getUserMailId();
		int blogId=blogsDTO.getBlogsId();
		String path=null;
		if((dirPath!=null)&&(userMailId!=null)&&(blogId!=0)) {
			path=dirPath+File.separator+userMailId+File.separator+blogId;
			// Call parseRequestToStoreUploads(-,-) to store uploads.
			String resltPath=parseRequestToStoreUploads(request,path);
			blogsDTO.setPath(resltPath);
		}else {
			logger.error("BlogsServiceUtil :: parseRequestToStoreUploads -- Directory Path or UserMailId or BlogId is null");
			throw new BlogServiceException(" Directory Path or UserMailId or BlogId is null ");
		}
		
		return blogsDTO;
	}
	
	public static String getCurrentTimeAsString(String format) {
		//SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMMYYYY_hh:mm:ss_a");
		
		logger.info("BlogsServiceUtil :: getCurrentTimeAsString");
		
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		
		String dateString=dateFormat.format(timestamp);
		
		return dateString;
	}
	
	public static boolean removeBlogAttachments(String file) throws Exception {
		logger.info("BlogsServiceUtil :: removeBlogAttachments");
		if(file!=null) {
			File dirPath=new File(file);
			if(dirPath.exists()) {
				Path path=dirPath.toPath();
				deleteFileOrFolder(path);
			}else {
				logger.error("BlogsServiceUtil :: removeBlogAttachments Error ==== "+file+" does't exist.");
				throw new BlogServiceException(dirPath.getName()+" does't exist.");
			}
		}else {
			logger.error("BlogsServiceUtil :: removeBlogAttachments Error ==== "+file+" does't exist.");
			throw new BlogServiceException(file+" does't exist.");
		}
		return true;
	}
	public static void deleteFileOrFolder(final Path path) throws Exception {
		logger.info("BlogsServiceUtil :: deleteFileOrFolder");
		Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
		@Override public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)throws IOException {
		   Files.delete(file);
		   logger.info(" File "+file.getParent()+File.separator+file.getFileName()+" deleted");
		   return FileVisitResult.CONTINUE;
		}

		@Override public FileVisitResult visitFileFailed(final Path file, final IOException e) {
		   return handleException(e);
		}

		private FileVisitResult handleException(final IOException e) {
		   //e.printStackTrace();
		   logger.error("BlogsServiceUtil :: deleteFileOrFolder --   file or dir "+e.getMessage()+" does not exist.");
		   return FileVisitResult.TERMINATE;
		}

		@Override public FileVisitResult postVisitDirectory(final Path dir, final IOException e)throws IOException {
		   if(e!=null)return handleException(e);
		   Files.delete(dir);
		   logger.info(" Directory "+dir.getParent()+File.separator+dir.getFileName()+" deleted");
		   return FileVisitResult.CONTINUE;
		  }
	  });
	}
	public static HttpHeaders setFileTypeForHttpHeader(HttpHeaders headers,String fileName) throws Exception {
		logger.info("BlogsServiceUtil :: setFileTypeForHttpHeader");
		String[] fileNameSplit=fileName.split("\\.");
		if((fileNameSplit!=null)&&(fileNameSplit[fileNameSplit.length-1])!=null) {
			headers.setContentType(new MediaType("application", fileNameSplit[fileNameSplit.length-1]));
		}else {
			logger.error("BlogsServiceUtil :: setFileTypeForHttpHeader error === Invalid File Name "+fileName);
			throw new BlogServiceException("Invalid File Name: "+fileName);
		}
		return headers;
	}

	public static List<String> getAllFiles(String dirPath,int blogId){
		logger.info("BlogsServiceUtil :: getAllFiles");
		List<String> fileNames=null;
		if((dirPath!=null)&&(!StringUtils.isEmpty(dirPath))) {
			fileNames=new ArrayList<String>();
			File folder=new File(dirPath);
			if(folder.exists()) {
				File[] list=folder.listFiles();
				for(File file:list) {
					if(file.isFile()&&(!file.isHidden())) {
						fileNames.add(file.getName());
					}
				}
			}else {
				logger.error("BlogsServiceUtil :: getAllFiles error === No directory found with name "+dirPath+" for BlogId "+blogId);
				return null;
			}
			return fileNames;
		}else {
			logger.error("BlogsServiceUtil :: getAllFiles error === No directory found with name "+dirPath+" for BlogId "+blogId);
			return null;
		}
		
	}
	
	public static boolean validateHttpRequestForUploads(HttpServletRequest request) throws Exception {
		logger.info("BlogsServiceUtil :: validateHttpRequestForUploads");
		List<Part> fileParts = request.getParts().stream().filter(part -> "uploads".equals(part.getName())).collect(Collectors.toList());
		if(fileParts!=null) {
			for (Part filePart : fileParts) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				
				String[] fileNameParts=(String[]) fileName.split("\\.");
				if((fileNameParts!=null)&&(fileNameParts.length>2)) {
					logger.error("BlogsServiceUtil :: validateHttpRequestForUploads ==== "+" Error while creating file ==== File Name "+fileName+" contains \".\" symbol.");
					throw new BlogServiceException(" Error while creating file ==== File Name "+fileName+" contains \".\" symbol.");
				}
			}
		}else {
			logger.info("BlogsServiceUtil :: validateHttpRequestForUploads === No uploads found.");
		}
		return true;
	}
}
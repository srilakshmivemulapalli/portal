package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.Blogs;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.BlogsDTO;
import com.nisum.portal.service.dto.CategoriesDTO;

public class BlogsServiceUtil {
	
	public static List<BlogsDTO> convertDaoTODto(List<Blogs> blogsList) {
		List<BlogsDTO> blogsDTOs = new ArrayList<>();
		
		if (CollectionUtils.isNotEmpty(blogsList)) {
			for (Blogs blogs : blogsList) {
				BlogsDTO blogsDTO = new BlogsDTO();
				blogsDTO.setBlogsId(blogs.getBlogsId());
				blogsDTO.setCategoryId(blogs.getCategoryId());
				blogsDTO.setUserId(blogs.getUserId());
				blogsDTO.setCreatedDate(blogs.getCreatedDate());
				blogsDTO.setDescription(blogs.getDescription());
				blogsDTO.setPath(blogs.getPath());
				blogsDTOs.add(blogsDTO);
			}
		}
		return blogsDTOs;

	}
	
	public static BlogsDTO convertDaoToDtoInstance(Blogs blog) {
		BlogsDTO blogsDTO=new BlogsDTO();
		if(blog!=null) {
			blogsDTO.setBlogsId(blog.getBlogsId());
			blogsDTO.setCategoryId(blog.getCategoryId());
			blogsDTO.setUserId(blog.getUserId());
			blogsDTO.setCreatedDate(blog.getCreatedDate());
			blogsDTO.setDescription(blog.getDescription());
			blogsDTO.setPath(blog.getPath());
		}
		return blogsDTO;
	}

}

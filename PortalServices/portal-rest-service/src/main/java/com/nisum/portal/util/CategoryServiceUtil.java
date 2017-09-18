package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.CategoriesDTO;

public class CategoryServiceUtil {
	
	public static List<CategoriesDTO> convertDaoTODto(List<Categories>  categoriesList){
		List<CategoriesDTO> categoriesDTOs = new ArrayList<>();
		for(Categories categories:categoriesList){
			CategoriesDTO categoriesDTO= new CategoriesDTO();
			categoriesDTO.setCategoryId(categories.getCategoryId());
			categoriesDTO.setCategoryName(categories.getCategoryName());
			categoriesDTO.setCreateDate(categories.getCreateDate());
			categoriesDTOs.add(categoriesDTO);
		}
		return categoriesDTOs;
		
	}
	
	public static CategoriesDTO convertDaoToDtoInstance(Categories category) {
		
		CategoriesDTO categoriesDTO= new CategoriesDTO();
		if(category!=null) {
			categoriesDTO.setCategoryId(category.getCategoryId());
			categoriesDTO.setCategoryName(category.getCategoryName());
			categoriesDTO.setCreateDate(category.getCreateDate());
		}
		return categoriesDTO;
	}

}

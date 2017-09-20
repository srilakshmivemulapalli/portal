package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.CategoriesDTO;

public class CategoryServiceUtil {

	public static List<CategoriesDTO> convertDaoTODto(List<Categories> categoriesList) {
		List<CategoriesDTO> categoriesDTOs = new ArrayList<>();
		for (Categories categories : categoriesList) {
			CategoriesDTO categoriesDTO = new CategoriesDTO();
			categoriesDTO.setCategoryId(categories.getCategoryId());
			categoriesDTO.setCategoryName(categories.getCategoryName());
			categoriesDTO.setDescription(categories.getDescription());
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

	public static Categories convertDtoTODao(CategoriesDTO categoriesList) {

		Categories category = new Categories();
		category.setCategoryId(categoriesList.getCategoryId());
		category.setCategoryName(categoriesList.getCategoryName());
		category.setCreateDate(categoriesList.getCreateDate());

		return category;

	}

	
	public static List<Categories> convertDtoTODao(List<CategoriesDTO>  categoriesList){
		List<Categories> categoriess = new ArrayList<>();
		for(CategoriesDTO categoriesDTO:categoriesList){
			Categories categories= new Categories();
			categories.setCategoryId(categoriesDTO.getCategoryId());
			categories.setCategoryName(categoriesDTO.getCategoryName());
			categories.setCreateDate(categoriesDTO.getCreateDate());
			categoriess.add(categories);
		}
		return categoriess;
	}
		

	public static CategoriesDTO convertDaoTODto(Categories categories)
	{
		CategoriesDTO categoriesDTO=new CategoriesDTO();
		categoriesDTO.setCategoryId(categories.getCategoryId());
		categoriesDTO.setCategoryName(categories.getCategoryName());
		categoriesDTO.setCreateDate(categories.getCreateDate());
		return categoriesDTO;

	}
}

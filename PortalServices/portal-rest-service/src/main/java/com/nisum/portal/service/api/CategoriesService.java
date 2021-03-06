package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;

public interface CategoriesService {
	
	List<CategoriesDTO> getCategories();
	
	ServiceStatusDto addCategory(CategoriesDTO category);
	
	ServiceStatusDto deleteCategories(Integer categoryId);
	
	ServiceStatusDto update(CategoriesDTO categoriesDTO) throws CategoryServiceException;

	Object getCategory(Integer id);

	Categories getCategoryByName( String categoryNname ) ; 

}

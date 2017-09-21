package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;

public interface CategoriesService {
	
	List<CategoriesDTO> getCategories();
	
	ServiceStatusDto addCategory(CategoriesDTO category);
	
	String deleteCategories(Integer categoryId);
	
	String update(CategoriesDTO categoriesDTO) throws CategoryServiceException;

	Object getCategory(Integer id);


}

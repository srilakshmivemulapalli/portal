package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;

public interface CategoriesService {
	
	List<CategoriesDTO> getCategories();
	
	ServiceStatusDto addCategory(CategoriesDTO category);
	CategoriesDTO update(Categories categories) throws CategoryServiceException;

}

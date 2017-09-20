package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;

public interface CategoriesService {
	
	List<CategoriesDTO> getCategories();
	
	CategoriesDTO getCategory(Integer id);
	ServiceStatusDto addCategory(CategoriesDTO category);
	
	String deleteCategories(List<CategoriesDTO> categories);
	
	CategoriesDTO update(Categories categories);


}

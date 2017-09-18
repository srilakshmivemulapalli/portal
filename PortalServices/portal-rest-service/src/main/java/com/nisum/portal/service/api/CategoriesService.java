package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;

public interface CategoriesService {
	
	List<CategoriesDTO> getCategories();
	
	ServiceStatusDto addCategory(CategoriesDTO category);


}

package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.CategoryServiceUtil;
import com.nisum.portal.util.KeyConstants;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	@Autowired
	private CategoriesDAO categoriesDAO;

	@Override
	public List<CategoriesDTO> getCategories() {
		List<Categories>  categoriesList=categoriesDAO.getCategories();
		return CategoryServiceUtil.convertDaoTODto(categoriesList);
	}
	
	
	@Override
	public ServiceStatusDto addCategory(CategoriesDTO categoryDto) {
		

        Date date = new Date();
        ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
        Timestamp createdDate = new Timestamp(date.getTime());
       
		categoryDto.setCreateDate(createdDate);
		Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);
		
	  
		int serviceStatus = categoriesDAO.addCategory(category);
		
		if(serviceStatus == 0) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(KeyConstants.CATEGORY_EXISTS);
		}else if(serviceStatus == 1){
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(KeyConstants.ERROR_MESSAGE);
		}else {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(KeyConstants.SUCCESS_MESSAGE);
		}

		return serviceStatusDto;
	}
	@Override
	public CategoriesDTO update(Categories categories) throws CategoryServiceException 
	{
		// TODO Auto-generated method stub
		try
		{
			Categories categories2 = categoriesDAO.updateCategories(categories);
			return CategoryServiceUtil.convertDaoTODto(categories2);
		}
		catch(Exception e)
		{
			throw new CategoryServiceException("Category id "+categories.getCategoryId()+" is Not Exists");
		}
	}

	@Override
	public CategoriesDTO getCategory(Integer id) {
		Categories category=categoriesDAO.getCategory(id);
		return CategoryServiceUtil.convertDaoToDtoInstance(category);
	}
	

}

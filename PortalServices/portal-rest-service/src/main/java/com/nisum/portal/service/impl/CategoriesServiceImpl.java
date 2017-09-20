package com.nisum.portal.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.util.CategoryServiceUtil;
import com.nisum.portal.util.KeyConstants;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	
	private static Logger logger = LoggerFactory.getLogger(CategoriesServiceImpl.class);
	@Autowired
	private CategoriesDAO categoriesDAO;

	@Override
	public List<CategoriesDTO> getCategories() {
		List<Categories>  categoriesList=categoriesDAO.getCategories();
		return CategoryServiceUtil.convertDaoTODto(categoriesList);
	}
	
	
	/* (non-Javadoc)
	 * @see com.nisum.portal.service.api.CategoriesService#addCategory(com.nisum.portal.service.dto.CategoriesDTO)
	 */
	@Override
	public ServiceStatusDto addCategory(CategoriesDTO categoryDto) {
		
		logger.info("CategoriesServiceImpl :: addCategories");

        Date date = new Date();
        ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
        Timestamp createdDate = new Timestamp(date.getTime());
       
		categoryDto.setCreateDate(createdDate);
		Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);
		
	  
		Integer serviceStatus = categoriesDAO.addCategory(category);
		
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
	public CategoriesDTO update(Categories categories) 
	{

		logger.info("CategoriesServiceImpl :: updateCategories :: Category Details "+categories.toString());
			Categories categories2 = categoriesDAO.updateCategories(categories);
			return CategoryServiceUtil.convertDaoTODto(categories2);
	}

	@Override
	public CategoriesDTO getCategory(Integer id) {
		Categories category=categoriesDAO.getCategory(id);
		return CategoryServiceUtil.convertDaoToDtoInstance(category);
	}
	
	@Override
	public String deleteCategories(List<CategoriesDTO> categories) {
		List<Categories> catgories=CategoryServiceUtil.convertDtoTODao(categories);
		Integer  count=categoriesDAO.deleteCategories(catgories);
		if(count>0)
		return  count+" Categories deleted successfully";
		else
		return "Categories not Exist";
	}
	

	@Override
	public CategoriesDTO getCategory(Integer id) {
		Categories category=categoriesDAO.getCategory(id);
		return CategoryServiceUtil.convertDaoToDtoInstance(category);
	}
	

}

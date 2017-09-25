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
import com.nisum.portal.service.exception.CategoryServiceException;
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
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(KeyConstants.CATEGORY_EXISTS);
		}else if(serviceStatus == 1){
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(KeyConstants.SUCCESS_MESSAGE);
		}

		return serviceStatusDto;
	}
	/*
	 * (non-Javadoc)
	 * @see com.nisum.portal.service.api.CategoriesService#update(com.nisum.portal.data.domain.Categories)
	 */
	@Override
	public ServiceStatusDto update(CategoriesDTO categoriesDTO) throws CategoryServiceException 
	{
		// TODO Auto-generated method stub
		logger.info("CategoriesServiceImpl :: updateCategories :: Category Details "+categoriesDTO.toString());
			Date date = new Date();
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			Timestamp createdDate = new Timestamp(date.getTime());
			categoriesDTO.setCreateDate(createdDate);
			Categories categories = CategoryServiceUtil.convertDtoTODao(categoriesDTO);
			boolean flag = categoriesDAO.updateCategories(categories);
			try
			{
			if(flag==true)
			{
				logger.info("CategoriesServiceImpl :: updateCategories :: Categories updated Successfully");
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(KeyConstants.SUCCESS_UPDATE_MESSAGE);
			}
			else
			{
				logger.error("CategoriesServiceImpl :: updateCategories :: Unable To Update Categories with categoryId not found."+categories.getCategoryId());
				serviceStatusDto.setStatus(false);
				serviceStatusDto.setMessage(KeyConstants.CATEGORY_EXISTS);
			}
			return serviceStatusDto;
			}
			catch(Exception e)
			{
				logger.error("CategoriesServiceImpl :: updateCategories :: Exception");
				throw new CategoryServiceException("CategoryId Not Existed");
			}
			
	}

	
	@Override
	public String deleteCategories(Integer categoryId)  {
		logger.info("CategoriesServiceImpl :: deleteCategories");
          return categoriesDAO.deleteCategories(categoryId);
	}


	@Override
	public Object getCategory(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}

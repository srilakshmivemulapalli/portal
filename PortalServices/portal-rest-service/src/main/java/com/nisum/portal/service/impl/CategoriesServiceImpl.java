package com.nisum.portal.service.impl;

import java.sql.Timestamp; 
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.CategoryServiceUtil;
import com.nisum.portal.util.Constants;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	private static Logger logger = LoggerFactory.getLogger(CategoriesServiceImpl.class);
	@Autowired
	private CategoriesDAO categoriesDAO;

	@Override
	@Cacheable(value="Categories")
	public List<CategoriesDTO> getCategories() {
		logger.info("categories in service impl");
		List<Categories> categoriesList = categoriesDAO.getCategories();
	 
		return CategoryServiceUtil.convertDaoTODto(categoriesList);  
	}
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.portal.service.api.CategoriesService#addCategory(com.nisum.portal.
	 * service.dto.CategoriesDTO)
	 */
	@Override
	@CacheEvict(value="Categories", key="#categoryDto.categoryName", allEntries=true)
	public ServiceStatusDto addCategory(CategoriesDTO categoryDto) {

		logger.info("CategoriesServiceImpl :: addCategories");

		Date date = new Date();
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		Timestamp createdDate = new Timestamp(date.getTime());

		categoryDto.setCreateDate(createdDate);
		Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);

		Integer serviceStatus = categoriesDAO.addCategory(category);

		if (serviceStatus == 0) {
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.CATEGORY_EXISTS);
		} else if (serviceStatus == 1) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
		}

		return serviceStatusDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.portal.service.api.CategoriesService#update(com.nisum.portal.data.
	 * domain.Categories)
	 */
	@Override 
	@CacheEvict(value="Categories", key="#categoriesDTO.categoryId", allEntries=true)
	public ServiceStatusDto update(CategoriesDTO categoriesDTO) throws CategoryServiceException {
		logger.info("CategoriesServiceImpl :: updateCategories :: Category Details " + categoriesDTO.toString());
		Date date = new Date();
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		Timestamp createdDate = new Timestamp(date.getTime());
		categoriesDTO.setCreateDate(createdDate);
		Categories categories = CategoryServiceUtil.convertDtoTODao(categoriesDTO);
		Integer flag = categoriesDAO.updateCategories(categories);
		try {
			if (flag == 1) {
				logger.info("CategoriesServiceImpl :: updateCategories :: Categories updated Successfully");
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(Constants.MSG_RECORD_UPDATE);
			} else if(flag==2)
			{
				logger.info("CategoriesServiceImpl :: updateCategories :: Only Description is updated Successfully");
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(Constants.CATEGORY_DESCRIPTION_ONLY_EXISTS);
			}
			else {
				logger.error(
						"CategoriesServiceImpl :: updateCategories :: Unable To Update Categories with categoryId not found."
								+ categories.getCategoryId());
				serviceStatusDto.setStatus(false);
				serviceStatusDto.setMessage(Constants.CATEGORY_EXISTS);
			}
			return serviceStatusDto;
		} catch (Exception e) {
			logger.error("CategoriesServiceImpl :: updateCategories :: Exception");
			throw new CategoryServiceException("CategoryId Not Existed");
		}

	}

	@Override
	@CacheEvict(value="Categories", key="#categoryId", allEntries=true)
	public ServiceStatusDto deleteCategories(Integer categoryId) {
		logger.info("CategoriesServiceImpl :: deleteCategories");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		String status = categoriesDAO.deleteCategories(categoryId);
		if (status.equals("Success")) {
			serviceStatusDto.setStatus(true);
			serviceStatusDto.setMessage(Constants.CATEGORY_DELETE);
		} else if (status.equals("Failure")) {
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.CATEGORY_NOT_EXIST);
		} else if (status.equalsIgnoreCase(Constants.CANT_DELETE_CATEGORY)) {
			serviceStatusDto.setStatus(false);
			serviceStatusDto.setMessage(Constants.CANT_DELETE_CATEGORY);
		}
		return serviceStatusDto;
	}

	@Override
	public Object getCategory(Integer id) {
		logger.info("CategoriesServiceImpl :: getCategory");
		return categoriesDAO.getCategory(id);
	}
	
	@Override
	public Categories getCategoryByName(String categoryNname) {
		logger.info("CategoriesServiceImpl :: getCategoryByName");
		return categoriesDAO.getCategoryByName(categoryNname);
	}

}

package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.repository.CategoriesRepository;

@Configuration
public class CategoriesDAOImpl implements CategoriesDAO {

	private static Logger logger = LoggerFactory.getLogger(CategoriesDAOImpl.class); 
	@Autowired
	CategoriesRepository categoriesRepository;

	@Override
	public List<Categories> getCategories() {
		logger.info("getting data from db");
		return categoriesRepository.findAll();
	}

	@Override
	public Categories getCategory(Integer id) {
		return categoriesRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.portal.data.dao.api.CategoriesDAO#addCategory(com.nisum.portal.data
	 * .domain.Categories)
	 */
	public Integer addCategory(Categories category) {

		logger.info("CategoriesDAOImpl :: addCategories");

		Integer status;

		Categories categories = categoriesRepository.findByCategoryName(category.getCategoryName());

		if (categories == null) {
			Categories savedCategory = categoriesRepository.save(category);
			status = 1;
		} else {
			status = 0;

		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nisum.portal.data.dao.api.CategoriesDAO#updateCategories(com.nisum.portal
	 * .data.domain.Categories)
	 */
	@Override
	public Integer updateCategories(Categories categories) {
		logger.info("CategoriesDAOImpl :: updateCategories :: Category Details " + categories.toString());
		Integer flag = 0;
		Categories category = categoriesRepository.findByCategoryId(categories.getCategoryId());
		Categories findByCategoryName = categoriesRepository.findByCategoryName(categories.getCategoryName());
		List<Categories> findAll = categoriesRepository.findAll();
		final Integer categoryId = categories.getCategoryId();
		final String categoryName = categories.getCategoryName();
		final String description = categories.getDescription();
		try
		{
		for(Categories c:findAll)
		{
			if(category!=null&&categoryName!=null&&description!=null)
			{
				if(findByCategoryName==null&&!c.getCategoryName().equalsIgnoreCase(categoryName)&&!c.getDescription().equalsIgnoreCase(description))
				{
					logger.info("CategoriesDAOImpl :: updateCategories :: Both CategoryName and Description updated");
					categoriesRepository.save(categories);
					flag=1;
					break;
				}
				else if(c.getCategoryId()==categoryId&&c.getCategoryName().equalsIgnoreCase(categoryName)&&!c.getDescription().equalsIgnoreCase(description))
				{
					logger.info("CategoriesDAOImpl :: updateCategories :: Only Description is updated");
					c.setDescription(description);
					categoriesRepository.save(c);
					flag=2;
					break;
				}
				else if(c.getCategoryName().equalsIgnoreCase(categoryName)&&c.getDescription().equalsIgnoreCase(description))
				{
					logger.info("CategoriesDAOImpl :: updateCategories :: Category Nam Already Existed");
					flag=0;
					break;
				}
			}
		}
		}
		catch(Exception e)
		{
			logger.error("CategoriesDAOImpl :: updateCategories :: Got An Exception -->"+e);
			flag=0;
		}
		return flag;
	}

	@Override
	public String deleteCategories(Integer categoryId)   {
		logger.info("CategoriesDAOImpl :: deleteCategories");
		if(categoriesRepository.findOne(categoryId) != null)
		{
		 categoriesRepository.delete(categoryId);
		 return "Success";
		}
		else
		{
			 return "Failure";
		}

	}

}
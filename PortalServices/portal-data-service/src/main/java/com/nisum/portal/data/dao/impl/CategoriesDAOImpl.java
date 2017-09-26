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
	public boolean updateCategories(Categories categories) {
		logger.info("CategoriesDAOImpl :: updateCategories :: Category Details " + categories.toString());
		Categories category = categoriesRepository.findByCategoryId(categories.getCategoryId());
		Categories findByCategoryName = categoriesRepository.findByCategoryName(categories.getCategoryName());
		boolean flag;
		if (category != null&&findByCategoryName==null) {
			categoriesRepository.save(categories);
			flag = true;
		} else {
			logger.error("Unable To Update Categories with categoryId not found."+categories.getCategoryId());
			flag = false;
		}
		return flag;
	}

	@Override
	public String deleteCategories(Integer categoryId)   {
		logger.info("CategoriesDAOImpl :: deleteCategories");
		 categoriesRepository.delete(categoryId);
		 return "Success";

	}

}
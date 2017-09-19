package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.repository.CategoriesRepository;

@Configuration
public class CategoriesDAOImpl implements CategoriesDAO {

	@Autowired
	CategoriesRepository categoriesRepository;

	@Override
	public List<Categories> getCategories() {
		return categoriesRepository.findAll();
	}

	@Override
	public Categories getCategory(Integer id) {
		// TODO Auto-generated method stub
		return categoriesRepository.findOne(id);
	}
	public int addCategory(Categories category) {

		Boolean serviceStatus = false;
		int status;

		Categories categories = categoriesRepository.findByCategoryName(category.getCategoryName());

		if (categories == null) {
			Categories savedCategory = categoriesRepository.save(category);

			if (savedCategory == null) {
				status = 1;
			} else {
				status = 2;
			}
		} else {
			status = 0;

		}

		return status;
	}

	@Override
	public String updateCategories(Categories categories) {
		// TODO Auto-generated method stub
		return null;
	}

}

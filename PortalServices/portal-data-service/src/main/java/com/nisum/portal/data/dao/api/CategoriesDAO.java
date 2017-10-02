package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;

public interface CategoriesDAO {

	List<Categories> getCategories();

	Integer addCategory(Categories category);

	Integer updateCategories(Categories categories);

	Categories getCategory(Integer id);

	String deleteCategories(Integer categoryId);
}
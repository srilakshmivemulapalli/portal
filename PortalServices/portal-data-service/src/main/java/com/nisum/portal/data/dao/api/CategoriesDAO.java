package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;

public interface CategoriesDAO {
	
	List<Categories> getCategories();

	Integer addCategory(Categories category);

	Categories updateCategories(Categories categories);

	Categories getCategory(Integer id);

	Integer deleteCategories(List<Categories> categories);
}

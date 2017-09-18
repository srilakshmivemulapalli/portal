package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;

public interface CategoriesDAO 
{
	List<Categories> getCategories();
	
	int addCategory(Categories category);

	String updateCategories(Categories categories);
}

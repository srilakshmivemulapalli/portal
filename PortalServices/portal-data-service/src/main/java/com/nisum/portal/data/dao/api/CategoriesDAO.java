package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.Categories;

public interface CategoriesDAO 
{
	List<Categories> getCategories();
	String updateCategories(Categories categories);
}

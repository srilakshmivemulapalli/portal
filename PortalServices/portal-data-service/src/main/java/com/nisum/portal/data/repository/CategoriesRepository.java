package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.portal.data.domain.Categories;

public interface CategoriesRepository extends JpaRepository<Categories,Integer>{
	
	Categories findByCategoryName(final String categoryName);
	Categories findByCategoryId(Integer categoryId);

}

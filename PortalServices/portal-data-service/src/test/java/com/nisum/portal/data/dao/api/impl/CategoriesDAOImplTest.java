package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nisum.portal.data.dao.impl.CategoriesDAOImpl;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.repository.CategoriesRepository;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesDAOImplTest {

	
	@InjectMocks
	CategoriesDAOImpl categoriesDaoImpl;
	
	@Mock
	CategoriesRepository categoriesRepository;
	
	
	@Test
	public void addCategory() {
		
		boolean expected=true;
		Categories category=new Categories();
        category.setCategoryName("Hadoop");
        category.setCategoryId(1);
        
		when(categoriesRepository.findByCategoryName(category.getCategoryName())).thenReturn(null);
		when(categoriesRepository.save(category)).thenReturn(new Categories());
		
		assertEquals(2, categoriesDaoImpl.addCategory(category));
		//assertTrue(categoriesDaoImpl.addCategory(category));
		
	}
	
	@Test
	public void addCategoryFailure() {
		
		boolean expected=false;
		Categories categories=new Categories();

		//Categories categories1=null;
	
		when(categoriesRepository.findByCategoryName(categories.getCategoryName())).thenReturn(new Categories());
		
		assertEquals(0, categoriesDaoImpl.addCategory(categories));
		
		//assertFalse(categoriesDaoImpl.addCategory(categories));	
	}
	
	@Test
	public void addCategoryFailure2() {
		
		boolean expected=false;
		Categories categories=new Categories();

		//Categories categories1=null;
	
		when(categoriesRepository.findByCategoryName(categories.getCategoryName())).thenReturn(new Categories());
		
		assertEquals(1, categoriesDaoImpl.addCategory(categories));
		
		//assertFalse(categoriesDaoImpl.addCategory(categories));	
	} 
	
	@Test
	public void addCategoryF() {
		
		boolean expected=true;
		Categories category=new Categories();
        category.setCategoryName("Hadoop");
        category.setCategoryId(1);
        
		when(categoriesRepository.findByCategoryName(category.getCategoryName())).thenReturn(null);
		when(categoriesRepository.save(category)).thenReturn(null);
		System.out.println(categoriesDaoImpl.addCategory(category));
		
		assertEquals(1, categoriesDaoImpl.addCategory(category));
		
		
	}
	
}

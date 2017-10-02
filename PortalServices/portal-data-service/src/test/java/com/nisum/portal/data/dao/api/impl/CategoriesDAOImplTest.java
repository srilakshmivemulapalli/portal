package com.nisum.portal.data.dao.api.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

		Integer status = 2;
		boolean expected = true;
		Categories category = new Categories();
		category.setCategoryName("Hadoop");
		category.setCategoryId(1);

		when(categoriesRepository.findByCategoryName(category.getCategoryName())).thenReturn(null);
		when(categoriesRepository.save(category)).thenReturn(new Categories());

		assertEquals(status, categoriesDaoImpl.addCategory(category));
		// assertTrue(categoriesDaoImpl.addCategory(category));

	}
	@Test
	public void updateCategory() {
		boolean expected = true;
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		Categories category = new Categories();
		category.setCategoryId(1);
		category.setCategoryName("Hadoop");
		category.setCreateDate(timestamp);
		Mockito.when(categoriesRepository.findByCategoryId(1)).thenReturn(category);
		assertEquals(expected, categoriesDaoImpl.updateCategories(category));
		Mockito.when(categoriesRepository.findByCategoryId(2)).thenReturn(null);
		Categories cg=new Categories();
		cg.setCategoryId(2);
		//assertFalse(categoriesDaoImpl.updateCategories(cg));
	}

	@Test
	public void addCategoryFailure() {
		Integer status = 0;
		boolean expected = false;
		Categories categories = new Categories();

		// Categories categories1=null;

		when(categoriesRepository.findByCategoryName(categories.getCategoryName())).thenReturn(new Categories());

		assertEquals(status, categoriesDaoImpl.addCategory(categories));

		// assertFalse(categoriesDaoImpl.addCategory(categories));
	}

	@Test
	public void addCategoryFailure2() {
		Integer status = 1;
		boolean expected = false;
		Categories categories = new Categories();

		// Categories categories1=null;

		when(categoriesRepository.findByCategoryName(categories.getCategoryName())).thenReturn(new Categories());

		assertEquals(status, categoriesDaoImpl.addCategory(categories));

		// assertFalse(categoriesDaoImpl.addCategory(categories));
	}

	@Test
	public void addCategoryF() {
		Integer status = 1;
		boolean expected = true;
		Categories category = new Categories();
		category.setCategoryName("Hadoop");
		category.setCategoryId(1);

		when(categoriesRepository.findByCategoryName(category.getCategoryName())).thenReturn(null);
		when(categoriesRepository.save(category)).thenReturn(null);
		System.out.println(categoriesDaoImpl.addCategory(category));

		assertEquals(status, categoriesDaoImpl.addCategory(category));

	}

	@Test
	public void deleteCategoriesinDaoTest() {
		String expMesg="Success";
		/*ArrayList<Categories> categoriesList = new ArrayList<Categories>();
		Categories categories1 = new Categories();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		categories1.setCategoryId(101);
		categories1.setCategoryName("java");
		categories1.setCreateDate(timestamp);
		categoriesList.add(categories1);*/
		 String actMsg = categoriesDaoImpl.deleteCategories(101);
		assertEquals(expMesg, actMsg);
	}
	
	@Test
	public void getCategoryTest() {
		Integer id=new Integer(101);
		long millis=1505900926000L;
		Timestamp createdDate=new Timestamp(millis);
		Categories expMesg=new Categories();
		expMesg.setCategoryId(id);
		expMesg.setCategoryName("SpringBootSTS");
		expMesg.setDescription("training");
		expMesg.setCreateDate(createdDate);
		
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		when(categoriesDaoImpl.getCategory(id)).thenReturn(expMesg);
		
		Categories actMsg=categoriesDaoImpl.getCategory(id);
		assertEquals(expMesg,actMsg);
	}
	
	@Test
	public void getCategoriesTest() {
		List<Categories> categories=new ArrayList<Categories>();
		when(categoriesDaoImpl.getCategories()).thenReturn(categories);
		List<Categories> expMsg=categoriesDaoImpl.getCategories();
		assertEquals(expMsg,categories);
	}


}

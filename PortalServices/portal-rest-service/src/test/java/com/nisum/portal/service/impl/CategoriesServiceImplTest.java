package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.CategoryServiceUtil;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoriesServiceImplTest {

	@Mock
	CategoriesDAO categoriesDAO;
	
	@InjectMocks 
	CategoriesServiceImpl categoryServiceImpl;
	
	
	@Test
	public void addCategory() { 
		
		CategoriesDTO categoryDto = new CategoriesDTO();

        Date date = new Date();
        ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
        Timestamp createdDate = new Timestamp(date.getTime());
        Boolean status = true;
		categoryDto.setCreateDate(createdDate);
		Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);

		when(categoriesDAO.addCategory(category)).thenReturn(0);
		
		ServiceStatusDto serviceStatus = categoryServiceImpl.addCategory(categoryDto);
		assertEquals(serviceStatus.isStatus(), status);
	}
	
	@Test
	public void addCategoryFailure() { 
		
		CategoriesDTO categoryDto = new CategoriesDTO();

        Date date = new Date();
        ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
        Timestamp createdDate = new Timestamp(date.getTime());
      
		//false case
		 Boolean status = false;
			categoryDto.setCreateDate(createdDate);
			Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);

			when(categoriesDAO.addCategory(category)).thenReturn(1);
			
			ServiceStatusDto serviceStatus2 = categoryServiceImpl.addCategory(categoryDto);
			assertEquals(serviceStatus2.isStatus(), false);

	}
	 
	@Test
	public void addCategoryFailure1() { 
		
		CategoriesDTO categoryDto = new CategoriesDTO();

        Date date = new Date();
        ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
        Timestamp createdDate = new Timestamp(date.getTime());
      
		//false case
		 Boolean status = false;
			categoryDto.setCreateDate(createdDate);
			Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);

			when(categoriesDAO.addCategory(category)).thenReturn(2);
			
			ServiceStatusDto serviceStatus2 = categoryServiceImpl.addCategory(categoryDto);
			assertEquals(serviceStatus2.isStatus(), true);

	}
	@Test
	public void updateCategoryTest() throws CategoryServiceException 
	{ 
		Boolean status = true; 
		Date date = new Date();
        Timestamp createdDate = new Timestamp(date.getTime());
		CategoriesDTO categoryDto = new CategoriesDTO();
		categoryDto.setCategoryId(1);
		categoryDto.setCategoryName("JAVA");
		categoryDto.setCreateDate(createdDate);
		Categories categories = CategoryServiceUtil.convertDtoTODao(categoryDto);
		when(categoriesDAO.updateCategories(categories)).thenReturn(status);
		assertEquals("success",categoryServiceImpl.update(categoryDto));
		when(categoriesDAO.updateCategories(new Categories())).thenReturn(false);
		assertEquals("fail",categoryServiceImpl.update(new CategoriesDTO()));
	}
	@Test(expected=Exception.class)
	public void updateCategoryTestFailure() throws CategoryServiceException
	{
		CategoriesDTO categoryDto = new CategoriesDTO();
		categoryDto.setCategoryId(1);
		when(categoryServiceImpl.update(categoryDto)).thenThrow(new CategoryServiceException("Hello"));
	}
	@Test
	public void deleteCategoriesinServiceTest()
	{
		String expMsg="Success";
		when(categoriesDAO.deleteCategories(101)).thenReturn("Success");
	 String ActMsg= categoryServiceImpl.deleteCategories(101);
       System.out.println(ActMsg);
	      assertEquals(expMsg,ActMsg);
	}

	
}

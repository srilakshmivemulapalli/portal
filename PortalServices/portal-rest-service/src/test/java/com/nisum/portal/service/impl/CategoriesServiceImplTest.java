package com.nisum.portal.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
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
import com.nisum.portal.util.CategoryServiceUtil;
import com.nisum.portal.util.KeyConstants;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoriesServiceImplTest {

	@Mock
	private CategoriesDAO categoriesDAO;
	
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
	public void deleteCategoriesinServiceTest()
	{
		int count=1;
		String expMsg=count+" Categories deleted successfully";
		
		ArrayList<Categories> categoriesList=new ArrayList<Categories>();
		Categories categories1=new Categories();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		categories1.setCategoryId(101);
		categories1.setCategoryName("java");
		categories1.setCreateDate(timestamp);
		categoriesList.add(categories1);
		
		when(categoriesDAO.deleteCategories(categoriesList)).thenReturn(count);
		List<CategoriesDTO>  categoriesLis= CategoryServiceUtil.convertDaoTODto(categoriesList);
	 String ActMsg= categoryServiceImpl.deleteCategories(categoriesLis);

	      assertEquals(expMsg,ActMsg);
	}

	
}

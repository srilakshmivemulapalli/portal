package com.nisum.portal.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.CategoryServiceUtil;
import com.nisum.portal.util.KeyConstants;

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

		// false case
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

		// false case
		Boolean status = false;
		categoryDto.setCreateDate(createdDate);
		Categories category = CategoryServiceUtil.convertDtoTODao(categoryDto);

		when(categoriesDAO.addCategory(category)).thenReturn(2);

		ServiceStatusDto serviceStatus2 = categoryServiceImpl.addCategory(categoryDto);
		assertEquals(serviceStatus2.isStatus(), true);

	}

	@Test
	public void updateCategoryTest() throws CategoryServiceException {
		Integer status = 1;
		Date date = new Date();
		Timestamp createdDate = new Timestamp(date.getTime());
		CategoriesDTO categoryDto = new CategoriesDTO();
		categoryDto.setCategoryId(1);
		categoryDto.setCategoryName("JAVA");
		categoryDto.setCreateDate(createdDate);
		Categories categories = CategoryServiceUtil.convertDtoTODao(categoryDto);
		when(categoriesDAO.updateCategories(categories)).thenReturn(status);
		assertEquals("success", categoryServiceImpl.update(categoryDto));
		when(categoriesDAO.updateCategories(new Categories())).thenReturn(0);
		assertEquals("fail", categoryServiceImpl.update(new CategoriesDTO()));
	}

	@Test(expected = Exception.class)
	public void updateCategoryTestFailure() throws CategoryServiceException {
		CategoriesDTO categoryDto = new CategoriesDTO();
		categoryDto.setCategoryId(1);
		when(categoryServiceImpl.update(categoryDto)).thenThrow(new CategoryServiceException("Hello"));
	}

	@Test
	public void deleteCategoriesinServiceTest() {
		String expMsg = "Success";
		Integer categoryId = 101;
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(true);
		serviceStatusDto.setMessage(categoryId + KeyConstants.CATEGORY_DELETE);
		when(categoriesDAO.deleteCategories(categoryId)).thenReturn("Success");
		ServiceStatusDto serviceStatusDto1 = categoryServiceImpl.deleteCategories(101);
		assertEquals(serviceStatusDto1.isStatus(), serviceStatusDto.isStatus());
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
		
		
		when(categoriesDAO.getCategory(id)).thenReturn(expMesg);
		
		Categories actMsg=(Categories) categoryServiceImpl.getCategory(id);
		
		assertThat(actMsg).isEqualToComparingFieldByField(expMesg);
		
		
	}
	
	@Test
	public void getCategoriesTest() {
		List<CategoriesDTO> categories=new ArrayList<CategoriesDTO>();
		when(categoryServiceImpl.getCategories()).thenReturn(categories);
		List<CategoriesDTO> expMsg=categoryServiceImpl.getCategories();
		assertEquals(expMsg,categories);
	}


}

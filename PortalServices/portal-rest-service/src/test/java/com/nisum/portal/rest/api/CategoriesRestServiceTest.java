package com.nisum.portal.rest.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;

import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.KeyConstants;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoriesRestServiceTest {

	
	@InjectMocks
	CategoriesRestService mainController;
	
	@Mock
	private static Logger logger = LoggerFactory.getLogger(CategoriesRestServiceTest.class);


	@Mock
	CategoriesService categoryService;

	
	@Test
	public void addCategory() throws CategoryServiceException {

		CategoriesDTO categoryDto = new CategoriesDTO();
	
		
		logger.info("CategoriesRestService :: addCategories");
		
		ServiceStatusDto serviceStatusExpected = new ServiceStatusDto();
		serviceStatusExpected.setStatus(true);
		serviceStatusExpected.setMessage(KeyConstants.SUCCESS_MESSAGE);
		when(categoryService.addCategory(categoryDto)).thenReturn(serviceStatusExpected);
		ResponseEntity<ServiceStatusDto> serviceStatusactual = mainController.addCategory(categoryDto);

		Assert.assertEquals(serviceStatusExpected, serviceStatusactual );
		//return categoryService.addCategory(category);
	}
	@Test(expected=CategoryServiceException.class)
	public void updateCategoryTest() throws CategoryServiceException {
		ResponseEntity<String> serviceStatusExpected=new ResponseEntity<String>("Categories Updated Successfully",HttpStatus.OK);
		Timestamp createDate=new Timestamp(System.currentTimeMillis());
		CategoriesDTO categoryDto = new CategoriesDTO();
		categoryDto.setCategoryId(1);
		categoryDto.setCategoryName("JAVA");
		categoryDto.setCreateDate(createDate);
		logger.info("CategoriesRestService :: updateCategoriesTest");
		String expectedStatus="success";
		Mockito.when(categoryService.update(categoryDto)).thenReturn(expectedStatus);
		ResponseEntity<String> serviceStatusactual = mainController.updateCategories(categoryDto);
		Assert.assertEquals(serviceStatusExpected, serviceStatusactual );
		CategoriesDTO categoryDto1 = new CategoriesDTO();
		categoryDto.setCategoryId(2);
		ResponseEntity<String> serviceStatusactualf = mainController.updateCategories(categoryDto1);
		when(serviceStatusactualf).thenThrow(new CategoryServiceException());
	}
	@Test
	public void deleteCategoriesTest() throws CategoryServiceException 
	{
	  String message=" Categories deleted successfully";
	  ResponseEntity<Object> expmsg=new ResponseEntity<Object>(message,HttpStatus.OK);
		when(categoryService.deleteCategories(101)).thenReturn("Success");
		ResponseEntity<Object> result =mainController.deletingCategories(101);
		System.out.println(result);
		assertEquals(expmsg, result);
		
	}

}

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
	
	@Test
	public void deleteCategoriesTest() throws CategoryServiceException 
	{
	  String message="Categories deleted successfully";
	  ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
	  serviceStatusDto.setStatus(true);
	  serviceStatusDto.setMessage(KeyConstants.CATEGORY_DELETE);
	  
	  ResponseEntity<Object> expmsg=new ResponseEntity<Object>(message,HttpStatus.OK);
		when(categoryService.deleteCategories(101)).thenReturn(serviceStatusDto);
		ResponseEntity<ServiceStatusDto> result =mainController.deletingCategories(101);
		System.out.println(result);
		assertEquals(serviceStatusDto, expmsg);
		
	}

}

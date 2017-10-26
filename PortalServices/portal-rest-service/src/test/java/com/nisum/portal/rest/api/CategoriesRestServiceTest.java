package com.nisum.portal.rest.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.dto.CategoriesDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.util.Constants;
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
		serviceStatusExpected.setMessage(Constants.MSG_RECORD_ADD);
		when(categoryService.addCategory(categoryDto)).thenReturn(serviceStatusExpected);
		ResponseEntity<?> serviceStatusactual = mainController.addCategory(categoryDto);

		Assert.assertEquals(serviceStatusExpected, serviceStatusactual.getBody());
		// return categoryService.addCategory(category);
	}

	@Test
	public void updateCategoryTest() throws CategoryServiceException {
		ServiceStatusDto serviceStatusExpected = new ServiceStatusDto();
		serviceStatusExpected.setStatus(true);
		serviceStatusExpected.setMessage(Constants.MSG_RECORD_ADD);
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		CategoriesDTO categoryDto = new CategoriesDTO();
		categoryDto.setCategoryId(1);
		categoryDto.setCategoryName("JAVA");
		categoryDto.setCreateDate(createDate);
		logger.info("CategoriesRestService :: updateCategoriesTest");
		Mockito.when(categoryService.update(categoryDto)).thenReturn(serviceStatusExpected);
		ResponseEntity<?> serviceStatusactual = mainController.updateCategories(categoryDto);
		Assert.assertEquals(serviceStatusExpected, serviceStatusactual.getBody());
	}
	@Test
	public void updateCategoryFailureTest() throws CategoryServiceException
	{
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		CategoriesDTO categoryDto1 = new CategoriesDTO();
		categoryDto1.setCategoryId(2);
		categoryDto1.setCategoryName("JAVA");
		categoryDto1.setCreateDate(createDate);
		Errors errorStatusExpected = new Errors();
		errorStatusExpected.setErrorCode("Errors-UserRole");
		errorStatusExpected.setErrorMessage(Constants.CATEGORY_EXISTS);
		ResponseEntity<?> serviceStatusActualFailure = mainController.updateCategories(categoryDto1);
		Errors errorActual= (Errors) serviceStatusActualFailure.getBody();
		assertThat(errorActual).isEqualToComparingFieldByField(errorStatusExpected);		
	}
	@Test
	public void deleteCategoriesTest() throws CategoryServiceException {
		String message = "Categories deleted successfully";
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(true);
		serviceStatusDto.setMessage(Constants.CATEGORY_DELETE);

		ResponseEntity<Object> expmsg = new ResponseEntity<Object>(serviceStatusDto, HttpStatus.OK);
		when(categoryService.deleteCategories(101)).thenReturn(serviceStatusDto);
		ResponseEntity<?> result = mainController.deletingCategories(101);
		System.out.println(result);
		assertEquals(result.getStatusCode(), expmsg.getStatusCode());

	}
	@Test
	public void deleteCategoriesFailTest() throws CategoryServiceException {
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(false);
		serviceStatusDto.setMessage(Constants.CANT_DELETE_CATEGORY);
		Errors error = new Errors();
		error.setErrorCode("417");
	    error.setErrorMessage(Constants.CANT_DELETE_CATEGORY);
		ResponseEntity<Object> expmsg = new ResponseEntity<Object>(error, HttpStatus.EXPECTATION_FAILED);
		when(categoryService.deleteCategories(101)).thenReturn(serviceStatusDto);
		ResponseEntity<?> result = mainController.deletingCategories(101);
		System.out.println(result);
		assertEquals(result.getStatusCode(), expmsg.getStatusCode());

	}
	
	@Test
	public void categoriesTestSuccess() throws CategoryServiceException {
		logger.info("CategoriesRestServiceTest :: categoriesTest");
		List<CategoriesDTO> expMsg=new ArrayList<CategoriesDTO>();
		when(mainController.categories()).thenReturn(expMsg);
		List<CategoriesDTO> actMsg=(List<CategoriesDTO>) mainController.categories();
		assertEquals(expMsg,actMsg);
	}
	
	@Test
	public void categoryTestSuccess() throws CategoryServiceException{
		logger.info("CategoriesRestServiceTest :: categoryTest");
		
		Integer id=new Integer(101);
		long millis=1505900926000L;
		Timestamp createdDate=new Timestamp(millis);
		
		CategoriesDTO expMesg=new CategoriesDTO();
		expMesg.setCategoryId(id);
		expMesg.setCategoryName("SpringBootSTS");
		expMesg.setDescription("training");
		expMesg.setCreateDate(createdDate);
		
		when(categoryService.getCategory(id)).thenReturn(expMesg);
		
		CategoriesDTO actMesg=(CategoriesDTO)mainController.category(id);
		
		assertEquals(expMesg,actMesg);
		
	}
	
	@Test
	public void categoryTestFailure() throws CategoryServiceException{
		Integer id=new Integer(101);
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Categories");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		when(categoryService.getCategory(id)).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.category(id);
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}
	
	@Test
	public void categoriesTestFailure() throws CategoryServiceException{
		Errors errors = new Errors();
		errors.setErrorCode("Errors-Categories");
		ResponseEntity<Errors> responseEntity=new ResponseEntity<Errors>(errors, HttpStatus.OK);
		
		when(categoryService.getCategories()).thenThrow(Exception.class);
		
		ResponseEntity<Errors> actualEntity=(ResponseEntity<Errors>)mainController.categories();
		
		assertEquals(responseEntity.getStatusCode(),actualEntity.getStatusCode());
		assertEquals(responseEntity.getBody().getErrorCode(),actualEntity.getBody().getErrorCode());
	}

}

package com.nisum.portal.rest.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.data.domain.ComponentsRoleMapping;
import com.nisum.portal.service.api.PageAccessService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.PageAccessDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.exception.BlogServiceException;
import com.nisum.portal.service.exception.PageAccessServiceException;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.Constants;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/")
public class PageAccessRestService {
	private static Logger logger = LoggerFactory.getLogger(PageAccessRestService.class);

	@Autowired
	private PageAccessService pageAccessService;

	/**
	 * Returns list of components access roles
	 * 
	 * @return
	 * @throws PageAccessServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "/get/pageAccess", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getComponentsRoleMapping() throws PageAccessServiceException {
		logger.info("PageAccessRestService :: Page access roles");
		Errors error = new Errors();
		List<PageAccessDTO> pageAccessListDTO = new ArrayList<PageAccessDTO>();
		try {
			pageAccessListDTO = pageAccessService.getAllComponentsAccessRoles();
			if (pageAccessListDTO.isEmpty()) {
				error.setErrorCode("204");
				error.setErrorMessage(Constants.COMPONENT_ACCESS_NOT_AVALIABLE);
				return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<PageAccessDTO>>(pageAccessListDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("PageAccessRestService :: getComponentsRoleMapping :: Internal Server Error");
			throw new PageAccessServiceException(Constants.COMPONENT_ACCESS_NOT_AVALIABLE, e);
		}
	}

	/**
	 * Returns list of components access roles
	 * 
	 * @return
	 * @throws PageAccessServiceException
	 */
	@RequestMapping(value = "/update/pageAccess", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceStatusDto> updatePageAccess(@RequestBody List<PageAccessDTO> pageAccessList)
			throws PageAccessServiceException {
		logger.info("PageAccessRestService :: Update page access roles");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		Errors errors = new Errors();
		try {
			Object obj = pageAccessService.updateComonetsAccessRoles(pageAccessList);
			if (obj.equals(null)) {
				serviceStatusDto.setStatus(false);
				serviceStatusDto.setMessage(Constants.COMPONENT_ACCESS_UPDATE_FAILED);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NOT_FOUND);
			} else {
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(Constants.COMPONENT_ACCESS_UPDATE_SUCCESS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("PageAccessRestService :: updatePageAccess Error");
			errors.setErrorCode("Errors-Update-Access");
			errors.setErrorMessage(Constants.COMPONENT_ACCESS_UPDATE_FAILED);
			throw new PageAccessServiceException(Constants.COMPONENT_ACCESS_UPDATE_FAILED, e);
		}
	}

}

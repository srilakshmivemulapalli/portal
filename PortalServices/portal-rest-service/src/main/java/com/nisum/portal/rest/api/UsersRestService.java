package com.nisum.portal.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.UsersService;
//import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.exception.CategoryServiceException;
import com.nisum.portal.service.exception.UsersServiceException;

@RestController
@RequestMapping(value = "/user")
public class UsersRestService {
	
	private static Logger logger = LoggerFactory.getLogger(UsersRestService.class);
		
		@Autowired
		private UsersService usersService;

		/**@RestController
	@RequestMapping(value = "/user")
	
		 * damagesType
		 * @return
		 * @throws InventoryReceiveException
		 */
		@RequestMapping(value="/retrieve",method=RequestMethod.GET,produces="application/json")
		public Object users() throws UsersServiceException {
			 logger.info("UsersRestService :: users");
			return usersService.getUsers();
		}

	}



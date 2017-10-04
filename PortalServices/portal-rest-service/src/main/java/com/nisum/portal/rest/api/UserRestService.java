package com.nisum.portal.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.UserRoleService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.service.exception.UserServiceException;
import com.nisum.portal.util.CommonsUtil;
import com.nisum.portal.util.Constants;

@RestController
@RequestMapping(value = "/v1/user")
public class UserRestService {

	private static Logger logger = LoggerFactory.getLogger(CategoriesRestService.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/deleteUser/{userId}",method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId) throws UserServiceException{
		logger.info("UserRestService :: deleteUser :: Deleting User");
		Errors error = new Errors();
		try {
			String activeStatus = userService.findUserById(userId);
			ServiceStatusDto serviceStatusDTO = new ServiceStatusDto();
			if (activeStatus == null || activeStatus.equalsIgnoreCase("No")) {
				error.setErrorCode("417");
				error.setErrorMessage(Constants.USER_NOT_EXISTS);
				return new ResponseEntity<Errors>(error, HttpStatus.EXPECTATION_FAILED);
			} else {
				userService.deleteUser(userId);
				serviceStatusDTO.setMessage(Constants.USER_DELETED);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: deleteUser :: Internal Server Error");
			error.setErrorCode("500");
			error.setErrorMessage(Constants.INTERNALSERVERERROR);
			return new ResponseEntity<Errors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Returns list of users
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?>  getUsers() throws UserServiceException {
		logger.info("UserRestService :: users");
		Errors error = new Errors();
		try {
			List<UserDTO> users = userService.getUsers();
			if (users.isEmpty()) {
				error.setErrorCode("204");
				error.setErrorMessage(Constants.USERS_NOT_AVALIABLE);
				return new ResponseEntity<Errors>(error, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: getUsers :: Internal Server Error");
			error.setErrorCode("500");
			error.setErrorMessage(Constants.INTERNALSERVERERROR);
			return new ResponseEntity<Errors>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates single user
	 * @param userDto
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceStatusDto> updateUser(@RequestBody UserDTO userDto) throws UserServiceException {
		logger.info("UserRestService :: users::: update");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			Object obj = userService.updateUserDetails(userDto);
			if(obj.equals(null))
			{
				serviceStatusDto.setMessage(Constants.USER_NOT_EXISTS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
			}
			else
			{
				serviceStatusDto.setMessage(Constants.USER_NOT_EXISTS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.info("UserRestService :: UpdateUser :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}
	}

	/**
	 * Updates list of users
	 * @param usersDTO
	 * @return
	 * @throws UserServiceException
	 */

	@RequestMapping(value = "/updateUsers",method=RequestMethod.PUT,consumes = "application/json",produces="application/json")
	public ResponseEntity<ServiceStatusDto>  updateUsers(@RequestBody List<UserDTO> usersDTO) throws UserServiceException{
		logger.info("UserRestService :: multiple users update :::");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			for(UserDTO userDto : usersDTO)
			{
				userService.updateUserDetails(userDto);
			}
			serviceStatusDto.setMessage(Constants.USER_UPDATED);
			return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.info("UserRestService :: Update multiple Users :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}
	}


	//	@RequestMapping(value = "/updateUsers",method=RequestMethod.PUT,consumes = "application/json",produces="application/json")
	//	public ResponseEntity<ServiceStatusDto>  updateUsers(@RequestBody List<UserDTO> usersDTO) throws UserServiceException{
	//		logger.info("UserRestService :: multiple users update :::");
	//		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
	//		try {
	//			for(UserDTO userDto : usersDTO)
	//			{
	//			userService.updateUserDetails(userDto);
	//			}
	//			serviceStatusDto.setMessage(Constants.USER_UPDATED);
	//         return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
	//	}
	//		catch(Exception e)
	//		{
	//			logger.info("UserRestService :: Update multiple Users :: Internal Server Error");
	//			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
	//		}
	//	}


	/**
	 * getUserCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieveCount", method = RequestMethod.GET)
	public Object retrieveCount() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: getUserCount");
		return userService.getUserCount();
	}

	/**
	 * 
	 * @param userDto
	 * @return
	 * @throws UserServiceException
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UserDTO addUser(@RequestBody UserDTO userDto) throws UserServiceException {

		UserDTO userInfo=null;
		logger.info("UserRestService :: Creating Users :::");
		try {

			String strEmail1 = null;
			strEmail1 = userDto.getEmailId();
			userInfo = userService.findByEmailId(strEmail1);
			if (userInfo != null && strEmail1.equals(userInfo.getEmailId())) {
				userInfo.setLoginDate(CommonsUtil.getCurrentDateTime());
				userService.updateUserDetails(userInfo);
				//return userInfo;
			} else {

				userDto.setLoginDate(CommonsUtil.getCurrentDateTime());
				userDto.setCreateDate(CommonsUtil.getCurrentDateTime());

				List<UserRoleDTO> roleDTOs = userRoleService.getUserRole();

				UserRoleDTO role = null;

				for (UserRoleDTO dto : roleDTOs) {

					if (Constants.USER_TYPE.equalsIgnoreCase(dto.getRole())) {

						role = new UserRoleDTO();
						role.setCreatedDate(dto.getCreatedDate());
						role.setRole(dto.getRole());
						role.setRoleId(dto.getRoleId());
					}
				}
				userDto.setRole(role);
				userDto.setActiveStatus(Constants.USER_STATUS);
				userService.saveUser(userDto);

				userInfo=userDto;
			}

		}

		catch (Exception e) {
			logger.info("UserRestService :: Creating Users :: Internal Server Error");
			throw new UserServiceException(Constants.INTERNALSERVERERROR, e);
		}


		return userInfo;
	}


	/**
	 * Exception handler
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		logger.info("UserRestService :: exceptionHandler :: caught exception");
		Errors errors=new Errors();
		errors.setErrorCode("Error-User");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
}

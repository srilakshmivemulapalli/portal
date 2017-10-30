package com.nisum.portal.rest.api;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.EmailAccount;
import com.nisum.portal.service.api.TrainingsService;
import com.nisum.portal.service.api.UserService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.TrainingToUserDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.dto.TrainingFeedBackDTO;
import com.nisum.portal.service.dto.TrainingRequestDTO;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.exception.TrainingsServiceException;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.MailSender;

@RestController
@RequestMapping(value ="/v1/trainings")
public class TrainingsRestService {
	private static Logger logger = LoggerFactory.getLogger(TrainingsRestService.class);

	@Autowired
	private TrainingsService trainingsService;
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/saveTrainings", method = RequestMethod.POST)
	public ResponseEntity<?> saveTrainings(@RequestBody TrainingsDTO trainingsDTO) {
		logger.info("TrainingsRestService :: saveTrainings");
		ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
		try {
			    trainingsService.saveTrainings(trainingsDTO);
				serviceStatusDto.setStatus(true);
				serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
		    } catch (Exception e) {
				logger.error(Constants.Training_Error);
				Errors error = new Errors();
				error.setErrorCode("Error-saveTrainings");
				error.setErrorMessage(Constants.Training_Error);
				return new ResponseEntity<Errors>(error, HttpStatus.OK);
		   }
		
	}

	@RequestMapping(value="/classroomUpcoming",method = RequestMethod.GET)
	public ResponseEntity<?> classroomUpcomingTrainings(@RequestHeader("emailId") String emailId)throws TrainingsServiceException
	{
		logger.info("TrainingsRestService :: classroomUpcomingTrainings");
	    try {
			    List<TrainingsDTO> upcomingList=	trainingsService.upComingTrainings("classroom",emailId,userService);
		    		return new ResponseEntity<List<TrainingsDTO>>(upcomingList,HttpStatus.OK);
	        } catch(Exception e){
	        	logger.error(Constants.TRAINING_NOT_FETCH);
		    	     Errors error=new Errors();
		    	     error.setErrorCode("Error-upcoming Trainings");
		    	     error.setErrorMessage(Constants.TRAINING_NOT_FETCH);
		         return new ResponseEntity<Errors>(error,HttpStatus.OK);
	         }
		
	}
	@RequestMapping(value="/onlineUpcoming",method = RequestMethod.GET)
	public ResponseEntity<?> onlineUpcomingTrainings(@RequestHeader("emailId") String emailId)throws TrainingsServiceException
	{
		logger.info("TrainingsRestService :: onlineUpcomingTrainings");
	    try {
			    List<TrainingsDTO> upcomingList=	trainingsService.upComingTrainings("online",emailId,userService);
		    		return new ResponseEntity<List<TrainingsDTO>>(upcomingList,HttpStatus.OK);
	        } catch(Exception e){
	        	logger.error(Constants.TRAINING_NOT_FETCH);
		    	     Errors error=new Errors();
		    	     error.setErrorCode("Error-upcoming Trainings");
		    	     error.setErrorMessage(Constants.TRAINING_NOT_FETCH);
		         return new ResponseEntity<Errors>(error,HttpStatus.OK);
	       }
	}


	@RequestMapping(value="/completed",method = RequestMethod.GET)
	public ResponseEntity<?> completedTrainings(@RequestHeader("emailId") String emailId) throws TrainingsServiceException {
		logger.info("TrainingsRestService :: completedTrainings");
		try {
				List<TrainingsDTO> upcomingList = trainingsService.completedTrainings(emailId,userService);
				return new ResponseEntity<List<TrainingsDTO>>(upcomingList, HttpStatus.OK);
		    }catch(Exception e) {
		     	logger.error(Constants.TRAINING_NOT_FETCH);
				Errors error = new Errors();
				error.setErrorCode("Error-completed Trainings");
				error.setErrorMessage(Constants.TRAINING_NOT_FETCH);
				return new ResponseEntity<Errors>(error, HttpStatus.OK);
		   }

	}

	@RequestMapping(value="/trainingToUser",method= RequestMethod.POST)
	public ResponseEntity<?> trainingToUser(@RequestBody TrainingToUserDTO trainingToUserDTO)
	{
		logger.info("TrainingsRestService :: trainingToUser");
	   	try
		{
	   		TrainingToUserDTO trainingToUser=trainingsService.trainingToUser(trainingToUserDTO);
				/* ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
				 serviceStatusDto.setStatus(true);
				 serviceStatusDto.setMessage(Constants.TRAINING_PRESENCE);*/
				 return new ResponseEntity<TrainingToUserDTO>(trainingToUser,HttpStatus.OK);
		}catch(Exception e)
		{
				 Errors error=new Errors();
	    	         error.setErrorCode("Error-Training presence");
	    	         error.setErrorMessage(Constants.TRAINING_NOT_PRESENCE);
	    	         return new ResponseEntity<Errors>(error,HttpStatus.OK);
		}
		 
	}

	

	@RequestMapping(value = "/addFeedBack", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addTrainingFeedBack(@RequestBody TrainingFeedBackDTO trainingFeedBackDTO) {
		logger.info("TrainingsRestService :: addTrainingFeedBack ::" + trainingFeedBackDTO.toString());
		TrainingFeedBackDTO feedBackDTO = trainingsService.addTrainingFeedBack(trainingFeedBackDTO);
		if (ObjectUtils.anyNotNull(feedBackDTO))
			return new ResponseEntity<TrainingFeedBackDTO>(feedBackDTO, HttpStatus.OK);
		else {
			logger.error("FeedBack for same already Exists");
			Errors error = new Errors();
			error.setErrorCode("Errors-TrainingFeedBack");
			error.setErrorMessage(Constants.TRAINING_FEEDBACK_EXISTS);
			ResponseEntity<Errors> rsEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
			return rsEntity;
		}
	}

	@RequestMapping(value = "/addTrainingRequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addTrainingRequest(@RequestBody TrainingRequestDTO trainingRequestDTO) throws Exception {
		
		logger.info("TrainingsRestService :: addTrainingRequest ::" + trainingRequestDTO.toString());

		ServiceStatusDto servicedto = trainingsService.addTrainingRequest(trainingRequestDTO);
		if (servicedto.isStatus())
			return new ResponseEntity<ServiceStatusDto>(servicedto, HttpStatus.OK);
		else {
			logger.error("FeedBack for same already Exists");
			Errors error = new Errors();
			error.setErrorCode("Errors-TrainingRequest");
			error.setErrorMessage(Constants.TRAINING_REQUEST_EXISTS);
			ResponseEntity<Errors> rsEntity = new ResponseEntity<Errors>(error, HttpStatus.NOT_ACCEPTABLE);
			return rsEntity;
		}

	}
	@RequestMapping(value = "/getAllTrainingRequests", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Object getAllTrainingRequests() throws TrainingsServiceException{

		logger.info("TrainingsRestService :: getAllTrainingRequests ");
		try {
				return trainingsService.getAllTrainingRequests();
			}
			catch(Exception e) {
				logger.error(Constants.Training_No_Data);
				Errors error = new Errors();
				error.setErrorCode("Error-All Trainings Requests");
				error.setErrorMessage(Constants.Training_No_Data);
				return new ResponseEntity<Errors>(error, HttpStatus.OK);
			}
	}
	@RequestMapping(value = "/getAllTrainingFeedBack", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Object getAllTrainingFeedBacks() throws TrainingsServiceException{

		logger.info("TrainingsRestService :: getAllTrainingFeedBacks ");
		try {
				return trainingsService.getAllTrainingFeedBacks();
			}
			catch(Exception e) {
				logger.error(Constants.Training_No_FeedBacks);
				Errors error = new Errors();
				error.setErrorCode("Error-All Trainings FeedBacks");
				error.setErrorMessage(Constants.Training_No_FeedBacks);
				return new ResponseEntity<Errors>(error, HttpStatus.OK);
			}
	}
	@RequestMapping(value="/getTrainingFeedBackWithId/{trainingId}", method=RequestMethod.GET,consumes = "application/json", produces = "application/json")
	public Object getTrainingFeedBacksByTrainingId(@PathVariable Integer trainingId) throws TrainingsServiceException{
		logger.info("TrainingsRestService :: getTrainingFeedBackByTrainingId ");
			List<TrainingFeedBackDTO> list = trainingsService.getTrainingFeedBack(trainingId);
			if(CollectionUtils.isNotEmpty(list)){
				return list;
			}
			else {
			logger.error(Constants.Training_No_FeedBacks);
			Errors error = new Errors();
			error.setErrorCode("Error-All Trainings FeedBacks");
			error.setErrorMessage(Constants.Training_No_FeedBacks);
			return new ResponseEntity<Errors>(error, HttpStatus.OK);
		}
		
	}
	
}

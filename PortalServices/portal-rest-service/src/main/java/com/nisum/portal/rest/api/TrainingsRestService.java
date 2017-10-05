package com.nisum.portal.rest.api;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nisum.portal.service.api.TrainingsService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.TrainingsDTO;
import com.nisum.portal.service.exception.TrainingsServiceException;
import com.nisum.portal.util.Constants;

@RestController
@RequestMapping(value = "/v1/trainings")
public class TrainingsRestService {
	private static Logger logger = LoggerFactory.getLogger(TrainingsRestService.class);
	
	@Autowired
	private TrainingsService trainingsService;
	
	@RequestMapping(value="/saveTrainings",method= RequestMethod.POST)
	public ResponseEntity<?> saveTrainings(@RequestBody TrainingsDTO trainingsDTO) 
	{
		logger.info("TrainingsRestService :: saveTrainings");
		 Errors error=new Errors();
		try
		{
			 if(trainingsService.saveTrainings(trainingsDTO)!=null)
			 {
	    	         error.setErrorCode("Success");
	    	         error.setErrorMessage(Constants.MSG_RECORD_ADD);
			 }
		}
		catch(Exception e)
		{
			logger.error(Constants.Training_Error);
	    	     error.setErrorCode("Error-saveTrainings");
	    	     error.setErrorMessage(Constants.Training_Error);
		}
		return new ResponseEntity<Errors>(error,HttpStatus.OK);
	}
	
	@RequestMapping("/upcoming")
	public ResponseEntity<?> upcomingTrainings()throws TrainingsServiceException
	{
		logger.info("TrainingsRestService :: upcomingTrainings");
	    List<TrainingsDTO> upcomingList=	trainingsService.upComingTrainings();
	    if(upcomingList.size()>0)
		return new ResponseEntity<List<TrainingsDTO>>(upcomingList,HttpStatus.OK);
	    else
	    {
	    	     logger.error(Constants.Training_No_Data);
	    	     Errors error=new Errors();
	    	     error.setErrorCode("Error-upcoming Trainings");
	    	     error.setErrorMessage(Constants.Training_No_Data);
	       return new ResponseEntity<Errors>(error,HttpStatus.OK);
	    }
		
	}
	@RequestMapping("/completed")
	public ResponseEntity<?> completedTrainings()throws TrainingsServiceException
	{
		logger.info("TrainingsRestService :: completedTrainings");
	    List<TrainingsDTO> upcomingList=	trainingsService.completedTrainings();
	    if(upcomingList.size()>0)
		return new ResponseEntity<List<TrainingsDTO>>(upcomingList,HttpStatus.OK);
	    else
	    {
	     	 logger.error(Constants.Training_No_Data);
	    	     Errors error=new Errors();
	    	     error.setErrorCode("Error-completed Trainings");
	    	     error.setErrorMessage(Constants.Training_No_Data);
	       return new ResponseEntity<Errors>(error,HttpStatus.OK);
	    }
		
	}
	


}

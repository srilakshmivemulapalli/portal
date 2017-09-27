package com.nisum.portal.rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.exception.QuestionariesServiceException;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/questionaries")
public class QuestionariesRestService {
	private static Logger logger = LoggerFactory.getLogger(QuestionariesRestService.class);

	@Autowired
	private QuestionariesService questionariesService;
	
	/**
	 * Questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveAllQuestionaries() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveAllQuestionaries");
		return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionaries(), HttpStatus.OK);
	}
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieveCount", method = RequestMethod.GET)
	public ResponseEntity<Long> retrieveCount() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: questionariesCount");
		return new ResponseEntity<Long>(questionariesService.getQuestionariesCount(), HttpStatus.OK);
	}
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/save/{emailId}/{categoryId}/{question}/{description}", method = RequestMethod.GET)
	public ResponseEntity<String> saveQuestionaries(@PathVariable String emailId, @PathVariable Integer categoryId, 
			@PathVariable String question, @PathVariable String description) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: saveQuestionaries"+emailId+"-"+categoryId+"-"+question+"-"+description);
		return new ResponseEntity<String>(questionariesService.saveQuestions(emailId, categoryId, question, description), HttpStatus.OK);
	}
	
	
	/**
	 * Questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrievemyquestions/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveMyQuestionaries(@PathVariable String emailId) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveMyQuestionaries");
		return new ResponseEntity<QuestionsDTO>(questionariesService.fetchMyQuestionaries(emailId), HttpStatus.OK);
	}
	
	
	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(QuestionariesServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();
		errors.setErrorCode("Error-Categories");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}
}

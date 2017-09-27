package com.nisum.portal.rest.api;


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

import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.ReplyQuestionDTO;
import com.nisum.portal.service.exception.QuestionariesRepliesServiceException;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/questionreply")
public class QuestionRepliesRestService {
	
	private static Logger logger = LoggerFactory.getLogger(QuestionRepliesRestService.class);
	
	@Autowired
	private QuestionRepliesService questionRepliesService;
	
	
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/replyCount/{questId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> retrieveReplyCount(@PathVariable int questId) throws QuestionariesRepliesServiceException {
		return new ResponseEntity<Integer>(questionRepliesService.getQuestionariesReplyCount(questId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/retrieve/questionReply/{questId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionReplysDTO> retrieveQuestionReply(@PathVariable int questId) throws QuestionariesRepliesServiceException {
		return new ResponseEntity<QuestionReplysDTO>(questionRepliesService.getQuestionariesReply(questId), HttpStatus.OK);
	}
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ResponseEntity<String> saveQuestionReply(@RequestBody ReplyQuestionDTO replyQuestionDTO) throws QuestionariesRepliesServiceException {
		logger.info("QuestionRepliesRestService :: saveQuestionReply"+replyQuestionDTO.getEmailId()+"-"+replyQuestionDTO.getQuestId()+"-"+replyQuestionDTO.getDescription());
		return new ResponseEntity<String>(questionRepliesService.saveQuestionariesReply(replyQuestionDTO.getQuestId(), replyQuestionDTO.getEmailId(), replyQuestionDTO.getDescription()), HttpStatus.OK);
	}
	
	
	/**
	 * exceptionHandler
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(QuestionariesRepliesServiceException.class)
	public ResponseEntity<Errors> exceptionHandler(Exception ex) {
		Errors errors = new Errors();
		errors.setErrorCode("Error-Categories");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}

}

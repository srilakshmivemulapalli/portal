package com.nisum.portal.rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.ReplyQuestionDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.QuestionariesRepliesServiceException;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.util.Constants;

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
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<QuestionRepliesDTO> saveQuestionReply(@RequestBody ReplyQuestionDTO replyQuestionDTO) throws QuestionariesRepliesServiceException {
		logger.info("QuestionRepliesRestService :: saveQuestionReply"+replyQuestionDTO.getEmailId()+"-"+replyQuestionDTO.getQuestionId()+"-"+replyQuestionDTO.getReplyDescription());
		return new ResponseEntity<QuestionRepliesDTO>(questionRepliesService.saveQuestionariesReply(replyQuestionDTO.getQuestionId(), replyQuestionDTO.getEmailId(), replyQuestionDTO.getReplyDescription()), HttpStatus.OK);
	}
	
	/**
	 * To save reply comment
	 * @param emailId
	 * @param comment
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST) 
	public ResponseEntity<?> saveReplyComment(@RequestHeader String emailId, @RequestBody QuestionReplyCommentsDTO comment) throws QuestionariesServiceException {
		logger.info("QuestionRepliesRestService :: saveReplyComment :: saving reply comment"); 
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			boolean question = questionRepliesService.findReplyById(comment.getid());
			if (question) {
				QuestionReplyCommentsDTO questionReplyCommentsDTO = questionRepliesService.saveReplyComment(emailId, comment);
				return new ResponseEntity<QuestionReplyCommentsDTO>(questionReplyCommentsDTO, HttpStatus.OK);
			} else {
				serviceStatusDto.setMessage(Constants.QUESTION_NOT_EXIST);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception e) {
			logger.info("QuestionRepliesRestService :: saveReplyComment :: Internal Server Error");
			throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR);
		}
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

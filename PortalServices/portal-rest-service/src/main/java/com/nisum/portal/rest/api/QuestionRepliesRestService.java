package com.nisum.portal.rest.api;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import com.nisum.portal.data.domain.QuestionReplies;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.repository.QuestionRepliesRepository;
import com.nisum.portal.service.api.EmailAccount;
import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.QuestionRepliesDTO;
import com.nisum.portal.service.dto.QuestionReplyCommentsDTO;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.dto.ReplyQuestionDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.QuestionariesRepliesServiceException;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.MailSender;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/questionreply")
public class QuestionRepliesRestService {
	
	private static Logger logger = LoggerFactory.getLogger(QuestionRepliesRestService.class);
	
	@Autowired
	private QuestionRepliesService questionRepliesService;
	
	@Autowired
	private QuestionRepliesRepository questionRepliesRepository;   

	private static EmailAccount emailAccount;
	
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
	 * @throws Exception 
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<QuestionRepliesDTO> saveQuestionReply(@RequestBody ReplyQuestionDTO replyQuestionDTO) throws Exception {
		logger.info("QuestionRepliesRestService :: saveQuestionReply"+replyQuestionDTO.getEmailId()+"-"+replyQuestionDTO.getQuestionId()+"-"+replyQuestionDTO.getReplyDescription());
		
		List<QuestionReplies> lstEmail=questionRepliesRepository.getQuestionariesReply(replyQuestionDTO.getQuestionId());

		Questionaries questionaries=  questionRepliesRepository.findByuserEmail(replyQuestionDTO.getQuestionId());

		String userName[]=questionaries.getEmailId().split("@");

		QuestionRepliesDTO questionRepliesDTO=questionRepliesService.saveQuestionariesReply(replyQuestionDTO.getQuestionId(), replyQuestionDTO.getEmailId(), replyQuestionDTO.getReplyDescription());
		
		MailSender.sendEmail(emailAccount.getAdminemail(), emailAccount.getAdminpassword(),
				questionaries.getEmailId(),MailSender.removeLastChar(getEmailIds(lstEmail)), emailAccount.getSubQuestToAnser(), MailSender.ReplayToQuestionBody(userName[0], replyQuestionDTO.getReplyDescription(),String.valueOf(replyQuestionDTO.getQuestionId())));

		
		return new ResponseEntity<QuestionRepliesDTO>(questionRepliesDTO, HttpStatus.OK);
	}
	
	/**
	 * To save reply comment
	 * @param emailId
	 * @param comment
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST) 
	public ResponseEntity<?> saveReplyComment(@RequestHeader String emailId, @RequestBody QuestionReplyCommentsDTO comment) throws QuestionariesRepliesServiceException {
		logger.info("QuestionRepliesRestService :: saveReplyComment :: saving reply comment"); 
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			boolean question = questionRepliesService.findReplyById(comment.getid());
			if (question) {
				QuestionReplyCommentsDTO questionReplyCommentsDTO = questionRepliesService.saveReplyComment(emailId, comment);
				return new ResponseEntity<QuestionReplyCommentsDTO>(questionReplyCommentsDTO, HttpStatus.OK);
			} else {
				serviceStatusDto.setMessage(Constants.REPLY_NOT_EXISTS);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception e) {
			logger.info("QuestionRepliesRestService :: saveReplyComment :: Internal Server Error");
			throw new QuestionariesRepliesServiceException(Constants.INTERNALSERVERERROR);
		}
	}
	
	/**
	 * Used to get list of question for replied by a particular email id
	 * @param emailId
	 * @return list of questions
	 * @throws QuestionariesRepliesServiceException
	 */
	@RequestMapping(value = "/retrieve/myReplyQuestions/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveMyReplyQuestions(@PathVariable String emailId) throws QuestionariesRepliesServiceException {
		try {
			logger.info("QuestionRepliesRestService :: retriveMyReplyQuestion");
			return new ResponseEntity<QuestionsDTO>(questionRepliesService.fetchMyReplyQuestions(emailId), HttpStatus.OK);
		} catch(Exception e) {
			logger.info("QuestionRepliesRestService :: retriveMyReplyQuestions :: Internal Server Error");
			throw new QuestionariesRepliesServiceException(Constants.INTERNALSERVERERROR);
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

	
	@Autowired
	public void setEmailAccount(EmailAccount emailAccount) {
		QuestionRepliesRestService.emailAccount = emailAccount;
	}

	private String getEmailIds(List<QuestionReplies> lstEmail) {
		StringBuilder toEmail = new StringBuilder();
		Set<String> set=new HashSet<String>();
		for(QuestionReplies questionaries : lstEmail){

			toEmail.append(questionaries.getEmailid());
			toEmail.append(",");

		}
		String email[]=toEmail.toString().split(",");
		for(String str : email){
			set.add(str);
		}
		StringBuilder emailNames=new StringBuilder();
		Iterator<String> itr=set.iterator();
		while(itr.hasNext()){

			emailNames.append(itr.next());
			emailNames.append(",");
		}
		return emailNames.toString();
	}
}

package com.nisum.portal.rest.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.dto.AddQuestionDTO;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.PageableDTO;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.util.Constants;

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
	@RequestMapping(value = "/retrieve/allQuestions", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveAllQuestionaries(@RequestHeader("EmailId") String emailId) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveAllQuestionaries");
		System.out.println("EmailId"+emailId);
		return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionaries(), HttpStatus.OK);
	}
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieveCount", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveCount() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retrieveCount");
		try {
			return new ResponseEntity<CountDTO>(questionariesService.getQuestionariesCount(), HttpStatus.OK);
		}
		catch(Exception e) {
			logger.error("QuestionariesRestService :: retrieveCount");
			Errors errors=new Errors();
			errors.setErrorCode("Errors-Questionaries");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<ServiceStatusDto> saveQuestionaries(@RequestBody AddQuestionDTO questionDTO) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: saveQuestionaries"+questionDTO.getEmailId()+"-"+questionDTO.getCategoryId()+"-"+questionDTO.getQuestion()+"-"+questionDTO.getQuestion());
		ServiceStatusDto serviceStatusDto=new ServiceStatusDto();
		serviceStatusDto.setStatus(true);
		serviceStatusDto.setMessage(Constants.MSG_RECORD_ADD);
		questionariesService.saveQuestions(questionDTO.getEmailId(), questionDTO.getCategoryId(), questionDTO.getQuestion(), questionDTO.getQuestion());
		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
	}
	
	
	/**
	 * Questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/myQuestions/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveMyQuestionaries(@PathVariable String emailId) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveMyQuestionaries");
		return new ResponseEntity<QuestionsDTO>(questionariesService.fetchMyQuestionaries(emailId), HttpStatus.OK);
	}
	
	
	/**
	 * Questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/unanswQuestions", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveAllUnansweredQuestionaries() throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveAllUnansweredQuestionaries");
		return new ResponseEntity<QuestionsDTO>(questionariesService.retriveAllUnansweredQuestionaries(), HttpStatus.OK);
	}
	
	/**
	 * To save Question comment
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST) 
	public ResponseEntity<?> saveQuestionComment(@RequestHeader String emailId, @RequestBody QuestionariesCommentsDTO comment) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: saveQuestionComment :: saving question comment"); 
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			boolean question = questionariesService.findQuestionById(comment.getid());
			if (question) {
				QuestionariesCommentsDTO questionariesCommentsDTO= questionariesService.saveQuestionComment(emailId, comment);
				return new ResponseEntity<QuestionariesCommentsDTO>(questionariesCommentsDTO, HttpStatus.OK);
			} else {
				serviceStatusDto.setMessage(Constants.QUESTION_NOT_EXIST);
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception e) {
			logger.info("QuestionariesRestService :: saveQuestionComment :: Internal Server Error");
			throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR);
		}
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

	/**
	 * QuestionariesByCategoryThroughPagination
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/questionsByCategory/{categoryId}", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<QuestionsDTO> retriveQuestionariesByCategoryThroughPagination(@RequestHeader("EmailId") String emailId, @PathVariable Integer categoryId, @RequestBody PageableDTO pageableDto) throws QuestionariesServiceException {
		//logger.info("QuestionariesRestService :: retriveQuestionariesByCategoryThroughPagination(PageNumber: "+ pageable.getPageNumber()+", PageSize: "+pageable.getPageSize()+")");
		logger.info("QuestionariesRestService :: retriveQuestionariesByCategoryThroughPagination(PageNumber: "+ pageableDto.getPage()+", PageSize: "+pageableDto.getSize()+")");
		
		System.out.println("EmailId"+emailId);
		if(categoryId==0) {
			return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionariesByPagination(new PageRequest(pageableDto.getPage(), pageableDto.getSize(), Sort.Direction.ASC,Constants.SORT_BY_ELEMENT)), HttpStatus.OK);
			//return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionariesByPagination(new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "questionId")), HttpStatus.OK);
		}else {	
			return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionariesByCategory(categoryId, new PageRequest(pageableDto.getPage(), pageableDto.getSize(), Sort.Direction.ASC,Constants.SORT_BY_ELEMENT)), HttpStatus.OK);
			//return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionariesByCategory(categoryId, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "questionId")), HttpStatus.OK);
		}
	}
}

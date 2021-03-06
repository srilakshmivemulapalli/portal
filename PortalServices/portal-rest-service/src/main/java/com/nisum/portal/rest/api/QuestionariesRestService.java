package com.nisum.portal.rest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.repository.UserProfileRepository;
import com.nisum.portal.service.api.CategoriesService;
import com.nisum.portal.service.api.EmailAccount;
import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.dto.AddQuestionDTO;
import com.nisum.portal.service.dto.CountDTO;
import com.nisum.portal.service.dto.Errors;
import com.nisum.portal.service.dto.QuestionariesCommentsDTO;
import com.nisum.portal.service.dto.QuestionsDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.QuestionariesServiceException;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.MailSender;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/questionaries")
public class QuestionariesRestService {
	private static Logger logger = LoggerFactory.getLogger(QuestionariesRestService.class);

	@Autowired
	private QuestionariesService questionariesService;

	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private CategoriesDAO categoriesDAO;
	
	@Value("${category.importexcel}")
	private String CATEGORY_TYPE_IMPORT;

	Map<String, Object> categiriesMap = new HashMap<String, Object>();



	private static EmailAccount emailAccount;

	/**
	 * Questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/allQuestions", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveAllQuestionaries(@RequestHeader("EmailId") String emailId)
			throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveAllQuestionaries");
		System.out.println("EmailId" + emailId);
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
		} catch (Exception e) {
			logger.error("QuestionariesRestService :: retrieveCount");
			Errors errors = new Errors();
			errors.setErrorCode("Errors-Questionaries");
			errors.setErrorMessage(e.getMessage());
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
	}

	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveQuestionaries(@RequestBody AddQuestionDTO questionDTO) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: saveQuestionaries" + questionDTO.getEmailId() + "-"
				+ questionDTO.getCategoryId() + "-" + questionDTO.getQuestion() + "-" + questionDTO.getQuestion());
		try {
			List<User> list = userProfileRepository.findByCategoryId(questionDTO.getCategoryId());

			String status = questionariesService.saveQuestions(questionDTO.getEmailId(), questionDTO.getCategoryId(),
					questionDTO.getQuestion(), questionDTO.getQuestion());
			ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
			if (status.equalsIgnoreCase(Constants.CATEGORY_NOT_EXIST)) {
				Errors error = new Errors();
				error.setErrorCode("417");
				error.setErrorMessage(status);
				return new ResponseEntity<Errors>(error, HttpStatus.EXPECTATION_FAILED);	 
			} else {
				serviceStatusDto.setMessage(status);
				StringBuilder toEmail = new StringBuilder();

				for (User user : list) {

					toEmail.append(user.getEmailId());
					toEmail.append(",");
				}

				if (toEmail.toString() != null && !toEmail.toString().equals("")) {
					MailSender.sendEmail(emailAccount.getAdminemail(), emailAccount.getAdminpassword(),
							MailSender.removeLastChar(toEmail.toString()), null, emailAccount.getQuestionSub(),
							MailSender.questionMsgBody("Users", questionDTO.getQuestion(), questionDTO.getDescription()));
				}
				return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);	
			}
		} catch(Exception e) {
			throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR);
		}
	}

	/**
	 * Update questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ServiceStatusDto> updateQuestionaries(@RequestBody AddQuestionDTO questionDTO)throws QuestionariesServiceException {

	logger.info("QuestionariesRestService *****:: updateQuestionaries" + questionDTO.getEmailId() + "-"
	+ questionDTO.getQuestionId() + "-" + questionDTO.getCategoryId() + "-" + questionDTO.getQuestion()
	+ "-" + questionDTO.getQuestion());

	ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
	serviceStatusDto.setStatus(true);
	serviceStatusDto.setMessage(Constants.MSG_RECORD_UPDATE);
	logger.info("in side service ********serice..");
	try {
	boolean question = questionariesService.findQuestionById(questionDTO.getQuestionId());
	if (question) {
	questionariesService.updateQuestion(questionDTO.getQuestionId(),questionDTO.getCategoryId(),questionDTO.getQuestion(),questionDTO.getDescription(),questionDTO.getEmailId());
	  return new ResponseEntity<ServiceStatusDto>(serviceStatusDto,HttpStatus.OK);
	}else {
	serviceStatusDto.setMessage(Constants.QUESTION_NOT_EXIST);
	serviceStatusDto.setStatus(false);
	return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.EXPECTATION_FAILED);
	}
	} catch (Exception e) {
	logger.info("QuestionariesRestService :: saveQuestionComment :: Internal Server Error");
	throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR);
	}
	}



	/**
	 * Questionaries
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/myQuestions/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retriveMyQuestionaries(@PathVariable String emailId)
			throws QuestionariesServiceException {
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
		return new ResponseEntity<QuestionsDTO>(questionariesService.retriveAllUnansweredQuestionaries(),
				HttpStatus.OK);
	}

	/**
	 * To save Question comment
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public ResponseEntity<?> saveQuestionComment(@RequestHeader String emailId,
			@RequestBody QuestionariesCommentsDTO comment) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: saveQuestionComment :: saving question comment");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		try {
			boolean question = questionariesService.findQuestionById(comment.getid());
			if (question) {
				QuestionariesCommentsDTO questionariesCommentsDTO = questionariesService.saveQuestionComment(emailId,
						comment);
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
		errors.setErrorCode("Error-Questionaries");
		errors.setErrorMessage(ex.getMessage());
		return new ResponseEntity<Errors>(errors, HttpStatus.OK);
	}

	/**
	 * QuestionariesByCategoryThroughPagination
	 * 
	 * @return latest questionaries based on category through pagination
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/allQuestions/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retrieveQuestionariesByCategoryThroughPagination(
			@PathVariable Integer categoryId, Pageable pageable, String searchKey) throws QuestionariesServiceException {

		logger.info("QuestionariesRestService :: retrieveQuestionariesByCategoryThroughPagination(PageNumber: "
				+ pageable.getPageNumber() + ", PageSize: " + pageable.getPageSize() + ")");
		try {
				if (categoryId == 0) { 
				    if(searchKey != null && !searchKey.equals("")&&!searchKey.equals("undefined")){
				    	return new ResponseEntity<QuestionsDTO>(
								questionariesService.getQuestionariesBySearchKeyPagination(new PageRequest(pageable.getPageNumber(),
										pageable.getPageSize(), Sort.Direction.DESC, Constants.SORT_BY_ELEMENT),searchKey),
								HttpStatus.OK);
				    }else {
				    	return new ResponseEntity<QuestionsDTO>(
								questionariesService.getQuestionariesByPagination(new PageRequest(pageable.getPageNumber(),
										pageable.getPageSize(), Sort.Direction.DESC, Constants.SORT_BY_ELEMENT)),
								HttpStatus.OK);
				    }
					
				} else {
					return new ResponseEntity<QuestionsDTO>(questionariesService.getQuestionariesByCategory(categoryId,
							new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC,
									Constants.SORT_BY_ELEMENT)),
							HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.info("QuestionariesRestService :: retrieveQuestionariesByCategoryThroughPagination :: Internal Server Error");
			throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR); 
		}
	}

	/**
	 * UnAnsweredQuestionaries
	 * 
	 * @return unanswered questionaries based on category through pagination
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/unanswQuestions/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retrieveAllUnansweredQuestionariesByCategory(@PathVariable Integer categoryId,
			Pageable pageable) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveAllUnansweredQuestionariesByCategory()");
		try {
				if (categoryId == 0) {  
					return new ResponseEntity<QuestionsDTO>(questionariesService
							.retrieveAllUnansweredQuestionariesByPagination(new PageRequest(pageable.getPageNumber(),
									pageable.getPageSize(), Sort.Direction.DESC, Constants.SORT_BY_ELEMENT)),
							HttpStatus.OK); 
				} else {
					return new ResponseEntity<QuestionsDTO>(questionariesService
							.retrieveAllUnansweredQuestionariesByCategory(categoryId, new PageRequest(pageable.getPageNumber(),
									pageable.getPageSize(), Sort.Direction.DESC, Constants.SORT_BY_ELEMENT)),
							HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.info("QuestionariesRestService :: retriveAllUnansweredQuestionariesByCategory :: Internal Server Error");
			throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR);
		}
	}

	/**
	 * MyQuestionaries
	 * 
	 * @return Questionaries based on emailId and category through pagination
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieve/myQuestions/{emailId}/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<QuestionsDTO> retrieveMyQuestionariesByCategory(@PathVariable String emailId,
			@PathVariable Integer categoryId, Pageable pageable) throws QuestionariesServiceException {
		logger.info("QuestionariesRestService :: retriveMyQuestionariesByCategory(Email id: " + emailId
				+ ", categoryId:" + categoryId);
		try {
				if (categoryId == 0 && pageable.getPageSize() == 20) {
					return retriveMyQuestionaries(emailId);
				} else if (categoryId == 0) {
					return new ResponseEntity<QuestionsDTO>(questionariesService.fetchMyQuestionariesByPagination(emailId,
							new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC,
									Constants.SORT_BY_ELEMENT)),
							HttpStatus.OK);
				}else {
				return new ResponseEntity<QuestionsDTO>(questionariesService.fetchMyQuestionariesByCategory(emailId, categoryId,
						new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC,
								Constants.SORT_BY_ELEMENT)),
						HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.info("QuestionariesRestService :: retriveMyQuestionariesByCategory :: Internal Server Error");
			throw new QuestionariesServiceException(Constants.INTERNALSERVERERROR);
		}
	}

	@PostConstruct
	public Map<String, Object> getCategories() {
		List<Categories> list = categoriesDAO.getCategories();
		for (int i = 0; i < list.size(); i++) {
			Categories cList = list.get(i);
			categiriesMap.put(cList.getCategoryName().toLowerCase(), cList);
		}
		return categiriesMap;
	}

	@RequestMapping(value = "/processImportExcel", method = RequestMethod.POST)
	public ResponseEntity processExcel(Model model, @RequestParam("excelfile") MultipartFile excelfile, HttpServletRequest request) {
		String emailId = request.getParameter("emailId");
		Categories categories = null;
		ResponseEntity responseEntity = null;
		int k = 1;
		try {
			List<Questionaries> questionariesList = new ArrayList<>();
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			Questionaries questionaries = null;
			if (!(workbook == null)) {
				int noOfSheets = workbook.getNumberOfSheets();
				Categories importCategories = (Categories) categiriesMap.get(CATEGORY_TYPE_IMPORT);
				for (int j = 0; j < noOfSheets; j++) {
					XSSFSheet worksheet = workbook.getSheetAt(j);
					if (worksheet.getRow(1) !=null) {
						XSSFRow row = null;
						k = 1;
						int lastRowNum = worksheet.getLastRowNum();
						while (k <= lastRowNum) {
							categories = importCategories;
							questionaries = new Questionaries();
							row = worksheet.getRow(k);
							if (row != null) {
								String category = row.getCell(0).getStringCellValue().toLowerCase();
								if (categiriesMap.containsKey(category)) {
									categories = (Categories) categiriesMap.get(category);
								}
								questionaries.setCategoryId(categories);
								questionaries.setQuestion(row.getCell(1).getStringCellValue());
								questionaries.setDescription(row.getCell(2).getStringCellValue());
								questionariesList.add(questionaries);
							}
							k++;
						}
					} else {
						Errors errors = new Errors();
						errors.setErrorCode("200");
						errors.setErrorMessage("Please Upload valid excel with formated data");
						return new ResponseEntity<Errors>(errors, HttpStatus.OK);
					}
				}
				List<ResponseEntity<?>> responseList = new ArrayList<>();
				for (Questionaries listOfQuestionaries : questionariesList) {
					AddQuestionDTO addQuestionDTO = new AddQuestionDTO();
					addQuestionDTO.setCategoryId(listOfQuestionaries.getCategoryId().getCategoryId());
					addQuestionDTO.setDescription(listOfQuestionaries.getDescription());
					addQuestionDTO.setQuestion(listOfQuestionaries.getQuestion());
					addQuestionDTO.setQuestionId(listOfQuestionaries.getQuestionId());
					addQuestionDTO.setEmailId(emailId);
					ResponseEntity<?> serviceStatusDto = saveQuestionaries(addQuestionDTO);
					responseList.add(serviceStatusDto);
				}
				responseEntity = new ResponseEntity(responseList, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			Errors errors = new Errors();
			errors.setErrorCode("200");
			errors.setErrorMessage("Please Upload valid excel");
			return new ResponseEntity<Errors>(errors, HttpStatus.OK);
		}
		return responseEntity;
	}

	@Autowired
	public void setEmailAccount(EmailAccount emailAccount) {
		QuestionariesRestService.emailAccount = emailAccount;
	}
}

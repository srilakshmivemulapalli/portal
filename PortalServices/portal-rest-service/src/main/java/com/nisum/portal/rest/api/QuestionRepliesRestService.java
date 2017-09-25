package com.nisum.portal.rest.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.QuestionRepliesService;
import com.nisum.portal.service.dto.QuestionReplysDTO;
import com.nisum.portal.service.exception.QuestionariesServiceException;

/**
 * @author nisum
 */
@RestController
@RequestMapping(value = "/v1/questionreply")
public class QuestionRepliesRestService {
	
	@Autowired
	private QuestionRepliesService questionRepliesService;
	
	
	
	/**
	 * questionariesCount
	 * 
	 * @return
	 * @throws QuestionariesServiceException
	 */
	@RequestMapping(value = "/retrieveReplyCount/{questId}", method = RequestMethod.GET)
	public Integer retrieveReplyCount(@PathVariable int questId) throws QuestionariesServiceException {
		return questionRepliesService.getQuestionariesReplyCount(questId);
	}
	
	@RequestMapping(value = "/retrieveQuestionReply/{questId}", method = RequestMethod.GET)
	public QuestionReplysDTO retrieveQuestionReply(@PathVariable int questId) throws QuestionariesServiceException {
		return questionRepliesService.getQuestionariesReply(questId);
	}

}

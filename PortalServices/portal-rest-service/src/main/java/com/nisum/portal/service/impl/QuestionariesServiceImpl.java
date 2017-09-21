package com.nisum.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.QuestionariesDAO;
import com.nisum.portal.data.domain.Questionaries;
import com.nisum.portal.service.api.QuestionariesService;
import com.nisum.portal.service.dto.QuestionariesDTO;
import com.nisum.portal.util.QuestionariesUtil;

@Service
public class QuestionariesServiceImpl implements QuestionariesService{

	private static Logger logger = LoggerFactory.getLogger(QuestionariesServiceImpl.class);
	@Autowired
	private QuestionariesDAO questionariesDAO;
	
	@Override
	public List<QuestionariesDTO> getQuestionaries() {
		List<Questionaries> questionariesList = questionariesDAO.getQuestionaries();
		return QuestionariesUtil.convertDaoToDto(questionariesList);
	}

}

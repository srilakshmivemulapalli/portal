package com.nisum.portal.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nisum.portal.data.dao.api.CategoriesDAO;
import com.nisum.portal.data.dao.api.NotificationsDAO;
import com.nisum.portal.data.domain.Notifications;
import com.nisum.portal.data.repository.NotificationsRepository;
import com.nisum.portal.util.NotificationsUtil;

@Aspect
@Component
public class NotificationAspect {
private static Logger logger = LoggerFactory.getLogger(NotificationAspect.class);

@Autowired
NotificationsRepository notificationsRepository;
@Autowired
private CategoriesDAO categoriesDAO;
@Autowired
private NotificationsDAO notificationsDAO;

@AfterReturning(value = "execution(* com.nisum.portal.service.impl.QuestionariesServiceImpl.saveQuestions(..)) and args(emailId, categoryId, question, description)")
public void afterQuestionAdvice(JoinPoint joinPoint, String emailId, Integer categoryId, String question, String description) {
int questionId=notificationsRepository.fetchMaxQuestionTd();
logger.info("NotificationAspect :: afterQuestionAdvice");
Notifications notifications = NotificationsUtil.convertDtoToDao(questionId, categoriesDAO.getCategory(categoryId),emailId);
notificationsDAO.saveNotificationsQuestionaries(notifications);
}

}

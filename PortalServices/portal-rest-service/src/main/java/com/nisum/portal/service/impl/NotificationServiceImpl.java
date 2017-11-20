package com.nisum.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.NotificationsDAO;
import com.nisum.portal.data.dao.api.ProfileSettingsDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.NotificationUserMapping;
import com.nisum.portal.data.domain.Notifications;
import com.nisum.portal.data.repository.NotificationsRepository;
import com.nisum.portal.service.api.NotificationService;
import com.nisum.portal.service.dto.NotificationsDTO;
import com.nisum.portal.service.dto.NotificationsDetailsDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.NotificationsUtil;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	NotificationsDAO notificationsDAO;
	@Autowired
	ProfileSettingsDAO profileSettingsDAO;
	@Autowired
	NotificationsRepository notificationsRepository;

	@Override
	public String updateNotification(NotificationsDTO notificationDTO) {
		logger.info("NotificationServiceImpl :: updateNotification");
		NotificationUserMapping notificationUserMapping = NotificationsUtil
				.convertDtoToDao(notificationDTO.getNotificationId(), Constants.YES, notificationDTO.getEmailId());
		notificationsDAO.updateNotification(notificationUserMapping);

		return Constants.MSG_RECORD_UPDATE;
	}

	@SuppressWarnings("unused")
	public NotificationsDetailsDTO retriveAllUnreadNotifications(String emailId) {
		
			logger.info("NotificationServiceImpl :: retriveAllUnreadNotifications(emailId: ");
			emailId = emailId.substring(0, emailId.indexOf("@")) + "@nisum.com";
			int userId = profileSettingsDAO.getUserByEmail(emailId).getUserId();
			List<Categories> categoryList = (List<Categories>) notificationsRepository.getCategoryByUserId(userId);
			Integer categoryId = null;

			List<Notifications> notificationsList = new ArrayList<>();
			List<Notifications> finalList = new ArrayList<>();
			for (Categories category : categoryList) {
				categoryId = category.getCategoryId();
				notificationsList = notificationsDAO.getByNotificationsCategoriesCategoryId(category, emailId);
				finalList.addAll(notificationsList);

			}
			NotificationsDetailsDTO notificationsDetailsDTO = new NotificationsDetailsDTO();
			return NotificationsUtil.convertDaoToDto(finalList, notificationsDetailsDTO);

	}
}


package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.NotificationsDAO;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.NotificationUserMapping;
import com.nisum.portal.data.domain.Notifications;
import com.nisum.portal.data.repository.NotificationUserMappingRepository;
import com.nisum.portal.data.repository.NotificationsRepository;

@Configuration
public class NotificationsDAOImpl implements NotificationsDAO {

	private static Logger logger = LoggerFactory.getLogger(NotificationsDAOImpl.class);

	@Autowired
	NotificationsRepository notificationsRepository;

	@Autowired
	NotificationUserMappingRepository notificationsUserMappingRepository;

	@Override
	public Notifications saveNotificationsQuestionaries(Notifications notifications) {
		logger.info("NotificationsDAOImpl ::saveNotificationsQuestionaries");
		return notificationsRepository.save(notifications);
	}

	@Override
	public NotificationUserMapping updateNotification(NotificationUserMapping notificationUserMapping) {
		logger.info("NotificationsDAOImpl ::updateNotification");
		return notificationsUserMappingRepository.save(notificationUserMapping);
	}

	@Override
	public List<Notifications> getByNotificationsCategoriesCategoryId(Categories category,String emailId) {
		logger.info("NotificationsDAOImpl ::getByNotificationsCategoriesCategoryId");
		List<Notifications> retriveAllUnreadNotifications = null;
		try {
			retriveAllUnreadNotifications = notificationsRepository.getByNotificationsCategoriesCategoryId(category,emailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retriveAllUnreadNotifications;

	}

}

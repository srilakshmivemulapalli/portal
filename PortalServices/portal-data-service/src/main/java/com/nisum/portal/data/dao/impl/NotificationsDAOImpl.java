package com.nisum.portal.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.NotificationsDAO;
import com.nisum.portal.data.domain.NotificationUserMapping;
import com.nisum.portal.data.domain.Notifications;
import com.nisum.portal.data.repository.NotificationUserMappingRepository;
import com.nisum.portal.data.repository.NotificationsRepository;

@Configuration
public class NotificationsDAOImpl implements NotificationsDAO {

	@Autowired
	NotificationsRepository notificationsRepository;

	@Autowired
	NotificationUserMappingRepository notificationsUserMappingRepository;

	@Override
	public Notifications saveNotificationsQuestionaries(Notifications notifications) {
		return notificationsRepository.save(notifications);
	}

	@Override
	public NotificationUserMapping updateNotification(NotificationUserMapping notificationUserMapping) {

		return notificationsUserMappingRepository.save(notificationUserMapping);
	}

}

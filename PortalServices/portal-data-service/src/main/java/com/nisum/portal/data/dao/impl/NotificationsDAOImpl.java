package com.nisum.portal.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.NotificationsDAO;
import com.nisum.portal.data.domain.Notifications;
import com.nisum.portal.data.repository.NotificationsRepository;

@Configuration
public class NotificationsDAOImpl implements NotificationsDAO{
	
	@Autowired
	NotificationsRepository notificationsRepository;

	@Override
	public Notifications saveNotificationsQuestionaries(Notifications notifications) {
		return notificationsRepository.save(notifications);
	}
	

}

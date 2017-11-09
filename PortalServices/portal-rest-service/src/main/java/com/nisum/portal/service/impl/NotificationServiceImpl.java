package com.nisum.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.NotificationsDAO;
import com.nisum.portal.data.domain.NotificationUserMapping;
import com.nisum.portal.service.api.NotificationService;
import com.nisum.portal.service.dto.NotificationsDTO;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.NotificationsUtil;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	NotificationsDAO notificationsDAO;

	@Override
	public String updateNotification(NotificationsDTO notificationDTO) {

		logger.info("NotificationServiceImpl :: updateNotification");
		NotificationUserMapping notificationUserMapping = NotificationsUtil.convertDtoToDao(notificationDTO.getId(),
				Constants.YES, notificationDTO.getEmailId());
		notificationsDAO.updateNotification(notificationUserMapping);

		return null;
	}

}

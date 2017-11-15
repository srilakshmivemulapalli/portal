package com.nisum.portal.service.api;

import com.nisum.portal.service.dto.NotificationsDTO;
import com.nisum.portal.service.dto.NotificationsDetailsDTO;

public interface NotificationService {

	String updateNotification(NotificationsDTO notificationDTO);
	
	NotificationsDetailsDTO retriveAllUnreadNotifications(String Email);
}

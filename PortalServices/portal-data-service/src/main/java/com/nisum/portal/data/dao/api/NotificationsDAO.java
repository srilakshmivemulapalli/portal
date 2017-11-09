package com.nisum.portal.data.dao.api;

import com.nisum.portal.data.domain.NotificationUserMapping;
import com.nisum.portal.data.domain.Notifications;

public interface NotificationsDAO {
	Notifications saveNotificationsQuestionaries(Notifications notifications);

	public NotificationUserMapping updateNotification(NotificationUserMapping notificationUserMapping);

}

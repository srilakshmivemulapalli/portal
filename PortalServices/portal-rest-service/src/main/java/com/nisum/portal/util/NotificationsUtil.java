package com.nisum.portal.util;

import java.sql.Timestamp;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Notifications;

public class NotificationsUtil {

	public static Notifications convertDtoToDao(int questionId, Categories categoryId, String emailId) {
		return new Notifications(Constants.NOTIFICATION_TYPE_QUESTION,questionId,categoryId,emailId,new Timestamp(System.currentTimeMillis()));
	}
}
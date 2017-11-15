package com.nisum.portal.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.NotificationUserMapping;
import com.nisum.portal.data.domain.Notifications;
import com.nisum.portal.service.dto.NotificationsDTO;
import com.nisum.portal.service.dto.NotificationsDetailsDTO;

public class NotificationsUtil {

	public static Notifications convertDtoToDao(int questionId, Categories categoryId, String emailId) {
		return new Notifications(Constants.NOTIFICATION_TYPE_QUESTION, questionId, categoryId, emailId,
				new Timestamp(System.currentTimeMillis()));
	}

	public static NotificationUserMapping convertDtoToDao(int notificationId, String status, String email) {
		return new NotificationUserMapping(notificationId, status, email);
	}

	public static NotificationsDetailsDTO convertDaoToDto(List<Notifications> notificationsList,
			NotificationsDetailsDTO notificationsDetailsDTO) {

		List<NotificationsDTO> notificationsDTOs = new ArrayList<NotificationsDTO>();
		if (CollectionUtils.isNotEmpty(notificationsList)) {
			for (Notifications notifications : notificationsList) {
				NotificationsDTO dto = new NotificationsDTO();
				dto.setCreatedDate(notifications.getCreatedDate());
				dto.setEmailId(notifications.getEmailId());
				dto.setNotificationNavId(notifications.getNotificationNavId());
				dto.setNotificationType(notifications.getNotificationType());
				dto.setCategoryId(notifications.getCategoryId());
				dto.setNotificationId(notifications.getNotificationId());
				notificationsDTOs.add(dto);
			}
		}
		notificationsDetailsDTO.setTotalQuestionariesNotificationsCount(notificationsDTOs.size());
		notificationsDetailsDTO.setNotificationsDetails(notificationsDTOs);
		return notificationsDetailsDTO;
	}

}
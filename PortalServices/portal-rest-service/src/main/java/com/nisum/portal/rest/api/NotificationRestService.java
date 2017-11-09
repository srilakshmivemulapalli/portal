package com.nisum.portal.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.portal.service.api.NotificationService;
import com.nisum.portal.service.dto.NotificationsDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.NotificationServiceException;
import com.nisum.portal.util.Constants;

@RestController
@RequestMapping(value = "/v1/notification")
public class NotificationRestService {
	private static Logger logger = LoggerFactory.getLogger(NotificationRestService.class);

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ServiceStatusDto> updateNotification(@RequestBody NotificationsDTO notificationDTO)
			throws NotificationServiceException {

		logger.info("NotificationRestService *****:: updateNotification" + notificationDTO.getId() + "-"
				+ notificationDTO.getNotificationNavId() + "-" + notificationDTO.getNotificationType() + "-"
				+ notificationDTO.getEmailId());

		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(true);
		serviceStatusDto.setMessage(Constants.MSG_RECORD_UPDATE);

		notificationService.updateNotification(notificationDTO);

		return new ResponseEntity<ServiceStatusDto>(serviceStatusDto, HttpStatus.OK);
	}

}

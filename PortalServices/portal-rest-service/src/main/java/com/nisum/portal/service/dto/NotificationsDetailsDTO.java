package com.nisum.portal.service.dto;

import java.util.List;

public class NotificationsDetailsDTO {
	
		private List<NotificationsDTO> notificationsDetails;
		private long totalQuestionariesNotificationsCount;
		/**
		* @return the notificationsDetails
		*/
		public List<NotificationsDTO> getNotificationsDetails() {
		return notificationsDetails;
		}
		/**
		* @param notificationsDetails the notificationsDetails to set
		*/
		public void setNotificationsDetails(List<NotificationsDTO> notificationsDetails) {
		this.notificationsDetails = notificationsDetails;
		}
		/**
		* @return the totalQuestionariesNotificationsCount
		*/
		public long getTotalQuestionariesNotificationsCount() {
		return totalQuestionariesNotificationsCount;
		}
		/**
		* @param totalQuestionariesNotificationsCount the totalQuestionariesNotificationsCount to set
		*/
		public void setTotalQuestionariesNotificationsCount(long totalQuestionariesNotificationsCount) {
		this.totalQuestionariesNotificationsCount = totalQuestionariesNotificationsCount;
		}

		}
	




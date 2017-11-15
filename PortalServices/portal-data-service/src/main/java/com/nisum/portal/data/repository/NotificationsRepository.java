package com.nisum.portal.data.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisum.portal.data.domain.Categories;
import com.nisum.portal.data.domain.Notifications;
import org.springframework.data.repository.query.Param;

/**
 * @author nisum
 *
 */
@Repository
@Transactional
public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {
	/**
	 * 
	 * @param questionId
	 * @return max of questionaries
	 */

	@Transactional
	@Query(value = "SELECT max(questionId) from Questionaries")
	int fetchMaxQuestionTd();

	/**
	 * 
	 * @param categoryId
	 * @return unreadNotifications of notifications
	 */

	@Transactional
	@Query(value = "SELECT n FROM Notifications n where n.notificationId  not in (SELECT nu.notificationId FROM NotificationUserMapping nu where nu.emailId = :emailId) and n.categoryId = :category")
	List<Notifications> getByNotificationsCategoriesCategoryId(@Param("category") Categories category,@Param("emailId") String emailId);

	/**
	 * 
	 * @param userId
	 * @return categoryId of ProfileSetting
	 */

	@Transactional
	@Query(value = "SELECT categoryId from ProfileSetting where UserId =:userId")
	List<Categories> getCategoryByUserId(@Param("userId") int userId);

}

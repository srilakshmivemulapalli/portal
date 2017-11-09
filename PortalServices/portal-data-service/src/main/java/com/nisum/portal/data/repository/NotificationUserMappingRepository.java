package com.nisum.portal.data.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.portal.data.domain.NotificationUserMapping;

@Repository
@Transactional
public interface NotificationUserMappingRepository extends JpaRepository<NotificationUserMapping, Integer> {

}

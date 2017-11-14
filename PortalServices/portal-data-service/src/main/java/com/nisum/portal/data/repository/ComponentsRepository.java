package com.nisum.portal.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nisum.portal.data.domain.Components;

/**
 * @author nisum
 *
 */
public interface ComponentsRepository extends JpaRepository<Components, Integer> {
	
}

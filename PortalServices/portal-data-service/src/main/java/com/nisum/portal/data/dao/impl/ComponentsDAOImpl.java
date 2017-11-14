package com.nisum.portal.data.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.ComponentsDAO;
import com.nisum.portal.data.domain.Components;
import com.nisum.portal.data.repository.ComponentsRepository;

/**
 * @author nisum
 *
 */
@Configuration
public class ComponentsDAOImpl implements ComponentsDAO {
	
	private static Logger logger = LoggerFactory.getLogger(ComponentsDAOImpl.class);
	
	@Autowired
	ComponentsRepository componentsRepository;
	
	@Override
	public List<Components> fetchComponents() {
		logger.info("ComponentsDAOImpl :: fetchComponents :: fetching components");
		return componentsRepository.findAll();
	}
	
	@Override
	public Components saveComponents(Components components) {
		logger.info("ComponentsDAOImpl :: saveComponents :: saving components");
		return componentsRepository.save(components);
	}
}

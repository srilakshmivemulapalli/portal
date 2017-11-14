package com.nisum.portal.data.dao.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nisum.portal.data.dao.api.ComponentsRoleMappingDAO;
import com.nisum.portal.data.domain.ComponentsRoleMapping;
import com.nisum.portal.data.repository.ComponentsRoleMappingRepository;

@Configuration
public class ComponentsRoleMappingDAOImpl implements ComponentsRoleMappingDAO {

	@Autowired
	ComponentsRoleMappingRepository componentsRoleMappingRepository;
	
	@Override
	public List<ComponentsRoleMapping> fetchComponentsRolesAccess() {
		return componentsRoleMappingRepository.findAll();
	}
	
	@Override
	public void updateComponentsRolesAccess(ComponentsRoleMapping componentsRoleMapping) {
		componentsRoleMappingRepository.save(componentsRoleMapping);
	}
	
	@Override
	public List<ComponentsRoleMapping> updateComponentsRolesAccess(List<ComponentsRoleMapping> componentsRoleMappingList) {
		return componentsRoleMappingRepository.save(componentsRoleMappingList);
	}
}

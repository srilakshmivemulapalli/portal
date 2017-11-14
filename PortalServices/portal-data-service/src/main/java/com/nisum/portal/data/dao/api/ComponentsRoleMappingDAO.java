package com.nisum.portal.data.dao.api;

import java.util.List;

import com.nisum.portal.data.domain.ComponentsRoleMapping;

/**
 * @author nisum
 *
 */
public interface ComponentsRoleMappingDAO {
	
	public List<ComponentsRoleMapping> fetchComponentsRolesAccess();
	public void updateComponentsRolesAccess(ComponentsRoleMapping componentsRoleMapping);
	List<ComponentsRoleMapping> updateComponentsRolesAccess(List<ComponentsRoleMapping> componentsRoleMappingList);
}

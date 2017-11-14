package com.nisum.portal.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

import com.nisum.portal.data.domain.Components;
import com.nisum.portal.data.domain.ComponentsRoleMapping;
import com.nisum.portal.service.dto.PageAccessDTO;
import com.nisum.portal.service.dto.PageRole;
import com.nisum.portal.service.dto.UserRoleDTO;
import com.nisum.portal.data.domain.UserRole;

public class PageAccessRoleUtil {

	public static List<PageAccessDTO> convertComponentsRoleMapperDaoToPageAccessDto(
			List<ComponentsRoleMapping> componentsRoleMappingList, List<UserRole> userRoleList,
			List<Components> componentsList) {

		List<PageAccessDTO> pageAccessDTOList = new ArrayList<PageAccessDTO>();

		if (CollectionUtils.isNotEmpty(componentsRoleMappingList)) {
			for (Components component : componentsList) {
				PageAccessDTO dto = new PageAccessDTO();
				List<PageRole> pageRoleList = new ArrayList<>();
				for (ComponentsRoleMapping pageAccess : componentsRoleMappingList) {
					if (component.getComponentId() == pageAccess.getComponentId()) {
						dto.setPageName(getPageName(pageAccess, componentsList));
						pageRoleList = getRoleAccess(userRoleList, pageAccess, pageRoleList);
					}
				}
				dto.setRoles(pageRoleList);
				pageAccessDTOList.add(dto);
			}
		}
		return pageAccessDTOList;
	}

	public static List<ComponentsRoleMapping> convertPageAccessDtoToComponentsRoleMapperDao(
			List<PageAccessDTO> pageAccessList, List<Components> componentsList, List<UserRole> userRoleList) {

		List<ComponentsRoleMapping> componentsRoleMappingList = new ArrayList<ComponentsRoleMapping>();
		for (PageAccessDTO pageAccess : pageAccessList) {
			for (Components component : componentsList) {
				if (pageAccess.getPageName().equalsIgnoreCase(component.getComponentName())) {
				
					for (UserRole userRole : userRoleList) {
						ComponentsRoleMapping dao = new ComponentsRoleMapping();
						dao.setComponentId(getPageId(pageAccess, componentsList));
						List<PageRole> pageRoleList = pageAccess.getRoles();
						for (PageRole pageRole : pageRoleList) {
							if (pageRole.getRoleName().equalsIgnoreCase(userRole.getRole())) {
								dao.setRoleId(userRole.getRoleId());
								Boolean isAccessible = Boolean.valueOf(pageRole.getRoleAccess());
								dao.setIsCompAccessible(isAccessible);
							}
						}
						componentsRoleMappingList.add(dao);
					}
				}	
			}
		}
		return componentsRoleMappingList;
	}

	private static List<PageRole> getRoleAccess(List<UserRole> userRoleList, ComponentsRoleMapping pageAccess,
			List<PageRole> pageRoleList) {
		// TODO: JAVA 8
		for (UserRole userRole : userRoleList) {
			if (userRole.getRoleId() == pageAccess.getRoleId()) {
				PageRole pageRole = new PageRole();
				pageRole.setRoleAccess(Boolean.toString(pageAccess.getIsCompAccessible()));
				pageRole.setRoleName(userRole.getRole());
				pageRoleList.add(pageRole);
			}
		}
		return pageRoleList;
	}

	private static String getPageName(ComponentsRoleMapping pageAccess, List<Components> componentsList) {
		String compName = "";
		// TODO: JAVA 8
		for (Components component : componentsList) {
			if (pageAccess.getComponentId() == component.getComponentId()) {
				compName = component.getComponentName();
			}
		}
		return compName;
	}

	private static int getPageId(PageAccessDTO pageAccess, List<Components> componentsList) {
		int compId = 0;
		// TODO: JAVA 8
		for (Components component : componentsList) {
			if (pageAccess.getPageName().equalsIgnoreCase(component.getComponentName())) {
				compId = component.getComponentId();
			}
		}
		return compId;
	}

}

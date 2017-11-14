package com.nisum.portal.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.portal.data.dao.api.ComponentsDAO;
import com.nisum.portal.data.dao.api.ComponentsRoleMappingDAO;
import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.domain.Components;
import com.nisum.portal.data.domain.ComponentsRoleMapping;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.api.PageAccessService;
import com.nisum.portal.service.dto.PageAccessDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.PageAccessServiceException;
import com.nisum.portal.util.Constants;
import com.nisum.portal.util.PageAccessRoleUtil;

@Service
public class PageAccessServiceImpl implements PageAccessService {

	private static Logger logger = LoggerFactory.getLogger(PageAccessServiceImpl.class);

	@Autowired
	ComponentsRoleMappingDAO componentsRoleMappingDAO;

	@Autowired
	UserRoleDAO userRoleDao;

	@Autowired
	ComponentsDAO pageComponentsDAO;

	@Override
	public List<PageAccessDTO> getAllComponentsAccessRoles() {

		List<ComponentsRoleMapping> componentsRoleMappingList = componentsRoleMappingDAO.fetchComponentsRolesAccess();
		List<UserRole> userRoleList = userRoleDao.getUserRole();
		List<Components> componentsList = pageComponentsDAO.fetchComponents();
		return PageAccessRoleUtil.convertComponentsRoleMapperDaoToPageAccessDto(componentsRoleMappingList, userRoleList,
				componentsList);
	}

	@Override
	public ServiceStatusDto updateComonetsAccessRoles(List<PageAccessDTO> pageAccessList)
			throws PageAccessServiceException {
		logger.info("PageAccessServiceImpl :: updateComonetsAccessRoles");
		ServiceStatusDto serviceStatusDto = new ServiceStatusDto();
		serviceStatusDto.setStatus(false);
		List<Components> componentsList = pageComponentsDAO.fetchComponents();
		List<UserRole> userRoleList = userRoleDao.getUserRole();
		List<ComponentsRoleMapping> componentRoleMappingList = PageAccessRoleUtil
				.convertPageAccessDtoToComponentsRoleMapperDao(pageAccessList, componentsList, userRoleList);
		List<ComponentsRoleMapping> componentRoleMappingListDB = componentsRoleMappingDAO.fetchComponentsRolesAccess();

		for (ComponentsRoleMapping componentsRoleMapping : componentRoleMappingList) {
			for (ComponentsRoleMapping compRoleMappingDB : componentRoleMappingListDB) {
				if (componentsRoleMapping.getComponentId() == compRoleMappingDB.getComponentId()
						&& componentsRoleMapping.getRoleId() == compRoleMappingDB.getRoleId()) {
					componentsRoleMapping.setCompRoleMapId(compRoleMappingDB.getCompRoleMapId());
				}
			}
		}

	/*	int compRoleMapId = 1;
		for (ComponentsRoleMapping componentsRoleMapping : componentRoleMappingList) {
			componentsRoleMapping.setCompRoleMapId(compRoleMapId);
			compRoleMapId++;
		}
	 */
		try {
			List<ComponentsRoleMapping> componentRoleMappingListResponse = componentsRoleMappingDAO.updateComponentsRolesAccess(componentRoleMappingList);
			if (componentRoleMappingListResponse.equals(null)) {
				return serviceStatusDto;
			} else {
				serviceStatusDto.setStatus(true);
				return serviceStatusDto;
			}
		} catch (Exception e) {
			throw new PageAccessServiceException(Constants.COMPONENT_ACCESS_UPDATE_FAILED);
		}
	}

}

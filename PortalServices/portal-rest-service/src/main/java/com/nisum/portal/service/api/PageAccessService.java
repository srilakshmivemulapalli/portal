package com.nisum.portal.service.api;

import java.util.List;

import com.nisum.portal.data.domain.ComponentsRoleMapping;
import com.nisum.portal.service.dto.PageAccessDTO;
import com.nisum.portal.service.dto.ServiceStatusDto;
import com.nisum.portal.service.exception.PageAccessServiceException;

public interface PageAccessService {
	
	public List<PageAccessDTO> getAllComponentsAccessRoles();
	public ServiceStatusDto updateComonetsAccessRoles(List<PageAccessDTO> pageAccessList) throws PageAccessServiceException;
}

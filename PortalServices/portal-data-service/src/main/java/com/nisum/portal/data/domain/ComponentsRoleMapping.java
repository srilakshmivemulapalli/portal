package com.nisum.portal.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author nisum
 *
 */
@Entity
@Table(name = "Components_Role_Mapping")
public class ComponentsRoleMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "comp_role_map_id")
	private int compRoleMapId;

	@Column(name = "is_comp_accessible")
	private boolean IsCompAccessible;

	@Column(name = "component_id")
	@JoinColumn(foreignKey = @ForeignKey(name = "component_id"))
	private int componentId;

	@JoinColumn(foreignKey = @ForeignKey(name = "roleId"))
	private int roleId;

	public int getCompRoleMapId() {
		return compRoleMapId;
	}

	public void setCompRoleMapId(int compRoleMapId) {
		this.compRoleMapId = compRoleMapId;
	}

	public boolean getIsCompAccessible() {
		return IsCompAccessible;
	}

	public void setIsCompAccessible(boolean isCompAccessible) {
		IsCompAccessible = isCompAccessible;
	}

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	

}

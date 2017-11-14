package com.nisum.portal.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "components")
public class Components implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "component_id")
	private int componentId;
	@Column(name = "component_name")
	private String componentName;

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

}

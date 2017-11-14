package com.nisum.portal.data.dao.api;

import java.util.List;
import com.nisum.portal.data.domain.Components;

/**
 * @author nisum
 *
 */
public interface ComponentsDAO {
	
	public List<Components> fetchComponents();
	public Components saveComponents(Components components);
}

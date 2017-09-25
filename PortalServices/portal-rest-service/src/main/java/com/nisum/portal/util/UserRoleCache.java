package com.nisum.portal.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.portal.data.dao.api.UserRoleDAO;
import com.nisum.portal.data.dao.impl.UserRoleDAOImpl;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.data.repository.UserRoleRepository;
import com.nisum.portal.service.dto.UserRoleDTO; 

public class UserRoleCache implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4864956391586447308L;

	private static Logger logger=LoggerFactory.getLogger(UserRoleCache.class);
	
	private static UserRoleCache instance;
	@Autowired
	private UserRoleRepository userRoleDao;

	private Map<String,List<UserRole>> dataMap;
	private UserRoleCache(){
		dataMap=new ConcurrentHashMap<>();
	}
	
	public static synchronized UserRoleCache getInstance() {
		if(instance==null) {
			instance=new UserRoleCache();
		}
		return instance; 
	}
	
	public boolean containsKey(String key) {
		return dataMap.containsKey(key);
	}
	public void put(String key, List<UserRole> userRoleList) {
		dataMap.put(key, userRoleList);
	}
	public List<UserRole> get(String key){
		return dataMap.get(key); 
	}
	
	public boolean verifyUserRoleToCache(UserRoleDTO userRoleDto) {
		List<UserRole> userRoleList=instance.get("user-role");
		logger.info("checking whether data is availaible or not");
		for(UserRole user : userRoleList) {
			if(user.getRole().equalsIgnoreCase(userRoleDto.getRole())){
				return false;
			}
		}
		return true;
	}
	
	public void updateCache() {	
		List<UserRole> userRoleUpdate=userRoleDao.findAll();
		System.out.println(userRoleUpdate);
		instance.put("user-role", userRoleUpdate);
	}
 
	
	public boolean findUserRole(Integer id, String roleName) {
		List<UserRole> userRoleList=instance.get("user-role");   
		logger.info("checking whether data is availaible or not");
		for(UserRole user : userRoleList) {
			if(user.getRoleId() == id && user.getRole().equals(roleName)){
				return true;
			}
		}
		return false;
	}
	
	public void remove(int id, String roleName) {
		
		List<UserRole> userRoleDeleteList=instance.get("user-role"); 
		logger.info("UserRoleCache :: remove() "+userRoleDeleteList);
		for(UserRole userRole :  userRoleDeleteList) {
				if((userRole.getRoleId()==id)&&(userRole.getRole().equals(roleName))) {
					userRoleDeleteList.remove(userRole);
					logger.info("Given User Role"+id+"is removed from Cache "+userRoleDeleteList);
					break;
				}
		}
		//logger.info("Updated cache after deletion of userRole"+userRoleDeleteList);
		instance.put("user-role", userRoleDeleteList);
	}
}

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


	private static final long serialVersionUID = -4864956391586447308L;

	private static Logger logger=LoggerFactory.getLogger(UserRoleCache.class);
	
	private static UserRoleCache instance;
	
	@Autowired
	private UserRoleRepository userRoleDao;

	private Map<String,List<UserRole>> dataMap;
	private UserRoleCache(){
		dataMap=new ConcurrentHashMap<>();
	}
	/**
	 * 
	 * @return Cache Object
	 */
	public static synchronized UserRoleCache getInstance() {
		if(instance==null) {
			instance=new UserRoleCache();
		}
		return instance; 
	}
	/**
	 * Verifies that key("user-role") is present or not in dataMap and returns true or false
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		return dataMap.containsKey(key);
	}
	/**
	 * Adds the user role value  to cache
	 * @param key
	 * @param userRoleList
	 */
	public void put(String key, List<UserRole> userRoleList) {
		dataMap.put(key, userRoleList);
	}
	/**
	 * Returns the existing cache for the given key
	 * @param key
	 * @return 
	 */
	public List<UserRole> get(String key){
		return dataMap.get(key); 
	}
	/**
	 * Checks whether the given user role present in cache or not
	 * @param userRoleDto
	 * @return  
	 */
	public boolean verifyUserRoleToCache(UserRoleDTO userRoleDto) {
		List<UserRole> userRoleList=instance.get("user-role");
		logger.info("checking whether data is availaible or not");
		for(UserRole user : userRoleList) {
			if(user.getRole().equalsIgnoreCase(userRoleDto.getRole())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * update the cache
	 */
	public void updateCache() {	
		List<UserRole> userRoleUpdate=userRoleDao.findAll();
		System.out.println(userRoleUpdate);
		instance.put("user-role", userRoleUpdate);
	}
 
	/**
	 * checks whether user role's id exist or not
	 * @param id
	 * @return
	 */
	public boolean findUserRole(Integer id) {
		List<UserRole> userRoleList=instance.get("user-role");   
		logger.info("checking whether data is availaible or not");
		for(UserRole user : userRoleList) {
			if(user.getRoleId() == id){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * removes the user role from cache for given role id
	 * @param id
	 */
	public void remove(Integer id) {
		
		List<UserRole> userRoleDeleteList=instance.get("user-role"); 
		logger.info("UserRoleCache :: remove() "+userRoleDeleteList);
		for(UserRole userRole :  userRoleDeleteList) {
				if((userRole.getRoleId()==id)) {
					userRoleDeleteList.remove(userRole);
					logger.info("Given User Role"+id+"is removed from Cache "+userRoleDeleteList);
					break;
				}
		}
		logger.info("Updated cache after deletion of userRole"+userRoleDeleteList);
		instance.put("user-role", userRoleDeleteList);
	}
}

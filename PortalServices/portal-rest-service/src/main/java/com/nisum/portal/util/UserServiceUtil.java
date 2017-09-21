package com.nisum.portal.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nisum.portal.data.domain.User;
import com.nisum.portal.data.domain.UserRole;
import com.nisum.portal.service.dto.UserDTO;
import com.nisum.portal.service.dto.UserRoleDTO;

public class UserServiceUtil {

public static List<UserDTO> convertDaoListTODto(List<User> users) {
List<UserDTO> userDTO = new ArrayList<UserDTO>();
for(User user : users)
{
UserDTO userDto = new UserDTO();

userDto.setUserId(user.getUserId());
userDto.setActiveStatus(user.getActiveStatus());
userDto.setEmailId(user.getEmailId());
userDto.setLoginDate(user.getLoginDate());
userDto.setName(user.getName());
UserRoleDTO userRoleDTO = new UserRoleDTO();
userRoleDTO.setCreatedDate(user.getRole().getCreatedDate());
userRoleDTO.setRole(user.getRole().getRole());
userRoleDTO.setRoleId(user.getRole().getRoleId());
userDto.setUserRole(userRoleDTO);
userDTO.add(userDto);
}
return userDTO;
}


public static UserDTO convertDaoObjectTODto(User user) 
{
UserDTO userDTO = new UserDTO();
userDTO.setUserId(user.getUserId());
userDTO.setName(user.getName());
userDTO.setEmailId(user.getEmailId());
userDTO.setLoginDate(user.getLoginDate());
userDTO.setActiveStatus(user.getActiveStatus());
UserRoleDTO userRoleDTO = new UserRoleDTO();
userRoleDTO.setCreatedDate(user.getRole().getCreatedDate());
userRoleDTO.setRole(user.getRole().getRole());
userRoleDTO.setRoleId(user.getRole().getRoleId());
userDTO.setUserRole(userRoleDTO);
return userDTO;

}
public static List<User> convertDtoListTODao(List<UserDTO> userDtoList) {
List<User> users = new ArrayList<User>();
for(UserDTO userlist : userDtoList)
{
User user = new User();
user.setUserId(userlist.getUserId());
user.setActiveStatus(userlist.getActiveStatus());
user.setEmailId(userlist.getEmailId());
user.setLoginDate(userlist.getLoginDate());
user.setName(userlist.getName());
UserRole userRole = new UserRole();
userRole.setCreatedDate(userlist.getUserRole().getCreatedDate());
userRole.setRole(userlist.getUserRole().getRole());
userRole.setRoleId(userlist.getUserRole().getRoleId());
user.setRole(userRole);
users.add(user);
}
return users;
}
public static User convertDtoObjectTODao(UserDTO userdto) 
{
User user = new User();
user.setUserId(userdto.getUserId());
user.setName(userdto.getName());
user.setEmailId(userdto.getEmailId());
user.setLoginDate(userdto.getLoginDate());
user.setActiveStatus(userdto.getActiveStatus());
UserRole userRole = new UserRole();
userRole.setCreatedDate(userdto.getUserRole().getCreatedDate());
userRole.setRole(userdto.getUserRole().getRole());
userRole.setRoleId(userdto.getUserRole().getRoleId());
user.setRole(userRole);
return user;
}
}
package com.bechir.departement.services;

import com.bechir.departement.Role;
import com.bechir.departement.User;

public interface UserService {

	
	void deleteAllusers(); 
	void deleteAllRoles(); 
	User saveUser(User user); 
	User findUserByUsername (String username); 
	Role addRole(Role role); 
	
	 
	 User addRoleToUser(String username, String rolename);
	
}

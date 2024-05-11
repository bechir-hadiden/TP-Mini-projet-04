package com.bechir.departement.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bechir.departement.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{

	Role findByRole(String role); 
	
}

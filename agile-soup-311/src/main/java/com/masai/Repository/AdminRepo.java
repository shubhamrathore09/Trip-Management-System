package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByAdminMobile(String adminMobile);
	
}

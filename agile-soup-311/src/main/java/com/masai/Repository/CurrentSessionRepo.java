package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Admin;
import com.masai.model.CurrentLoginSession;

public interface CurrentSessionRepo extends JpaRepository<CurrentLoginSession, Integer>{
	
	public CurrentLoginSession findByUserKey(String key);
	public CurrentLoginSession findByUserMobile(String mobile);
	
}

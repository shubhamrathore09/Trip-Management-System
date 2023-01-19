package com.masai.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.CurrentLoginSession;

public interface CurrentSessionRepo extends JpaRepository<CurrentLoginSession, Integer>{
	
	public CurrentLoginSession findByUserKey(String userKey);
	public CurrentLoginSession findByUserMobile(String userMobile);
	
	
	@Query("select c from CurrentLoginSession c where c.authKey=?1")
	public Optional<CurrentLoginSession> findByAuthkey(String key);
}

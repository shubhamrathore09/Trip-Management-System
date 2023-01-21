package com.masai.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Bus;
import com.masai.model.Routes;

@Repository
public interface RouteRepository extends JpaRepository<Routes, Integer>{

	public Routes findByRouteCode(String routeCode);
	
	@Query("select r.buses from Routes r where r.routeFrom=?1 AND r.routeTo=?2")
	public Set<Bus> getBusByRoutes(String routeFrom,String routeTo);
	
}

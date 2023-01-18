package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Routes;

@Repository
public interface RouteRepository extends JpaRepository<Routes, Integer>{

}

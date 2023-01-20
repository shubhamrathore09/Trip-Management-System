package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus,Integer>{
	public Bus findByBusNumber(String busNumber);
}

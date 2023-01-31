package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	public Hotel findByHotelCode(String hotelCode);

	public List<Hotel> findByAddress(String address);
	
	public List<Hotel> findByFareBetween(Integer loweramount,Integer higheramount);
	
	
	

	
}
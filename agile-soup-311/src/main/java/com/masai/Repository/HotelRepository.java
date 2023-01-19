package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {


    @Query("select h from Hotel h where h.hotelName = ?1")
	List<Hotel> getHotelByName(String name);

	@Query("select h from Hotel h where h.rent = ?1")
	List<Hotel> findByCost(String rent);
	
}
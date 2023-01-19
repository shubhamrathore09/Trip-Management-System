package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}

package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Travels;

@Repository
public interface TravelsRepository extends JpaRepository<Travels, Integer>{

}

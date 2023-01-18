package com.masai.Service;

import java.util.List;

import com.masai.Exception.TravelsException;
import com.masai.model.Travels;

public interface TravelsService {

	public Travels addTravels(Travels travels) throws TravelsException;
	
	public Travels updateTravels(Travels travels) throws TravelsException;
	
	public Travels removeTravels(Integer id) throws TravelsException;
	
	public Travels getTravelsById(Integer id) throws TravelsException;
	
	public List<Travels> getAllTravels() throws TravelsException;
}

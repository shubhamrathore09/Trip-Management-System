package com.masai.Service;

import java.util.List;

import com.masai.Exception.LoginException;
import com.masai.Exception.TravelsException;
import com.masai.model.Travels;

public interface TravelsService {

	public Travels addTravels(Travels travels,String key) throws TravelsException,LoginException;
	
	public Travels updateTravels(Travels travels,String key) throws TravelsException,LoginException;
	
	public Travels removeTravels(Integer id,String key) throws TravelsException,LoginException;
	
	public Travels getTravelsById(Integer id,String key) throws TravelsException,LoginException;
	
	public List<Travels> getAllTravels(String key) throws TravelsException,LoginException;
}

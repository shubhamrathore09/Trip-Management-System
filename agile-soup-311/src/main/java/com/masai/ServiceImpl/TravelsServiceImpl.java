package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.LoginException;
import com.masai.Exception.TravelsException;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.TravelsRepository;
import com.masai.Service.TravelsService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Travels;

@Service
public class TravelsServiceImpl implements TravelsService{

	@Autowired
	private TravelsRepository travelRepo;
	
	@Autowired 
	private CurrentSessionRepo currentSessionRepo;
	
	@Override
	public Travels addTravels(Travels travels,String key) throws TravelsException,LoginException {
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Travels tr = travelRepo.save(travels);
		if(tr==null) {
			throw new TravelsException("Not Able to save travels...");
		}
		return tr;
	}

	@Override
	public Travels updateTravels(Travels travels,String key) throws TravelsException,LoginException {
		
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		
		Optional<Travels> opt = travelRepo.findById(travels.getTravelsId());
		if(opt.isEmpty()) {
			throw new TravelsException("No Travel is found with Id : "+travels.getTravelsId());
		}else {
			Travels tr = opt.get();
			travelRepo.save(travels);
			return tr;
		}		
	}

	@Override
	public Travels removeTravels(Integer id,String key) throws TravelsException,LoginException {
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<Travels> opt = travelRepo.findById(id);
		if(opt.isEmpty()) {
			throw new TravelsException("No Travel is found with Id : "+id);
		}else {
			Travels tr = opt.get();
			travelRepo.delete(tr);
			return tr;
		}
		
	}

	@Override
	public Travels getTravelsById(Integer id,String key) throws TravelsException,LoginException{
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<Travels> opt = travelRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new TravelsException("No Travel is found with Id : "+id);
		}
	}

	@Override
	public List<Travels> getAllTravels(String key) throws TravelsException,LoginException{
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		List<Travels> list = travelRepo.findAll();
		if(list.isEmpty()) {
			throw new TravelsException("No Travels found...");
		}
		return list;
	}

}

package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.TravelsException;
import com.masai.Repository.TravelsRepository;
import com.masai.Service.TravelsService;
import com.masai.model.Travels;

@Service
public class TravelsServiceImpl implements TravelsService{

	@Autowired
	private TravelsRepository travelRepo;
	
	@Override
	public Travels addTravels(Travels travels) throws TravelsException {
		Travels tr = travelRepo.save(travels);
		if(tr==null) {
			throw new TravelsException("Not Able to save travels...");
		}
		return tr;
	}

	@Override
	public Travels updateTravels(Travels travels) throws TravelsException {
		Optional<Travels> opt = travelRepo.findById(travels.getTravelsId());
		if(opt.isEmpty()) {
			throw new TravelsException("No Travel is found with Id : "+travels.getTravelsId());
		}else {
			Travels tr = opt.get();
			travelRepo.save(tr);
			return tr;
		}		
	}

	@Override
	public Travels removeTravels(Integer id) throws TravelsException {
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
	public Travels getTravelsById(Integer id) throws TravelsException {
		Optional<Travels> opt = travelRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new TravelsException("No Travel is found with Id : "+id);
		}
	}

	@Override
	public List<Travels> getAllTravels() throws TravelsException {
		List<Travels> list = travelRepo.findAll();
		if(list.isEmpty()) {
			throw new TravelsException("No Travels found...");
		}
		return list;
	}

}

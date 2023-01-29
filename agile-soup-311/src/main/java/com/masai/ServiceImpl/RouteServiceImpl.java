package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Exception.TravelsException;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.RouteRepository;
import com.masai.Service.RouteService;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Routes;
import com.masai.model.Travels;

@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;

	@Override
	public Routes addRoute(Routes routes,String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		return routeRepo.save(routes);
	}

	@Override
	public String DeleteRoute(Integer routeId,String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<Routes> routes=routeRepo.findById(routeId);
		
		if(routes.isPresent()) {
			routeRepo.delete(routes.get());
			return "route has been deleted";
		}
		else {
			return "there is no route by that id: "+routeId;
		}
	}

	@Override
	public List<Routes> routes(String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<Routes> list=routeRepo.findAll();
		
		if(list==null) {
			throw new RouteException("No routes are available");
			
		}
		return list;
	}

	@Override
	public Routes findById(Integer id,String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<Routes> routes=routeRepo.findById(id);
		if(routes.isPresent()) {
			return routes.get();
		}
		throw new RouteException("there is not route by that id: "+id);
	}

	
	
	

}

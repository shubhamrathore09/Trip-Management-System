package com.masai.ServiceImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BusException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.RouteRepository;
import com.masai.Service.BusService;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Routes;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private RouteRepository routeRepository;

	@Override
	public Bus RegistorBus(Bus bus, String key) throws BusException, LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Bus bus2=busRepository.findByBusNumber(bus.getBusNumber());
		
		if(bus2==null) {
			return busRepository.save(bus);
		}
		
		throw new BusException("Bus already present by that number: "+bus.getBusNumber());
	}

	
	
	
	@Override
	public String RemoveBus(Integer id, String key) throws BusException, LoginException {
		
	CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<Bus> opt=busRepository.findById(id);
		
		if(opt.isPresent()) {
			busRepository.delete(opt.get());
			return "Bus has been deleted";
		}
		
		else {
			throw new BusException("Bus is not present by id: "+id);
		}
	
	}

	@Override
	public Bus GetBusById(Integer id, String key) throws BusException, LoginException {
		
        CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
	return busRepository.findById(id).orElseThrow(()->new BusException("No bus are available by that id; "+id));
	
	}

	@Override
	public List<Bus> getAllTheBuses(String key) throws BusException, LoginException {

		 CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
			
			if(currentLoginSession==null) {
				throw new LoginException("You have to login first");
			}
			
			List<Bus> list=busRepository.findAll();
			if(list==null) {
				throw new BusException("no buses are available");
			}
			

		return list;
	}

	@Override
	public Set<Bus> getBusBySourceAndDestincation(String routeFrom, String routeTo, String key,LocalDate date)
			throws LoginException, RouteException, BusException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		Set<Bus> set=routeRepository.getBusByRoutes(routeFrom, routeTo);
		Set<Bus> buses = new HashSet<>();
		for(Bus e : set) {
			if(e.getDoj().compareTo(date)==0) {
				buses.add(e);
			}
		}
		if(set.isEmpty()) {
			throw new RouteException("No Buses are avaialble by that route");
		}
		return buses;
	}
//	@Override
//	public String AssingBusToRoute(String routeCode, String BusNumber, String key)throws LoginException, BusException, RouteException {
//		
//		 CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
//			
//			if(currentLoginSession==null) {
//				throw new LoginException("You have to login first");
//			}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
//				throw new LoginException("Please Login As Admin");
//			}
//			
//			Routes routes=routeRepository.findByRouteCode(routeCode);
//			Bus bus=busRepository.findByBusNumber(BusNumber);
//			
//			if(bus==null) {
//				throw new BusException("wrong bus number: "+bus);
//			}
//			else if(routes==null) {
//				throw new RouteException("wrong route: "+routes);
//			}
//			else {
//			
//				routes.getBuses().add(bus);
//				bus.setRoutes(routes);
//				busRepository.save(bus);
//				return "assign succesfully";
//				
//			}
//	}
}

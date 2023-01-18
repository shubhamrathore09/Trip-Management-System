package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.RouteException;
import com.masai.Exception.TravelsException;
import com.masai.Repository.RouteRepository;
import com.masai.Service.RouteService;
import com.masai.model.Routes;
import com.masai.model.Travels;

@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepository routeRepo;

	@Override
	public Routes createRoute(Routes route) throws RouteException {
		Routes r = routeRepo.save(route);
		if(r==null) {
			throw new RouteException("Not Able to save route...");
		}
		return r;
	}

	@Override
	public Routes updateRoute(Routes route) throws RouteException {
		Optional<Routes> opt = routeRepo.findById(route.getRouteId());
		if(opt.isEmpty()) {
			throw new RouteException("No Route is found with Id : "+route.getRouteId());
		}else {
			Routes tr = opt.get();
			routeRepo.save(tr);
			return tr;
		}	
	}

	@Override
	public Routes removeRoute(Integer id) throws RouteException {
		Optional<Routes> opt = routeRepo.findById(id);
		if(opt.isEmpty()) {
			throw new RouteException("No Route is found with Id : "+id);
		}else {
			Routes tr = opt.get();
			routeRepo.delete(tr);
			return tr;
		}
	}

	@Override
	public Routes getRouteById(Integer id) throws RouteException {
		Optional<Routes> opt = routeRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new RouteException("No Route is found with Id : "+id);
		}
	}

	@Override
	public List<Routes> getAllRoute() throws RouteException {
		List<Routes> list = routeRepo.findAll();
		if(list.isEmpty()) {
			throw new RouteException("No Routes found...");
		}
		return list;
	}

	
	
	

}

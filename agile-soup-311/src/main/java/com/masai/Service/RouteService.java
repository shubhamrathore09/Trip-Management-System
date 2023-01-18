package com.masai.Service;

import java.util.List;
import com.masai.Exception.RouteException;
import com.masai.model.Routes;

public interface RouteService {

	public Routes createRoute(Routes route) throws RouteException;
	
	public Routes updateRoute(Routes route) throws RouteException;
	
	public Routes removeRoute(Integer id) throws RouteException;
	
	public Routes getRouteById(Integer id) throws RouteException;
	
	public List<Routes> getAllRoute() throws RouteException;
}

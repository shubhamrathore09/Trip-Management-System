package com.masai.Service;

import java.util.List;

import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.model.Routes;

public interface RouteService {

	public Routes addRoute(Routes routes,String key)throws RouteException,LoginException;
	public String DeleteRoute(Integer routeId,String key)throws RouteException,LoginException;
	public List<Routes> routes(String key)throws RouteException,LoginException;
	public Routes findById(Integer id,String key)throws RouteException,LoginException;
}

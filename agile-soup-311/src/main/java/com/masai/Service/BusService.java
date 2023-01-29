package com.masai.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.masai.Exception.BusException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.model.Bus;

public interface BusService {

	public Bus RegistorBus(Bus bus,String key)throws BusException,LoginException;
	public String RemoveBus(Integer id,String key)throws BusException,LoginException;
	public Bus GetBusById(Integer id ,String key)throws BusException,LoginException;
	public List<Bus> getAllTheBuses(String key)throws BusException,LoginException;
//	public String AssingBusToRoute(String routeCode, String BusNumber,String key)throws LoginException,BusException,RouteException;
	public Set<Bus> getBusBySourceAndDestincation(String routeFrom,String routeTo,String key,LocalDate date)throws LoginException,RouteException, BusException;


}

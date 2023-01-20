package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.AdminException;
import com.masai.Exception.BusException;
import com.masai.Exception.CustomerException;
import com.masai.Exception.LoginException;
import com.masai.Exception.RouteException;
import com.masai.Repository.AdminRepo;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.RouteRepository;
import com.masai.Service.AdminService;
import com.masai.model.Admin;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.CustomerDTO;
import com.masai.model.Routes;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	
	@Autowired
	private BusRepository busRepository;

	@Override
	public Admin InsertAdmin(Admin admin) throws AdminException {
		
		Admin admin2=adminRepo.findByAdminMobile(admin.getAdminMobile());
		
		if(admin2==null) {
		
			return adminRepo.save(admin);
		}
		
		throw new AdminException("admin already present by that number");
		
	}

	@Override
	public Admin GetAdminById(Integer id,String key) throws AdminException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		return adminRepo.findById(id).orElseThrow(()->new AdminException("please right id"));
				
	}

	@Override
	public List<CustomerDTO> GetAllCustomerDetails(String key) throws CustomerException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<CustomerDTO> list=customerRepo.getAllCustomer();
		
		if(list==null)
		{
			throw new CustomerException("no customer are available");
		}
		else
		return list;
	}

//	***********************************ROUTE******************************************************
	
	@Override
	public Routes MakeRoute(Routes routes,String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		return routeRepository.save(routes);
	}

	@Override
	public String DeleteRoute(Integer routeId,String key) throws RouteException,LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<Routes> routes=routeRepository.findById(routeId);
		
		if(routes.isPresent()) {
			routeRepository.delete(routes.get());
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
		
		List<Routes> list=routeRepository.findAll();
		
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
		
		Optional<Routes> routes=routeRepository.findById(id);
		if(routes.isPresent()) {
			return routes.get();
		}
		throw new RouteException("there is not route by that id: "+id);
	}
	
//***************************************BUS***************************************************	

	@Override
	public Bus RagistorBus(Bus bus, String key) throws BusException, LoginException {
		
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
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

}
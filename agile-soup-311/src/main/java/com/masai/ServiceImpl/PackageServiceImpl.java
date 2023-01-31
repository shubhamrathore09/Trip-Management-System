package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.BusException;
import com.masai.Exception.HotelException;
import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.Repository.BusRepository;
import com.masai.Repository.CurrentSessionRepo;
import com.masai.Repository.HotelRepository;
import com.masai.Repository.PackageRepository;
import com.masai.Service.PackageService;
import com.masai.model.Bus;
import com.masai.model.CurrentLoginSession;
import com.masai.model.Hotel;
import com.masai.model.PackageModule;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	private PackageRepository packageRepo;
	
	@Autowired
	private HotelRepository hotelRepo;
	
	@Autowired
	private BusRepository busRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;
	@Override
	public PackageModule addPackage(PackageModule p,String key) throws PackageException,LoginException,BusException,HotelException {
	
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<PackageModule> opt=packageRepo.findById(p.getPackageId());
		
		if(opt.isPresent()) {
			throw new PackageException("Package is already Present...");
		}else {
			Optional<Hotel> opt1=hotelRepo.findById(p.getHotelId());
			if(opt1.isPresent()) {
				Optional<Bus> opt2=busRepo.findById(p.getBusId());
				if(opt2.isPresent()) {
					if(opt2.get().getTravelMode().equalsIgnoreCase("trip")) {
						return packageRepo.save(p);
					}
					throw new BusException("Bus is of Non-Trip");
				}else {
					throw new BusException("No Bus found with Id : "+p.getBusId());
				}
			}else {
				throw new HotelException("No Hotel Found with Id : "+p.getHotelId());
			}
			
		}
	}

	@Override
	public PackageModule deletePackage(Integer id,String key) throws PackageException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}else if(!currentLoginSession.getUserType().equals("ADMIN")) {
			throw new LoginException("Please Login As Admin");
		}
		
		Optional<PackageModule> opt=packageRepo.findById(id);
		
		if(opt.isPresent()) {
			PackageModule pm = opt.get();
			packageRepo.delete(pm);
			return pm;
		}else {
			throw new PackageException("Package is not available with id : "+id);
		}
	}

	@Override
	public PackageModule searchPackage(Integer id,String key) throws PackageException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<PackageModule> opt = packageRepo.findById(id);
		if(opt.isEmpty())
			throw new PackageException("Package is not found with Id : "+id +" Please provide correct id.");
		return opt.get();
	}

	@Override
	public List<PackageModule> viewAllPackages(String key) throws PackageException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		List<PackageModule> list = packageRepo.findAll();
		if(list.isEmpty())
			throw new PackageException("Package list is empty...");
		return list;
	}
	
	@Override
	public PackageModule bookPackage(Integer id,String key) throws PackageException,LoginException {
		CurrentLoginSession currentLoginSession=currentSessionRepo.findByUserKey(key);
		if(currentLoginSession==null) {
			throw new LoginException("You have to login first");
		}
		
		Optional<PackageModule> opt = packageRepo.findById(id);
		if(opt.isEmpty())
			throw new PackageException("Package is not found with Id : "+id +" Please provide correct id.");
		return opt.get();
	}
}

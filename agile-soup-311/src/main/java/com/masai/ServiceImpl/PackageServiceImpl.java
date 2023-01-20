package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.PackageException;
import com.masai.Repository.PackageRepository;
import com.masai.Service.PackageService;
import com.masai.model.PackageModule;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	private PackageRepository packageRepo;

	@Override
	public PackageModule addPackage(PackageModule p) throws PackageException {
		PackageModule pk = packageRepo.save(p);
		if(pk == null)
			throw new PackageException("Package is not saved...Please provide correct details");
		return pk;
	}

	@Override
	public PackageModule deletePackage(Integer id) throws PackageException {
		Optional<PackageModule> opt = packageRepo.findById(id);
		if(opt.isEmpty())
			throw new PackageException("Package is not found with Id : "+id +" Please provide correct id.");
		PackageModule p = opt.get();
		packageRepo.delete(p);
		return p;
	}

	@Override
	public PackageModule searchPackage(Integer id) throws PackageException {
		Optional<PackageModule> opt = packageRepo.findById(id);
		if(opt.isEmpty())
			throw new PackageException("Package is not found with Id : "+id +" Please provide correct id.");
		return opt.get();
	}

	@Override
	public List<PackageModule> viewAllPackages() throws PackageException {
		List<PackageModule> list = packageRepo.findAll();
		if(list.isEmpty())
			throw new PackageException("Package list is empty...");
		return list;
	}

//	@Override
//	public Package addPackage(Package p) throws PackageException {
//		Package pk = packageRepo.save(p);
//		if(pk == null)
//			throw new PackageException("Package is not saved...Please provide correct details");
//		return pk;
//	}
//
//	@Override
//	public Package deletePackage(Integer id) throws PackageException {
//		Optional<Package> opt = packageRepo.findById(id);
//		if(opt.isEmpty())
//			throw new PackageException("Package is not found with Id : "+id +" Please provide correct id.");
//		Package p = opt.get();
//		packageRepo.delete(p);
//		return p;
//	}
//
//	@Override
//	public Package searchPackage(Integer id) throws PackageException {
//		Optional<Package> opt = packageRepo.findById(id);
//		if(opt.isEmpty())
//			throw new PackageException("Package is not found with Id : "+id +" Please provide correct id.");
//		return opt.get();
//	}
//
//	@Override
//	public List<Package> viewAllPackages() throws PackageException {
//		List<Package> list = packageRepo.findAll();
//		if(list.isEmpty())
//			throw new PackageException("Package list is empty...");
//		return list;
//	}

}

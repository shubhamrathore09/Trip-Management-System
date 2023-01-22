package com.masai.Service;

import java.util.List;

import com.masai.Exception.LoginException;
import com.masai.Exception.PackageException;
import com.masai.model.PackageModule;

public interface PackageService {
	
	public PackageModule addPackage(PackageModule p,String key) throws PackageException,LoginException;
	
	public PackageModule deletePackage(Integer id,String key ) throws PackageException,LoginException;
	
	public PackageModule searchPackage(Integer id,String key) throws PackageException,LoginException;
	
	public List<PackageModule> viewAllPackages(String key) throws PackageException,LoginException;

}

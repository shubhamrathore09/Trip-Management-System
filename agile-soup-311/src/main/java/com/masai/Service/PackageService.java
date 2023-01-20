package com.masai.Service;

import java.util.List;

import com.masai.Exception.PackageException;
import com.masai.model.PackageModule;

public interface PackageService {
	
	public PackageModule addPackage(PackageModule p) throws PackageException;
	
	public PackageModule deletePackage(Integer id ) throws PackageException;
	
	public PackageModule searchPackage(Integer id) throws PackageException;
	
	public List<PackageModule> viewAllPackages() throws PackageException;

}

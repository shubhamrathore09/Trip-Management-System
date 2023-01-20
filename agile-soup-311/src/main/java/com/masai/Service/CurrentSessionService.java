
package com.masai.Service;

import com.masai.Exception.LoginException;

import com.masai.model.LoginDTO;

public interface CurrentSessionService {
	
	public String LoginInSystem(LoginDTO loginDTO)throws LoginException;
	
	public String LogoutFromSystem(String key) throws LoginException;
}

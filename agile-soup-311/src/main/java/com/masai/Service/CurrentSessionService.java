package com.masai.Service;

import javax.security.auth.login.LoginException;

import com.masai.model.LoginDTO;

public interface CurrentSessionService {
	public String LoginInSystem(LoginDTO loginDTO)throws LoginException;
}

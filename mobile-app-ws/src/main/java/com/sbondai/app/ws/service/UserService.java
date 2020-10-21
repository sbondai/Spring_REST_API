package com.sbondai.app.ws.service;

 

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sbondai.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
}

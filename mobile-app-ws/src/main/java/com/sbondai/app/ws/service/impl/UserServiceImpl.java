package com.sbondai.app.ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbondai.app.ws.db.repos.UserRepository;
import com.sbondai.app.ws.io.entity.UserEntity;
import com.sbondai.app.ws.service.UserService;
import com.sbondai.app.ws.shared.dto.UserDto;
import com.sbondai.app.ws.shared.dto.Utils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity userFoundByEmail = userRepository.findUserByEmail(user.getEmail());
		
		if (userFoundByEmail != null) {
			throw new RuntimeException("Record already exists");
		}
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String generatedUserID = utils.generateUserId(30);
		userEntity.setEncryptedPassword("test");
		userEntity.setUserId(generatedUserID);
		
		UserEntity savedUser = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(savedUser, returnValue);
		return returnValue;
	}

}

package com.sbondai.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity userFoundByEmail = userRepository.findByEmail(user.getEmail());
		
		if (userFoundByEmail != null) {
			throw new RuntimeException("Record already exists");
		}
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String generatedUserID = utils.generateUserId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(generatedUserID);
		
		UserEntity savedUser = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(savedUser, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

}

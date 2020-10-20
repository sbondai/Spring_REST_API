package com.sbondai.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbondai.app.ws.service.UserService;
import com.sbondai.app.ws.shared.dto.UserDto;
import com.sbondai.app.ws.ui.model.model.request.UserdetailRequestModel;
import com.sbondai.app.ws.ui.model.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getUser(){
		
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserdetailRequestModel userDetails){
		
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto(); 
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		
		return "user was updated";
	}
	
	@DeleteMapping
	public String deleteUser() {
		
		return "delete user was called";
	}

}

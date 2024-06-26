package com.app.library_system.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.library_system.entity.UserEntity;
import com.app.library_system.response_model.LoginTokenResponse;
import com.app.library_system.response_model.MessageModel;
import com.app.library_system.response_model.UserLogin;
import com.app.library_system.service.UserService;
import com.app.library_system.utils.AppStrings;

@RestController
@RequestMapping(AppStrings.ENDPOINT_API+AppStrings.ENDPOINT_PROFILE)
public class UserController {

	@Autowired
	UserService user_service;
	
	@PutMapping("/profile")
	public ResponseEntity<MessageModel> updateProfile(@RequestBody UserEntity user){
		return user_service.updateProfile(user);
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity getProfileById(@PathVariable long id){
		return user_service.getProfileById(id);
	}
}

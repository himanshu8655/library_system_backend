package com.app.library_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(AppStrings.ENDPOINT_API + AppStrings.ENDPOINT_AUTH)
public class AuthenticationController {

	@Autowired
	UserService user_service;

	@PostMapping("/login")
	public ResponseEntity<LoginTokenResponse> login(@RequestBody UserLogin login){
		return user_service.validateLogin(login);
	}

	@PostMapping("/register")
	public ResponseEntity<MessageModel> register(@RequestBody UserEntity user){
		return user_service.register(user);
}
}

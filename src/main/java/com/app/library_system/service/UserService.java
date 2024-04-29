package com.app.library_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.library_system.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	public UserRepo repo_user;
	
}

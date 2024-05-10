package com.app.library_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.library_system.controller.UserLogin;
import com.app.library_system.entity.UserEntity;
import com.app.library_system.repository.UserRepo;
import com.app.library_system.response_model.MessageModel;

@Service
public class UserService {

	@Autowired
	public UserRepo repo_user;

	public ResponseEntity validateLogin(UserLogin login) {
		UserEntity user = repo_user.getByEmailIdAndPassword(login.getEmail_id(), login.getPassword());
		if (user == null)
			return getResponseEntity("Login Failed", HttpStatus.UNAUTHORIZED);
		
		user.setPassword(null);
		user.setPassword(null);
			return getResponseEntity(user, HttpStatus.OK);
	}

	public static ResponseEntity<MessageModel> getResponseEntity(String msg, HttpStatusCode code) {
		return new ResponseEntity<MessageModel>(new MessageModel(msg), code);
	}

	public static ResponseEntity<UserEntity> getResponseEntity(UserEntity user, HttpStatusCode code) {
		return new ResponseEntity<UserEntity>(user, code);
	}

	public ResponseEntity<MessageModel> register(UserEntity user) {
		repo_user.save(user);
		return getResponseEntity("Registered Successfully", HttpStatus.OK);
	}

	public ResponseEntity<MessageModel> updateProfile(UserEntity user) {
		UserEntity db_user = getUserByID(user.getUID()).orElse(null);
		if(db_user == null) return getResponseEntity("No Id found", HttpStatus.BAD_REQUEST);
		user.setEmail_id(db_user.getEmail_id());
		user.setPassword(db_user.getPassword());
		repo_user.save(user);
		return getResponseEntity("Profile Updated!", HttpStatus.OK);
	}

	public Optional<UserEntity> getUserByID(Long uid) {
		return repo_user.findById(uid);
	}

}

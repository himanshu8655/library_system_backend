package com.app.library_system.service;

import java.util.Collection;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.library_system.entity.UserEntity;
import com.app.library_system.repository.UserRepository;
import com.app.library_system.response_model.LoginTokenResponse;
import com.app.library_system.response_model.MessageModel;
import com.app.library_system.response_model.UserLogin;
import com.app.library_system.security.JWTGenerator;

@Service
public class UserService{

	@Autowired
	public UserRepository repo_user;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

	public ResponseEntity<LoginTokenResponse> validateLogin(UserLogin login) {
		 Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		login.getEmail_id(),
	                		login.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = jwtGenerator.generateToken(authentication);
	        return new ResponseEntity<LoginTokenResponse>(new LoginTokenResponse(token,"Bearer","Login Success!"), HttpStatus.OK);
	}

	public static ResponseEntity<MessageModel> getResponseEntity(String msg, HttpStatusCode code) {
		return new ResponseEntity<MessageModel>(new MessageModel(msg), code);
	}

	public static ResponseEntity<UserEntity> getResponseEntity(UserEntity user, HttpStatusCode code) {
		return new ResponseEntity<UserEntity>(user, code);
	}

	public ResponseEntity<MessageModel> register(UserEntity user) {
		UserEntity user_db = repo_user.getByEmailId(user.getEmail_id()).orElse(null);
		if (user_db != null)
			return getResponseEntity("Email Id already exists!", HttpStatus.UNAUTHORIZED);
		String encrypt_pwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encrypt_pwd);
		repo_user.save(user);
		return getResponseEntity("Registered Successfully", HttpStatus.OK);
	}

	public ResponseEntity<MessageModel> updateProfile(UserEntity user) {
		UserEntity db_user = getUserByID(user.getUID()).orElse(null);
		if (db_user == null)
			return getResponseEntity("No Id found", HttpStatus.BAD_REQUEST);
		user.setEmail_id(db_user.getEmail_id());
		user.setPassword(db_user.getPassword());
		repo_user.save(user);
		return getResponseEntity("Profile Updated!", HttpStatus.OK);
	}

	public Optional<UserEntity> getUserByID(Long uid) {
		return repo_user.findById(uid);
	}

	public ResponseEntity getProfileById(long id) {
		UserEntity user = repo_user.findById(id).orElse(null);
		if (user == null)
			return getResponseEntity("User not found", HttpStatus.NOT_FOUND);
		user.setPassword(null);
		return getResponseEntity(user, HttpStatus.OK);

	}

}

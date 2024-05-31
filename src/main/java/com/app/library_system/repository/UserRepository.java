package com.app.library_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.library_system.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	  @Query(value = "SELECT * FROM library_system.user WHERE email_id = ?1 AND password = ?2", nativeQuery = true)
	    UserEntity getByEmailIdAndPassword(String emailId, String password);
	  
	  @Query(value = "SELECT * FROM library_system.user WHERE email_id = ?1", nativeQuery = true)
	    Optional<UserEntity> getByEmailId(String emailId);
}

package com.app.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.library_system.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{

}

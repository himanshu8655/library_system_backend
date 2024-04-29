package com.app.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.library_system.entity.BookEntity;

public interface LibraryRepo extends JpaRepository<BookEntity,Long>{

}

package com.app.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.library_system.entity.BookEntity;

public interface LibraryRepository extends JpaRepository<BookEntity,Long>, PagingAndSortingRepository<BookEntity, Long>{

}

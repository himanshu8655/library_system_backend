package com.app.library_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.library_system.entity.BookEntity;
import com.app.library_system.response_model.MessageModel;
import com.app.library_system.service.LibraryService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class LibraryController {

	@Autowired
	public LibraryService service_library;
	
	@GetMapping("/books")
	public ResponseEntity<List<BookEntity>> getAllBooks(HttpServletRequest request) {
		return new ResponseEntity<List<BookEntity>>(service_library.getAll(request),HttpStatus.OK);
	}
	@PostMapping("/book")
	public ResponseEntity<MessageModel> addBook(@ModelAttribute BookEntity book, @RequestParam(required = true) MultipartFile ebook) {
		return service_library.add(book, ebook);
	}
	
	@GetMapping("/book/downlaod/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		return service_library.getFile(id);
	}
	
	@PutMapping("/book")
	public ResponseEntity<MessageModel> editBook(@RequestParam MultipartFile file,@ModelAttribute BookEntity book){
	
		return service_library.editBook(book,file);
	}
	@GetMapping("/book/{id}")
	public ResponseEntity getBookById(@PathVariable Long id,HttpServletRequest request) {
		return service_library.getBookById(id,request);
	}

}

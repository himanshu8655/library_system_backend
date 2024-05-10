package com.app.library_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "book")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long book_id;

	public String book_name;

	public String about_book;

	public String author;

	public String price;

	@Transient
	public String file_url;

	@Column(length = 100000)
	public byte[] file;

	public BookEntity(Long book_id, String book_name, String about_book, String author,String price) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.about_book = about_book;
		this.author = author;
		this.price=price;
	}

	public BookEntity() {
	}

	public Long getBook_id() {
		return book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAbout_book() {
		return about_book;
	}

	public void setAbout_book(String about_book) {
		this.about_book = about_book;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}

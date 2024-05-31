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
	public Long bookId;

	public String bookName;

	public String aboutBook;

	public String author;

	public long price;

	@Transient
	public String fileUrl;

	@Column(length = 100000)
	public byte[] file;
	
	@Column(length = 100000)
	public byte[] thumbnail;
	
	@Transient
	public String thumbnailUrl;

	public BookEntity(Long bookId, String bookName, String aboutBook, String author,long price) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.aboutBook = aboutBook;
		this.author = author;
		this.price=price;
	}

	public BookEntity() {
	}

	public Long getBookId() {
		return bookId;
	}
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAboutBook() {
		return aboutBook;
	}

	public void setAboutBook(String aboutBook) {
		this.aboutBook = aboutBook;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


		

}

package com.app.library_system.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.library_system.entity.BookEntity;
import com.app.library_system.repository.LibraryRepo;
import com.app.library_system.response_model.MessageModel;
import com.app.library_system.utils.AppStrings;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class LibraryService {

	@Autowired
	public LibraryRepo repo_library;

//	@Autowired(required = true)
//	private HttpServletRequest request;

	public ResponseEntity getBookById(Long id, HttpServletRequest request) {
		BookEntity book = repo_library.findById(id).orElse(null);
		if (book == null)
			return getResponseEntity("No Book Found!", HttpStatus.NOT_FOUND);
		book.setFile(null);
		book.setFile_url(getBaseUrl(request) + AppStrings.ENDPOINT_API+AppStrings.ENDPOINT_BOOK+"/file/" + book.getBook_id());
		return new ResponseEntity<BookEntity>(book, HttpStatus.OK);
	}

	public ResponseEntity<MessageModel> editBook(BookEntity book, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<byte[]> getFile(Long id) {
		BookEntity book = repo_library.findById(id).orElse(null);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);

		return new ResponseEntity<byte[]>(book.getFile(), headers, HttpStatus.OK);
	}
	
	public ResponseEntity<byte[]> getThumbnail(Long id) {
		BookEntity book = repo_library.findById(id).orElse(null);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(book.getThumbnail(), headers, HttpStatus.OK);
	}


	public ResponseEntity<MessageModel> add(BookEntity book, MultipartFile file) {
		if (file == null || file.getSize() == 0)
			return getResponseEntity("File is empty!", HttpStatus.BAD_REQUEST);

		try {
			if (!file.getContentType().equals(AppStrings.PDF_CONTENT_TYPE))
				return getResponseEntity("File should be pdf!", HttpStatus.NOT_ACCEPTABLE);
			book.setFile(file.getBytes());
			PDDocument doc = Loader.loadPDF(file.getBytes());
			PDFRenderer pdfRenderer = new PDFRenderer(doc);
			BufferedImage image = pdfRenderer.renderImage(0);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image,AppStrings.FORMAT_PNG, baos);
			book.setThumbnail(baos.toByteArray());
			repo_library.save(book);
		} catch (Exception e) {
			return getResponseEntity("Error processing request", HttpStatus.BAD_REQUEST);
		}
		return getResponseEntity("Book Added Successfully", HttpStatus.OK);
	}

	public static byte[] convertBufferedImageToByteArray(BufferedImage image) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(image, "png", baos);
			return baos.toByteArray();
		}
	}

	public List<BookEntity> getAll(HttpServletRequest request) {
		List<BookEntity> books = repo_library.findAll();
		books.parallelStream().forEach(book -> {
			book.setFile_url(getBaseUrl(request) + AppStrings.ENDPOINT_API+AppStrings.ENDPOINT_BOOK+"/file/" + book.getBook_id());
			book.setFile(null);
			book.setThumbnail_url(getBaseUrl(request) + AppStrings.ENDPOINT_API+AppStrings.ENDPOINT_BOOK+"/thumbnail/" + book.getBook_id());
			book.setThumbnail(null);
		});
		return books;
	}

	public static ResponseEntity<MessageModel> getResponseEntity(String msg, HttpStatusCode code) {
		return new ResponseEntity<MessageModel>(new MessageModel(msg), code);
	}

	public String getBaseUrl(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String baseUrl = url.replace(request.getServletPath(), "");
		return baseUrl;
	}
}

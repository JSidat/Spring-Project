package com.bae.starterproject.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.starterproject.domain.Book;
import com.bae.starterproject.service.BookService;

@RestController
public class BookController {

	private BookService service;

	public BookController(BookService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createBook")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(this.service.createBook(book), HttpStatus.CREATED);
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return this.service.getBook();
	}

	@GetMapping("/getBookById/{id}")
	public Book getBookById(@PathVariable Long id) {
		return this.service.getBookById(id);
	}

	@GetMapping("/getBookByName/{title}")
	public Book getBookByTitle(@PathVariable String title) {
		return this.service.getBookByTitle(title);
	}

}

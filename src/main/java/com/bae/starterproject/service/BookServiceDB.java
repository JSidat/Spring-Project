package com.bae.starterproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bae.starterproject.domain.Book;
import com.bae.starterproject.repos.BookRepo;

@Service
public class BookServiceDB implements BookService {

	private BookRepo repo;

	public BookServiceDB(BookRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Book createBook(Book book) {
		return this.repo.save(book);
	}

	@Override
	public List<Book> getBook() {
		return this.repo.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		Optional<Book> optBook = this.repo.findById(id);
		return optBook.get();
	}

	@Override
	public Book getBookByTitle(String title) {
		return this.repo.findByTitle(title);
	}

}

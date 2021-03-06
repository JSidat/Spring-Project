package com.bae.starterproject.service;

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

}

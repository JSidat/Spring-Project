package com.bae.starterproject.service;

import java.util.List;

import com.bae.starterproject.domain.Book;

public interface BookService {

	Book createBook(Book book);

	List<Book> getBook();

	Book getBookById(Long id);

	Book getBookByTitle(String title);

}

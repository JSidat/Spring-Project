package com.bae.starterproject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.starterproject.domain.Book;
import com.bae.starterproject.repos.BookRepo;

@SpringBootTest
@ActiveProfiles("test")
public class BookServiceDBTest {

	@Autowired
	private BookServiceDB service;

	@MockBean
	private BookRepo repo;

	@Test
	void testCreate() {
		Book newBook = new Book("Shenanigans in Java", "Jordan Harrison", "Computer Programming");
		Book savedBook = new Book(1L, "Shenanigans in Java", "Jordan Harrison", "Computer Programming");

		Mockito.when(this.repo.save(newBook)).thenReturn(savedBook);

		assertThat(this.service.createBook(newBook)).isEqualTo(savedBook);
		Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
	}

	@Test
	void testRead() {
		Book savedBook = new Book(1L, "Born to run", "Christopher Mcdougal", "Sports");
		List<Book> allBooks = List.of(savedBook);

		Mockito.when(this.repo.findAll()).thenReturn(allBooks);

		assertThat(this.service.getBooks().equals(allBooks));
	}

	@Test
	void updateTest() {
		Long id = 1L;

		Book newBook = new Book("Born to run", "Christopher Mcdougal", "Running");
		Optional<Book> optionalBook = Optional.of(new Book(id, "Born to run", "Christopher Mcdougal", "Sports"));
		Book updatedBook = new Book(id, newBook.getTitle(), newBook.getAuthor(), newBook.getGenre());

		Mockito.when(this.repo.findById(id)).thenReturn(optionalBook);
		Mockito.when(this.repo.save(updatedBook)).thenReturn(updatedBook);

		assertThat(this.service.updateBook(id, newBook)).isEqualTo(updatedBook);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedBook);
	}

	@Test
	void deleteTest() {
		Long id = 1L;

		this.repo.deleteById(id);

		assertThat(this.repo.count()).isZero();

	}

}

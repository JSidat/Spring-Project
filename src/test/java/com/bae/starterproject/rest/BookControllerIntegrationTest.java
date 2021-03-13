package com.bae.starterproject.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.starterproject.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:book-schema.sql",
		"classpath:book-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {

		Book newBook = new Book("Shenanigans in Java", "Jordan Harrison", "Computer Programming");

		String newBookAsJSON = this.mapper.writeValueAsString(newBook);

		RequestBuilder mockRequest = post("/createBook").contentType(MediaType.APPLICATION_JSON).content(newBookAsJSON);

		Book savedBook = new Book(2L, "Shenanigans in Java", "Jordan Harrison", "Computer Programming");

		String savedBookAsJSON = this.mapper.writeValueAsString(savedBook);

		ResultMatcher matchStatus = status().isCreated();

		ResultMatcher matchBody = content().json(savedBookAsJSON);

		this.mockMvc.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

	}

	@Test
	void readTest() throws Exception {
		Book newBook = new Book(1L, "Born to run", "Christopher Mcdougal", "Sports");
		List<Book> allBooks = List.of(newBook);
		String newBookAsJSON = this.mapper.writeValueAsString(allBooks);

		RequestBuilder mockRequest = get("/getBooks");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(newBookAsJSON);

		this.mockMvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readByIdTest() throws Exception {
		Book newBook = new Book(1L, "Born to run", "Christopher Mcdougal", "Sports");
		String newBookAsJSON = this.mapper.writeValueAsString(newBook);

		RequestBuilder mockRequst = get("/getBookById/1");

		ResultMatcher CheckStatus = status().isOk();
		ResultMatcher CheckBody = content().json(newBookAsJSON);

		this.mockMvc.perform(mockRequst).andExpect(CheckStatus).andExpect(CheckBody);

	}

	@Test
	void readByNameTest() throws Exception {
		Book newBook = new Book(1L, "Born to run", "Christopher Mcdougal", "Sports");
		String newBookAsJSON = this.mapper.writeValueAsString(newBook);

		RequestBuilder mockRequst = get("/getBookByTitle/Born to run");

		ResultMatcher CheckStatus = status().isOk();
		ResultMatcher CheckBody = content().json(newBookAsJSON);

		this.mockMvc.perform(mockRequst).andExpect(CheckStatus).andExpect(CheckBody);

	}

	@Test
	void updateTest() throws Exception {
		Book updatedBook = new Book(1L, "Born to run", "Christopher Mcdougal", "Running");
		String updatedBookAsJSON = this.mapper.writeValueAsString(updatedBook);

		RequestBuilder mockRequest = put("/updateBook/1").contentType(MediaType.APPLICATION_JSON)
				.content(updatedBookAsJSON);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(updatedBookAsJSON);

		this.mockMvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void deleteTest() throws Exception {
		RequestBuilder mockRequest = delete("/removeBook/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("true");

		this.mockMvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
}

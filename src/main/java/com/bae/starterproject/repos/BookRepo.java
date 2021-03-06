package com.bae.starterproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.starterproject.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}

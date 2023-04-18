package com.bookStore.BookStore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
	}

	@PostMapping("/books")
	public Book createBook(@Validated @RequestBody Book book) {
		return bookRepository.save(book);
	}

	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable Long id, @Validated @RequestBody Book bookDetails) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
		book.setAuthor(bookDetails.getAuthor());
		book.setBookname(bookDetails.getBookname());
		book.setPrice(bookDetails.getPrice());
		bookRepository.save(book);
		return book;
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
		bookRepository.delete(book);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}

package swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd20.bookstore.domain.Book;
import swd20.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner booktDemo(BookRepository bookrepository) {
		return (args) -> {
			log.info("save a couple of BOOKS");
			bookrepository.save(new Book("Testikirja1", "Ernest Hemingway", 1929, "1232323-21", 19.90));
			bookrepository.save(new Book("Testikirja2", "George Orwell", 1945, "2212343-5", 16.66));	
			
			log.info("fetch all BOOKS");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}

package swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd20.bookstore.domain.Book;
import swd20.bookstore.domain.BookRepository;
import swd20.bookstore.domain.Category;
import swd20.bookstore.domain.CategoryRepository;
import swd20.bookstore.domain.User;
import swd20.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner booktDemo(BookRepository bookrepository, CategoryRepository categoryrepository, UserRepository userrepository) {
		return (args) -> {
			log.info("save a couple of BOOKS with CATEGORIES");
			Category category1 = new Category("");
			categoryrepository.save(category1);
			Category category2 = new Category("Horror");
			categoryrepository.save(category2);
			Category category3 = new Category("Sci-fi");
			categoryrepository.save(category3);
			Category category4 = new Category("Education");
			categoryrepository.save(category4);
			
			bookrepository.save(new Book("Toisinajattelijan päiväkirjasta", "Pentti Linkola", 1979, "1232323-21", 19.90, category4));
			bookrepository.save(new Book("Eläinten vallankumous", "George Orwell", 1945, "2212343-5", 16.66, category3));	
			bookrepository.save(new Book("Ohjelmoinnin perusteet", "Pekka Python", 2020, "5656563-5", 9.90));	
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$fXA4jwmhd1su66D1mAsz7up4MfogUg8JWR9cvW3U8vPtVzGByiWJy", "USER");
			User user2 = new User("admin", "$2a$10$g9iNYmjd3V1hUA/YP90Fyek.WFO.D6qAyprbEFusEFLzW.wRjnK7G", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);

			
			log.info("fetch all BOOKS");
			for (Book b : bookrepository.findAll()) {
				log.info(b.toString());
			}
			log.info("fetch all CATEGORIES");
			for (Category c : categoryrepository.findAll()) {
				log.info(c.toString());
			}

		};
	}
}

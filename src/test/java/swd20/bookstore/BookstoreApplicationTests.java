package swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import swd20.bookstore.controller.BookController;
import swd20.bookstore.controller.CategoryController;
import swd20.bookstore.controller.UserDetailServiceImpl;


@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@SpringBootTest
class BookstoreApplicationTests {
	
	@Autowired
	private BookController bookController;
	
	@Autowired
	private CategoryController categoryController;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Test
	public void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(userDetailServiceImpl).isNotNull();
	}
	
	

}

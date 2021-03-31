package swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import swd20.bookstore.domain.Book;
import swd20.bookstore.domain.BookRepository;
import swd20.bookstore.domain.Category;
import swd20.bookstore.domain.CategoryRepository;
import swd20.bookstore.domain.User;
import swd20.bookstore.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class BooklistRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Test  // testataan BookRepositoryn findByTitle()-metodin toimivuutta
    public void findByNameShouldReturnAuthor() {
        List<Book> books = bookRepository.findByTitle("Toisinajattelijan päiväkirjasta");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Pentti Linkola");
    }
    
    @Test  // testataan BookRepositoryn findByAuthor()-metodin toimivuutta
    public void findByAuthorShouldReturnTitleTest() {
        List<Book> books = bookRepository.findByAuthor("Pentti Linkola");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Toisinajattelijan päiväkirjasta");
    }
    
    @Test // testataan BookRepositoryn save()-metodin toimivuutta
    public void createNewBookTest() {
    	Book book = new Book("Title", "Author", 2020, "ISBN", 99.0, categoryRepository.findByName("Education").get(0));
    	bookRepository.save(book);
    	assertThat(bookRepository.findByTitle("Title").get(0).getAuthor()).isEqualTo("Author");
    }    
    
    @Test // testataan BookRepositoryn delete()-metodin toimivuutta
    public void deleteBookTest() {
    	Book book = new Book("Title", "Poista", 2020, "ISBN", 99.0, categoryRepository.findByName("Education").get(0));
    	Book book2 = new Book("Title2", "Äläpoista", 2020, "ISBN", 99.0, categoryRepository.findByName("Education").get(0));
    	bookRepository.save(book);
    	bookRepository.save(book2);
    	bookRepository.deleteById(book.getBookid());
    	//System.out.println("ID "+book.getBookid());
    	assertThat(bookRepository.findByAuthor("Poista").size()).isEqualByComparingTo(0); //pitää onnistua
    	//assertThat(bookRepository.findByAuthor("Äläpoista").size()).isEqualByComparingTo(0); //pitää failata
    }
    
    @Test // testataan BookRepositoryn update()-metodin toimivuutta
    public void updateBookTest() {
    	Book book = new Book("Alkup", "Alkup", 1000, "Virhe", 0, categoryRepository.findByName("Education").get(0));
    	bookRepository.save(book);
    	bookRepository.findById(book.getBookid()).get().setTitle("Testititle");
    	bookRepository.findById(book.getBookid()).get().setAuthor("Testiauthor");
    	bookRepository.findById(book.getBookid()).get().setYear(2021);
    	bookRepository.findById(book.getBookid()).get().setIsbn("TestiISBN");
    	bookRepository.findById(book.getBookid()).get().setPrice(99.0);
    	bookRepository.findById(book.getBookid()).get().setCategory(categoryRepository.findByName("Horror").get(0));

    	assertThat(bookRepository.findByTitle("Testititle").get(0).getTitle()).isEqualTo("Testititle");
    	assertThat(bookRepository.findByTitle("Testititle").get(0).getAuthor()).isEqualTo("Testiauthor");
    	assertThat(bookRepository.findByTitle("Testititle").get(0).getYear()).isEqualTo(2021);
    	assertThat(bookRepository.findByTitle("Testititle").get(0).getIsbn()).isEqualTo("TestiISBN");
    	assertThat(bookRepository.findByTitle("Testititle").get(0).getPrice()).isEqualTo(99.0);
    	assertThat(bookRepository.findByTitle("Testititle").get(0).getCategory()).isEqualTo(categoryRepository.findByName("Horror").get(0));
    }  
    
    @Test  // testataan CategoryRepositoryn findByName()-metodin toimivuutta
    public void findByNameShouldReturnId() {
        List<Category> categories = categoryRepository.findByName("Horror");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getCategoryid()).isEqualTo(2);
    }
    
    @Test  // testataan UserRepositoryn findByUsername()-metodin toimivuutta
    public void findByUserNameExists() {
        User user =new User("testaaja", "$2a$10$TukIRwbQpSl0tpFMPYB/S.6plIXkhmAO6gY9inVVvL96famn4sXHm" ,"USER"); //hash testi
        userRepository.save(user);
        //System.out.println("testiä "+user);
        assertThat(userRepository.findByUsername("testaaja").getRole()).isEqualTo("USER");
    }
    

}
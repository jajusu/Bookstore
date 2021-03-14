package swd20.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd20.bookstore.domain.Book;
import swd20.bookstore.domain.BookRepository;
import swd20.bookstore.domain.CategoryRepository;

@CrossOrigin //Huom! Lisää tämä jos koodi ja palvelin eri sijainnissa
@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryrepository;
	
	//Oma login EI TOIMI
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// RESTful service to get all books
    //JAva-kielinen Student-luokan oliolista muunnetaan JSON-opiskelijalistaksi ja 
    //lähetetään web-selaimelle vastauksena
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }
    
    //voi tehdä myös näi RequestMappingin sijaan: @GetMapping ("books/{id}")
    
	// RESTful service to get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }      
    
    // RESTful service to save new book
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return bookRepository.save(book);
    }
	
    //booklist restin kautta
    @RequestMapping(value="/booklist2", method = RequestMethod.GET)
    public String booklist(Model model) {
    	model.addAttribute("books", bookRepository.findAll());
    	return "booklist";
    }
    
	//testi
	@RequestMapping(value = "/index", method= RequestMethod.GET) //http://localhost:8080/index
	public String index() {
		return "test";
	}
	
	//hakee kaikki kirjat 
    @RequestMapping(value="/booklist") //http://localhost:8080/booklist
    public String bookList(Model model) {	
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
	
    //palauttaa tyhjän kirjan lisäyslomakkeen
    @RequestMapping(value = "/add") 
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryrepository.findAll());
        return "addbook"; //addbook.html
    }     
    
    //vastaanottaa kirjalomakkeen tiedot ja tallentaa ne tietokantaan
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bookRepository.save(book);
        return "redirect:booklist";
    }    

    //poistaa kirjan id-arvon perusteella tietokannasta
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable(value="id") Long bookId) {
    	bookRepository.deleteById(bookId);
        return "redirect:../booklist"; //..vie hierkiassa alaspäin ja sen jälkeen /booklist
    }
    
    //palauttaa kirjan muokkauslomakkeen
    @RequestMapping(value = "/update/{id}") 
    public String updatebook(@PathVariable(value="id") Long bookId, Model model){
    	model.addAttribute("categories", categoryrepository.findAll());
    	model.addAttribute("book", bookRepository.findById(bookId));
        return "updatebook"; //updatebook.html
    }     
}

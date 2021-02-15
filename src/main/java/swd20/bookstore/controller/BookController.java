package swd20.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.bookstore.domain.Book;
import swd20.bookstore.domain.BookRepository;

@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
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
    public String addStudent(Model model){
    	model.addAttribute("book", new Book());
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
    	model.addAttribute("book", bookRepository.findById(bookId));
        return "updatebook"; //updatebook.html
    }     
}

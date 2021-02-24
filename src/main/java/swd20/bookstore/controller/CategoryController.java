package swd20.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.bookstore.domain.Category;
import swd20.bookstore.domain.CategoryRepository;

@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	//hakee kaikki kategoriat 
    @RequestMapping(value="/categorylist") //http://localhost:8080/categorylist
    public String categoryList(Model model) {	
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }
    
    //palauttaa tyhjän kategorian lisäyslomakkeen
    @RequestMapping(value = "/addCategory") 
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory"; //addcategory.html
    }     
    
    //vastaanottaa kategorialomakkeen tiedot ja tallentaa ne tietokantaan
    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String save(Category category){
        categoryRepository.save(category);
        return "redirect:categorylist";
    }    
	

}

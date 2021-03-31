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

import swd20.bookstore.domain.Category;
import swd20.bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
	
	// RESTful service to get all categories
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getDepartmentsRest() {	
        return (List<Category>) categoryRepository.findAll();
    }    

	// RESTful service to get category by id
    @RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategories(@PathVariable("id") Long dId) {	
    	return categoryRepository.findById(dId);
    } 
    
    // RESTful service to save new category
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {	
    	return categoryRepository.save(category);
    }
	
	
	//hakee kaikki kategoriat 
    @RequestMapping(value="/categorylist") //http://localhost:8080/categorylist
    public String categoryList(Model model) {	
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }
    
    //palauttaa tyhjän kategorian lisäyslomakkeen
    @RequestMapping(value = "/addcategory") 
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory"; //addcategory.html
    }     
    
    //vastaanottaa kategorialomakkeen tiedot ja tallentaa ne tietokantaan
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(Category category){
        categoryRepository.save(category);
        return "redirect:booklist"; //Muuta tähän categorylist, jos haluat että ohjaa sinne
    }    
	

}

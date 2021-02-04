package swd20.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //Spring-alustaohjelma luo HelloController-luokasta olion sovelluksen käynnistyessä

public class BookController {
	@RequestMapping(value = "/index", method= RequestMethod.GET) //http://localhost:8080/index
	public String index() {
		return "test";
	}
	
	
}

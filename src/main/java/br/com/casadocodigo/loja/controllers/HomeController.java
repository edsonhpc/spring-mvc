package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index() {
		
		System.out.println("Home Controller CDC");
		
		return "home"; // Retorno o nome da string que representa a view home.jsp criado.
	}
}

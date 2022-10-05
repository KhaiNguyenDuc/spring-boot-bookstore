package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/metis")
public class HomeController {

	
	@GetMapping
	public String home() {
		return "client/index.html";
	}
}

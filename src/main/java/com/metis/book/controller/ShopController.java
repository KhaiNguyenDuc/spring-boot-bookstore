package com.metis.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("metis/shop")
public class ShopController {

	@GetMapping
	public String shop() {
		return "client/shop.html";
	}
}

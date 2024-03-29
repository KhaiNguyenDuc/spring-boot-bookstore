package com.metis.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metis.book.model.Blog;
import com.metis.book.model.Book;
import com.metis.book.model.Category;
import com.metis.book.service.IBlogService;
import com.metis.book.service.IBookService;
import com.metis.book.service.ICategoryService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	IBookService bookService;
	
	@Autowired
	IBlogService blogService;

	@GetMapping
	public ModelAndView home(ModelAndView mav) {
		List<Category> categories = categoryService.getAllCategories();
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(categories.get(1).getImage().getUrl());
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		List<Book> topFeatured = bookService.getTopFeatured();
		List<Book> bestSeller = bookService.getBestSeller();
		List<Blog> latestBlogs = blogService.getLatestBlogs();
		mav.addObject("categories", categories);
		mav.addObject("topFeatured", topFeatured);
		mav.addObject("bestSeller", bestSeller);
		mav.addObject("latestBlogs", latestBlogs);
		mav.setViewName("client/index.html");
		return mav;
	}

	
	// Test request for upload file
	@GetMapping("/upload")
	public ModelAndView testUploadImage(ModelAndView mav) {
		mav.setViewName("client/uploadImage.html");
		return mav;
	}
}

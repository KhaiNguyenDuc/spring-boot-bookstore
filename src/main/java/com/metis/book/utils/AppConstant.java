package com.metis.book.utils;

import org.springframework.beans.factory.annotation.Value;

public class AppConstant {

	public static final String UPLOAD_DIRECTORY = "uploads";
	public static final String UPLOAD_USER_DIRECTORY = UPLOAD_DIRECTORY + "/users";
	public static final String UPLOAD_BOOK_DIRECTORY = UPLOAD_DIRECTORY +  "/books";
	public static final String UPLOAD_BLOG_DIRECTORY = UPLOAD_DIRECTORY + "/blogs";
	public static final String UPLOAD_CATEGORY_DIRECTORY = UPLOAD_DIRECTORY + "/categories";;
	public static final String ROLE_NOT_FOUND = "Not found role with name: ";
	public static final String CATEGORY_NOT_FOUND = "Not found category with name: ";
	public static final String LANGUAGE_NOT_FOUND = "Not found language with name: ";
	public static final String AUTHOR_NOT_FOUND = "Not found author with name: ";
	public static final String USER_NOT_FOUND = "Not found user with username: ";
	public static final String CART_NOT_FOUND = "Not found cart with user: ";
	public static final String CART_ITEM_NOT_FOUND = "Not found cart item with id: ";
	public static final String BOOK_NOT_FOUND = "Not found book with title: ";
	public static final String BLOG_NOT_FOUND = "Not found blog with id: ";
	public static final String CONTACT_NOT_FOUND = "Not found contact with id: ";
	public static final String REQUEST_NOT_FOUND = "Not found request with id: ";
	public static final Long STANDARD = 20000L;
	public static final Long FAST = 40000L;
	public static final Long VERY_FAST = 60000L;
}

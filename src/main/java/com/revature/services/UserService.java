package com.revature.services;

import java.util.Set;

import com.revature.beans.Book;

public interface UserService {
	// services represent business logic - actual user activities.
	// what can a user do?
	public Set<Book> viewAllBooks();
	public Book getById(int id);
	public Set<Book> searchBooksByIbn(String ibn);
	public Set<Book> searchBooksByTitle(String title);
	public Set<Book> searchBooksByAuthor(String author);
	public Book editStatus(Book BookToEdit);
	public Book updateBook(Book updatedBook);
	public int addNewBook(Book book);
		
	}

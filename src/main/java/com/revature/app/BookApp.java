package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

// this static import is for the path and get/post/put methods
import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import com.revature.beans.Book;

public class BookApp {
	private static UserService userServ = new UserServiceImpl();

	public static void main(String[] args) {
		Javalin app = Javalin.create();
		
		app.start(7778);
		
		/*
		 what endpoints do we need?
		 in other words, what actions would a user need to do
		 and what address + HTTP method combo would represent
		 each of those actions?
		 (in your p0, these endpoints are provided to you.)
		*/
		app.routes(() -> {
			// localhost:8080/book
			path("/books", () -> {
				get(ctx -> {
					// checking query paramaters
					String bookIBNSearch = ctx.queryParam("IBN");
					String titleSearch = ctx.queryParam("title");
					String authorSearch = ctx.queryParam("author");
					// when using .equals with a String literal, put the
					// String literal first because it prevents null pointer
					// exceptions
					if (bookIBNSearch != null && !"".equals(bookIBNSearch)) {
						Set<Book> booksFound = userServ.searchBooksByIbn(bookIBNSearch);
						ctx.json(booksFound);
					} else if (titleSearch != null && !"".equals(titleSearch)) {
						Set<Book> booksFound = userServ.searchBooksByTitle(titleSearch);
						ctx.json(booksFound);
					} else if (authorSearch != null && !"".equals(authorSearch)) {
						Set<Book> booksFound = userServ.searchBooksByAuthor(authorSearch);
						ctx.json(booksFound);
					} else {
						// if they didn't put query parms
						Set<Book> availableBooks = userServ.viewAllBooks();
						ctx.json(availableBooks);
					}
				});
				post(ctx -> {
					// bodyAsClass turns JSON into a Java object based on the class you specify
					Book newBook = ctx.bodyAsClass(Book.class);
					if (newBook !=null) {
						userServ.addNewBook(newBook);
						ctx.status(HttpStatus.CREATED_201);
					} else {
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
				});
				
				
				path("/{id}", () -> {
					
					get(ctx -> {
						try {
							int Id = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Book book = userServ.getById(Id);
							if (book != null)
								ctx.json(book);
							else
								ctx.status(404);
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Book ID must be a numeric value");
						}
					});
					
					put(ctx -> {
						try {
							int Id = Integer.parseInt(ctx.pathParam("id")); 
							Book updatedBook = ctx.bodyAsClass(Book.class);
							if (updatedBook != null && updatedBook.getId() == Id) {
								updatedBook = userServ.updateBook(updatedBook);
								if (updatedBook != null)
									ctx.json(updatedBook);
								else
									ctx.status(404);
							} else {
								// conflict: the id doesn't match the id of the pet sent
								ctx.status(HttpCode.CONFLICT);
							}
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Book ID must be a numeric value");
						}
					});
					
				});
			});
		});
	}
	
}

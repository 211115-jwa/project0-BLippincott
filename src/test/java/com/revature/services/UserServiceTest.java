package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Book;
import com.revature.data.BookDAO;

// tell JUnit that we're using Mockito
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	// tell Mockito which classes/interfaces that we'll be mocking
	@Mock
	private BookDAO bookDao;
	
	// tell Mockito to override the regular DAOs with our mock DAOs
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
	private static Set<Book> mockAllBooks;
	
	@BeforeAll
	public static void mockAllBooksSetup() {
		mockAllBooks = new HashSet<>();
		
		// set up book data
		String[] ibns = {"35IBN3535","36IBN3636","37IBN3737","38IBN3838","39IBN3939"};
		String[] titles = {"The West Wing","Tom Sawyer","The Hunger Games","Stargaate","E.T."};
		String[] authors = {"Wynters","Twain","Ubetcha","Mac","Alien"};
		float[] prices = {29.99f,15.99f,14.98f,24.99f,9.99f};
		String[] statuss = {"A","A","A","A","A"};
			for (int i=0; i<5; i++) {
				Book book = new Book();
				book.setId(i+1);
				book.setIbn(ibns[i]);
				book.setTitle(titles[i]);
				book.setAuthor(authors[i]);
				book.setPrice(prices[i]);
				book.setStatus(statuss[i]);
				mockAllBooks.add(book);
			}
		}
		
	@Test
	public void viewAllBooks() {
		when(bookDao.getAll()).thenReturn(mockAllBooks);
		Set<Book> actualBooks = userServ.viewAllBooks();
		assertEquals(mockAllBooks, actualBooks);
		}
	
	@Test
	public void searchBooksByTitleExists() {
		String title = "E.T.";
		Set<Book> mockTitleMatch = new HashSet<Book>();
		Book newBook = new Book();
		newBook.setTitle("E.T.");
		mockTitleMatch.add(newBook);
		when(bookDao.getByTitle("E.T.")).thenReturn(mockTitleMatch);
		Set<Book> actualBooks = userServ.searchBooksByTitle(title);
		boolean booksMatch = true;
		for (Book book : actualBooks) {
			if (!book.getBook().equals(book))
				booksMatch = false;
		}
		assertTrue(booksMatch);
	}
	
	@Test
	public void searchBooksByTitleDoesNotExist() {
		String title = "The Book of Morman";
		Set<Book> mockTitleDoesNotExist = new HashSet<Book>();
		when(bookDao.getByTitle("The Book of Morman")).thenReturn(mockTitleDoesNotExist);
		Set<Book> actualBooks = userServ.searchBooksByTitle(title);
		assertTrue(actualBooks.isEmpty());
	}
	
	@Test
	public void searchBooksByAuthorExists() {
		String author = "Twain.";
		Set<Book> mockAuthorMatch = new HashSet<Book>();
		Book newBook = new Book();
		newBook.setAuthor("Twain");
		mockAuthorMatch.add(newBook);
		when(bookDao.getByAuthor("Twain")).thenReturn(mockAuthorMatch);
		Set<Book> actualBooks = userServ.searchBooksByAuthor(author);
		boolean booksMatch = true;
		for (Book book : actualBooks) {
			if (!book.getBook().equals(book))
				booksMatch = false;
		}
		assertTrue(booksMatch);
	}
	
	@Test
	public void searchBooksByAuthorDoesNotExist() {
		String author = "Mark";
		Set<Book> mockAuthorDoesNotExist = new HashSet<Book>();
		when(bookDao.getByAuthor("Mark")).thenReturn(mockAuthorDoesNotExist);
		Set<Book> actualBooks = userServ.searchBooksByAuthor(author);
		assertTrue(actualBooks.isEmpty());
	}
	
	@Test
	public void updateBookSuccessfully() {
		Book updatedBook = new Book();
		updatedBook.setId(38);
		updatedBook.setTitle("Stargate");
		
		when(bookDao.getById(38)).thenReturn(updatedBook);
		doNothing().when(bookDao).update(Mockito.any(Book.class));
		
		Book actualBook = userServ.updateBook(updatedBook);
		
		assertEquals(updatedBook, actualBook);
	}
	
	@Test
	public void updateBookWrong() {
		Book mockBook = new Book();
		mockBook.setId(38);
		
		when(bookDao.getById(38)).thenReturn(mockBook);
		doNothing().when(bookDao).update(Mockito.any(Book.class));

		Book updatedBook = new Book();
		updatedBook.setId(38);
		updatedBook.setTitle("Stargate");
		
		Book actualBook = userServ.updateBook(updatedBook);
		
		assertNotEquals(updatedBook, actualBook);
	}
	
	@Test
	public void addNewBookSuccessfully() {
		Book book = new Book();
		
		when(bookDao.create(book)).thenReturn(40);
		
		int newId = userServ.addNewBook(book);
		
		assertNotEquals(0, newId);
	}
	
	@Test
	public void addNewBookWrong() {
		Book book = new Book();
		
		when(bookDao.create(book)).thenReturn(0);
		
		int newId = userServ.addNewBook(book);
		
		assertEquals(0, newId);
	}
	
}	
package com.revature.data;

import org.junit.jupiter.api.Test;

import com.revature.beans.Book;
import com.revature.data.BookDAO;
import com.revature.data.postgres.BookPostgres;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;

public class BookDAOTest {
	private BookDAO bookDao = new BookPostgres();
	
	@Test // Test 
	public void getByIdWhenIdExists() {
		// setup
		int idInput = 0;
		// call the method we're testing
		Book idOutput = bookDao.getById(idInput);
		// assert that it did what we expected
		assertEquals(1, idOutput.getId());
	}
	
	@Test
	public void getByIdWhenIdDoesNotExists() {
		int idInput = -1;
		Book bookOutput = bookDao.getById(idInput);
		assertNull(bookOutput);
	}
	
	@Test
	public void getAll() {
		Set<Book> givenOutput = bookDao.getAll();
		assertNotNull(givenOutput);
	}
	
	@Test
		public void addNewBook() {
		Book dataInput = new Book();
		int generatedId = bookDao.create(dataInput);
		assertNotEquals(1, generatedId);
		
	}
}

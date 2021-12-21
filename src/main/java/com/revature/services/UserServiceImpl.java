package com.revature.services;

import java.util.Set;
import java.util.stream.Collectors;

import com.revature.beans.Book;
import com.revature.data.BookDAO;
import com.revature.data.postgres.BookPostgres;

public class UserServiceImpl implements UserService {
		private BookDAO bookDao = new BookPostgres();

	@Override
	public Set<Book> viewAllBooks() {
			return bookDao.getAll();
	}

	@Override
	public Book getById(int id) {
		// TODO Auto-generated method stub
		return bookDao.getById(id);
	}
	
	@Override
	public Set<Book> searchBooksByIbn(String ibnToSearch) {
		// TODO Auto-generated method stub
		return bookDao.getByIbn(ibnToSearch);
	}

	@Override
	public Set<Book> searchBooksByTitle(String titleToSearch) {
		// TODO Auto-generated method stub
		return bookDao.getByTitle(titleToSearch);
	}

	@Override
	public Set<Book> searchBooksByAuthor(String authorToSearch) {
		// TODO Auto-generated method stub
		return bookDao.getByAuthor(authorToSearch);
	}
	
	@Override
	public Book updateBook(Book updatedBook) {
			Book bookFromDatabase = bookDao.getById(updatedBook.getId());
			if (bookFromDatabase != null) {
				bookDao.update(updatedBook);
				return bookDao.getById(updatedBook.getId());
			}
		return null;
	}

	@Override
	public int addNewBook(Book newBook) {
			return bookDao.create(newBook);
	}

	@Override
	public Book editStatus(Book BookToEdit) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.revature.data;

import java.util.Set;

import com.revature.beans.Book;

//the BookDAO extends the GenericDAO in order to
//inherit the CRUD methods, and it sets the type of the
//data to be Book objects
public interface BookDAO extends GenericDAO<Book> {
	// here, we could add any additional behaviors that are
	// unique to accessing Book data (not just basic CRUD)
	public Set<Book> getById(Int id);
	public Set<Book> getByIbn(String ibn);
	public Set<Book> getByTitle(String title);
	public Set<Book> getByAuthor(String author);
}

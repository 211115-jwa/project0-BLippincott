/*
 * package com.revature.data.collections;
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * import com.revature.beans.Stock; import com.revature.beans.Book; import
 * com.revature.data.BookDAO;
 * 
 * @Deprecated public class BookCollection implements BookDAO { private
 * Set<Book> allBooks = new HashSet<>(); private int currentId = 1;
 * 
 * @Override public int create(Book dataToAdd) { dataToAdd.setId(currentId);
 * allBooks.add(dataToAdd); currentId++; return dataToAdd.getId(); }
 * 
 * @Override public Book getById(int id) { for (Book book : allBooks) { if
 * (book.getId() == id) { return book; } } return null; }
 * 
 * @Override public Set<Book> getAll() { return allBooks; }
 * 
 * @Override public void update(Book dataToUpdate) { Book previousData =
 * getById(dataToUpdate.getId()); if (previousData != null) {
 * allBooks.remove(previousData); allBooks.add(dataToUpdate); } }
 * 
 * @Override public void delete(Book dataToDelete) { Book previousData =
 * getById(dataToDelete.getId()); if (previousData != null) {
 * allBooks.remove(previousData); } }
 * 
 * @Override public Set<Book> getByStatus(String status) { // TODO
 * Auto-generated method stub return null; }
 * 
 * @Override public Set<Book> getByStatus(String status) { Set<Book>
 * booksWithStatus = new HashSet<>(); for (Book book : allBooks) { if
 * (book.getStatus().equals(status)) { booksWithStatus.add(book); } } return
 * booksWithStatus;
 * 
 * 
 * }
 */
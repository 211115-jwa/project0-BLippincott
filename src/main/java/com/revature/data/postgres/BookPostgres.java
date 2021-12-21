package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Book;
import com.revature.data.BookDAO;
import com.revature.data.Int;
//import com.revature.data.Int;
import com.revature.utils.ConnectionUtil;

public class BookPostgres implements BookDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Book getById(int id) {
		Book book = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from book where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				book = new Book();
				book.setId(id);
				book.setIbn(resultSet.getString("IBN"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
				book.setStatus(resultSet.getString("status"));
							}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	@Override
	public Set<Book> getByIbn(String ibn) {
		Set<Book> booksByIbn = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from book where ibn=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ibn);
	
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			// while the result set has another row
			while (resultSet.next()) {
				Book book = new Book();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				book.setId(resultSet.getInt("id"));
				book.setIbn(resultSet.getString("ibn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
				book.setStatus(resultSet.getString("status"));
								
				booksByIbn.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksByIbn;
	}
	
	@Override
	public Set<Book> getByTitle(String title) {
		Set<Book> booksByTitle = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from book where title=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, title);
	
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			// while the result set has another row
			while (resultSet.next()) {
				Book book = new Book();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				book.setId(resultSet.getInt("id"));
				book.setIbn(resultSet.getString("ibn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
				book.setStatus(resultSet.getString("status"));
								
				booksByTitle.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksByTitle;
	}
	
	@Override
	public Set<Book> getByAuthor(String author) {
		Set<Book> booksByAuthor = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from book where author=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, author);
	
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			// while the result set has another row
			while (resultSet.next()) {
				Book book = new Book();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				book.setId(resultSet.getInt("id"));
				book.setIbn(resultSet.getString("ibn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
				book.setStatus(resultSet.getString("status"));
								
				booksByAuthor.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksByAuthor;
	}
	
	@Override
	public Set<Book> getAll() {
		Set<Book> allBooks = new HashSet<>();
			
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from book";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			// while the result set has another row
			while (resultSet.next()) {
				// create the Book object
				Book book = new Book();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				book.setId(resultSet.getInt("id"));
				book.setIbn(resultSet.getString("ibn"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getFloat("price"));
				book.setStatus(resultSet.getString("status"));
				
				allBooks.add(book);
			}		
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allBooks;
	}

	@Override
	public void update(Book dataToUpdate) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update book set "
					+ "ibn=?,title=?,author=?,price=?,status=? "
					+ "where id=?";
					
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, dataToUpdate.getIbn());
			pStmt.setString(2, dataToUpdate.getTitle());
			pStmt.setString(3, dataToUpdate.getAuthor());
			pStmt.setFloat(4, dataToUpdate.getPrice());
			pStmt.setString(5, dataToUpdate.getStatus());
			pStmt.setInt(6, dataToUpdate.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			
			if (rowsAffected==1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int create(Book dataToAdd) {
		int generatedId = 0;
		
		// try-with-resources auto-closes resources
		try (Connection conn = connUtil.getConnection()) {
			// when you run DML statements, you want to manage the TCL
			conn.setAutoCommit(false);
			
			String sql = "insert into book (id,ibn,title,author,price,status) "
					+ "values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"}; // the name of the primary key column that will be autogenerated
			// creating the prepared statement
			PreparedStatement pStmt = conn.prepareStatement(sql, keys);
			// we need to set the values of the question marks
			pStmt.setString(1, dataToAdd.getIbn()); // question mark index starts at 1
			pStmt.setString(2, dataToAdd.getTitle());
			pStmt.setString(3, dataToAdd.getAuthor());
			pStmt.setFloat(4, dataToAdd.getPrice());
			pStmt.setString(5, dataToAdd.getStatus());
			
			// after setting the values, we can run the statement
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			
			if (resultSet.next()) { // "next" goes to the next row in the result set (or the first row)
				// getting the ID value from the result set
				generatedId = resultSet.getInt("id");
				conn.commit(); // running the TCL commit statement
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return generatedId;
	}

	@Override
	public void delete(Book dataToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Book> getById(Int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
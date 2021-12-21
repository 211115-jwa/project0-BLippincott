package com.revature.beans;

//import com.revature.data.Int;

public class Book {
	private int id;
	private String ibn;
	private String title;
	private String author;
	private Float price;
	private String status;
		
	public Book() {
		id = 0;
		ibn = "0IBN";
		title = "title";
		author = "author";
		price = (float) 21.99;
		status = "A";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getIbn() {
		return ibn;
	} 
	public void setIbn(String ibn) {
		this.ibn = ibn;
		}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Object getBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		float result = 1;
		result = prime * result + id;
		result = prime * result + ((ibn == null) ? 0 : ibn.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + price;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (price != other.price)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (ibn != other.ibn)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
				return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", ibn=" + ibn + ", title=" + title + ", author=" + author + ",  price="
				+ price + ", status=" + status + "]";
	}

}

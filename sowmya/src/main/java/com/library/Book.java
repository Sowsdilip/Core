package com.library;

public class Book {

	String name;

	long quantity;

	int bookId;

	Book(String bookname,long bookquantity){
		
		this.name=bookname;
		this.quantity=bookquantity;
		
	}

	public String getName() {
		return name;
	}

	public long getQuantity() {
		return quantity;
	}

	public int getBookId() {
		return bookId;
	}

	public long decreaseBookQuantity() {
		return --quantity;
		
	}
   
	public long increaseBookQuantity() {
		return ++quantity;
		
	}
}

package com.library;

import java.util.ArrayList;

public class User {

	String userId;

	String name;

	ArrayList<String> books = new ArrayList<>();

	User(String username,String email){
		this.name=username;
		this.userId=email;
	}
	public String getName() {

		return name;

	}

	public String getUserId() {

		return userId;

	}

	public ArrayList<String> getUserBooks() {

		return books;

	}

	public int getUserBooksQuantity() {

		return books.size();
	}

	public void addUserBook(String bname) {

		books.add(bname);
	}

	public void removeUserBook(String bname) {

		books.remove(bname);
	}

}

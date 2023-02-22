package com.library;

import java.util.ArrayList;

public class EmployeeBo {

	EmployeeDaoExt dao = new EmployeeDaoExt();

	public boolean bookExists(String bname) {

		boolean result = false;

		ArrayList<Book> book = new ArrayList<>();
		book = dao.loadBooksFromDb();
		if (dao.checkIfAvailable(bname, book) != null)
			result = true;

		return result;
	}

	public boolean userExists(String uname) {
		boolean result = false;

		ArrayList<String> users = new ArrayList<>();
		users = dao.loadUserFromDb();
		if (users.contains(uname))
			result = true;

		return result;
	}

	public void addBook(String bname, int quantity) {

		Book b1 = new Book(bname, quantity);

		dao.insertToDb(b1);

	}

	public void addUser(String uname, String email) {

		User u1 = new User(uname, email);

		dao.insertToDb(u1);

	}

	public void issueBook(String bname, String uname) {

		ArrayList<String> userbooks = new ArrayList<>();
		userbooks = dao.loadUserBooksFromDb(uname);
		if (userbooks.size() < 4) {
			ArrayList<Book> book = new ArrayList<>();
			book = dao.loadBooksFromDb();
			Book b1 = dao.checkIfAvailable(bname, book);
			if (b1 != null) {
				if (b1.getQuantity() > 0) {
					b1.decreaseBookQuantity();
					dao.updateBookQuantity(b1);
				} else
					System.out.println("Book " + bname + " not Available");
				System.out.println("Book " + bname + " issued to " + uname);
			} else
				System.out.println("Enter valid book name");
		} else
			System.out.println(uname + " book limit exceeded....");
	}

	public void returnBook(String uname, String bname) {
        boolean result = false; 
		ArrayList<Book> book = new ArrayList<>();
		book = dao.loadBooksFromDb();
		result = dao.updateUserBooks(uname,bname);
		if(result) {
		for (Book b1 : book)
			if (b1.getName().equals(bname)) {
				b1.increaseBookQuantity();
				dao.updateBookQuantity(b1);
			}
		 System.out.println("Book " + bname + " returned " + uname);
		}
		

	}

	public void showUserBooks(String uname) {

		ArrayList<String> userbook = new ArrayList<>();
		System.out.print("Books issued to user " + uname + "...");
		userbook = dao.loadUserBooksFromDb(uname);
		for (String bookname : userbook)
			System.out.print('\t' + bookname);
		System.out.println();

	}

	public void showBooks() {
		ArrayList<Book> book = new ArrayList<>();
		System.out.println("BookName" + '\t' + "Quantity");
		book = dao.loadBooksFromDb();
		for (Book b : book)
			System.out.println(b.getName() + '\t' + '\t' + b.getQuantity());

	}

}

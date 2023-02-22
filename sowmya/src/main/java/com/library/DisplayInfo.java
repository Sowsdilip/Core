package com.library;

import java.util.Scanner;

public class DisplayInfo {

	Scanner in = new Scanner(System.in);

	EmployeeBo emp = new EmployeeBo();

	int i;

	String bname;

	String uname;

	int quantity;

	String email;

	public int displaySelection() throws NumberFormatException {

		System.out.println("Press 1 to show all books");

		System.out.println("Press 2 to add a new book");

		System.out.println("Press 3 to add a new User");

		System.out.println("Press 4 to issue a book");

		System.out.println("Press 5 to return a book");

		System.out.println("Press 6 to books issued to user");

		System.out.println("Press 0 to exit...");

		i = Integer.parseInt(in.nextLine());

		return i;
	}

	public void selectOne() {

		System.out.println("Books Available...");

		emp.showBooks();

	}

	public void selectTwo() {

		System.out.println("Add Book Details...");

		System.out.println("Enter Book Name..  ");

		bname = in.nextLine();

		try {

			if (!(emp.bookExists(bname))) {

				System.out.println("Enter Book Quantity..  ");
				quantity = Integer.parseInt(in.nextLine());
				emp.addBook(bname, quantity);
			} else
				throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("!!! Book  " + bname + "  already Exists");
		}

	}

	public void selectThree() {

		System.out.println("Enter User Name..  ");

		uname = in.nextLine();

		System.out.println("Enter User Email..  ");

		email = in.nextLine();

		emp.addUser(uname, email);

	}

	public void selectFour() {

		System.out.println("Enter User Name..  ");

		uname = in.nextLine();

		if ((emp.userExists(uname))) {

			System.out.println("Enter Book Name..  ");

			bname = in.nextLine();

			if ((emp.bookExists(bname)))
				emp.issueBook(bname, uname);
			else
				System.out.println("!!! Please enter valid bookname");

		} else
			System.out.println("!!! User doent exist....");
	}

	public void selectFive() {

		System.out.println("Enter User Name..  ");

		uname = in.nextLine();

		if (emp.userExists(uname)) {

			System.out.println("Enter Book Name..  ");

			bname = in.nextLine();

			emp.returnBook(uname, bname);

		} else
			System.out.println("!!! User doesn't exist....");

	}

	public void selectSix() {

		System.out.println("Enter User Name..  ");

		uname = in.nextLine();

		if (emp.userExists(uname)) {

			emp.showUserBooks(uname);

			System.out.println();
		} else
			System.out.println("User not added....");
	}

}

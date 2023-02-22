package com.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoExt extends EmployeeDao {

	public void insertToDb(Book b1) {

		loadDriver();
		Connection con = getConnection();
		String sql = "insert into book values (?,?)";

		PreparedStatement ps = getPreparedStatement(con, sql);
		try {
			ps.setString(1, b1.getName());
			ps.setLong(2, b1.getQuantity());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("!!! Sql Exception");
		}
		System.out.println("Book added");
		closeConnection(con);
		closePreparedStatement(ps);
	}

	public void insertToDb(User u1) {

		loadDriver();
		Connection con = getConnection();
		String sql = "insert into user values (?,?)";

		PreparedStatement ps = getPreparedStatement(con, sql);
		try {
			ps.setString(1, u1.getName());
			ps.setString(2, u1.getUserId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("User added");
		closeConnection(con);
		closePreparedStatement(ps);

	}

	public ArrayList<Book> loadBooksFromDb() {
		ArrayList<Book> book = new ArrayList<>();
		loadDriver();
		Connection con = getConnection();
		String sql = "select * from book";

		PreparedStatement ps = getPreparedStatement(con, sql);

		try {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book b1 = new Book(rs.getString("bookname"), rs.getLong("quanity"));
				book.add(b1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con);
		closePreparedStatement(ps);

		return book;

	}

	public Book checkIfAvailable(String bname, ArrayList<Book> book) {

		for (Book b1 : book) {
			if (b1.getName().equals(bname)) {
				return b1;
			}
		}
		return null;
	}

	public void updateBookQuantity(Book b1) {

		loadDriver();
		Connection con = getConnection();
		String sql = "update book set quanity=? where bookname=?";
		PreparedStatement ps = getPreparedStatement(con, sql);

		try {	
			ps.setLong(1,b1.getQuantity() );
			ps.setString(2, b1.getName());
			ps.executeLargeUpdate();		
            } catch (SQLException e) {
			e.printStackTrace();
		    }

		closeConnection(con);
		closePreparedStatement(ps);

	}

	public void addUSerBook(String uname, String bname) {

		loadDriver();
		Connection con = getConnection();
		String sql = "insert into userbooks values (?,?)";

		PreparedStatement ps = getPreparedStatement(con, sql);
		try {
			ps.setString(1, uname);
			ps.setString(2, bname);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("User Book added");
		closeConnection(con);
		closePreparedStatement(ps);

	}

	public ArrayList<String> loadUserBooksFromDb(String uname) {

		ArrayList<String> userbook = new ArrayList<>();
		loadDriver();
		Connection con = getConnection();
		String sql = "select book1 from userbooks where uname=?";

		PreparedStatement ps = getPreparedStatement(con, sql);

		try {
			ps.setNString(1, uname);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userbook.add(rs.getNString("book1"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con);
		closePreparedStatement(ps);

		return userbook;

	}

	public ArrayList<String> loadUserFromDb() {
		ArrayList<String> users = new ArrayList<>();
		loadDriver();
		Connection con = getConnection();
		String sql = "select uname from user";

		PreparedStatement ps = getPreparedStatement(con, sql);

		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(rs.getNString("uname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con);
		closePreparedStatement(ps);

		return users;

	}

	public boolean updateUserBooks(String uname, String bname) {
		boolean result=false;
		Connection con = getConnection();
		String sql = "delete from userbooks where uname=? && book1= ?";

		PreparedStatement ps = getPreparedStatement(con, sql);

		try {
			 ps.setString(1, uname);
			 ps.setString(2, bname);
			 ps.executeUpdate();
			 result = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection(con);
		closePreparedStatement(ps);

		return result;
		
	}

}

package test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/javaee_ebook?useSSL=false";
		String user = "root";
		String pass = "";
		
		try {
			System.out.println("Connecting to DB: " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Conection succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package models;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Cone {
	
	Connection con;
	
	public Connection getConnection() {
		
		try {
			
			final String url = "jdbc:mysql://localhost:3306/puntoVenta";
			final String user = "root";
			final String password = "123456789";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(url, user, password);
			
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR CONNECTION");
		
		}
		
		return con;
		
		
	}

}

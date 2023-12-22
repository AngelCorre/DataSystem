package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import models.Cone;
import models.MUser;

public class CLogin {
	
	Cone cone = new Cone();
	
	public boolean LogIn(MUser mu) {
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT role, status FROM user WHERE userName = '" + mu.getUserName() +"' AND password = '" + mu.getPassword() + "'");
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
			
				mu.setRole(rs.getString("role"));
				mu.setStatus(rs.getString("status"));
				
				cn.close();
				
				return true;
				
			} else {
				
				cn.close();
				
				return false;
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}

}

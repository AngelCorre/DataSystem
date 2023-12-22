package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Cone;
import models.MSale;

public class CSale {

	Cone cone = new Cone();

	public boolean save(MSale mv) {

		Connection cn;
		PreparedStatement ps = null;

		try {

			cn = cone.getConnection();

			ps = cn.prepareStatement("INSERT INTO sale (sellerName, total, dateSale) VALUES (?,?,?)");

			ps.setString(1, mv.getSellerName());
			ps.setDouble(2, mv.getTotal());
			ps.setDate(3, mv.getDateT());
			
			ps.executeUpdate();

			cn.close();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;
		}

	}
	
	public boolean findLastId(MSale ms) {
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT id FROM sale ORDER BY id DESC LIMIT 1");
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				ms.setId(rs.getInt("id"));
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}

}

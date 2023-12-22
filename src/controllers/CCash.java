package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Cone;
import models.MCash;

public class CCash {
	
	Cone cone = new Cone();
	
	public boolean findCashProducts(MCash mc) {
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT SUM(cost * stock) FROM product");
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				mc.setTotalProduct(rs.getDouble("SUM(cost * stock)"));
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean findCashSales (MCash mc) {
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT SUM(total) FROM sale");
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				mc.setTotal(rs.getDouble("SUM(total)"));
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean deleteSales() {
		
		Connection cn;
		PreparedStatement ps = null;
		
		try {
			
			cn = cone.getConnection();
			ps = cn.prepareStatement("DELETE FROM SALE WHERE id > 0");
			
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean saveCashOut(MCash mc) {
		
		Connection cn;
		PreparedStatement ps = null;
		
		try {
			
			cn = cone.getConnection();
			ps = cn.prepareStatement("INSERT INTO Cashout (userName, totalEstimated, total, dateC) VALUES (?,?,?,?)");
			
			ps.setString(1, mc.getUserName());
			ps.setDouble(2, mc.getTotalEstimated());
			ps.setDouble(3, mc.getTotal());
			ps.setDate(4, mc.getDateToday());

			ps.executeUpdate();
			
			cn.close();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
		}
		
	}

}

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import models.Cone;
import models.MUser;

public class CUser {

	Cone cone = new Cone();
	
	public boolean save(MUser l) {

		Connection cn;
		PreparedStatement ps = null;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("INSERT INTO user(userName, password, role, status) VALUES (?,?,?,?)");
			
			ps.setString(1, l.getUserName());
			ps.setString(2, l.getPassword());
			ps.setString(3, l.getRole());
			ps.setString(4, l.getStatus());
			
			ps.executeUpdate();
			
			cn.close();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean update(MUser m) {
		
		Connection cn;
		PreparedStatement ps = null;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("UPDATE user SET userName = ?, password = ?, role = ?, status = ? WHERE id = ?");
			
			ps.setString(1, m.getUserName());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getRole());
			ps.setString(4, m.getStatus());
			ps.setInt(5, m.getId());
			
			ps.executeUpdate();
			
			cn.close();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean delete(int id) {
		
		Connection cn;
		PreparedStatement ps = null;
		
		try {
			
			cn = cone.getConnection();
			ps = cn.prepareStatement("DELETE FROM user WHERE id = ?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			cn.close();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return true;
		
		
	}
	
	public boolean findById(MUser mu) {
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			ps = cn.prepareStatement("SELECT * FROM user WHERE id = ?");
			
			ps.setInt(1, mu.getId());
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				mu.setUserName(rs.getString("userName"));
				mu.setPassword(rs.getString("password"));
				mu.setRole(rs.getString("role"));
				mu.setStatus(rs.getString("status"));
				
				cn.close();
				
				return true;
				
			} else {
				
				JOptionPane.showMessageDialog(null, "INVALID ID");
				
				cn.close();
				
				return false;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public boolean findByName(MUser mu) {
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			ps = cn.prepareStatement("SELECT userName FROM user WHERE userName = ?");
			
			ps.setString(1, mu.getUserName());
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				JOptionPane.showMessageDialog(null, "USERNAME ALREADY EXISTS");
				
				return true;
				
			} else {
				
				return false;
				
			}
			
		} catch (Exception e) {
			
			return false;
			
		}
		
	}

}

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import models.Cone;
import models.MInventary;
import models.MUser;

public class CInventary {

	Cone cone = new Cone();

	public boolean save(MInventary mi) {

		Connection cn;
		PreparedStatement ps = null;

		try {

			cn = cone.getConnection();
			ps = cn.prepareStatement("INSERT INTO product (product, author, cost, stock) VALUES (?,?,?,?)");

			ps.setString(1, mi.getProduct());
			ps.setString(2, mi.getAuthor());
			ps.setDouble(3, mi.getCost());
			ps.setInt(4, mi.getStock());

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

			ps = cn.prepareStatement("DELETE FROM product WHERE id = ?");

			ps.setInt(1, id);

			ps.executeUpdate();

			cn.close();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	public boolean update(MInventary mi) {

		Connection cn;
		PreparedStatement ps = null;

		try {

			cn = cone.getConnection();

			ps = cn.prepareStatement("UPDATE product SET product = ?, author = ?, cost = ?, stock = ? WHERE id = ?");

			ps.setString(1, mi.getProduct());
			ps.setString(2, mi.getAuthor());
			ps.setDouble(3, mi.getCost());
			ps.setInt(4, mi.getStock());
			ps.setInt(5, mi.getId());

			ps.executeUpdate();

			cn.close();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	public boolean findById(MInventary mi) {

		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;

		try {

			cn = cone.getConnection();

			ps = cn.prepareStatement("SELECT * FROM product WHERE id = ?");

			ps.setInt(1, mi.getId());

			rs = ps.executeQuery();

			if (rs.next()) {

				// mi.setId(rs.getInt("id"));
				mi.setProduct(rs.getString("product"));
				mi.setAuthor(rs.getString("author"));
				mi.setCost(rs.getDouble("cost"));
				mi.setStock(rs.getInt("stock"));

				cn.close();

				return true;

			} else {

				JOptionPane.showMessageDialog(null, "INVALID CODE");

				cn.close();

				return false;

			}

		} catch (SQLException e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean findByIdStock(MInventary mi) {

		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			cn = cone.getConnection();

			ps = cn.prepareStatement("SELECT stock FROM product WHERE id = ?");

			ps.setInt(1, mi.getId());

			rs = ps.executeQuery();

			if (rs.next()) {

				mi.setStock(rs.getInt("stock"));

				return true;

			} else {

				return false;

			}

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	public boolean updateStock(MInventary mi) {

		Connection cn;
		PreparedStatement ps = null;

		try {

			cn = cone.getConnection();

			ps = cn.prepareStatement("UPDATE product SET stock = ? WHERE id = ?");

			ps.setInt(1, mi.getStock());
			ps.setInt(2, mi.getId());

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();

			return false;

		}

	}

	public boolean findByName(MInventary mi) {

		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;

		try {

			cn = cone.getConnection();
			ps = cn.prepareStatement("SELECT product FROM product WHERE product = ?");

			ps.setString(1, mi.getProduct());

			rs = ps.executeQuery();

			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "PRODUCT ALREADY EXISTS");

				return true;

			} else {
				

				return false;

			}

		} catch (Exception e) {

			return false;

		}

	}

}

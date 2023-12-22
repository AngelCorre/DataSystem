package views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import models.Cone;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Inventory {

	private JFrame frmInventory;
	private JTable tInventario;

	DefaultTableModel model = new DefaultTableModel();
	Cone cone = new Cone();
	
	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
					window.frmInventory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInventory = new JFrame();
		frmInventory.setTitle("Inventory");
		frmInventory.setBounds(100, 100, 575, 411);
		frmInventory.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmInventory.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 541, 250);
		frmInventory.getContentPane().add(scrollPane);
		
		tInventario = new JTable();
		scrollPane.setViewportView(tInventario);
		
		model.addColumn("Id");
		model.addColumn("Name");
		model.addColumn("Author");
		model.addColumn("Cost");
		model.addColumn("Stock");
		
		String[] show = new String[5];
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT * FROM product");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				show[0] = rs.getString(1);
				show[1] = rs.getString(2);
				show[2] = rs.getString(3);
				show[3] = rs.getString(4);
				show[4] = rs.getString(5);
				
				model.addRow(show);
				
			}
			
			tInventario.setModel(model);
			
			JLabel lblNewLabel = new JLabel("Inventary");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
			lblNewLabel.setBounds(10, 21, 541, 38);
			frmInventory.getContentPane().add(lblNewLabel);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import models.Cone;

public class Sales {

	private JFrame frmSales;
	private JTable tSales;
	
	private DefaultTableModel model = new DefaultTableModel();
	private Cone cone = new Cone();

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales window = new Sales();
					window.frmSales.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSales = new JFrame();
		frmSales.setTitle("Sales");
		frmSales.setBounds(100, 100, 575, 401);
		frmSales.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmSales.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 541, 289);
		frmSales.getContentPane().add(scrollPane);
		
		tSales = new JTable();
		tSales.setEnabled(false);
		tSales.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(tSales);
		
		model.addColumn("Id");
		model.addColumn("SellerName");
		model.addColumn("Total");
		model.addColumn("Date");
		
		String[] show = new String[4];
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT * FROM sale");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				show[0] = rs.getString(1);
				show[1] = rs.getString(2);
				show[2] = rs.getString(3);
				show[3] = rs.getString(4);
				
				model.addRow(show);
				
			}
			
			tSales.setModel(model);
			
			JLabel lblSales = new JLabel("Sales");
			lblSales.setEnabled(false);
			lblSales.setHorizontalAlignment(SwingConstants.CENTER);
			lblSales.setFont(new Font("Arial", Font.BOLD, 25));
			lblSales.setBounds(0, 10, 561, 38);
			frmSales.getContentPane().add(lblSales);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}

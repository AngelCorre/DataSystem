package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import models.Cone;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PreviousCash {
	
	private DefaultTableModel model = new DefaultTableModel();
	private Cone cone = new Cone();

	private JFrame frmPreviousCashout;
	private JTable tPrevious;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviousCash window = new PreviousCash();
					window.frmPreviousCashout.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PreviousCash() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPreviousCashout = new JFrame();
		frmPreviousCashout.setTitle("Previous CashOut");
		frmPreviousCashout.setBounds(100, 100, 658, 402);
		frmPreviousCashout.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmPreviousCashout.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 624, 230);
		frmPreviousCashout.getContentPane().add(scrollPane);
		
		tPrevious = new JTable();
		scrollPane.setViewportView(tPrevious);
		
		model.addColumn("Id");
		model.addColumn("UserName");
		model.addColumn("Total Estimated");
		model.addColumn("Total");
		model.addColumn("Date");

		String[] show = new String[5];

		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;

		try {

			cn = cone.getConnection();

			ps = cn.prepareStatement("SELECT * FROM Cashout");

			rs = ps.executeQuery();

			while (rs.next()) {

				show[0] = rs.getString(1);
				show[1] = rs.getString(2);
				show[2] = rs.getString(3);
				show[3] = rs.getString(4);
				show[4] = rs.getString(5);

				model.addRow(show);

			}

			tPrevious.setModel(model);

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		lblNewLabel = new JLabel("Previous CashOut");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 25, 624, 38);
		frmPreviousCashout.getContentPane().add(lblNewLabel);
	}
}

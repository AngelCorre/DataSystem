package views;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import models.Cone;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Profiles {

	private JFrame frmProfiles;
	private JTable tUsers;
	
	private DefaultTableModel model = new DefaultTableModel();
	private Cone cone = new Cone();

	/**
	 * Launch the application.
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profiles window = new Profiles();
					window.frmProfiles.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Profiles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProfiles = new JFrame();
		frmProfiles.setTitle("Profiles");
		frmProfiles.setBounds(100, 100, 575, 401);
		frmProfiles.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmProfiles.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 541, 289);
		frmProfiles.getContentPane().add(scrollPane);
		
		tUsers = new JTable();
		tUsers.setEnabled(false);
		tUsers.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(tUsers);
		
		model.addColumn("Id");
		model.addColumn("UserName");
		model.addColumn("Password");
		model.addColumn("Role");
		model.addColumn("Status");
		
		String[] show = new String[5];
		
		Connection cn;
		PreparedStatement ps = null;
		ResultSet rs;
		
		try {
			
			cn = cone.getConnection();
			
			ps = cn.prepareStatement("SELECT * FROM user");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				show[0] = rs.getString(1);
				show[1] = rs.getString(2);
				show[2] = rs.getString(3);
				show[3] = rs.getString(4);
				show[4] = rs.getString(5);
				
				model.addRow(show);
				
			}
			
			tUsers.setModel(model);
			
			JLabel lblProfiles = new JLabel("Profiles");
			lblProfiles.setHorizontalAlignment(SwingConstants.CENTER);
			lblProfiles.setFont(new Font("Arial", Font.BOLD, 25));
			lblProfiles.setBounds(10, 10, 541, 38);
			frmProfiles.getContentPane().add(lblProfiles);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		
		
		
	}
}

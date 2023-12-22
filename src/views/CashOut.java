package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controllers.CCash;
import models.Cone;
import models.MCash;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashOut {

	private JFrame frmCashout;
	private JTable tCashout;

	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel model2 = new DefaultTableModel();
	private Cone cone = new Cone();
	private CCash cc = new CCash();
	private MCash mc = new MCash();
	private Login l = new Login();
	private String user;
	
	private JLabel lblDate;
	private JButton btnSave;
	private JButton btnPrevious;
	private JTable tProducts;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashOut window = new CashOut();
					window.frmCashout.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CashOut() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCashout = new JFrame();
		frmCashout.setTitle("CashOut");
		frmCashout.setBounds(100, 100, 1164, 606);
		frmCashout.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmCashout.getContentPane().setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(599, 126, 541, 156);
		frmCashout.getContentPane().add(scrollPane);

		tCashout = new JTable();
		tCashout.setEnabled(false);
		tCashout.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane.setViewportView(tCashout);

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

			while (rs.next()) {

				show[0] = rs.getString(1);
				show[1] = rs.getString(2);
				show[2] = rs.getString(3);
				show[3] = rs.getString(4);

				model.addRow(show);

			}

			tCashout.setModel(model);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		JLabel lblSales = new JLabel("Cash Out");
		lblSales.setEnabled(false);
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSales.setFont(new Font("Arial", Font.BOLD, 25));
		lblSales.setBounds(509, 11, 126, 53);
		frmCashout.getContentPane().add(lblSales);

		lblDate = new JLabel("");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(994, 23, 146, 31);
		frmCashout.getContentPane().add(lblDate);

		Date date = new Date();

		java.sql.Date dateT = new java.sql.Date(date.getTime());

		lblDate.setText("" + dateT);

		btnPrevious = new JButton("Prevoius CashOut");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreviousCash pc = new PreviousCash();
				
				pc.NewScreen();
				
			}
		});
		btnPrevious.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\apoyo-tecnico.png"));
		btnPrevious.setHorizontalAlignment(SwingConstants.LEADING);
		btnPrevious.setFont(new Font("Arial", Font.BOLD, 20));
		btnPrevious.setBounds(258, 375, 293, 73);
		frmCashout.getContentPane().add(btnPrevious);
		
		JLabel lblTotalE = new JLabel("Total Expectation: $");
		lblTotalE.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalE.setBounds(599, 292, 180, 31);
		frmCashout.getContentPane().add(lblTotalE);
		
		JLabel lblTotal = new JLabel("Total: $");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotal.setBounds(599, 458, 75, 31);
		frmCashout.getContentPane().add(lblTotal);
		
		JLabel lblTotalProducts = new JLabel("Total Products: $");
		lblTotalProducts.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalProducts.setBounds(599, 375, 152, 31);
		frmCashout.getContentPane().add(lblTotalProducts);
		
		JLabel lblTotalEN = new JLabel("$");
		lblTotalEN.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalEN.setBounds(776, 292, 112, 31);
		frmCashout.getContentPane().add(lblTotalEN);
		
		JLabel lblTotalPN = new JLabel("$");
		lblTotalPN.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalPN.setBounds(752, 375, 112, 31);
		frmCashout.getContentPane().add(lblTotalPN);
		
		JLabel lblTotalN = new JLabel("$");
		lblTotalN.setFont(new Font("Arial", Font.BOLD, 18));
		lblTotalN.setBounds(667, 458, 112, 31);
		frmCashout.getContentPane().add(lblTotalN);
		
		cc.findCashProducts(mc);
		
		double cashProducts = mc.getTotalProduct();
		
		cc.findCashSales(mc);
		
		double cashSales = mc.getTotal();
		
		double totalEstimated = cashProducts + cashSales;
		
		lblTotalEN.setText("" + totalEstimated);
		lblTotalPN.setText("" + cashProducts);
		lblTotalN.setText("" + cashSales);
	
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 126, 541, 156);
		frmCashout.getContentPane().add(scrollPane_1);
		
		tProducts = new JTable();
		tProducts.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPane_1.setViewportView(tProducts);
		
		model2.addColumn("Id");
		model2.addColumn("Product");
		model2.addColumn("Cost");
		model2.addColumn("Stock");

		String[] show2 = new String[4];

		Connection cn2;
		PreparedStatement ps2 = null;
		ResultSet rs2;

		try {

			cn2 = cone.getConnection();

			ps2 = cn2.prepareStatement("SELECT id, product, cost, stock FROM product");

			rs2 = ps2.executeQuery();

			while (rs2.next()) {

				show2[0] = rs2.getString(1);
				show2[1] = rs2.getString(2);
				show2[2] = rs2.getString(3);
				show2[3] = rs2.getString(4);

				model2.addRow(show2);

			}

			tProducts.setModel(model2);

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		JLabel lblProductsN = new JLabel("Products");
		lblProductsN.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductsN.setFont(new Font("Arial", Font.BOLD, 25));
		lblProductsN.setEnabled(false);
		lblProductsN.setBounds(202, 56, 126, 53);
		frmCashout.getContentPane().add(lblProductsN);
		
		JLabel lblSalesN = new JLabel("Sales");
		lblSalesN.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesN.setFont(new Font("Arial", Font.BOLD, 25));
		lblSalesN.setEnabled(false);
		lblSalesN.setBounds(803, 56, 126, 53);
		frmCashout.getContentPane().add(lblSalesN);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				user = l.user;
				
				mc.setUserName(user);
				mc.setTotalEstimated(Double.parseDouble(lblTotalEN.getText()));
				mc.setTotal(Double.parseDouble(lblTotalN.getText()));
				mc.setDateToday(dateT);
				
				if (cc.deleteSales() && cc.saveCashOut(mc)) {
					
					JOptionPane.showMessageDialog(null, "CASH OUT SAVED");
					
				} else {
					
					JOptionPane.showMessageDialog(null, "ERROR");
					
				}
				

			}
		});
		btnSave.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\grafica.png"));
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(258, 292, 293, 73);
		frmCashout.getContentPane().add(btnSave);
		

	}
}

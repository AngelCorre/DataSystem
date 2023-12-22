package views;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;

import controllers.CInventary;
import controllers.CSale;
import models.MInventary;
import models.MSale;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import javax.swing.border.BevelBorder;

public class Administrador {

	private JFrame frmAdministrador;

	DefaultTableModel model = new DefaultTableModel();

	private MSale ms = new MSale();
	private CSale cs = new CSale();
	private CInventary ci = new CInventary();
	private MInventary mi = new MInventary();
	private Login l = new Login();

	private String user;
	private String role;
	private double totalC = 0.0;
	private int item = 0;

	private JTable tSale;
	private JTextField txtCode;
	private JTextField txtProduct;
	private JTextField txtAdd;
	private JTextField txtCost;
	private JTextField txtStock;
	private JLabel lblPurchaseT;

	/**
	 * Launch the application.
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrador window = new Administrador();
					window.frmAdministrador.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Administrador() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		user = l.user;

		model.addColumn("Code");
		model.addColumn("Product");
		model.addColumn("Amount");
		model.addColumn("Cost");
		model.addColumn("Total");

		frmAdministrador = new JFrame();
		frmAdministrador.setTitle("DataSystem");
		frmAdministrador.setBounds(100, 100, 1114, 642);
		frmAdministrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministrador.getContentPane().setLayout(null);
		frmAdministrador.setLocationRelativeTo(null);

		JButton btnRUsers = new JButton("Register Users");
		btnRUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnRUsers.setFont(new Font("Arial", Font.BOLD, 17));
		btnRUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RUsers ru = new RUsers();

				ru.NewScreen();

			}
		});
		btnRUsers.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\addUser.png"));
		btnRUsers.setBounds(33, 95, 279, 73);
		frmAdministrador.getContentPane().add(btnRUsers);

		JLabel lblBienvenida = new JLabel("Welcome " + user);
		lblBienvenida.setIcon(new ImageIcon("C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\creatividad.png"));
		lblBienvenida.setFont(new Font("Arial", Font.BOLD, 25));
		lblBienvenida.setBounds(33, 10, 393, 75);
		frmAdministrador.getContentPane().add(lblBienvenida);

		JButton btnRProducts = new JButton("Register Products");
		btnRProducts.setHorizontalAlignment(SwingConstants.LEFT);
		btnRProducts.setFont(new Font("Arial", Font.BOLD, 17));
		btnRProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RProducts rp = new RProducts();

				rp.NewScreen();

			}
		});
		btnRProducts.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\apoyo-tecnico.png"));
		btnRProducts.setBounds(33, 261, 279, 73);
		frmAdministrador.getContentPane().add(btnRProducts);

		JButton btnCash = new JButton("Cash Out");
		btnCash.setHorizontalAlignment(SwingConstants.LEFT);
		btnCash.setFont(new Font("Arial", Font.BOLD, 17));
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CashOut co = new CashOut();

				co.NewScreen();

				/*
				 * try { File path = new
				 * File("D:\\pelon\\UPT\\4to Cuatrimestre\\ENGLISH IV\\CV.pdf");
				 * Desktop.getDesktop().open(path); } catch (IOException e2) {
				 * e2.printStackTrace(); }
				 */

			}
		});
		btnCash.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\impresora.png"));
		btnCash.setBounds(33, 510, 279, 73);
		frmAdministrador.getContentPane().add(btnCash);

		JButton btnSales = new JButton("Sales");
		btnSales.setHorizontalAlignment(SwingConstants.LEFT);
		btnSales.setFont(new Font("Arial", Font.BOLD, 17));
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Sales s = new Sales();

				s.NewScreen();

			}
		});
		btnSales.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\tecnico.png"));
		btnSales.setBounds(33, 427, 279, 73);
		frmAdministrador.getContentPane().add(btnSales);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 173, 700, 320);
		frmAdministrador.getContentPane().add(scrollPane);

		tSale = new JTable();
		tSale.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tSale.setFont(new Font("Arial", Font.PLAIN, 14));
		tSale.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Code", "Product", "Add", "Cost", "Total" }));
		scrollPane.setViewportView(tSale);

		txtCode = new JTextField();
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				try {
					
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {

						if (!"".equals(txtCode.getText())) {

							int id = Integer.parseInt(txtCode.getText());

							mi.setId(id);

							for (int i = 0; i < tSale.getRowCount(); i++) {

								if (tSale.getValueAt(i, 0).equals(txtCode.getText())) {

									JOptionPane.showMessageDialog(null, "THE PRODUCT HAS BEEN REGISTERED");

									txtCode.setEditable(true);
									txtAdd.setEditable(false);

									clean();

									return;

								}

							}

							if (ci.findById(mi)) {

								txtProduct.setText("" + mi.getProduct());
								txtCost.setText("" + Double.toString(mi.getCost()));
								txtStock.setText("" + Integer.toString(mi.getStock()));

								txtCode.setEditable(false);
								txtAdd.setEditable(true);
								txtAdd.requestFocus();

							}

						} else {

							JOptionPane.showMessageDialog(null, "INVALID CODE");
						}

					}
					
				} catch (Exception e2) {
					
					e2.printStackTrace();
					
					JOptionPane.showMessageDialog(null, "INVALID CODE");
					
				}

			}
		});
		txtCode.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCode.setBounds(368, 130, 109, 33);
		frmAdministrador.getContentPane().add(txtCode);
		txtCode.setColumns(10);

		JLabel lblCode = new JLabel("Code:");
		lblCode.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCode.setBounds(368, 95, 109, 25);
		frmAdministrador.getContentPane().add(lblCode);

		txtProduct = new JTextField();
		txtProduct.setEditable(false);
		txtProduct.setFont(new Font("Arial", Font.PLAIN, 14));
		txtProduct.setColumns(10);
		txtProduct.setBounds(487, 130, 109, 33);
		frmAdministrador.getContentPane().add(txtProduct);

		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		lblProduct.setBounds(487, 95, 109, 25);
		frmAdministrador.getContentPane().add(lblProduct);

		txtAdd = new JTextField();
		txtAdd.setEditable(false);
		txtAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (!"".equals(txtAdd.getText())) {

						String cod = txtCode.getText();
						String product = txtProduct.getText();
						int amount = Integer.parseInt(txtAdd.getText());
						double cost = Double.parseDouble(txtCost.getText());

						if (Integer.parseInt(txtAdd.getText()) <= Integer.parseInt(txtStock.getText())) {

							double total = amount * cost;

							item = item + 1;

							ArrayList lista = new ArrayList();

							lista.add(item);
							lista.add(cod);
							lista.add(product);
							lista.add(amount);
							lista.add(cost);
							lista.add(total);

							Object[] table = new Object[5];

							table[0] = lista.get(1);
							table[1] = lista.get(2);
							table[2] = lista.get(3);
							table[3] = lista.get(4);
							table[4] = lista.get(5);

							model.addRow(table);

							tSale.setModel(model);

							txtCode.setEditable(true);
							txtAdd.setEditable(false);
							txtCode.requestFocus();

							total();
							clean();

						} else {

							JOptionPane.showMessageDialog(null, "INSUFFICIENT STOCK");

						}

					} else {

						JOptionPane.showMessageDialog(null, "INVALID AMOUNT");

					}

				}

			}
		});
		txtAdd.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAdd.setColumns(10);
		txtAdd.setBounds(606, 130, 109, 33);
		frmAdministrador.getContentPane().add(txtAdd);

		JLabel lblAdd = new JLabel("Add:");
		lblAdd.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAdd.setBounds(606, 95, 109, 25);
		frmAdministrador.getContentPane().add(lblAdd);

		txtCost = new JTextField();
		txtCost.setEditable(false);
		txtCost.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCost.setColumns(10);
		txtCost.setBounds(725, 130, 109, 33);
		frmAdministrador.getContentPane().add(txtCost);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCost.setBounds(725, 95, 109, 25);
		frmAdministrador.getContentPane().add(lblCost);

		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setFont(new Font("Arial", Font.PLAIN, 14));
		txtStock.setColumns(10);
		txtStock.setBounds(844, 130, 109, 33);
		frmAdministrador.getContentPane().add(txtStock);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Arial", Font.PLAIN, 16));
		lblStock.setBounds(844, 95, 109, 25);
		frmAdministrador.getContentPane().add(lblStock);

		lblPurchaseT = new JLabel("Purchase total: $0.0");
		lblPurchaseT.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPurchaseT.setBounds(853, 503, 215, 32);
		frmAdministrador.getContentPane().add(lblPurchaseT);

		JButton btnConfirm = new JButton("Confirm purchase");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (model.getRowCount() > 0) {

					Date date = new Date();

					java.sql.Date dateT = new java.sql.Date(date.getTime());

					ms.setSellerName(user);
					ms.setTotal(totalC);
					ms.setDateT(dateT);

					for (int i = 0; i < model.getRowCount(); i++) {

						int id = Integer.parseInt(String.valueOf(model.getValueAt(i, 0)));
						int amount = Integer.parseInt(String.valueOf(model.getValueAt(i, 2)));

						mi.setId(id);

						ci.findByIdStock(mi);

						int stockP = mi.getStock() - amount;

						mi.setStock(stockP);

						ci.updateStock(mi);

					}

					if (cs.save(ms)) {

						pdf();
						
						while (model.getRowCount() > 0) {

							model.removeRow(0);

						}

						JOptionPane.showMessageDialog(null, "PURCHASE MADE");
						total();

					}

				} else {

					JOptionPane.showMessageDialog(null, "ADD A PRODUCT");

				}

			}
		});
		btnConfirm.setFont(new Font("Arial", Font.BOLD, 18));
		btnConfirm.setBounds(559, 503, 241, 32);
		frmAdministrador.getContentPane().add(btnConfirm);

		JButton btnDelete = new JButton("Delete Product");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					model.removeRow(tSale.getSelectedRow());

					total();

					txtCode.requestFocus();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "UNSELECTED ROW");
				}

			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setBounds(368, 503, 181, 32);
		frmAdministrador.getContentPane().add(btnDelete);

		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Inventory i = new Inventory();

				i.NewScreen();

			}
		});
		btnInventory.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\editar.png"));
		btnInventory.setHorizontalAlignment(SwingConstants.LEFT);
		btnInventory.setFont(new Font("Arial", Font.BOLD, 17));
		btnInventory.setBounds(33, 344, 279, 73);
		frmAdministrador.getContentPane().add(btnInventory);

		JButton btnProfiles = new JButton("Profiles");
		btnProfiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Profiles p = new Profiles();

				p.NewScreen();

			}
		});
		btnProfiles.setIcon(new ImageIcon(
				"C:\\Users\\pelon\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PuntoVenta\\src\\img\\informationuser.png"));
		btnProfiles.setHorizontalAlignment(SwingConstants.LEFT);
		btnProfiles.setFont(new Font("Arial", Font.BOLD, 17));
		btnProfiles.setBounds(33, 178, 279, 73);
		frmAdministrador.getContentPane().add(btnProfiles);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clean();
				txtCode.setEditable(true);
				txtAdd.setEditable(false);
				txtCode.requestFocus();	
				
			}
		});
		btnX.setFont(new Font("Arial", Font.BOLD, 18));
		btnX.setBounds(1020, 129, 48, 32);
		frmAdministrador.getContentPane().add(btnX);
		
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login l = new Login();
				
				l.main(null);
				
				frmAdministrador.dispose();
				
				
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		btnNewButton.setBounds(994, 10, 96, 25);
		frmAdministrador.getContentPane().add(btnNewButton);
		
		role = l.role;

		if (role.equals("Librarian")) {
			
			btnRUsers.setEnabled(false);
			btnSales.setEnabled(false);
			btnProfiles.setEnabled(false);

		}
		

	}

	private void total() {

		totalC = 0.0;

		int rowNumber = tSale.getRowCount();

		for (int i = 0; i < rowNumber; i++) {

			double cos = Double.parseDouble(String.valueOf(model.getValueAt(i, 4)));
			totalC = totalC + cos;

		}

		lblPurchaseT.setText("Purchase total: $" + totalC);

	}

	private void clean() {

		txtCode.setText("");
		txtProduct.setText("");
		txtAdd.setText("");
		txtCost.setText("");
		txtStock.setText("");

	}
	
private void pdf() {
	
	MSale ms = new MSale();
	CSale cs = new CSale();	
	
		try {
			
			FileOutputStream fileD;
			
			cs.findLastId(ms);
			
			int id = ms.getId();
			
			File file = new File("src/pdfs/sale" + id + ".pdf");
			
			fileD = new FileOutputStream(file);
			
			Document doc = new Document();
			
			PdfWriter.getInstance(doc, fileD);
			
			doc.open();
			
			Image img = Image.getInstance("src/images/creatividad.png");
			
			Paragraph date = new Paragraph();
			//com.itextpdf.text.Font;
			
			date.add(Chunk.NEWLINE);
			
			Date dateToday = new Date();
			
			date.add("DATE: " + new SimpleDateFormat("dd-MM-yyyy").format(dateToday) + "\n\n");
			
			PdfPTable header = new PdfPTable(4);
			
			header.setWidthPercentage(100);
			header.getDefaultCell().setBorder(0);
			
			float[] columnHeader = new float[]{20f, 30f, 70f, 40f};
			
			header.setWidths(columnHeader);
			header.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			header.addCell(img);
			
			String name = "Angel Correa";
			String tel = "5569020214";
			String position = "Java Developer";
			
			header.addCell("");
			header.addCell("Name: " + name + "\n\nTel: " + tel + "\n\nPosition: " + position);
			header.addCell(date);
			doc.add(header);
			
			Paragraph user = new Paragraph();
			
			user.add(Chunk.NEWLINE);
			user.add("" + "\n\n");
			doc.add(user);
			
			PdfPTable tableP = new PdfPTable(5);
			tableP.setWidthPercentage(100);
			tableP.getDefaultCell().setBorder(0);
			float[] columnProducts = new float[]{15f, 20f, 20f, 20f, 20f};
			tableP.setWidths(columnProducts);
			tableP.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell p = new PdfPCell(new Phrase("Product"));
			PdfPCell p2 = new PdfPCell(new Phrase("Add"));
			PdfPCell p3 = new PdfPCell(new Phrase("Cost"));
			PdfPCell p4 = new PdfPCell(new Phrase("Total"));
			PdfPCell p5 = new PdfPCell(new Phrase(""));
			
			
			p.setBorder(0);
			p2.setBorder(0);
			p3.setBorder(0);
			p4.setBorder(0);
			p5.setBorder(0);
			
			tableP.addCell(p5);
			tableP.addCell(p);
			tableP.addCell(p2);
			tableP.addCell(p3);
			tableP.addCell(p4);
			
			for (int i = 0; i < tSale.getRowCount(); i++) {
				
				String product = tSale.getValueAt(i, 1).toString();
				String add = tSale.getValueAt(i, 2).toString();
				String cost = tSale.getValueAt(i, 3).toString();
				String total = tSale.getValueAt(i, 4).toString();
				
				tableP.addCell("");
				tableP.addCell(product);
				tableP.addCell(add);
				tableP.addCell(cost);
				tableP.addCell(total);
				
			}
			
			doc.add(tableP);
			
			user.add(Chunk.NEWLINE);
			user.add("¡THANKS FOR YOUR PURCHASE!" + "\n\n");
			user.setAlignment(Element.ALIGN_CENTER);
			doc.add(user);
			
			
			doc.close();
			fileD.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}

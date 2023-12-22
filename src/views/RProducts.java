package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controllers.CInventary;
import models.MInventary;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RProducts {

	private JFrame frmInventario;
	private JTextField txtName;
	private JLabel lblRegisterP;
	private JLabel lblAuthor;
	private JTextField txtCost;
	private JLabel lblCost;
	private JTextField txtStock;
	private JLabel lblStock;
	private JButton btnRegister;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnFind;

	MInventary mi = new MInventary();
	CInventary ci = new CInventary();
	private JTextField txtAuthor;
	private JTextField txtId;
	private JButton btnClean;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RProducts window = new RProducts();
					window.frmInventario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RProducts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInventario = new JFrame();
		frmInventario.setTitle("RegisterProducts");
		frmInventario.setBounds(100, 100, 490, 442);
		frmInventario.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frmInventario.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.PLAIN, 20));
		lblName.setBounds(57, 123, 140, 18);
		frmInventario.getContentPane().add(lblName);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					txtAuthor.requestFocus();

				}

			}
		});
		txtName.setBounds(57, 154, 140, 25);
		frmInventario.getContentPane().add(txtName);
		txtName.setColumns(10);

		lblRegisterP = new JLabel("Register Products");
		lblRegisterP.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterP.setFont(new Font("Arial", Font.BOLD, 25));
		lblRegisterP.setBounds(10, 10, 456, 38);
		frmInventario.getContentPane().add(lblRegisterP);

		lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Arial", Font.PLAIN, 20));
		lblAuthor.setBounds(57, 189, 140, 18);
		frmInventario.getContentPane().add(lblAuthor);

		txtCost = new JTextField();
		txtCost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					txtStock.requestFocus();

				}
				
			}
		});
		txtCost.setColumns(10);
		txtCost.setBounds(57, 286, 140, 25);
		frmInventario.getContentPane().add(txtCost);

		lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCost.setBounds(57, 255, 140, 18);
		frmInventario.getContentPane().add(lblCost);

		txtStock = new JTextField();
		txtStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnRegister.requestFocus();

				}
				
			}
		});
		txtStock.setColumns(10);
		txtStock.setBounds(57, 352, 140, 25);
		frmInventario.getContentPane().add(txtStock);

		lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("Arial", Font.PLAIN, 20));
		lblStock.setBounds(57, 321, 140, 18);
		frmInventario.getContentPane().add(lblStock);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtName.getText().equals("") && !txtAuthor.getText().equals("") && !txtCost.getText().equals("")
						&& !txtStock.getText().equals("")) {

					mi.setProduct(txtName.getText());
					
					if (!ci.findByName(mi)) {
						
						int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?");

						if (decision == 0) {

							mi.setProduct(txtName.getText());
							mi.setAuthor(txtAuthor.getText());
							mi.setCost(Double.parseDouble(txtCost.getText()));
							mi.setStock(Integer.parseInt(txtStock.getText()));

							if (ci.save(mi)) {

								JOptionPane.showMessageDialog(null, "PRODUCT HAS BEEN REGISTERED");
								clean();

							} else {

								JOptionPane.showMessageDialog(null, "ERROR");

							}

						}
						
					}

				} else {

					JOptionPane.showMessageDialog(null, "FILL OUT ALL THE FIELDS");

				}

			}
		});
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRegister.setBounds(254, 82, 140, 31);
		frmInventario.getContentPane().add(btnRegister);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int id = Integer.parseInt(JOptionPane.showInputDialog("ENTER THE ID OF THE PRODUCT TO DELETE"));

					mi.setId(id);

					if (ci.findById(mi)) {

						int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?");

						if (decision == 0) {

							if (ci.delete(id)) {

								JOptionPane.showMessageDialog(null, "PRODUCT HAS BEEN DELETED");

							} else {

								JOptionPane.showMessageDialog(null, "INVALID ID");

							}

						}

					}

				} catch (Exception e2) {

					e2.printStackTrace();

					JOptionPane.showMessageDialog(null, "INVALID ID");

				}

			}
		});
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 18));
		btnDelete.setBounds(254, 148, 140, 31);
		frmInventario.getContentPane().add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txtName.getText().equals("") && !txtAuthor.getText().equals("") && !txtCost.getText().equals("")
						&& !txtStock.getText().equals("")) {

					try {

						int id = Integer.parseInt(txtId.getText());

						int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?");

						mi.setProduct(txtName.getText());
						mi.setAuthor(txtAuthor.getText());
						mi.setCost(Double.parseDouble(txtCost.getText()));
						mi.setStock(Integer.parseInt(txtStock.getText()));
						mi.setId(id);

						if (decision == 0) {

							if (ci.update(mi)) {

								JOptionPane.showMessageDialog(null, "PRODUCT HAS BEEN UPDATED");

								clean();

							} else {

								JOptionPane.showMessageDialog(null, "ERROR");

							}

						}

					} catch (Exception e2) {

						e2.printStackTrace();

						JOptionPane.showMessageDialog(null, "INVALID ID");

					}

				} else {

					JOptionPane.showMessageDialog(null, "FILL OUT ALL THE FIELDS");

				}

			}
		});
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 18));
		btnUpdate.setBounds(254, 214, 140, 31);
		frmInventario.getContentPane().add(btnUpdate);

		btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int id = Integer.parseInt(JOptionPane.showInputDialog("ENTER THE ID OF THE PRODUCT TO FIND"));

					mi.setId(id);

					if (ci.findById(mi)) {

						txtId.setText("" + mi.getId());
						txtName.setText("" + mi.getProduct());
						txtAuthor.setText("" + mi.getAuthor());
						txtCost.setText("" + mi.getCost());
						txtStock.setText("" + mi.getStock());

					}

				} catch (Exception e2) {

					e2.printStackTrace();

					JOptionPane.showMessageDialog(null, "INVALID ID");

				}

			}
		});
		btnFind.setFont(new Font("Arial", Font.PLAIN, 18));
		btnFind.setBounds(254, 280, 140, 31);
		frmInventario.getContentPane().add(btnFind);

		txtAuthor = new JTextField();
		txtAuthor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					txtCost.requestFocus();

				}
				
			}
		});
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(57, 220, 140, 25);
		frmInventario.getContentPane().add(txtAuthor);

		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Arial", Font.PLAIN, 20));
		lblId.setBounds(57, 57, 140, 18);
		frmInventario.getContentPane().add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(57, 88, 140, 25);
		frmInventario.getContentPane().add(txtId);

		btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clean();

			}
		});
		btnClean.setFont(new Font("Arial", Font.PLAIN, 18));
		btnClean.setBounds(254, 347, 140, 31);
		frmInventario.getContentPane().add(btnClean);

	}

	private void clean() {

		txtId.setText("");
		txtName.setText("");
		txtAuthor.setText("");
		txtCost.setText("");
		txtStock.setText("");

	}

}

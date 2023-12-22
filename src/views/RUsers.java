package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controllers.CUser;
import models.MUser;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RUsers {

	private JFrame frame;
	private JTextField txtName;
	private JPasswordField pfPassword;
	private JButton btnRegister;
	
	MUser mu = new MUser();
	
	CUser cu = new CUser();
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RUsers window = new RUsers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RUsers() {
		initialize();
	
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Register Users");
		frame.setBounds(100, 100, 490, 524);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegister = new JLabel("Register Users");
		lblRegister.setFont(new Font("Arial", Font.BOLD, 25));
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setBounds(0, 21, 476, 38);
		frame.getContentPane().add(lblRegister);
		
		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					pfPassword.requestFocus();

				}
				
			}
		});
		txtName.setFont(new Font("Arial", Font.PLAIN, 15));
		txtName.setBounds(58, 179, 140, 25);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.PLAIN, 20));
		lblName.setBounds(58, 148, 140, 18);
		frame.getContentPane().add(lblName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPassword.setBounds(58, 214, 140, 18);
		frame.getContentPane().add(lblPassword);
		
		JComboBox cmbRole = new JComboBox();
		cmbRole.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnRegister.requestFocus();

				}
				
			}
		});
		cmbRole.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"SysAdmin", "Librarian"}));
		cmbRole.setBounds(58, 377, 140, 25);
		frame.getContentPane().add(cmbRole);
		
		JComboBox cmbStatus = new JComboBox();
		cmbStatus.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					cmbRole.requestFocus();

				}
				
			}
		});
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] {"Active", "Disable"}));
		cmbStatus.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbStatus.setBounds(58, 311, 140, 25);
		frame.getContentPane().add(cmbStatus);
		
		pfPassword = new JPasswordField();
		pfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					cmbStatus.requestFocus();

				}
			
			}
		});
		pfPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		pfPassword.setBounds(58, 245, 140, 25);
		frame.getContentPane().add(pfPassword);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRole.setBounds(58, 346, 140, 18);
		frame.getContentPane().add(lblRole);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!txtName.getText().equals("") && !pfPassword.getText().equals("")){
					
					String name = txtName.getText();
					
					mu.setUserName(name);
					
					if (!cu.findByName(mu)) {
						
						int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?");
						
						if (decision == 0) {
							
							mu.setUserName(txtName.getText());
							mu.setPassword(pfPassword.getText());
							int role = cmbRole.getSelectedIndex() + 1;
							int status = cmbStatus.getSelectedIndex() + 1;
							
							if (role == 1) {
								
								mu.setRole("SysAdmin");
								
							} else {
								
								mu.setRole("Librarian");
								
							}
							
							if (status == 1) {
								
								mu.setStatus("Active");
								
							} else {
								
								mu.setStatus("Disable");
								
							}
							
							if (cu.save(mu)) {
								
								JOptionPane.showMessageDialog(null, "USER HAS BEEN REGISTERED");
								
							} else {
								
								JOptionPane.showMessageDialog(null, "ERROR");
								
							}
							
							clean();
							
						}
						
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null, "FILL OUT ALL THE FIELDS");
					
				}
				
			}
		});
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRegister.setBounds(254, 107, 140, 31);
		frame.getContentPane().add(btnRegister);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if (!txtName.getText().equals("") && !pfPassword.getText().equals("")) {
					
						int id = Integer.parseInt(txtId.getText());
						
						int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?");
						
						mu.setId(id);
						mu.setUserName(txtName.getText());
						mu.setPassword(pfPassword.getText());
						int role = cmbRole.getSelectedIndex() + 1;
						int status = cmbStatus.getSelectedIndex() + 1;
						
						if (role == 1) {
							
							mu.setRole("SysAdmin");
							
						} else {
							
							mu.setRole("Librarian");
							
						}
						
						if (status == 1) {
							
							mu.setStatus("Active");
							
						} else {
							
							mu.setStatus("Disable");
							
						}
						
						if (decision == 0) {
							
							if (cu.update(mu)) {
								
								JOptionPane.showMessageDialog(null, "USER HAS BEEN UPDATED");
								clean();
								
							} else {
								
								JOptionPane.showMessageDialog(null, "ERROR");
								
							}
							
						}
						
					} else {
						
						JOptionPane.showMessageDialog(null, "FILL OUT ALL THE FIELDS");
						
					}
					
					
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "INVALID ID");
					
				}
				
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 18));
		btnUpdate.setBounds(254, 241, 140, 31);
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		lblStatus.setBounds(58, 280, 140, 18);
		frame.getContentPane().add(lblStatus);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.parseInt(JOptionPane.showInputDialog("ENTER THE ID OF THE USER TO DELETE"));
					
					mu.setId(id);
					
					if (cu.findById(mu)) {
						
						int decision = JOptionPane.showConfirmDialog(null, "ARE YOU SURE?");
						
						if (decision == 0) {
							
							if(cu.delete(id)) {
								
								JOptionPane.showMessageDialog(null, "USER HAS BEEN DELETED");
								
								
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
		btnDelete.setBounds(254, 175, 140, 31);
		frame.getContentPane().add(btnDelete);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					int id = Integer.parseInt(JOptionPane.showInputDialog("ENTER THE ID OF THE USER TO FIND"));
					
					mu.setId(id);
					
					if (cu.findById(mu)) {
						
						int numberRole = 0;
						int numberStatus = 0;
						
						if (mu.getRole().equals("SysAdmin") && mu.getStatus().equals("Active")) {
							
							numberRole = 0;
							numberStatus = 0;
							
						} else if (mu.getRole().equals("SysAdmin") && mu.getStatus().equals("Disable")) {
							
							numberRole = 0;
							numberStatus = 1;
							
						} else if (mu.getRole().equals("Librarian") && mu.getStatus().equals("Active")) {
							
							numberRole = 1;
							numberStatus = 0;
							
						} else if (mu.getRole().equals("Librarian") && mu.getStatus().equals("Disable")) {
							
							numberRole = 1;
							numberStatus = 1;
							
						}
						
						txtId.setText("" + id);
						txtName.setText(mu.getUserName());
						pfPassword.setText(mu.getPassword());
						cmbRole.setSelectedIndex(numberRole);
						cmbStatus.setSelectedIndex(numberStatus);
						
					}
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "INVALID ID");
					
				}
				
				
			}
		});
		btnFind.setFont(new Font("Arial", Font.PLAIN, 18));
		btnFind.setBounds(254, 307, 140, 31);
		frame.getContentPane().add(btnFind);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Arial", Font.PLAIN, 20));
		lblId.setBounds(58, 82, 140, 18);
		frame.getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setFont(new Font("Arial", Font.PLAIN, 15));
		txtId.setColumns(10);
		txtId.setBounds(58, 113, 140, 25);
		frame.getContentPane().add(txtId);
		
		JButton btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clean();
				
			}
		});
		btnClean.setFont(new Font("Arial", Font.PLAIN, 18));
		btnClean.setBounds(254, 373, 140, 31);
		frame.getContentPane().add(btnClean);
	}
	
	private void clean() {
		
		txtId.setText("");
		txtName.setText("");
		pfPassword.setText("");
		
	}
}

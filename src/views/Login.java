package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controllers.CLogin;
import models.Cone;
import models.MUser;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

	private JFrame frame;
	private JTextField txtUser;

	public static String user = null;
	public static String role = null;

	MUser m = new MUser();

	Cone cone = new Cone();

	CLogin cl = new CLogin();
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 488, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblDataSystem = new JLabel("DataSystem");
		lblDataSystem.setFont(new Font("Arial", Font.BOLD, 30));
		lblDataSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataSystem.setBounds(10, 20, 454, 48);
		frame.getContentPane().add(lblDataSystem);

		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Arial", Font.BOLD, 25));
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setBounds(84, 137, 148, 37);
		frame.getContentPane().add(lblUser);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 25));
		lblPassword.setBounds(84, 214, 148, 37);
		frame.getContentPane().add(lblPassword);

		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					txtPassword.requestFocus();

				}

			}
		});
		txtUser.setFont(new Font("Arial", Font.PLAIN, 20));
		txtUser.setBounds(242, 138, 148, 37);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JButton btnLogin = new JButton("LogIn");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String password;

				user = txtUser.getText();
				password = txtPassword.getText();

				m.setUserName(user);
				m.setPassword(password);

				if (cl.LogIn(m)) {
					
					role = m.getRole();

					if (!user.equals("") || !password.equals("")) {

						if (m.getRole().equals("SysAdmin") && m.getStatus().equals("Active")) {

							Administrador admin = new Administrador();

							admin.NewScreen();

							frame.dispose();

						} else if (m.getRole().equals("Librarian") && m.getStatus().equals("Active")) {

							Administrador admin = new Administrador();

							admin.NewScreen();

							frame.dispose();

						} else {

							JOptionPane.showMessageDialog(null, "DISABLED ACCOUNT");

						}

					} else {

						JOptionPane.showMessageDialog(null, "FILL OUT ALL THE FIELDS");

					}

				} else {

					JOptionPane.showMessageDialog(null, "INCORRECT CREDENTIALS");

				}

			}
		});
		btnLogin.setFont(new Font("Arial", Font.BOLD, 25));
		btnLogin.setBounds(138, 310, 196, 48);
		frame.getContentPane().add(btnLogin);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnLogin.requestFocus();

				}

			}
		});
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 20));
		txtPassword.setBounds(242, 215, 148, 37);
		frame.getContentPane().add(txtPassword);
	}

}

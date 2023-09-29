package main.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogPage implements ActionListener {
	java.net.URL url = ClassLoader.getSystemResource("app/src/main/java/main/src/images/EIcon.jpg");

	/** Frame and layout panels for the login frame */
	protected static JFrame LoginFrame;
	private JFrame invalid;
	protected static JTextField tmpUser;
	protected JPasswordField tmpPass;

	// Strings for user input
	protected static String username = null;
	protected static String password = null;

	static protected String userID;

	/** Buttons for login */
	static JButton[] LogButtons = new JButton[2];

	// Button for invaled frame
	protected JButton reset;

	static Font myFont = new Font("Times New Roman", Font.BOLD, 25); // Font for text and button text.
	static ImageIcon EIcon = new ImageIcon("app/src/main/java/main/src/images/EIcon.jpg");
	

	void LoginPage() {

		/** Creates main frame and its settings */
		LoginFrame = new JFrame("E-Book Reader Login");
		LoginFrame.setIconImage(EIcon.getImage());
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginFrame.setSize(400, 300);
		LoginFrame.setLocationRelativeTo(null);
		LoginFrame.setLayout(new BorderLayout());
		LoginFrame.setResizable(false);

		/** Top Text */
		JLabel Message = new JLabel();
		Message.setText("Login to your account");
		Message.setFont(myFont);

		/** Top Panel */
		JPanel panelT = new JPanel();
		panelT.setBackground(Color.white);
		panelT.setPreferredSize(new Dimension(50, 70));
		panelT.add(Message);

		/** All elements Top Panel */
		tmpUser = new JTextField();
		tmpUser.setPreferredSize(new Dimension(250, 40));

		tmpPass = new JPasswordField();
		tmpPass.setPreferredSize(new Dimension(250, 40));
		tmpPass.setEchoChar('*');

		//Labels
		JLabel user = new JLabel();
		user.setText("Username:");
		JLabel pass = new JLabel();
		pass.setText("Password:");

		// Center Panel
		JPanel panelC = new JPanel();
		panelC.setBackground(Color.white);
		panelC.setPreferredSize(new Dimension(25, 10));
		panelC.setLayout(new GridLayout(0, 1));
		panelC.add(user);
		panelC.add(tmpUser);
		panelC.add(pass);
		panelC.add(tmpPass);

		/** All Elements and Bottom Panel */
		// Buttons
		LogButtons[0] = new JButton("Login");
		LogButtons[1] = new JButton("Sign-Up");

		// Changes font and adds ActionListener to each button
		for (int i = 0; i < 2; i++) {
			LogButtons[i].addActionListener(this);
			LogButtons[i].setFont(myFont);
			LogButtons[i].setFocusable(false);
			LogButtons[i].setBackground(Color.white);
		}

		// Bottom Panel
		JPanel panelB = new JPanel();
		panelB.setBackground(Color.white);
		panelB.setPreferredSize(new Dimension(50, 60));
		panelB.setLayout(new GridLayout(1, 2, 10, 0));

		panelB.add(LogButtons[0]);
		panelB.add(LogButtons[1]);

		// adds
		LoginFrame.add(panelT, BorderLayout.NORTH);
		LoginFrame.add(panelC, BorderLayout.CENTER);
		LoginFrame.add(panelB, BorderLayout.SOUTH);

		LoginFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == LogButtons[0]) {
			username = tmpUser.getText();
			password = String.valueOf(tmpPass.getPassword());
			try {
				Connect connect = new Connect();
				connect.login();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			tmpUser.setText("");
			tmpPass.setText("");
		}
		if (e.getSource() == LogButtons[1]) {
			username = tmpUser.getText();
			password = String.valueOf(tmpPass.getPassword());
			try {
				Connect connect = new Connect();
				connect.Signup();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			tmpUser.setText("");
			tmpPass.setText("");
		}
		if (e.getSource() == reset) {
			invalid.dispose();
		}
	}
}

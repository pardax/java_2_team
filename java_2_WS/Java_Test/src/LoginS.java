

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.mysql.cj.protocol.Resultset;



public class LoginS extends JFrame implements ActionListener {
	
		//DB
		private Connection conn = null;
		private Statement stmt;
		private ResultSet rs;
		
		String idsql;
		
		static String customerid;
		
		private JPanel logp1, logp2, logBase;
		private JTextField logID, logPW;
		private JLabel lblID, lblPW;
		private JButton btnlogin, btnsign;
		
		private String checkid, checkpw, checkadmin, tfid, tfpw;
		private boolean checklogin = false, thisadmin;
		private SignIn sm;
		private AdminMain adm;
		private UserMain usm;
	
	public LoginS(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		lookandfeelinter();
		
		createBase();
		add(logBase);
		
		createCenter();
		logBase.add(logp1, BorderLayout.CENTER);
		createSouth();
		logBase.add(logp2, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new LoginS("로그인 화면", 300, 150);
	}

	private void createBase() {
		logBase = new JPanel();
		logBase.setLayout(new BorderLayout());
		
		createCenter();
		logBase.add(logp1, BorderLayout.CENTER);
		createSouth();
		logBase.add(logp2, BorderLayout.SOUTH);
	}
	
	private void createCenter() {
		logp1 = new JPanel();
		logp1.setLayout(new GridLayout(2,2));
		logp1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
		
		lblID = new JLabel("ID : ", JLabel.CENTER);
		logID = new JTextField(15);
		lblPW = new JLabel("PW : ", JLabel.CENTER);
		logPW = new JPasswordField(15);
		
		logp1.add(lblID);
		logp1.add(logID);
		logp1.add(lblPW);
		logp1.add(logPW);
	}
	private void createSouth() {
		logp2 = new JPanel();
		btnlogin = new JButton("로그인");
		btnsign = new JButton("회원가입");
		
		btnlogin.addActionListener(this);
		btnsign.addActionListener(this);
		
		logp2.add(btnlogin);
		logp2.add(btnsign);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnlogin) {
			insertlogin();
		} else if(obj == btnsign) {
			sm = new SignIn("Login",320 ,360, this);
		}
	}
	private void insertlogin() {
								adm = new AdminMain("관리자 화면", 400, 300, customerid);
								usm = new UserMain("유저 화면", 400, 300, customerid);
	public void lookandfeelinter() {
		    try {
		        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    }  catch (Exception e) { }
	}


	
} //LoginS class

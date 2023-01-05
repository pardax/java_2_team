

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.util.StringUtils;

public class SignIn extends JFrame implements ActionListener {

	String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
	
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;
	
   private JPasswordField pw1, pw2;
   private JButton btn1, btnIDOK;
   private JLabel lbl1, lbl2, lbl3, lbl4, lblRc, lbl5;
   private JTextField tf1, tf2;
   
   private String chkpw1, chkpw2, chkid, getid;
   private boolean pwchk = false, idchk = false, namechk = false;
   
   private String rName, rPW, rID;
   
   private String sql;

   

   public SignIn(String title, int width, int height, LoginS logm) {
      setTitle(title);
      setSize(width, height);
      setLocationRelativeTo(this);
      setLayout(null);
      setsignIF();

      
      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if(obj == btn1) {
    	  cmppw();
    	  namech();
    	  registcust();
      } else if (obj == btnIDOK) {
    	  if(StringUtils.isNullOrEmpty(tf1.getText())) {
    		  JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
    	  }else {
    		  chkID();
    	  }
      }
      
      
   }
   private void setsignIF() {
	      lbl1 = new JLabel("회원가입");
	      lbl1.setBounds(130, 0, 300, 50);
	      lbl2 = new JLabel("ID");
	      lbl2.setBounds(20, 60, 120, 30);
	      lbl3 = new JLabel("Password");
	      lbl3.setBounds(20, 140, 120, 30);
	      lbl4 = new JLabel("Recheck Password");
	      lbl4.setBounds(20, 180, 120, 30);
	      lblRc = new JLabel();
	      lblRc.setBounds(20, 200, 120, 30);
	      lbl5 = new JLabel("이름");
	      lbl5.setBounds(20, 220, 120, 30);
	      
	      tf1 = new JTextField(); //아이디
	      tf1.setBounds(140, 60, 150, 30);
	      btnIDOK = new JButton("중복확인");
	      btnIDOK.setBounds(180, 100, 80, 30);
	      btnIDOK.addActionListener(this);
	      
	      pw1 = new JPasswordField(); //패스워드
	      pw1.setBounds(140, 140, 150, 30);
	      pw2 = new JPasswordField();
	      pw2.setBounds(140, 180, 150, 30);
	      
	      tf2 = new JTextField(); //이름
	      tf2.setBounds(140, 220, 150, 30);
	      
	      btn1 = new JButton("회원가입");
	      btn1.setBounds(95, 270, 100, 30);
	      btn1.addActionListener(this);
	      
	      add(lbl1);
	      add(lbl2);
	      add(lbl3);
	      add(lbl4);
	      add(lbl5);
	      add(lblRc);
	      add(tf1);
	      add(tf2);
	      add(pw1);
	      add(pw2);
	      add(btn1);
	      add(btnIDOK);
   }
   private void registcust() {
	   if(idchk == true && pwchk == true && namechk == true) {
		   sql = "INSERT INTO babaisyou.login_customer (ID, PW, custname, isadmin) VALUES('"+rID+"', '"+rPW+"', '"+rName+"', 0);";
		   try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   
				   stmt.execute(sql);
				   
				   rs.close();
				   conn.close();
				   JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
				   dispose();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
		   
	   } else if(idchk == false){
		   JOptionPane.showMessageDialog(null, "아이디중복을 확인 해 주세요.");
	   }else if(namechk == false){
		   JOptionPane.showMessageDialog(null, "이름을 확인 해 주세요.");
	   }else {
		   JOptionPane.showMessageDialog(null, "다시 확인 해 주세요.");
	   }
   }
   private void cmppw() {
	   chkpw1 = pw1.getText();
	   chkpw2 = pw2.getText();
	   if(StringUtils.isNullOrEmpty(chkpw1) || StringUtils.isNullOrEmpty(chkpw2)) {
		   JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
	   } else {
		   if(chkpw1.equals(chkpw2)) {
			   pwchk = true;
			   rPW = chkpw2;
		   } else {
			   JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다.");
			   pwchk = false;
		   }
	   }
	   
   }
   private void chkID() {
		try {
			 try {
				 //연결
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				//sql문
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select ID from login_customer");
				//로그인 확인
				getid = tf1.getText();
				while(rs.next()){
					chkid = rs.getString("ID");
					if(getid.equals(chkid) == true) {
						JOptionPane.showMessageDialog(null, "아이디가 중복 되었습니다.");
						idchk = false;
						break;
					}
						idchk = true;
					}
					if(idchk == true) {
						JOptionPane.showMessageDialog(null, "아이디를 사용할 수 있습니다.");
					}
					rID = tf1.getText();
					rs.close();
					conn.close();
			 } catch (SQLException e) {
				 System.out.println("연결 에러");
			}
			
			
		} catch (ClassNotFoundException e) {
			System.err.println("연결실패");
		}
   }
   private void namech() {
	   String a = tf2.getText();
	   if(StringUtils.isNullOrEmpty(a)) {
		   namechk = false;
	   }else {
		   rName = tf2.getText();
		   namechk = true;
	   }
   }

   
}
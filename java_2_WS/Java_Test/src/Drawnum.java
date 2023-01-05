

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.util.StringUtils;

public class Drawnum extends JFrame implements ActionListener{
	
	String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;
	
	private JPanel pbase, ptop, pcen, pbot;
	private JTextField n1, n2, n3, n4, n5, n6, nb;
	private JButton btnCon, btnauto, btnexit;
	private JLabel lblp, lbltitle;
	
	private String getturn, sarray[], inputnum, custid, nxtturn;
	private int array[];
	private boolean numchk;
	
	public Drawnum(String title, int w, int h, String id) {
		
		custid = id;
		
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		
		setpane();
		//setLayout(new BorderLayout());
		
		setVisible(true);
	}
	
	private void setpane() {
		pbase = new JPanel();
		pbase.setLayout(new BorderLayout());
		ptop = new JPanel();
		pcen = new JPanel();
		pbot = new JPanel();
		
		lbltitle = new JLabel("제 n회 로또 응모");//탑
		lbltitle.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
		lbltitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ptop.add(lbltitle);
		getturnnum();
		
		setspace(); //미드
		
		btnCon = new JButton("응모");
		btnauto = new JButton("자동등록");
		btnexit = new JButton("닫기");
		
		btnCon.addActionListener(this);
		btnauto.addActionListener(this);
		btnexit.addActionListener(this);
		pbot.add(btnCon);
		pbot.add(btnauto);
		pbot.add(btnexit);
		
		add(pbase);
		pbase.add(ptop, BorderLayout.NORTH);
		pbase.add(pcen, BorderLayout.CENTER);
		pbase.add(pbot, BorderLayout.SOUTH);
	}
	private void getturnnum() {
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery("SELECT lotto_turn FROM babaisyou.lotto_num;");
				   
				   while(rs.next()) {
					  getturn = rs.getString("lotto_turn");
				   }
				   
				   rs.close();
				   conn.close();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
		nextturn(getturn);
		lbltitle.setText("제 "+ nxtturn +"회 로또 응모");
	}
	private void setspace() {
		n1 = new JTextField(3);
		n2 = new JTextField(3);
		n3 = new JTextField(3);
		n4 = new JTextField(3);
		n5 = new JTextField(3);
		n6 = new JTextField(3);
		nb = new JTextField(3);
		lblp = new JLabel("+");
		
		n1.setHorizontalAlignment(JTextField.CENTER);
		n2.setHorizontalAlignment(JTextField.CENTER);
		n3.setHorizontalAlignment(JTextField.CENTER);
		n4.setHorizontalAlignment(JTextField.CENTER);
		n5.setHorizontalAlignment(JTextField.CENTER);
		n6.setHorizontalAlignment(JTextField.CENTER);
		nb.setHorizontalAlignment(JTextField.CENTER);
		
		n1.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		n2.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		n3.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		n4.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		n5.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		n6.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		lblp.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		nb.setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
		
		pcen.add(n1);
		pcen.add(n2);
		pcen.add(n3);
		pcen.add(n4);
		pcen.add(n5);
		pcen.add(n6);
		pcen.add(lblp);
		pcen.add(nb);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnauto) {
			getnum();
		} else if (obj == btnCon) {
			checknull();
			if(numchk == false) {
				JOptionPane.showMessageDialog(null, "입력을 확인 해 주세요.");
			}else {
				registnum();
			}
		} else if (obj == btnexit) {
			dispose();
		}
	}
	
	private void getnum() {
		Random rand = new Random();
		boolean chk;
		array = new int[7];
		sarray = new String[7];
		int num;
		
		for (int i = 0; i < 7; i++) {
			do {
				chk = false;
				num = rand.nextInt(45) + 1;
				for (int j = 0; j < array.length; j++) {
					if(num == array[j]) {
						chk = true;
						break;
					}
				}
			}while(chk);
			array[i] = num;
			sarray[i] = Integer.toString(num);
		}
		
		n1.setText(sarray[0]);
		n2.setText(sarray[1]);
		n3.setText(sarray[2]);
		n4.setText(sarray[3]);
		n5.setText(sarray[4]);
		n6.setText(sarray[5]);
		nb.setText(sarray[6]);
	}
	
	private void registnum() {
		//getturn
		//custid
		
		inputnum = "INSERT INTO babaisyou.customer_history (custid, lottoturn, num1, num2, num3, num4, num5, num6, numB) "
				+ "VALUES("+custid+", "+nxtturn+", "+n1.getText()+", "+n2.getText()+", "+n3.getText()+", "+n4.getText()+", "+n5.getText()+", "+n6.getText()+", "+nb.getText()+");";

		
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   stmt.execute(inputnum);
				   JOptionPane.showMessageDialog(null, "번호를 응모하였습니다.");
				   rs.close();
				   conn.close();
				   dispose();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
	}
	private void checknull() {
		numchk = true;
		if(StringUtils.isNullOrEmpty(n1.getText()) || StringUtils.isNullOrEmpty(n1.getText()) || StringUtils.isNullOrEmpty(n3.getText()) || StringUtils.isNullOrEmpty(n4.getText()) || StringUtils.isNullOrEmpty(n5.getText()) || StringUtils.isNullOrEmpty(n6.getText()) || StringUtils.isNullOrEmpty(nb.getText())) {
			numchk = false;
		}
	}
	private void nextturn(String turn) {
		int temp;
		
		temp = Integer.parseInt(turn);
		temp++;
		nxtturn = Integer.toString(temp);
	}

}

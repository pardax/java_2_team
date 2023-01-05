

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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

public class numchoice_admin extends JFrame implements ActionListener{
	
		String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
		String getturn, insertnum;
		
		private Connection conn = null;
		private Statement stmt;
		private ResultSet rs;
		
		JPanel pBase, p1, p2, p3;
		JButton btnrollnum, exitbtn;
		JLabel lbltitle;
		JLabel num1, num2, num3, num4, num5, num6, numB, numP;
		
		String turnnum, lnum1, lnum2, lnum3, lnum4, lnum5, lnum6, lnumB;
		String sarray[] = new String[7];
		int[] array;
	
	public numchoice_admin(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		
		setPane();
		getrandnum();
		
		setVisible(true);
	}
	
	private void setPane() {
		pBase = new JPanel();
		pBase.setLayout(new BorderLayout());
		//위
		p1 = new JPanel();
		getlottonum();
		lbltitle = new JLabel("제 " + turnnum +"회 로또결과");
		lbltitle.setFont(lbltitle.getFont().deriveFont(18.0f));
		lbltitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		
		//센터
		p2 = new JPanel();
		p2.setBackground(Color.white);
		setnum();
		viewnum();
		
		//아래
		p3 = new JPanel();
		btnrollnum = new JButton("번호 추첨");
		btnrollnum.addActionListener(this);
		exitbtn = new JButton("닫기");
		exitbtn.addActionListener(this);
		
		
		p1.add(lbltitle);
		
		add(pBase);
		pBase.add(p1, BorderLayout.NORTH);
		pBase.add(p2, BorderLayout.CENTER);
		pBase.add(p3, BorderLayout.SOUTH);
		p3.add(btnrollnum);
		p3.add(exitbtn);
		
	}
	private void getlottonum() {
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery("select * from lotto_num;");
				   
				   while(rs.next()) {
					   turnnum = rs.getString("lotto_turn");
				   }
				   
				   rs.close();
				   conn.close();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
	}
	private void setnum() {
		num1 = new JLabel("0");
		num2 = new JLabel("0");
		num3 = new JLabel("0");
		num4 = new JLabel("0");
		num5 = new JLabel("0");
		num6 = new JLabel("0");
		numP = new JLabel("+");
		numB = new JLabel("0");
		
		num1.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		num2.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		num3.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		num4.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		num5.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		num6.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		numP.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		numB.setBorder(BorderFactory.createEmptyBorder(70, 15, 0, 15));
		
		num1.setFont(new Font("Serif", Font.BOLD, 30));
		num2.setFont(new Font("Serif", Font.BOLD, 30));
		num3.setFont(new Font("Serif", Font.BOLD, 30));
		num4.setFont(new Font("Serif", Font.BOLD, 30));
		num5.setFont(new Font("Serif", Font.BOLD, 30));
		num6.setFont(new Font("Serif", Font.BOLD, 30));
		numP.setFont(new Font("Serif", Font.BOLD, 30));
		numB.setFont(new Font("Serif", Font.BOLD, 30));
		
		p2.add(num1);
		p2.add(num2);
		p2.add(num3);
		p2.add(num4);
		p2.add(num5);
		p2.add(num6);
		p2.add(numP);
		p2.add(numB);
	}
	private void viewnum() {
		
		getturn = "select * from lotto_num where lotto_turn = " + turnnum + ";";
		
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery(getturn);
				   
				   while(rs.next()) {
					   lnum1 = rs.getString("num_first");
					   lnum2 = rs.getString("num_second");
					   lnum3 = rs.getString("num_third");
					   lnum4 = rs.getString("num_fourth");
					   lnum5 = rs.getString("num_fifth");
					   lnum6 = rs.getString("num_sixth");
					   lnumB = rs.getString("num_bonus");
					   
				   }
				   		   
				   rs.close();
				   conn.close();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
		
			num1.setText(lnum1);
			num2.setText(lnum2);
			num3.setText(lnum3);
			num4.setText(lnum4);
			num5.setText(lnum5);
			num6.setText(lnum6);
			numB.setText(lnumB);
			
			setfontcolor();
		
	}
	private void setfontcolor() {
		
		Color red = new Color(0xC90000);
		Color green = new Color(0x1D8B15);
		Color blue = new Color(0x1F50B5);
		Color yellow = new Color(0xFFBB00);
		
		int tem1, tem2, tem3, tem4, tem5, tem6, temB;
		
		tem1 = Integer.parseInt(lnum1);
		tem2 = Integer.parseInt(lnum2);
		tem3 = Integer.parseInt(lnum3);
		tem4 = Integer.parseInt(lnum4);
		tem5 = Integer.parseInt(lnum5);
		tem6 = Integer.parseInt(lnum6);
		temB = Integer.parseInt(lnumB);
	
		if(tem1 >= 1 && tem1 <= 10) {
			num1.setForeground(yellow);
		}else if(tem1 >= 11 && tem1 <= 20) {
			num1.setForeground(blue);
		}else if(tem1 >= 21 && tem1 <= 30) {
			num1.setForeground(red);
		}else if(tem1 >= 31 && tem1 <= 40) {
			
		}else if(tem1 >= 41 && tem1 <= 45) {
			num1.setForeground(green);
		}
		
		if(tem2 >= 1 && tem2 <= 10) {
			num2.setForeground(yellow);
		}else if(tem2 >= 11 && tem2 <= 20) {
			num2.setForeground(blue);
		}else if(tem2 >= 21 && tem2 <= 30) {
			num2.setForeground(red);
		}else if(tem2 >= 31 && tem2 <= 40) {
			
		}else if(tem2 >= 41 && tem2 <= 45) {
			num2.setForeground(green);
		}
		
		if(tem3 >= 1 && tem3 <= 10) {
			num3.setForeground(yellow);
		}else if(tem3 >= 11 && tem3 <= 20) {
			num3.setForeground(blue);
		}else if(tem3 >= 21 && tem3 <= 30) {
			num3.setForeground(red);
		}else if(tem3 >= 31 && tem3 <= 40) {
			
		}else if(tem3 >= 41 && tem3 <= 45) {
			num3.setForeground(green);
		}
		
		if(tem4 >= 1 && tem4 <= 10) {
			num4.setForeground(yellow);
		}else if(tem4 >= 11 && tem4 <= 20) {
			num4.setForeground(blue);
		}else if(tem4 >= 21 && tem4 <= 30) {
			num4.setForeground(red);
		}else if(tem4 >= 31 && tem4 <= 40) {
			
		}else if(tem4 >= 41 && tem4 <= 45) {
			num4.setForeground(green);
		}
		
		if(tem5 >= 1 && tem5 <= 10) {
			num5.setForeground(yellow);
		}else if(tem5 >= 11 && tem5 <= 20) {
			num5.setForeground(blue);
		}else if(tem5 >= 21 && tem5 <= 30) {
			num5.setForeground(red);
		}else if(tem5 >= 31 && tem5 <= 40) {
			
		}else if(tem5 >= 41 && tem5 <= 45) {
			num5.setForeground(green);
		}
		
		if(tem6 >= 1 && tem6 <= 10) {
			num6.setForeground(yellow);
		}else if(tem6 >= 11 && tem6 <= 20) {
			num6.setForeground(blue);
		}else if(tem6 >= 21 && tem6 <= 30) {
			num6.setForeground(red);
		}else if(tem6 >= 31 && tem6 <= 40) {
			
		}else if(tem6 >= 41 && tem6 <= 45) {
			num6.setForeground(green);
		}
		
		if(temB >= 1 && temB <= 10) {
			numB.setForeground(yellow);
		}else if(temB >= 11 && temB <= 20) {
			numB.setForeground(blue);
		}else if(temB >= 21 && temB <= 30) {
			numB.setForeground(red);
		}else if(temB >= 31 && temB <= 40) {
			
		}else if(temB >= 41 && temB <= 45) {
			numB.setForeground(green);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnrollnum) { //0이 확인, 1이 취소
			int result = 0;
			result = JOptionPane.showConfirmDialog(null, "정말 추첨하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			if(result == 0) {
				getrandnum();
				runlottoroll();
			}
		}else if(obj == exitbtn) {
			dispose();
		}
	}
	private void runlottoroll() {
		
		insertnum = "INSERT INTO babaisyou.lotto_num (num_first, num_second, num_third, num_fourth, num_fifth, num_sixth, num_bonus) "
				+ "VALUES("+ sarray[0] +", "+ sarray[1] +", "+ sarray[2] +", "+ sarray[3] +", "+ sarray[4] +", "+ sarray[5] +", "+ sarray[6] +");";
		
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   
				   stmt.execute(insertnum);
				   
				   JOptionPane.showMessageDialog(null, "번호 추첨이 완료되었습니다.");
				   dispose();
				   
				   rs.close();
				   conn.close();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
	}
	private void getrandnum() {
		Random rand = new Random();
		boolean chk;
		array = new int[7];
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
	}

}

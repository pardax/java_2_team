import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PrizeMain extends JFrame implements ActionListener{
	
	String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
	private Connection conn = null;
	private Statement stmt, stmt2, stmt3;
	private ResultSet rs, rs2, rs3;
	
	private ArrayList<PrizeTable> list;
	private Vector<String> vector;
	private DefaultTableModel model;
	private JTable table;
	private JButton outBtn;
	private JPanel p, pNorth, pSouth;
	private JMenuBar Ubar;
	private JMenu menuAno;
	private JMenuItem menuItemCoin;
	private JLabel lb;
	private JPanel pb;
	
	private String custid, lototurn, bingoresult = "", turn = "";
	private String lotonum[] = new String[7];
	private String hisnum[] = new String[7];
	private int bingoCount = 0;
	
	public PrizeMain(String title, int w, int h, String id) {
		
		custid = id;
		
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setLayout(new BorderLayout());
		
		lb = new JLabel("당첨 확인");
		getname();
		
		Font f = new Font("맑은 고딕", Font.BOLD, 15);
		
		lb.setFont(f);
		
		JPanel pNorth = new JPanel();
		pNorth.add(lb);
		pNorth.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		outBtn = new JButton("닫기");
		
		JPanel pSouth = new JPanel();
		pSouth.add(outBtn);
		outBtn.addActionListener(this);
		pSouth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		//pSouth.setBorder(BorderFactory.createEmptyBorder());
		
		list = new ArrayList<PrizeTable>();
		getList();
		
		vector = new Vector<String>();
		vector.add("회차");
		vector.add("응모한 번호");
		vector.add("당첨 여부");
		
		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		
		for(PrizeTable at : list) {
			Vector<String> v = new Vector<String>();
			
			v.add(at.getN());
			v.add(at.getlNum());
			v.add(at.getWt());
			
			model.addRow(v);
		}
			 
		Container c = getContentPane();
		c.add(sc);
		
		add(sc, BorderLayout.CENTER);
		add(pNorth, BorderLayout.NORTH);
		add(pSouth, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	private void getname() {
		
		String sql = "SELECT custname FROM babaisyou.login_customer where custid = "+custid+";";
		String temp = "";		

		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery(sql);
				   
				   while(rs.next()) {
					   temp = rs.getString("custname");
				   }
				   rs.close();
				   conn.close();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
			lb.setText(temp + "님의 응모 목록");
	}
	
	private void getList() {
		
		String sql = "SELECT lottoturn, num1, num2, num3, num4, num5, num6, numB FROM babaisyou.customer_history where custid = " + custid + ";";
		String temp = "";
		
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery(sql);
				   
				   while(rs.next()) {
					   lototurn = rs.getString("lottoturn");
					   turn = lototurn;
					   lotonum[0] = rs.getString("num1");
					   lotonum[1] = rs.getString("num2");
					   lotonum[2] = rs.getString("num3");
					   lotonum[3] = rs.getString("num4");
					   lotonum[4] = rs.getString("num5");
					   lotonum[5] = rs.getString("num6");
					   lotonum[6] = rs.getString("numB");
					   
					   for (int i = 0; i < lotonum.length; i++) {
						   	temp += (" " + lotonum[i]);
					   }
					   searchnum();
					   list.add(new PrizeTable(lototurn, temp,bingoresult));
					   temp = "";
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

	private void searchnum() {
		
		String lotsql = "SELECT * FROM babaisyou.lotto_num where lotto_turn = "+ turn +";";

		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   //sql문
				   stmt2 = conn.createStatement();
				   //로그인 확인
				   rs2 = stmt2.executeQuery(lotsql);
				   
				   while(rs2.next()) {
					   hisnum[0] = rs2.getString("num_first");
					   hisnum[1] = rs2.getString("num_second");
					   hisnum[2] = rs2.getString("num_third");
					   hisnum[3] = rs2.getString("num_fourth");
					   hisnum[4] = rs2.getString("num_fifth");
					   hisnum[5] = rs2.getString("num_sixth");
					   hisnum[6] = rs2.getString("num_bonus");
					   cmpnum();
				   }
				   rs2.close();
			   } catch (SQLException e) {
				   System.out.println("연결 에러");
			   }
		   } catch (ClassNotFoundException e) {
			   System.err.println("연결실패");
		   }
	}
	
	private void cmpnum() {
		for (int i = 0; i < hisnum.length; i++) {
			for (int j = 0; j < lotonum.length; j++) {
				if(hisnum[i].equals(lotonum[j])) {
					bingoCount++;
				}
			}
		}//배열 끝
			if(bingoCount == 7) {
				bingoresult = "1등";
			}else if(bingoCount == 6) {
				bingoresult = "1등";
			}else if(bingoCount == 5) {
				bingoresult = "3등";
			}else if(bingoCount == 4) {
				bingoresult = "4등";
			}else if(bingoCount == 3){
				bingoresult = "5등";
			}else {
				bingoresult = "꽝";
			}
			
		bingoCount = 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == outBtn) {
			dispose();
		}
	}
	

}
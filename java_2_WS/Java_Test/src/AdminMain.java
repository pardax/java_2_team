


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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdminMain extends JFrame implements ActionListener{
	
	String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
	private Connection conn = null;
	private Statement stmt, stmt2;
	private ResultSet rs, rs2;
	
	private ArrayList<AdminTable> list;
	private Vector<String> vector;
	private DefaultTableModel model;
	private JTable table;
	private JButton drawBtn, confirmBtn;
	private JPanel p;
	private JLabel lblname;
	private String idsql, custid, listnum, listname, listid, countdraw = "";
	
	private numchoice_admin numcho;
	private EnterMain vcus;
	
	public static void main(String[] args) {
		new AdminMain("관리자 화면", 400, 300, "123");
	}
	
	public AdminMain(String title, int w, int h, String id) {
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		custid = id;
		
		//setLayout(new BorderLayout());
		
		list = new ArrayList<AdminTable>();
		viewlist();
		
		vector = new Vector<String>();
		vector.add("고객번호");
		vector.add("이름");
		vector.add("아이디");
		vector.add("응모횟수");
		
		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		
		for(AdminTable at : list) {
			Vector<String> v = new Vector<String>();
			
			v.add(at.getNum());
			v.add(at.getName());
			v.add(at.getId());
			v.add(at.getEn());
			
			model.addRow(v);
		}
		
		drawBtn = new JButton("번호추첨");
		drawBtn.addActionListener(this);
			 
		confirmBtn = new JButton("응모자 확인");
		confirmBtn.addActionListener(this);
		
		lblname = new JLabel("--- 님");
		viewadmin();
		
			 
		p = new JPanel(new FlowLayout());
		p.add(drawBtn);
		p.add(confirmBtn);
		p.add(lblname);
			 
		add("South", p);
			 
		Container c = getContentPane();
		c.add(sc);
			 
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == drawBtn) {
			numcho = new numchoice_admin("번호 추첨", 550, 350);
		}else if(obj == confirmBtn) {
			vcus = new EnterMain("응모자 확인", 600, 500);
		}
	}
	
	private void viewadmin() {
		
//		String temp = "";
//		idsql = "SELECT custname FROM babaisyou.login_customer where custid = '"+ custid +"';";
//		
//		try {
//			   try {
//				   //연결
//				   Class.forName("com.mysql.cj.jdbc.Driver");
//				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
//				   //sql문
//				   stmt = conn.createStatement();
//				   //로그인 확인
//				   rs = stmt.executeQuery(idsql);
//				   
//				   while(rs.next()) {
//					   temp = rs.getString("custname");
//				   }
//				   
//				   rs.close();
//				   conn.close();
//			   } catch (SQLException e) {
//				   System.out.println("연결 에러");
//			   }
//		   } catch (ClassNotFoundException e) {
//			   System.err.println("연결실패");
//		   }
//		lblname.setText(temp + " 님");
//		lblname.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	}
	
	private void viewlist() {
//		try {
//			   try {
//				   //연결
//				   Class.forName("com.mysql.cj.jdbc.Driver");
//				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
//				   //sql문
//				   stmt = conn.createStatement();
//				   //로그인 확인
//				   rs = stmt.executeQuery("SELECT custid, ID, custname FROM babaisyou.login_customer;");
//				   
//				   
//				   while(rs.next()) {
//					   listnum = rs.getString("custid");
//					   searchHistory(listnum);
//					   listname = rs.getString("custname");
//					   listid = rs.getString("ID");
//					   list.add(new AdminTable(listnum, listname, listid, countdraw));
//				   }
//				   
//				   rs.close();
//				   conn.close();
//			   } catch (SQLException e) {
//				   System.out.println("연결 에러");
//			   }
//		   } catch (ClassNotFoundException e) {
//			   System.err.println("연결실패");
//		   }
	}
	private void searchHistory(String id) {
		
//		String hissql = "SELECT count(*) FROM babaisyou.customer_history where custid = " + id + ";";
//		
//		try {
//			   try {
//				   //연결
//				   Class.forName("com.mysql.cj.jdbc.Driver");
//				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
//				   //sql문
//				   stmt2 = conn.createStatement();
//				   //로그인 확인
//				   rs2 = stmt2.executeQuery(hissql);
//				   
//				   while(rs2.next()) {
//					   countdraw = rs2.getString("count(*)");
//				   }
//				   
//				   rs2.close();
//				   conn.close();
//			   } catch (SQLException e) {
//				   System.out.println("연결 에러");
//			   }
//		   } catch (ClassNotFoundException e) {
//			   System.err.println("연결실패");
//		   }
	}
}

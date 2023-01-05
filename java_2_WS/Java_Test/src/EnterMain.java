
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

public class EnterMain extends JFrame implements ActionListener{
	
	String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;
	
	private ArrayList<EnterTable> list;
	private Vector<String> vector;
	private DefaultTableModel model;
	private JTable table;
	private JButton outBtn;
	private JPanel p, pNorth, pS;
	private JMenuBar Ubar;
	private JMenu menuAno;
	private JMenuItem menuItemCoin;
	private JLabel lb;
	
	private String arr[];
	
	public EnterMain(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		
		//setLayout(new BorderLayout());
		
		lb = new JLabel("응모자 확인 화면");
		
		Font f = new Font("맑은 고딕", Font.BOLD, 15);
		
		lb.setFont(f);
		
		JPanel pNorth = new JPanel();
		pNorth.add(lb);
		pNorth.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		list = new ArrayList<EnterTable>();
		setlist();
		
		vector = new Vector<String>();
		vector.add("고객번호");
		vector.add("회차");
		vector.add("번호(1)");
		vector.add("번호(2)");
		vector.add("번호(3)");
		vector.add("번호(4)");
		vector.add("번호(5)");
		vector.add("번호(6)");
		vector.add("번호(B)");
		
		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		
		for(EnterTable at : list) {
			Vector<String> v = new Vector<String>();
			
			v.add(at.getN());
			v.add(at.getCount());
			v.add(at.getNum1());
			v.add(at.getNum2());
			v.add(at.getNum3());
			v.add(at.getNum4());
			v.add(at.getNum5());
			v.add(at.getNum6());
			v.add(at.getNumB());
			
			model.addRow(v);
		}
		
		outBtn = new JButton("닫기");
		outBtn.addActionListener(this);
		
		pS = new JPanel(new FlowLayout());
		pS.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		pS.add(outBtn);
		
		Container c = getContentPane();
		c.add(sc);
		
		JPanel p = new JPanel();
		add(sc, BorderLayout.CENTER);
		
		add(pNorth, BorderLayout.NORTH);
		
		add(pS, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == outBtn) {
			dispose();
		}
	}
	private void setlist() {
		
		String sql = "SELECT * FROM babaisyou.customer_history;";
		arr = new String[9];
		
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
					   arr[0] = rs.getString("custid");
					   arr[1] = rs.getString("lottoturn");
					   arr[2] = rs.getString("num1");
					   arr[3] = rs.getString("num2");
					   arr[4] = rs.getString("num3");
					   arr[5] = rs.getString("num4");
					   arr[6] = rs.getString("num5");
					   arr[7] = rs.getString("num6");
					   arr[8] = rs.getString("numB");
					   
					   list.add(new EnterTable(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]));
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

}

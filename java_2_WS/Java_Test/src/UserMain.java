
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserMain extends JFrame implements ActionListener{
	
	String url = "jdbc:mysql://114.71.137.174:61083/babaisyou";
	private Connection conn = null;
	private Statement stmt;
	private ResultSet rs;
	
	private ArrayList<UserTable> list;
	private Vector<String> vector;
	private DefaultTableModel model;
	private JTable table;
	private JButton drawBtn, confirmBtn;
	private JPanel p;
	private JMenuBar Ubar;
	private JMenu menuAno;
	private JMenuItem menuItemCoin, menuItemexit, menulogout;
	private JLabel lblname;
	
	private String idsql, custid, listnum[];
	
	private Drawnum drn;
	private PrizeMain pm;
	private viewCoin vc;
	
	private LoginS lg;
	
	public UserMain(String title, int w, int h, String id) {
		
		custid = id;
		
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setLayout(new BorderLayout());
		
		setMenu();
		
		setJMenuBar(Ubar);
		
		
		list = new ArrayList<UserTable>();
		viewlist();
		
		vector = new Vector<String>();
		vector.add("회차");
		vector.add("번호");
		
		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		
		for(UserTable at : list) {
			Vector<String> v = new Vector<String>();
			
			v.add(at.getN());
			v.add(at.getlNum());
			
			model.addRow(v);
		}
		
		lblname = new JLabel("--- 님");
		
		drawBtn = new JButton("번호응모");
		drawBtn.addActionListener(this);
			 
		confirmBtn = new JButton("당첨 확인");
		confirmBtn.addActionListener(this);
			 
		p = new JPanel(new FlowLayout());
		p.add(drawBtn);
		p.add(confirmBtn);
		viewuser();
		p.add(lblname);
			 
		add("South", p);
			 
		Container c = getContentPane();
		c.add(sc);
		
		
		setVisible(true);
	}
	
	private void setMenu() {
		Ubar = new JMenuBar();
		
		menuAno = new JMenu("다른 기능");
		
		menuItemCoin = new JMenuItem("코인 차트 확인");
		menuItemCoin.addActionListener(this);
		menulogout = new JMenuItem("로그아웃");
		menulogout.addActionListener(this);
		menuItemexit = new JMenuItem("종료");
		menuItemexit.addActionListener(this);
		
		menuAno.add(menuItemCoin);
		menuAno.add(menulogout);
		menuAno.add(menuItemexit);
		
		Ubar.add(menuAno);
		
	}

	private void viewuser() {
		
		String temp = "";
		idsql = "SELECT custname FROM babaisyou.login_customer where custid = '"+ custid +"';";
		
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery(idsql);
				   
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
		lblname.setText(temp + " 님");
		lblname.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	}
	
	private void viewlist() {
		
		String turn= "", numsum = "";
		listnum = new String[7];
		
		try {
			   try {
				   //연결
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   conn = DriverManager.getConnection(url, "babaisyou", "youisbaba");
				   //sql문
				   stmt = conn.createStatement();
				   //로그인 확인
				   rs = stmt.executeQuery("SELECT * FROM babaisyou.lotto_num;");
				   
				   while(rs.next()) {
					   turn = rs.getString("lotto_turn");
					   listnum[0] = rs.getString("num_first");
					   listnum[1] = rs.getString("num_second");
					   listnum[2] = rs.getString("num_third");
					   listnum[3] = rs.getString("num_fourth");
					   listnum[4] = rs.getString("num_fifth");
					   listnum[5] = rs.getString("num_sixth");
					   listnum[6] = rs.getString("num_bonus");
					   
					   for (int i = 0; i < listnum.length; i++) {
						   numsum += (" " + listnum[i]);
					   }
					   list.add(new UserTable(turn, numsum));
					   numsum = "";
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == drawBtn) {
			drn = new Drawnum("응모 화면", 550, 250, custid);
		}else if(obj == confirmBtn) {
			pm = new PrizeMain("당첨 확인 화면", 450, 350, custid);
		}else if (obj == menuItemexit) {
			System.exit(0);
		}else if(obj == menuItemCoin) {
			vc = new viewCoin("코인", 400, 300);
		}else if(obj == menulogout) {
			lg = new LoginS("로그인화면", 300, 150);
			dispose();
		}
	}

}

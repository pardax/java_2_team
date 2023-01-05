

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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


public class viewCoin extends JFrame implements ActionListener{
	
	private ArrayList<CoinTable> list;
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
	
	private String bit, ether, binan, doge, bitP, etherP, binanP, dogeP;
	
	public viewCoin(String title, int w, int h) {
		setTitle(title);
		setSize(w, h);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setLayout(new BorderLayout());
		
		getbitcoin();
		binancecoin();
		Ethercoin();		
		dogecoin();
		
		lb = new JLabel("코인 차트");
		
		Font f = new Font("맑은 고딕", Font.BOLD, 15);
		
		lb.setFont(f);
		
		JPanel pNorth = new JPanel();
		pNorth.add(lb);
		pNorth.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		outBtn = new JButton("닫기");
		outBtn.addActionListener(this);
		
		JPanel pSouth = new JPanel();
		pSouth.add(outBtn);
		pSouth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		//pSouth.setBorder(BorderFactory.createEmptyBorder());
		
		list = new ArrayList<CoinTable>();
		list.add(new CoinTable(bit, bitP));
		list.add(new CoinTable(ether, etherP));
		list.add(new CoinTable(doge, dogeP));
		list.add(new CoinTable(binan, binanP));
		
		vector = new Vector<String>();
		vector.add("코인 이름");
		vector.add("가격 (KRW)");
		
		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		
		for(CoinTable at : list) {
			Vector<String> v = new Vector<String>();
			
			v.add(at.getN());
			v.add(at.getlNum());
			
			model.addRow(v);
		}
			 
		Container c = getContentPane();
		c.add(sc);
		
		add(sc, BorderLayout.CENTER);
		add(pNorth, BorderLayout.NORTH);
		add(pSouth, BorderLayout.SOUTH);
		
		
		
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == outBtn) {
			dispose();
		}
	}
	private void getbitcoin() {
		StringBuffer sbuf = new StringBuffer();
		try {
			URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=krw");
			
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			while((str=br.readLine())!= null){
				sbuf.append(str + "\r\n");
			}
			String temp = sbuf.toString();
			String[] strAry = temp.split("\\{|\\:|\"|\\}");
			bit = strAry[2];
			bitP = strAry[8];
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void Ethercoin() {
		StringBuffer sbuf = new StringBuffer();
		try {
			URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=Ethereum&vs_currencies=krw");
			
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			while((str=br.readLine())!= null){
				sbuf.append(str + "\r\n");
			}
			String temp = sbuf.toString();
			String[] strAry = temp.split("\\{|\\:|\"|\\}");
			ether = strAry[2];
			etherP = strAry[8];

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void binancecoin() {
		StringBuffer sbuf = new StringBuffer();
		try {
			URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=BinanceCoin&vs_currencies=krw");
			
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			while((str=br.readLine())!= null){
				sbuf.append(str + "\r\n");
			}
			String temp = sbuf.toString();
			String[] strAry = temp.split("\\{|\\:|\"|\\}");
			binan = strAry[2];
			binanP = strAry[8];
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void dogecoin() {
		StringBuffer sbuf = new StringBuffer();
		try {
			URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=Dogecoin&vs_currencies=krw");
			
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			String str;
			while((str=br.readLine())!= null){
				sbuf.append(str + "\r\n");
			}
			String temp = sbuf.toString();
			String[] strAry = temp.split("\\{|\\:|\"|\\}");
			doge = strAry[2];
			dogeP = strAry[8];
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

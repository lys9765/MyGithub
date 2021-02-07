
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MyPage extends JFrame implements ActionListener{
	JPanel mainPane = new JPanel();
			
			JLabel lbl1 = new JLabel("     등급별 선정 기준 및 혜택 안내");
			JLabel lbl2 = new JLabel("  선정기준");
			JLabel lbl3 = new JLabel("     혜택");
			JLabel lbl4 = new JLabel("      포인트 적립 기준 및 사용방법");
				
			JButton btn1 = new JButton("일반");
			JButton btn2 = new JButton("VIP");
			JButton btn3 = new JButton("VVIP");
			
			JTextArea ta1 = new JTextArea();// 선정기준
			JTextArea ta2 = new JTextArea();// 혜택
			JTextArea ta3 = new JTextArea();// 선정 포인트 기준
			JTextArea ta4 = new JTextArea();// 포인트 적립 및 사용방법 안내
			JPanel tpane = new JPanel();
			Font tf = new Font("맑은 고딕",Font.BOLD,22);	// 고정 폰트
	public MyPage() {
		setLayout(new BorderLayout());
		add("Center", mainPane);
		mainPane.setLayout(null);
		
		setBounds();		// 각 객체 위치조절
		setFont();			// 폰트 세팅
		setBackground();	// 배경색 세팅
		setTitleBorder();	// 타이틀 보더 세팅
		addPane();			// mainPane에 객체들 add
		setText();			// 텍스트 내용 설정
		
		ta1.setEnabled(false);	// 사용자가 텍스트 영역을 조작할 수 없도록 막음
		ta2.setEnabled(false);	// 대신 텍스트 색이 바뀜
		
		ta3.setEditable(false);	// 사용자가 텍스트 영역을 조작할 수 없도록 막음
								// 텍스트 색이 바뀌지 않음
		
		setSize(1000,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent ae) {	
		Object obj = ae.getSource();
		if(obj == btn1) {						// dao 테스트용
			MovieDAO dao = new MovieDAO();
			MovieVO vo = dao.setMovieData(12);	// 영화 코드번호를 보내서 해당 영화의 vo값을 가져온다
			ta4.setText(vo.getSummary());		// 영화의 줄거리를 세팅한다
		}else if(obj == btn2) {
			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.getMemberData("cat", "5555");
			ta1.setText(vo.getMbrID()+"\n"+vo.getPwd());
			ta2.setText(dao.getMemberPoint(vo));
		}else if(obj == btn3) {
			
		}
	}
	public void setText() {
		ta1.append("JTextArea");
		ta2.append("JTextArea");
		ta3.append("선정 포인트 기준\n"
				+ "영화관람, 매점&무비기프트샵 구매로 적립하신 구매 누적 포인트\n"
				+ "기준입니다.\n"
				+ "영화관람은 상영일 기준. 매점&무비 기프트샵은 적립일자 기준으로 누적\n"
				+ "적립됩니다.\n"
				+ "포인트는 적립 후 반영까지 2~3일 소요됩니다.\n"
				+ "선정기준 및 혜택은 추후에 당사 사정에 따라 변경될 수 있습니다.");
		ta4.append("JTextArea");
		
		
	}
	public void addPane() {
		mainPane.add(lbl1);
		mainPane.add(lbl2);
		mainPane.add(lbl3);
		mainPane.add(lbl4);
		
		mainPane.add(btn1);
		mainPane.add(btn2);
		mainPane.add(btn3);
		
		mainPane.add(ta1);
		mainPane.add(ta2);
		mainPane.add(ta3);
		mainPane.add(ta4);
	}
	public void setTitleBorder() {
		LineBorder lineBorder = new LineBorder(Color.WHITE);
		TitledBorder tBorder = new TitledBorder(lineBorder, "",
				TitledBorder.CENTER, TitledBorder.CENTER);
		
		LineBorder lineBorder2 = new LineBorder(Color.RED);
		TitledBorder tBorder2 = new TitledBorder(lineBorder2, "",
				TitledBorder.CENTER, TitledBorder.CENTER);
		
		lbl1.setBorder(tBorder);
		lbl2.setBorder(tBorder);
		lbl3.setBorder(tBorder);
		lbl4.setBorder(tBorder);
		
		ta1.setBorder(tBorder2);
		ta2.setBorder(tBorder2);
		ta3.setBorder(tBorder2);
		ta4.setBorder(tBorder2);
		
	}
	public void setBackground() {
		mainPane.setBackground(Color.BLACK);
		
		ta1.setBackground(Color.WHITE);
		ta2.setBackground(Color.WHITE);
		
		btn1.setBackground(Color.RED);
		btn2.setBackground(Color.RED);
		btn3.setBackground(Color.RED);
		
		btn1.setForeground(Color.WHITE);
		btn2.setForeground(Color.WHITE);
		btn3.setForeground(Color.WHITE);
		
		lbl1.setForeground(Color.WHITE);
		lbl2.setForeground(Color.WHITE);
		lbl3.setForeground(Color.WHITE);
		lbl4.setForeground(Color.WHITE);
		
//		ta3.setForeground(Color.red);
	}
	public void setFont() {
		lbl1.setFont(tf);
		lbl2.setFont(new Font("맑은 고딕",Font.BOLD,18));
		lbl3.setFont(new Font("맑은 고딕",Font.BOLD,18));
		lbl4.setFont(tf);
		
		btn1.setFont(tf);
		btn2.setFont(tf);
		btn3.setFont(tf);	
		
		ta1.setFont(tf);
		ta2.setFont(tf);
		ta3.setFont(new Font("맑은 고딕",Font.BOLD,14));
	}
	public void setBounds() {
		lbl1.setBounds(50,50,395,70);
		lbl2.setBounds(50,200,100,100);
		lbl3.setBounds(50,300,100,100);
		lbl4.setBounds(550,50,395,70);
		
		btn1.setBounds(50,150,100,40);
		btn2.setBounds(200,150,100,40);
		btn3.setBounds(345,150,100,40);
		
		ta1.setBounds(150,200,295,100);
		ta2.setBounds(150,300,295,100);
		ta3.setBounds(50,400,395,180);
		ta4.setBounds(550,150,395,430);	
	}
	public static void main(String[] args) {
		new MyPage();

	}

}

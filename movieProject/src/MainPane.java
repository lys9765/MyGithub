
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainPane extends JFrame{
	JTabbedPane tp = new JTabbedPane();
	JPanel northPane = new JPanel(new BorderLayout());
		JPanel eastPane = new JPanel(new BorderLayout());
			JTextField searchTf = new JTextField(20);
			JButton searchBtn = new JButton("검색");
	
	JPanel moviePane = new JPanel();
	JPanel ticketPane = new JPanel();
	JPanel timePane = new JPanel();
	JPanel Pane = new JPanel();
	JPanel schedulePane = new JPanel();
	JPanel myPagePane = new JPanel();
	JPanel loginPane = new JPanel();
	JPanel signUpPane = new JPanel();
	JPanel servicePane = new JPanel();
	
//	AdminPage ap = new AdminPage();
	public MainPane() {
		super("4Cinema");
		add(tp);
		add("North", northPane);
		
		northPane.add("East", eastPane);		
			eastPane.add(searchTf);
			eastPane.add("East",searchBtn);
				searchBtn.setFont(new Font("맑은 고딕",Font.BOLD,12));
		
		tp.setFont(new Font("맑은 고딕",Font.BOLD,14));
		tp.addTab("      영화       ", moviePane);
		tp.addTab("     예매     ", ticketPane);
		tp.addTab("    상영시간표    ", timePane);
		tp.addTab("    상영예정작    ", schedulePane);
		tp.addTab("   마이페이지   ", myPagePane);
		tp.addTab("   로그인   ", loginPane);
		tp.addTab("  회원가입  ", signUpPane);
		tp.addTab("  고객센터  /  오시는 길   ", servicePane);

		
		
		
		setSize(1000,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
//	public void login() {
//		String idText = idTf.getText();
//		String pwText = String.valueOf(pwPf.getPassword());
//		if(idText.equals("") || pwText.equals("")) {
//			JOptionPane.showMessageDialog(this, "아이디와 패스워드를 입력해주세요.");
//		}else {
//			MemberDAO dao = new MemberDAO();
//			MemberVO vo = dao.getMemberData(idText, pwText);
//			if(vo.getMbrID()==null) {
//				//가져온 id값이 없을 경우
//				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 확인해주세요.");
//				
//			}else {
//				//로그인 성공시
//				MainPane.tp.setEnabledAt(5, false);
//				MainPane.tp.setSelectedIndex(4);
//			}
//		}
//		
//	}
	public static void main(String[] args) {
		new MainPane();
	}

}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame implements ActionListener, ItemListener{
	////////////////상단 버튼표시 패널/////////////////
	JPanel northPane = new JPanel();
	String[] btnStr = {"예매 내역 / 매출 통계","영화 관리","상영 관리","회원 관리","상영관 관리"};
	////////////////얘매 내역 / 매출 통계 패널/////////////////
	JPanel centerPane = new JPanel(new BorderLayout());
		JPanel dayPane = new JPanel(new BorderLayout());
			JPanel dayMainPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
				JLabel dayLbl = new JLabel("기간 선택      ");
				static JComboBox<String> box1 = new JComboBox<String>();
				JLabel Lbl = new JLabel("  ~  ");
				static JComboBox<String> box2 = new JComboBox<String>();
		JPanel westPane = new JPanel(new BorderLayout());
			CalendarSwing cw = new CalendarSwing();
			
		JTable table;
			JScrollPane sp;
			static DefaultTableModel model;
				String[] modelStr = {"영화 명","평점","예매 수","매출"};
		JPanel southPane = new JPanel();
			JLabel maxSal = new JLabel("기간 총 매출액 : 0원");
		
		DigitalClock clock = new DigitalClock();
	////////////////영화 관리 패널/////////////////
	JPanel moviePane = new JPanel(new BorderLayout());
		JPanel movieNorthPane = new JPanel(new BorderLayout());
			JPanel movieLabelPane = new JPanel(new GridLayout(0,1));
				String[] setLbl;
			JPanel labelCenterPane = new JPanel(new GridLayout(0,1));
				JTextField[] tf = {new JTextField(15), new JTextField(15),
						new JTextField(15), new JTextField(15), new JTextField(15), 
						new JTextField(10), new JTextField(10), new JTextField(50),
						new JTextField(20), new JTextField(10)};
				
		JPanel movieCenterPane = new JPanel(new BorderLayout());
			JPanel movieBtnPane = new JPanel(new GridLayout(1,0));
				String[] movieBtn = {"전체목록","추가","수정","삭제","지우기"};
			JTable movieTable;
				JScrollPane movieScroll;
				DefaultTableModel movieModel;
				
			JPanel searchPane = new JPanel();
				JTextField searchTf = new JTextField(20);
				JButton searchBtn = new JButton("검색");

	Font ft = new Font("맑은 고딕",Font.BOLD,15);
	String str;
	
	MemberAdmin memAd = new MemberAdmin(tf, searchTf);

	public AdminPage() {
		add("North", northPane);
		northPane.setBackground(Color.BLACK);
		dayMainPane.setBackground(Color.BLACK);
		dayLbl.setForeground(Color.WHITE);
		Lbl.setForeground(Color.WHITE);
		southPane.setBackground(Color.BLACK);
		maxSal.setForeground(Color.WHITE);
		searchPane.setBackground(Color.BLACK);
		
		
		setBtn(); 				//버튼 세팅
		
		add("Center", centerPane);
		centerPane.add("North", dayMainPane);
				
		setBox(); 					//콤보박스 설정
		dayLbl.setFont(ft);
		dayMainPane.add(dayLbl);
		dayMainPane.add(box1);
		Lbl.setFont(new Font("맑은 고딕",Font.BOLD,20));
		dayMainPane.add(Lbl);
		dayMainPane.add( box2);
		
		/////////////디지털 시간 패널 세팅//////////////
		LineBorder lineBorder = new LineBorder(Color.BLACK);
		TitledBorder tBorder = new TitledBorder(lineBorder, "  Time  ",
		TitledBorder.CENTER, TitledBorder.CENTER);
		tBorder.setTitleFont(new Font("맑은 고딕", Font.BOLD, 20));
		clock.setBorder(tBorder);
		centerPane.add("West",westPane);
		westPane.add("Center", cw);
		westPane.add("South", clock);
		Thread t = new Thread(clock);
		t.start();
		
		model = new DefaultTableModel(modelStr,0);
		table = new JTable(model);
		sp = new JScrollPane(table);
		centerPane.add("Center", sp);
		
		maxSal.setFont(new Font("맑은 고딕",Font.BOLD,25));
		southPane.add(maxSal);
		centerPane.add("South", southPane);
		
		setSize(1000,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		box1.addItemListener(this);
		box2.addItemListener(this);
		
		searchTf.addActionListener(this);
		searchBtn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		String btn = ae.getActionCommand();
		Object obj = ae.getSource();
		if(btn.equals("예매 내역 / 매출 통계")) {
			paneOff();							//모든 패널 false
			centerPane.setVisible(true);
		}else if(btn.equals("영화 관리")) {
			paneOff();													//주연 ?
			str = "영화 관리";
			movieSet(setLbl = new String[] {"영화코드","영화명","감독명","장르코드", "개봉일","등급","상영시간","줄거리","등록일"}
			, str);
			getMovieAll();
		
		}else if(btn.equals("상영 관리")) {
			paneOff();	
			str = "상영 관리";
			movieSet(setLbl = new String[] {"스케줄 코드","영화코드","상영관코드","시작시간","종료시간"}
			, str);
		}else if(btn.equals("회원 관리")) {
			paneOff();	
			str = "회원 관리";
			movieSet(setLbl = new String[] {"회원_id","회원_pwd","회원명","연락처","이메일","생년월일","성별","등록일"}
			, str);
			memAd.getMemberAll();
		}else if(btn.equals("상영관 관리")) {
			paneOff();	
			str = "상영관 관리";
			movieSet(setLbl = new String[] {"","",""}
			, str);
		}else if(btn.equals("지우기")) {
			setFormClear();
		}else if(str.equals("영화 관리")) {
			if(btn.equals("전체목록")){
				getMovieAll();
			}else if(btn.equals("추가")) {
				setMovie();
			}else if(btn.equals("수정")) {
				setMovieUpdate();
			}else if(btn.equals("삭제")) {
				setMovieDelete();
			}else if(btn.equals("검색") || obj==searchTf ) {
				movieSearch();
			}
		}else if(str.equals("회원 관리")) {
			String message = "";
			if(btn.equals("전체목록")) {
				memAd.getMemberAll();
			}else if(btn.equals("추가")) {
				if(checkText()) {
					JOptionPane.showMessageDialog(this, "이메일과 등록일을 제외한 정보는 반드시 입력해야합니다");
				}else {
					message = memAd.setMember();			
					JOptionPane.showMessageDialog(this, message);
				}
			}else if(btn.equals("수정")) {
				if(checkText()) {
					JOptionPane.showMessageDialog(this, "이메일과 등록일을 제외한 정보는 반드시 입력해야합니다");
				}else {
					message = memAd.setMemberUpdate();
					JOptionPane.showMessageDialog(this, message);
				}
			}else if(btn.equals("삭제")) {
				message = memAd.setMemberDelete();
				JOptionPane.showMessageDialog(this, message);
			}else if(btn.equals("검색")) {
				String text = searchTf.getText();
				if(text.equals("")) {
					JOptionPane.showMessageDialog(this, "검색어를 입력해주세요.");
				}else {
					boolean searchCheck = memAd.memberSearch();
					if(searchCheck) {
						JOptionPane.showMessageDialog(this, text+"의 검색결과가 없습니다.");
					}
					
				}
			}
		}else if(str.equals("상영 관리")) {
			
		}
			

	}
	public boolean checkText() {
		if(tf[0].getText().equals("") || tf[1].getText().equals("") || tf[2].getText().equals("") ||
				tf[3].getText().equals("") || tf[5].getText().equals("") || tf[6].getText().equals("")) {
			
			return true;
		}else {
			return false;
		}
	
	}
	public void movieSearch() {		
		String searchWord = searchTf.getText();
		if(searchWord.equals("")) {
			JOptionPane.showMessageDialog(this, "검색어를 입력해주세요.");
		}else {
			MovieDAO dao = new MovieDAO();
			List<MovieVO> searchList = dao.getSearchRecord(searchWord);
			if(searchList.size()==0) {
				JOptionPane.showMessageDialog(this, searchWord+"의 검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			searchTf.setText("");
		}
	}
	public void setMovieDelete() {
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "영화코드는 반드시 입력하여야 합니다.");
		}else {
			int num = Integer.parseInt(tf[0].getText());
			MovieDAO dao = new MovieDAO();
			int result = dao.movieDelete(num);
			if(result > 0) {
				getMovieAll();
				JOptionPane.showMessageDialog(this, "영화정보가 삭제되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "영화정보 삭제를 실패했습니다.");
			}
			setFormClear();
		}
	}
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==1) {
			System.out.println((String)box1.getSelectedItem());
			System.out.println((String)box2.getSelectedItem());
		}
	}
//	public AdminPage(ActionEvent ae) {
//		String btn = ae.getActionCommand();
//		
//	}
	public void setMovieUpdate() {
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "영화코드는 반드시 입력하여야 합니다.");
		}else if(tf[3].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "장르코드는 반드시 입력하여야 합니다.");
		}else {
			MovieVO vo = new MovieVO();
			vo.setMovieCode(Integer.parseInt(tf[0].getText()));
			vo.setMovieName(tf[1].getText());
			vo.setDirectorName(tf[2].getText());
			vo.setJenreCode(Integer.parseInt(tf[3].getText()));
			vo.setReleaseDate(tf[4].getText());
			vo.setMrating(tf[5].getText());
			vo.setRtime(tf[6].getText());
			vo.setSummary(tf[7].getText());
			MovieDAO dao = new MovieDAO();
			int result = dao.movieUpdate(vo);
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "영화정보가 수정되었습니다.");
				getMovieAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "영화정보 수정을 실패했습니다.");
			}	
		}
	}
	public void setFormClear() {
		for(int i = 0; i < tf.length; i++) {
			tf[i].setText("");
		}
	}
	public void setMovie() {
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "영화코드는 반드시 입력해야합니다.");
		}else if(tf[3].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "장르코드는 반드시 입력해야합니다.");
		}else {
			MovieVO vo = new MovieVO(tf[1].getText(), tf[2].getText(), Integer.parseInt(tf[3].getText()), tf[4].getText(),
					tf[5].getText(), tf[6].getText(), tf[7].getText());
			MovieDAO dao = new MovieDAO();
			int result = dao.movieInsert(vo);
			if(result > 0) {
				
				JOptionPane.showMessageDialog(this, "영화가 등록되었습니다.");
				getMovieAll();
				setFormClear();
			}
		}
	}
	public void getMovieAll() {
		MovieDAO dao = new MovieDAO();
		List<MovieVO> lst = dao.movieAllSelect();
		
		setNewTableList(lst);
	}
	public void setNewTableList(List<MovieVO> lst) {
		model.setRowCount(0);
		for(int i = 0; i < lst.size(); i++) {
			MovieVO vo = lst.get(i);
			Object[] data = {vo.getMovieCode(), vo.getMovieName(), vo.getDirectorName(),
					vo.getJenreCode(), vo.getReleaseDate(), vo.getMrating(),
					vo.getRtime(), vo.getSummary(), vo.getRgtrdate()};
			model.addRow(data);
		}
	}
	public void movieSet(String[] setLbl, String borderStr) {
		movieTableRemove();		//패널 초기화 메소드 호출
		add("Center", moviePane);			//중앙 메인패널
		
		LineBorder lineBorder = new LineBorder(Color.BLACK);
		TitledBorder tBorder = new TitledBorder(lineBorder, borderStr,
		TitledBorder.CENTER, TitledBorder.CENTER);
		tBorder.setTitleFont(new Font("맑은 고딕", Font.BOLD, 20));
		movieNorthPane.setBorder(tBorder);
		moviePane.add("North", movieNorthPane);
		moviePane.add("Center", movieCenterPane);
		for(int i = 0; i < setLbl.length; i++) {
			JLabel lbl = new JLabel(setLbl[i]);
			lbl.setFont(ft);
			
			movieLabelPane.add(lbl);
		}
		movieNorthPane.add("West", movieLabelPane);//위쪽 패널 왼쪽
		
		for(int i = 0; i < setLbl.length; i++) {
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.LEFT));
			p.add(tf[i]);
			labelCenterPane.add(p);
			
		}
		movieNorthPane.add(labelCenterPane);	//위쪽패널 중앙
		
		
		
		for(int i = 0 ; i < movieBtn.length; i++) {
			JButton btn = new JButton(movieBtn[i]);
			btn.setBackground(Color.white);
			btn.setFont(ft);
			btn.addActionListener(this);
			movieBtnPane.add("Center", btn);
		}
		movieCenterPane.add("North", movieBtnPane);//센터패널 위
		
		model = new DefaultTableModel(setLbl,0);
		table = new JTable(model);
		movieScroll = new JScrollPane(table);
		
		movieCenterPane.add("Center", movieScroll);//센터패널 중앙
		
		
		movieCenterPane.add("South", searchPane);//센터패널 하단
			searchPane.add(searchTf);
			searchPane.add(searchBtn);
			searchBtn.setFont(ft);
			
		
		moviePane.setVisible(true);
	}
	public void movieTableRemove() {
		movieLabelPane.removeAll();
		labelCenterPane.removeAll();
		movieBtnPane.removeAll();
		movieCenterPane.removeAll();
	}
	public void paneOff() {
		centerPane.setVisible(false);
		moviePane.setVisible(false);

	}
	public void setBtn() {
		for(int i = 0; i < btnStr.length; i++) {
			JButton btn = new JButton(btnStr[i]);
			northPane.add(btn);
			btn.setFont(ft);
			btn.setBackground(Color.white);
			btn.addActionListener(this);
		}
	}
	public void setBox() {
		int month = 0;
		int lastDay;
		int toDay = cw.toDay;
		String str;
		for(int i = 1; i <= cw.month; i++) {
			cw.date.set(cw.year, month,1);
			lastDay = cw.date.getActualMaximum(Calendar.DATE);
			for(int j = 1; j <= lastDay; j++) {
				if(month == cw.month-1 && j > toDay) {
					break;
				}else {
					str = cw.year+"-"+i+"-"+j;
				}
				
				box1.addItem(str);
				box1.setFont(ft);
				box2.addItem(str);
				box2.setFont(ft);
			}
			month++;
		}
		box1.setBackground(Color.WHITE);
		box2.setBackground(Color.WHITE);
		box1.setPreferredSize(new Dimension(140,30));
		box2.setPreferredSize(new Dimension(140,30));
		
		
	}
	public static void main(String[] args) {
		new AdminPage();

	}

}

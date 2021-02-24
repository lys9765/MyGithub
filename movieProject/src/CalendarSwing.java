
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oracle.net.ano.CryptoDataPacket;

public class CalendarSwing extends JPanel implements ItemListener, ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,20);						
	//상단									 						
	JPanel selectPane = new JPanel();								
		JButton prevBtn = new JButton("◀");							
		JButton nextBtn = new JButton("▶");							
		JComboBox<Integer> yearCombo = new JComboBox<Integer>();	
		JComboBox<Integer> monthCombo = new JComboBox<Integer>();	
		JLabel yearLbl = new JLabel("년");							
		JLabel monthLbl = new JLabel("월");							
	//가운데		
	JPanel centerPane = new JPanel(new BorderLayout());				
		JPanel titlePane = new JPanel(new GridLayout(1,7));			
			String[] title = {"일","월","화","수","목","금","토"};		
		JPanel dayPane = new JPanel(new GridLayout(0,7));			
	//달력관련 데이터
		Calendar date;												
		int year;													
		int month;	
		int toDay;
		public CalendarSwing() {		
		titlePane.setBackground(Color.WHITE);
		dayPane.setBackground(Color.GRAY);
											
		setLayout(new BorderLayout());
		date = Calendar.getInstance();	
		year = date.get(Calendar.YEAR);								
		month = date.get(Calendar.MONTH) + 1;
		toDay = date.get(Calendar.DATE);
		//상단
		selectPane.setBackground(Color.WHITE);			
		selectPane.add(prevBtn); prevBtn.setFont(fnt);				
		selectPane.add(yearCombo); yearCombo.setFont(fnt);			
		selectPane.add(yearLbl); yearLbl.setFont(fnt);				
		selectPane.add(monthCombo); monthCombo.setFont(fnt);		
		selectPane.add(monthLbl); monthLbl.setFont(fnt);			
		selectPane.add(nextBtn); nextBtn.setFont(fnt);			
																
		add(BorderLayout.NORTH,selectPane);							
		//현재 년, 월 세팅
		setYear();													
		setMonth();
		//title호출													
		setCalendarTitle();											
		centerPane.add(BorderLayout.NORTH,titlePane);				
		add(centerPane);											
		
		//날짜 만들기
		centerPane.add(dayPane);									
		setDay();														
		
		yearCombo.addItemListener(this);						
		monthCombo.addItemListener(this);						
		
		prevBtn.addActionListener(this);						
		nextBtn.addActionListener(this);	
		
	}
	public void actionPerformed(ActionEvent ae) {				
		Object obj = ae.getSource();							
		if(obj == prevBtn) {//이전월 								
			prevMonth();										
		}else if(obj == nextBtn) {//다음월						
			nextMonth();										
		}else {
			String btn = ae.getActionCommand();
			String selItem = year+"-"+month+"-"+btn;
			
			AdminPage.box1.setSelectedItem(selItem);
			AdminPage.box2.setSelectedItem(selItem);
		}
		
		
	}
	
	public void nextMonth() {										
		if(month==12) {												
			year++;													
			month= 1;		
		}else if(date.get(Calendar.YEAR) ==year && Calendar.MONTH==month) {
		}else {														
			month++;	

		}									
		setDayReset();												
	}
	public void setDayReset() {										
		//년월 이벤트 등록 해제	
		yearCombo.removeItemListener(this);							
		monthCombo.removeItemListener(this);
		
		yearCombo.setSelectedItem(year); //itemevent발생				
		monthCombo.setSelectedItem(month);							
		
		dayPane.setVisible(false);									
		dayPane.removeAll();										
		setDay();													
		dayPane.setVisible(true);									
		
		//년월 이벤트 다시 등록 
		yearCombo.addItemListener(this);							
		monthCombo.addItemListener(this);
	}
	public void prevMonth() {										
		if(month==1) {											
														
			month = 1;								
		}else {	
			month--;												
		}
		setDayReset();												
	}
	public void itemStateChanged(ItemEvent ie) {					
		year = (int)yearCombo.getSelectedItem();					
		month = (int)monthCombo.getSelectedItem();					
		dayPane.setVisible(false);									
		dayPane.removeAll();//원래있는 날짜 지우기						
		setDay();//날짜 처리 함수 호출									
		dayPane.setVisible(true);	
		
		
	}
	//날짜 셋팅
	public void setDay() {											
		//1일에 대한 요일
		date.set(year, month-1,1);									
		int week = date.get(Calendar.DAY_OF_WEEK);					
		//마지막날
		int lastDay = date.getActualMaximum(Calendar.DATE);			
		//공백
		for(int s = 1; s <week; s++) {								
			JLabel lbl = new JLabel(" ");							
			dayPane.add(lbl);										
		}
//		System.out.println(year+"-"+month+"-"+toDay);
		//날짜 추가
		for(int day=1; day<= lastDay; day++) {						
			JButton btn = new JButton(String.valueOf(day));
			btn.setFont(fnt);
			btn.setBackground(Color.white);
			//출력하는 날짜에 대한 요일			
			date.set(Calendar.DATE, day); // 19->1	
			int w = date.get(Calendar.DAY_OF_WEEK);					
			if(w==1) btn.setForeground(Color.RED);				
			if(w==7) btn.setForeground(Color.BLUE);	
			if(day > this.toDay && month == Calendar.MONTH) {
				btn.setBackground(Color.LIGHT_GRAY);
				btn.setEnabled(false);
				

			}
			btn.addActionListener(this);
			dayPane.add(btn);
			
		}
	}
	//년도 셋팅
	public void setYear() {											
							
		yearCombo.addItem(year);									
		
		yearCombo.setSelectedItem(year);							
	}
	//월셋팅
	public void setMonth() {										
		for(int i = 1; i <=month; i++) {								
			monthCombo.addItem(i);									
		}
		monthCombo.setSelectedItem(month);						
	}
	//월화수목 타이틀 설정
	public void setCalendarTitle() {								
		for(int i = 0; i < title.length; i++) {						
			JLabel lbl = new JLabel(title[i],JLabel.CENTER);		
			lbl.setFont(fnt);										
			if(i==0) lbl.setForeground(Color.RED);					
			if(i==6) lbl.setForeground(Color.BLUE);						
			titlePane.add(lbl);										
		}
	}

}

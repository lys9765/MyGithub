
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Logout extends JFrame{
	JPanel mainPane = new JPanel();
	JButton btn = new JButton("로그아웃");
	
	public Logout() {
		add(mainPane);
		mainPane.setLayout(null);
		mainPane.add(btn);
		
		btn.setBounds(250,150,100,100);
		
		setSize(1000,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
//		frameSize = mainPane.getSize();
//		canvasSize = mc.getSize();
//		x = (int)canvasSize.getWidth()/2-25;
//		y = (int)canvasSize.getHeight()/2-25;
	}
	
	public static void main(String[] args) {
		new Logout();
	}

}

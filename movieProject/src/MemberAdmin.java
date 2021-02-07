
import java.util.List;

import javax.swing.JTextField;

public class MemberAdmin {
	JTextField tf[];
	JTextField searchTf;
	public MemberAdmin(JTextField[] tf, JTextField searchTf) {
		this.tf = tf;
		this.searchTf = searchTf;
	}
	public void getMemberAll() {
		MemberDAO dao = new MemberDAO();
		List<MemberVO> lst = dao.memberAllSelect();
		
		setNewTableList(lst);
	}
	public void setNewTableList(List<MemberVO> lst) {
		AdminPage.model.setRowCount(0);
		for(int i = 0; i < lst.size(); i++) {
			MemberVO vo = lst.get(i);
			Object[] data = {vo.getMbrID(), vo.getPwd(), vo.getName(),
					vo.getTel(), vo.getEmail(), vo.getBirth(),
					vo.getGender(), vo.getRgtrdate()};
			AdminPage.model.addRow(data);
		}
	}
	public String setMemberUpdate() {
		String message = "회원정보 수정에 실패했습니다.";
		MemberVO vo = new MemberVO();
		vo.setMbrID(tf[0].getText());
		vo.setPwd(tf[1].getText());
		vo.setName(tf[2].getText());
		vo.setTel(tf[3].getText());
		vo.setEmail(tf[4].getText());
		vo.setBirth(tf[5].getText());
		vo.setGender(Integer.parseInt(tf[6].getText()));
		MemberDAO dao = new MemberDAO();
		int result = dao.memberUpdate(vo);
		if(result > 0) {
			getMemberAll();
			setFormClear();
			message = "회원정보가 수정되었습니다.";
		}
		return message;
	}
public String setMemberDelete() {
		String message = "아이디와 비밀번호를 확인해주세요.";
		MemberDAO dao = new MemberDAO();
		int result = dao.memberDelete(tf[0].getText(), tf[1].getText());
		if(result > 0) {
			getMemberAll();
			message = "회원이 삭제되었습니다.";
			setFormClear();
		}
		return message;
	}
	public String setMember() {
		String message = "회원등록에 실패했습니다.";
		MemberVO vo = new MemberVO(tf[0].getText(), tf[1].getText(), tf[2].getText(), tf[3].getText(),
				tf[4].getText(), tf[5].getText(), Integer.parseInt(tf[6].getText()), tf[7].getText());
		MemberDAO dao = new MemberDAO();
		int result = dao.memberInsert(vo);
		if(result > 0) {
			getMemberAll();
			message = "회원이 등록되었습니다.";
			setFormClear();
		}
		return message;
		
	}
	public void setFormClear() {
		for(int i = 0; i < tf.length; i++) {
			tf[i].setText("");
		}
	}
	public boolean memberSearch() {
		boolean check = false;
		String searchWord = searchTf.getText();
		MemberDAO dao = new MemberDAO();
		List<MemberVO> searchList = dao.getSearchRecord(searchWord);
		if(searchList.size()==0) {
			check = true;
		}else {
			setNewTableList(searchList);
		}
		searchTf.setText("");
		return check;
		
	}
	public MemberVO setMemberData() {
		String mbrid = "";
		String pwe = "";
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getMemberData(mbrid, pwe);
		
		return vo;
	}
	public int setMemberPoint(MemberVO vo) {
		MemberDAO dao = new MemberDAO();
		int point = dao.getMemberPoint(vo);
		
		return point;
	}
	
}



import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DBConn {

	public MemberDAO() {
		
	}
	//레코드 전체선택
	public List<MemberVO> memberAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();										
			sql = "select mbrid, pwd , name, tel, email, birth, gender, rgtrdate "
					+ " from memTbl order by mbrid asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담고
				MemberVO vo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
						rs.getString(8));
				lst.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	} 
	//레코드 추가
	//레코드 수정
	//레코드 삭제
	//레코드 검색
	public List<MemberVO> getSearchRecord(String searchWord){
		List<MemberVO> lst = new ArrayList<MemberVO>();
		try {
			getConn();
			sql = "select mbrid, pwd, name, tel, email, birth, gender , rgtrdate "
					//								
					+ " from memTbl where name like ? order by name asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,"%"+searchWord+"%");
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMbrID(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setTel(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setBirth(rs.getString(6));
				vo.setGender(rs.getInt(7));
				vo.setRgtrdate(rs.getString(8));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}

	//회원 등록
	public int memberInsert(MemberVO vo) {
		int result = 0;
		try {
			getConn();
			
			sql = "insert into memTbl(mbrid, pwd, name, tel, email, birth, gender) "
					+ " values(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMbrID());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getBirth());
			pstmt.setInt(7, vo.getGender());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//회원정보 수정			
//		public int memberUpdate(int num, String tel, String email, String addr) {	
	public int memberUpdate(MemberVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update memTbl set pwd=?, name=?, tel=?, email=?, birth=?, gender=? where mbrid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getBirth());
			pstmt.setInt(6, vo.getGender());
			pstmt.setString(7, vo.getMbrID());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//회원 삭제
	public int memberDelete(String mbrid , String pwd) {
		int result = 0;
		try {
			getConn();
			sql = "delete from memTbl where mbrid=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbrid);
			pstmt.setString(2, pwd);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public MemberVO getMemberData(String mbrid, String pwd) {
		MemberVO vo = new MemberVO();
		try {
			getConn();
			sql = "select mbrid, pwd, name, tel, email, birth, gender , rgtrdate "
					+ "from memTbl where mbrid=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbrid);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setMbrID(rs.getString(1));
				vo.setPwd(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setTel(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setBirth(rs.getString(6));
				vo.setGender(rs.getInt(7));
				vo.setRgtrdate(rs.getString(8));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
	public int getMemberPoint(MemberVO vo) {
		int point =0;
		try {
			getConn();
			sql = "select memTbl.mbrid, point from memTbl join pointTbl on memTbl.mbrid = pointTbl.mbrid where memTbl.mbrid=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMbrID());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				point = rs.getInt(2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return point;
	}
}




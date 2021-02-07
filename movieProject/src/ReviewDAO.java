import java.util.ArrayList;
import java.util.List;

public class ReviewDAO extends DBConn{

	public ReviewDAO() {
		
	}
	public List<ReviewVO> ReviewAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<ReviewVO> lst = new ArrayList<ReviewVO>();
		try {
			getConn();										
			sql = "select gradecode, rcode, grade, review, rgtrdate "
					+ " from reviewTbl";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				ReviewVO vo = new ReviewVO(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getString(4), rs.getString(5));
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
	public List<ReviewVO> getSearchRecord(String searchWord){
		List<ReviewVO> lst = new ArrayList<ReviewVO>();
		try {
			getConn();
			sql = "select gradecode, rcode, grade, review, rgtrdate "
					//								
					+ " from reviewTbl rv join rcodeTbl rc on rv.rcode=rc.rcode where mbrid like ? order by gradecode asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,"%"+searchWord+"%");
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setGradecode(rs.getInt(1));
				vo.setRcode(rs.getInt(2));
				vo.setGrade(rs.getInt(3));
				vo.setReview(rs.getString(4));
				vo.setRgtrdate(rs.getString(5));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}

	//등록
	public int reviewInsert(ReviewVO vo) {
		int result = 0;
		try {
			getConn();
			
			sql = "insert into reviewTbl(gradecode, rcode, grade, reivew) "
					+ " values(sq ,?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getRcode());
			pstmt.setInt(2, vo.getGrade());
			pstmt.setString(3, vo.getReview());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//정보 수정			

	public int reviewUpdate(ReviewVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update reviewTbl set grade=?, review=?, where gradecode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getGrade());
			pstmt.setString(2, vo.getReview());
			pstmt.setInt(3, vo.getGradecode());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//회원 삭제
	public int reviewDelete(int gradecode) {
		int result = 0;
		try {
			getConn();
			sql = "delete from reviewTbl where gradecode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gradecode);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public ReviewVO getReviewData(int gradecode) {
		ReviewVO vo = new ReviewVO();
		try {
			getConn();
			sql = "select gradecode, rcode, grade, review, rgtrdate "
					+ "from reviewTbl rv join rcodeTbl rc on rv.rcode=rc.rcode where mbrid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gradecode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setGradecode(rs.getInt(1));
				vo.setRcode(rs.getInt(2));
				vo.setGrade(rs.getInt(3));
				vo.setReview(rs.getString(4));
				vo.setRgtrdate(rs.getString(5));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}


}

import java.util.ArrayList;
import java.util.List;

public class PointDAO extends DBConn{

	public PointDAO() {
		
	}
	//레코드 전체선택
	public List<PointVO> pointAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<PointVO> lst = new ArrayList<PointVO>();
		try {
			getConn();										
			sql = "select mbrid, point, rcode, rgtrdate "
					+ " from pointTbl order by point asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				PointVO vo = new PointVO(rs.getString(1), rs.getInt(2), rs.getInt(3),
						rs.getString(4));
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
	public List<PointVO> getSearchRecord(String searchWord){
		List<PointVO> lst = new ArrayList<PointVO>();
		try {
			getConn();
			sql = "select mbrid, point, rcode, rgtrdate "
					//								
					+ " from pointTbl where mbrid like ? order by mbrid asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,"%"+searchWord+"%");
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PointVO vo = new PointVO();
				vo.setMbrID(rs.getString(1));
				vo.setPoint(rs.getInt(2));
				vo.setRcode(rs.getInt(3));
				vo.setRgtrdate(rs.getString(4));
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
	public int pointInsert(PointVO vo) {
		int result = 0;
		try {
			getConn();
			
			sql = "insert into pointTbl(mbrid, point, rcode) "
					+ " values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMbrID());
			pstmt.setInt(2, vo.getPoint());
			pstmt.setInt(3, vo.getRcode());
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
	public int pointUpdate(PointVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update pointTbl set mbrid=?, point=?, rcode=?, where mbrid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMbrID());
			pstmt.setInt(2, vo.getPoint());
			pstmt.setInt(3, vo.getRcode());
			pstmt.setString(4, vo.getMbrID());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//회원 삭제
	public int pointDelete(String mbrid) {
		int result = 0;
		try {
			getConn();
			sql = "delete from pointTbl where mbrid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbrid);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public PointVO getPointData(String mbrID) {
		PointVO vo = new PointVO();
		try {
			getConn();
			sql = "select mbrid, point, rcode, rgtrdate "
					+ "from pointTbl where mbrid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mbrID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setMbrID(rs.getString(1));
				vo.setPoint(rs.getInt(2));
				vo.setRcode(rs.getInt(3));
				vo.setRgtrdate(rs.getString(4));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
}

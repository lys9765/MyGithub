import java.util.ArrayList;
import java.util.List;

public class MovieDAO extends DBConn {

	public MovieDAO() {
		
	}
	//레코드 전체선택
	public List<MovieVO> movieAllSelect() {
		//선택한 레코드를 보관할 컬렉션
		List<MovieVO> lst = new ArrayList<MovieVO>();
		try {
			getConn();										
			sql = "select moviecode, moviename , director, jenrecode, releasedate, mrating, rtime, summary, rgtrdate "
					+ " from movieTbl order by moviecode asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담고
				MovieVO vo = new MovieVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9));
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
	public List<MovieVO> getSearchRecord(String searchWord){
		List<MovieVO> lst = new ArrayList<MovieVO>();
		try {
			getConn();
			sql = "select moviecode, moviename, director, jenrecode, releasedate, mrating, rtime , summary, rgtrdate "
					//								
					+ " from movieTbl where moviename like ? order by moviecode asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,"%"+searchWord+"%");
		
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMovieCode(rs.getInt(1));
				vo.setMovieName(rs.getString(2));
				vo.setDirectorName(rs.getString(3));
				vo.setJenreCode(rs.getInt(4));
				vo.setReleaseDate(rs.getString(5));
				vo.setMrating(rs.getString(6));
				vo.setRtime(rs.getString(7));
				vo.setSummary(rs.getString(8));
				vo.setRgtrdate(rs.getString(9));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}
	
	//영화 등록
	public int movieInsert(MovieVO vo) {
		int result = 0;
		try {
			getConn();
			
			sql = "insert into movieTbl(moviecode, moviename, director, jenrecode, releasedate, mrating, rtime, summary) "
					+ " values(moviesq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMovieName());
			pstmt.setString(2, vo.getDirectorName());
			pstmt.setInt(3, vo.getJenreCode());
			pstmt.setString(4, vo.getReleaseDate());
			pstmt.setString(5, vo.getMrating());
			pstmt.setString(6, vo.getRtime());
			pstmt.setString(7, vo.getSummary());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//영화정보 수정			
//	public int memberUpdate(int num, String tel, String email, String addr) {	
	public int movieUpdate(MovieVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update movieTbl set moviename=?, director=?, jenrecode=?, releasedate=?, mrating=?, rtime=?, summary=? where moviecode=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMovieName());
			pstmt.setString(2, vo.getDirectorName());
			pstmt.setInt(3, vo.getJenreCode());
			pstmt.setString(4, vo.getReleaseDate());
			pstmt.setString(5, vo.getMrating());
			pstmt.setString(6, vo.getRtime());
			pstmt.setString(7, vo.getSummary());
			pstmt.setInt(8, vo.getMovieCode());
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	//영화 삭제
	public int movieDelete(int num) {
		int result = 0;
		try {
			getConn();
			sql = "delete from movieTbl where moviecode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public void selectMovie() {
		try {
			getConn();
			sql = "";
			pstmt = conn.prepareStatement(sql);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
	}
	public MovieVO setMovieData(int moviecode) {
		MovieVO vo = new MovieVO();
		try {
			getConn();
			sql = "select moviecode, moviename, director, jenrecode, releasedate, rtime, summary "
					+ "from movieTbl where moviecode=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, moviecode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setMovieCode(rs.getInt(1));
				vo.setMovieName(rs.getString(2));
				vo.setDirectorName(rs.getString(3));
				vo.setJenreCode(rs.getInt(4));
				vo.setReleaseDate(rs.getString(5));
				vo.setRtime(rs.getString(6));
				vo.setSummary(rs.getString(7));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return vo;
	}
}

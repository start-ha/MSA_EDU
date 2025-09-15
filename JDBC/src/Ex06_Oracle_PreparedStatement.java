import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex06_Oracle_PreparedStatement {

	public static void main(String[] args) {
		/*
			INSERT
			insert into dmlemp(empno, ename, deptno)
			values(?,?,?)
			
			UPDATE
			update dmlemp set ename=?, sal=? job=?, deptno=?
			where empno=?
		 */
		
		//insert는 결과집합을 안만드니까 rs 필요없음
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection(DBType.ORACLE);
		
			String sql = "update dmlemp set ename=?, sal=?, job=?, deptno=? where empno=?";
			pstmt = conn.prepareStatement(sql); //미리 컴파일
		
			//parameter 만들어 보낸다
			pstmt.setString(1, "아무게"); // SQL문 ?에 바인딩
			pstmt.setInt(2, 5555);
			pstmt.setString(3, "IT");
			pstmt.setInt(4, 40);
			pstmt.setInt(5, 9999);
			
			int row = pstmt.executeUpdate(); //DB아무게(1), 5555(2), IT(3), 40(4), 9999(5)만 가져감
		
			if(row > 0) {
				System.out.println("정상실행");
			}else {
				System.out.println("반영된 결과가 없어요");
			}
			
		} catch (Exception e) { //예외는 여기서
			//pstmt.executeUpdate(); 문제 생기면
			//여기서 예외정보 문제 풀기
			System.out.println(e.getMessage());
	
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		
	}

}

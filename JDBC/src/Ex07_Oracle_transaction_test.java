import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex07_Oracle_transaction_test {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			conn = ConnectionHelper.getConnection(DBType.MARIADB);
			String sql = "select empno, ename from emp where deptno=?"; //1. 미리 DB서버(shared pool)에 컴파일
			//where deptno = 10 or where deptno = 20
			//where empno=? and job=? and sal=?
			
			pstmt = conn.prepareStatement(sql); //실행이 아니고 준비(DB서버에 쿼리 준비)
			
			pstmt.setInt(1, 10);
			
			rs = pstmt.executeQuery(); //2. 파라미터만 가지고 db에 간다(쿼리문x)
			
			//공식같은 로직
			if(rs.next()) { //데이터 1건 또는 여러건
				do { //여러건 
					System.out.println(rs.getInt(1) + "/" + rs.getString(2)); //사번과 이름
				} while (rs.next());
				
			}else {
				System.out.println("조회된 데이터가 없습니다");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage()); //에러원인 출력
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

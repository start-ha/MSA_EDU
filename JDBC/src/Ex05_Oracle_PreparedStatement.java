/*
 PreparedStatement란?

PreparedStatement는 미리 SQL 구문을 컴파일해두고, 실행 시 값만 바인딩해서 사용하는 방식의 Statement입니다.

일반 Statement는 실행할 때마다 SQL을 파싱(Parsing)하고 컴파일해야 하지만, PreparedStatement는 
처음에 한 번만 컴파일한 뒤 재사용할 수 있습니다.
SQL 사전 컴파일
미리 컴파일해두기 때문에 여러 번 실행할 때 성능이 더 좋습니다.
특히 반복적으로 같은 SQL을 다른 값과 함께 실행해야 할 때 효율적입니다.


SQL Injection 방지
? (placeholder)에 값을 바인딩하면 JDBC가 내부적으로 escape 처리를 해주기 때문에 
SQL Injection 공격을 예방할 수 있습니다.
예: "SELECT * FROM users WHERE id = ?" → ? 자리에 값만 안전하게 치환.

가독성 & 유지보수성
SQL과 파라미터를 분리해 작성하므로 코드가 깔끔해지고, 유지보수하기가 쉽습니다.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex05_Oracle_PreparedStatement {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			conn = ConnectionHelper.getConnection(DBType.ORACLE);
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

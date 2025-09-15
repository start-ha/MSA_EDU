/*

 /*
		create table trans_A(
    		num number,
    		name varchar2(20)
		);

		create table trans_B(
    		num number constraint pk_trans_B_num primary key,
    		name varchar2(20)
		);
	
	JDBC >> DML >> auto commit >> 실반영
	
	JDBC >> autocommit >> 변경(false) >> 개발자 직접 (commit , rollback) >> 주의 (반드시... commit , rollback 강제)
	
	은행업무 :   A계좌 B계좌 이체 
	쇼핑몰 포인트 : 게시글을 쓰면 회원에게 포인트 부여 (insert , update) 
	쇼핑몰 결제 처리 : 카드 ... 벤더 승인 ... 되면 카트 구매 ... (update , .....)
	결제오류...
	
	
	
	2차 트랜잭션 처리 필수 구현... Spring @Transaction 언제 어떻게 처리...
	
	**OLTP 환경 (실시간 데이터 처리) > Back End (트랜잭션 구현 필수.....) >>
		>> 동기화
	
	*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex07_Oracle_transaction {

	public static void main(String[] args) {
		Connection conn =  null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		conn = ConnectionHelper.getConnection(DBType.ORACLE);
		
		String sql = "insert into trans_A(num,name) values(100,'A')";
		String sql2 = "insert into trans_B(num,name) values(100,'B')";

		//두 개의 작업을 하나의 논리적인 단위(트랜잭션)로 묶어서 처리
		//둘 다 성공, 둘 다 실패인 경우만 처리
		
		try {
			//JDBC autocommit
			conn.setAutoCommit(false); //반드시 개발자는 (commit, rollback)강제 >> 안그러면 lock, 다른사용자 작업x
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
		
			pstmt.executeUpdate(); //I,D,U모두 하나로 사용
			pstmt2.executeUpdate();
			
			//예외가 발생하지 않고 여기까지 오면
			//둘 다 정상처리
			//실반영
			conn.commit();
			
			
			//end
		} catch (Exception e) {
			//둘중 하나라도 예외가 발생 (rollback)
			System.out.println("예외 발생 : 모두 rollback 강제 하는 코드 " + e.getMessage());
			
			try {
				conn.rollback();  //예외처리..
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			//예외가 발생, 예외가 발생하지 않던
			//자원 해제
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(pstmt2);
			ConnectionHelper.close(conn);
		}
	}

}

//두 번 실행하면 tstmt2는 pk설정되서 tstmt2에서 에러터져서 catch에서 rollback,
//tstmt1도 insert 안되면 트랜잭션 성공......
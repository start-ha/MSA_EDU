/*

JDBC
개발자가 DB서버에 요청하는 건

1. SELECT
1.1  결과 집합 생성 (resultSet) > read

2. INSERT, UPDATE, DELETE
2.1 결과 집합 생성 안함 (x) > resultSet (x)
2.2 반영된 결과 return (반영된 행의 수) > update.. where deptno =10 > return 10
2.3 return 되는 값이 0보다 크면 반영된 것!!

자바코드 (CRUD) 로 위에거 만들면됨
[Today Point]
1. Oracle DML(Sql plus or SQL developer에서 날림) > 작업을 하면 반드시 Commit, rollback 강제
2. JDBC API를 작업하면 insert, update, delete 수행되면 > default > auto commit >> 실반영되버림
3. 만약 JDBC API(Connection, statement, resultset 가지고 있는 것) 
>>작업중에 옵션 설정 
>> auto commit 안하겠다 설정 
>> 개발자가 강제로 코드에 commit, rollback 명시

begin
	A계좌 인출(update)
	
	B계좌 이체(update)
end

//하나의 트랜잭션 설정하고 싶을 때 auto commit옵션 false로 잡아서 설정
//반드시 java code > commit, rollback 강제************* 

하나의 논리적인 작업 단위(트랜잭션 : transaction)

성공 아니면 실패
 */


/*
 실습데이터 생성(sql developer에서)
 
 create table dmlemp
as
    select * from emp;
    
    --단점 : 제약 정보는 복제를 못함
alter table dmlemp
add constraint pk_dmlemp_empno primary key(empno);
 
 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex02_Oracle_DML_UPDATE {

	public static void main(String[] args) {
		
		//정형적 표현
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			//드라이버 로딩(생략 Class.forName("oracle.jdbc.OracleDriver");
			
			//연결 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");
			
			//명령객체생성 >> 연결된 객체로부터..
			stmt = conn.createStatement();
			
			//parameter 받기
			int deptno = 0;
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("부서입력");
			deptno = Integer.parseInt(sc.nextLine()); //되도록 nextLinee(string으로 받고, 형변환)
			
			//update dmlemp set sal = 0 where deptno = 10;
			
			String sql = "update dmlemp set sal = 0 where deptno = " + deptno; 
			//무식한 코드 > deptno직접 쓰지 말고, 대체 > 미리 컴파일된 parameter를 보내는 > preparedStatement
			// values(?,?,?) 
			
			//자동 커밋, 원본에 실반영
			
			int resultRow = stmt.executeUpdate(sql); //반영된 행의 갯수 반환 >> select, delete 모두 update로 퉁침
			//if-else 안 타고, 
			//예외처리는 catch에서.. (executeUpdate에서 서버터지면) 
			
			if(resultRow > 0) {
				System.out.println("반영된 행의 수 : " + resultRow);
			}else {
				System.out.println("반영된 행이 없음");
			}
		
		
		} catch (Exception e) {
			System.out.println("SQL예외발생 : " + e.getMessage());
		}finally {
			try {
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}

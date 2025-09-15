import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*

JDBC

1. Java 언어(APP)를 통해서 Oracle(소프트웨어) 연결해서 CRUD작업
2. Java App : Oracle , My-sql , MS-sql 등등 연결하고 작업(CRUD)
		2.1 각각의 제품에 맞는 드라이버를 가지고 있어야 합니다
		CASE 1: 삼성 노트북 >> HP 프린터 연결 >> HP프린터 사이트에서 드라이버 다운 >> 삼성 설치 
		CASE 2: HP프린터 제조 회사는 ... 삼성, LG 회사마다 적용할 수 있는 드라이버를 별도 제작

각 언어에 맞는 드라이버를 다운로드 해서 제품에 맞게 설치 .... 접속 ...
Oracle (C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib)
Mysql (https://dev.mysql.com/downloads/connector/j/)

강사PC : D:\Duzon\DataBase\Connect Utils\OracleJDBC >> ojdbc6.jar

3. 드라이버를 참조 (현재 프로젝트에서 사용) >> Java Project -> 속성 -> build path ->
jar 파일 추가 
	3.1 드라이버 사용 준비 완료 >> 메모리에 load 사용 ....

3.2 Class.forName("oracle.jdbc.OracleDriver")..... new 객체 생성 ....

4. JAVA CODE (CRUD) >> JDBC API 제공 받는다
4.1 import java.sql.* >> interface , class 제공

4.2 개발자는 interface 를 통해서 표준화된 DB 작업 수행
	POINT) why interface형태로 제공 >> JDBC API >> Oracle, Mysql , ....)

	//OracleConnection >> Connection 구현
	//MysqlConnection >> Connection 구현 
	//다형성 Connection 부모타입 : 유연한 프로그래밍 작성 

>>다형성을 구현 (인터페이스 활용)
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement 등등 

5. 작업순서
	5.1 DB연결 -> 명령생성 -> 명령실행 -> 처리 -> 자원해제

5.1 명령 : DDL  (create , alter , drop)
		  CRUD (insert , select , update , delete)

실행 : 쿼리문을 DB서버에게 전달 
처리 : 결과를 받아서 화면 출력 , 또는 다른 프로그램에 전달 등등
자원해제 : 연결해제 
*/




public class Ex01_Oracle_Connection {
	
	public static void main(String[] args) throws Exception{
		//DB연결
		//제품에 맞는 드라이버 메모리에 로딩
		Class.forName("oracle.jdbc.OracleDriver");
		//NEW 객체 생성 코드 없이 (메로리 로딩)
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");
		System.out.println("DB 연결 : " + conn.isClosed());
		//false 나오면 연결 된 것
		
		//명령객체 얻어오기 (CRUD)
		Statement stmt = conn.createStatement();
		
		//명령 만들기
		String sql = "select empno, ename, sal from emp"; //; 구문 안에 ; 찍으면 안됨
		
		//명령 실행
		ResultSet rs = stmt.executeQuery(sql);
		//쿼리 > 네트워크 통해서 > 대전 (DB서버) > DB서버에서 쿼리 실행 > 결과 > 결과에 접근, 사용할 수 있는 객체 리턴
		//select한 결과 집합은 DB서버 메모리에 생성 > connection 기반(web server가 DB server Buffer 메모리에서 물러옴) > 연결이 지속되는 한 데이터 read 가능
		
		/*
		while(rs.next()) { 
			//rs.get타입("컬럼이름 또는 인덱스 값") + "/" + rs.getString()
			System.out.println(rs.getInt("emono") + 
			"/" + rs.getString("ename") + "/" + rs.getInt("sal")); 
		*/
		
		//db서버에서 하나씩 이동하면서 불러온다.
		while(rs.next()) { 
			//rs.get타입("컬럼이름 또는 인덱스 값") + "/" + rs.getString()
			System.out.println(rs.getInt(1) + 
			"/" + rs.getString(2) + "/" + rs.getInt(3)); 
		
		
		
	}
		//자원해제
		stmt.close();
		rs.close();
		conn.close();
		
		System.out.println("DB연결해제 : " + conn.isClosed()); //true

	}
}

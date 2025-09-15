package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//클래스 설계시 > 많이 사용하는 거 편하게 > new 없이 > static
public class ConnectionHelper {
	//db 연결 도와주는 class
	public static Connection getConnection(DBType dbType) {
		
		Connection conn = null;
		try {
			switch (dbType) {
			case ORACLE: 
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","KOSA","1004");

				break;
			
			case MARIADB: 
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/emp","root","1004");
				break;
			}
		}catch(Exception e ) {
			System.err.println("connection Factory: " + e.getMessage());
		}
		
		return conn;
		//연결된 객체의 주소값을 리턴
		
	}
	
	
	public static Connection getConnection(DBType dbType, String id, String pwd) {
		
		Connection conn = null;
		try {
			switch (dbType) {
			case ORACLE: 
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",id,pwd);

				break;
			
			case MARIADB: 
				conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/shopdb",id,pwd);
				break;
			}
		}catch(Exception e ) {
			System.err.println("connection Factory: " + e.getMessage());
		}
		
		return conn;
		//연결된 객체의 주소값을 리턴
		
	}

	
	public static void close(Connection conn){
		if(conn != null) {//주소를 갖고 있다면
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void close(ResultSet rs){
		if(rs != null) {//주소를 갖고 있다면
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void close(Statement stmt){
		if(stmt != null) {//주소를 갖고 있다면
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void close(PreparedStatement pstmt){
		if(pstmt != null) {//주소를 갖고 있다면
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
}

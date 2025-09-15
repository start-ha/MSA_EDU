import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kr.or.kosa.utils.ConnectionHelper;
import kr.or.kosa.utils.DBType;

public class Ex04_Oracle_DBInfo {

	public static void main(String[] args) {

		        String sql = "SELECT empno, ename, sal FROM emp";

		        try (
		    			Connection conn = ConnectionHelper.getConnection(DBType.ORACLE);

		             
	        		Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery(sql)) {

		            // 메타데이터 얻기
		            ResultSetMetaData metaData = rs.getMetaData();
		            int columnCount = metaData.getColumnCount();

		            System.out.println("=== 컬럼 정보 출력 ===");
		            for (int i = 1; i <= columnCount; i++) {
		                System.out.println("컬럼 번호: " + i);
		                System.out.println("이름: " + metaData.getColumnName(i));
		                System.out.println("라벨: " + metaData.getColumnLabel(i));
		                System.out.println("타입: " + metaData.getColumnTypeName(i));
		                System.out.println("크기: " + metaData.getColumnDisplaySize(i));
		                System.out.println("NULL 허용: " + metaData.isNullable(i));
		                System.out.println("-------------------");
		            }

		            System.out.println("=== 데이터 출력 ===");
		            while (rs.next()) {
		                for (int i = 1; i <= columnCount; i++) {
		                    System.out.print(rs.getObject(i) + "\t");
		                }
		                System.out.println();
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
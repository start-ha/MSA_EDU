/*
 Model (DTO(VO), DAO(DB연결 + 함수), SERVICE(요청 처리))
 
 View (Console)
 
 Controller (main 함수에서 처리)
 
 
 1. DB 연결
 2. CRUD 함수 (DAO 만들 수 있다)
 3. 기본 함수 + 알파(조건 검색, 문자열 검색)
 4. 전체조회, 조건 조회, 데이터 삽입, 데이터 삭제, 데이터 수정 쿼리문을 만들 수 있다.
 
 기본 중에 기본..(초급)
 
 1. 드라이버 로딩(jar 파일을 추가)
 2. 롬복 추가
 */

import java.util.List;
import DAO.DeptDao;
import DTO.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
public class Program {
public static void main(String[] args) {
		
		DeptDao deptDao = new DeptDao();
		System.out.println("[전체조회]");
		
		List<Dept> deptList = deptDao.getDeptAllList();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		
		System.out.println("[조건조회]");
		Dept dept = deptDao.getDeptListByDeptno(10);
		if(dept != null) {
			deptPrint(dept);
		}else {
			System.out.println("부서가 없습니다^^");
		}
		
		
		System.out.println("[데이터 삽입]");
		int insertRow = deptDao.insertDept(new Dept(100,"IT","SEOUL"));
		if(insertRow > 0 ) {
			System.out.println("INSERT ROW : " + insertRow);
		}else {
			System.out.println("INSERT FAIL");
		}
		
		System.out.println("[방금전 INSERT 한 데이터 조회]");
		deptList = deptDao.getDeptAllList();
		
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		
		
		System.out.println("[방금전 INSERT 데이터 수정]");
		int updateRow = deptDao.updateDept(new Dept(100,"IT_UP","SEOUL_UP"));
		if(updateRow > 0 ) {
			System.out.println("update ROW : " + updateRow);
		}else {
			System.out.println("update FAIL");
		}
		
		System.out.println("[방금전 update 한 데이터 조회]");
		deptList = deptDao.getDeptAllList();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
		
		System.out.println("[방금전 Update한 데이터 삭제]");
		int deleteRow = deptDao.deleteDept(100);
		if(deleteRow > 0 ) {
			System.out.println("deleteRow ROW : " + deleteRow);
		}else {
			System.out.println("deleteRow FAIL");
		}
		
		System.out.println("[방금전 delete 한 데이터 조회]");
		deptList = deptDao.getDeptAllList();
		if(deptList != null) {
			deptPrint(deptList);
		}
		
	}
	
	
	private static void deptPrint(List<Dept> list) {
		for(Dept dept : list) {
			System.out.println(dept.toString());
		}
	}
	
	private static void deptPrint(Dept dept) {
			System.out.println(dept.toString());
	}
	
}
	
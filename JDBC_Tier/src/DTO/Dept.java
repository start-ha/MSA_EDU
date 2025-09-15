package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 DTO, VO
 Oracle DB에 있는 Dept와 1:1 매칭

>> 한 건의 데이터 : new
 new Dept(); >> select deptno, dname, loc from dept where deptno=10

>>한 건 이상의 데이터 : List
 List<Dept> list = new ArrayList<>();
 >> select deptno, dname, loc from dept;
 
 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

	private int deptno;
	private String dname;
	private String loc;
	
}

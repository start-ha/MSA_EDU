<%@page import="kr.or.kosa.dto.Emp"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//서버 설정
	//CORS 정책 (동일출처문제>> 다른 출처라서 막는다 )
	//Cross origin resource sharing
	//다른 주소에 허락하지 않는다
	//동일주소 >> 다 허락 (프로토콜, 호스트, 포트번호)
	//서버가 웹브라우저에게 동일주8소 다 허락하도록 요청
	response.addHeader("Access-Control-Allow-Origin", "*");
	Class.forName("oracle.jdbc.OracleDriver");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kosa","1004");
	Statement stmt = conn.createStatement();
	String sql = "select empno , ename , sal , job , comm from emp";
	ResultSet rs = stmt.executeQuery(sql);
	
	List<Emp> list = new ArrayList<Emp>();
	while(rs.next()) {
	    Emp emp = new Emp();
	    emp.setEmpno(rs.getInt("empno"));
	    emp.setEname(rs.getString("ename"));
	    emp.setSal(rs.getInt("sal"));
	    emp.setJob(rs.getString("job"));
	    emp.setComm(rs.getInt("comm"));
	    list.add(emp);
	}
	
	rs.close();
	stmt.close();
	conn.close();
	
	//2줄 
	//[{empno:7788,ename:"SMITH"}]  
	Gson gson = new Gson();
	String jsonlist = gson.toJson(list);
%>    
<%=jsonlist%>
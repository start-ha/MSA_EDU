<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//서버쪽 코드를 사용할 수 있는 영역
//자바 코드 입력
	String str = "hello world";
//컴파일 후 스트링으로 바꿈
//내용은 클라이언트 브라우저 해석 불가능
//클라이언트가 해석가능하도록 바꿔서.. text만 나옴..
//WAS(Tomcat)이 페이지의 요청이 들어오면 이 페이지를 컴파일 (Ex02_Server.jsp.class) 실행
//클라이언트 웹 브라우저가 해석 가능하도록 html, css, javascript, text로 변환...
%>

서버가 가지고 있는 변수 값을 찍겠다<%=str%>

</body>
</html>
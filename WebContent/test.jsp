<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

try {
	Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_web_commu", "root","chun3032"); // JDBC 연결
	System.out.println("DB 연결 완료");
} catch (ClassNotFoundException e) {
	System.out.println("JDBC 드라이버 로드 오류");
} catch (SQLException e) {
	e.printStackTrace();
	System.out.println("DB 연결 오류");
}

%>

</body>
</html>
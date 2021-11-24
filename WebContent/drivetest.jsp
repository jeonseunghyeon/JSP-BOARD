<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>


<h1>드라이브 테스트</h1>
 <%
 
 try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
            																						//sql 이름, 비밀번호 입력
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_web_commu", "",""); // JDBC 연결
            System.out.println("DB 연결 완료");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB 연결 오류");
        }
%>
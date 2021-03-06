<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="user" class="user.UserBean" scope="page" />
<jsp:setProperty name="user" property="u_ID" />
<jsp:setProperty name="user" property="u_Password" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판-로그인</title>
</head>
<body>
	<%
		// 현재 세션 상태를 체크한다
		String u_ID = null;
		if(session.getAttribute("u_ID") != null){
			u_ID = (String)session.getAttribute("u_ID");
		}
		// 이미 로그인했으면 다시 로그인을 할 수 없게 한다
		if(u_ID != null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인이 되어 있습니다')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getU_ID(), user.getU_Password());
		if(result == 1){
			// 로그인에 성공하면 세션을 부여
			session.setAttribute("u_ID", user.getU_ID());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인 성공')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}else if(result == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == -2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류입니다')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>
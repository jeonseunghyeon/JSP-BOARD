<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import = "BoardCategory.CategoryBalladeDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:useBean id="ballade" class="BoardCategory.CategoryBalladeBean" scope ="page"/>
    <jsp:setProperty name="ballade" property="balladeTitle"/>
    <jsp:setProperty name="ballade" property="balladeContent"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<%

		//현재 세션 상태를 체크한다
	 String u_ID = null;
	if(session.getAttribute("u_ID") != null){
		u_ID = (String)session.getAttribute("u_ID");
	}
	
	//로그인을 한 사람만 글을 쓸 수 있도록 코드를 수정한다
	if(u_ID == null){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
		
	} else {
		
		if(ballade.getBalladeTitle() == null || ballade.getBalladeContent() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다')");
			script.println("history.back()");
			script.println("</script>");
		}else {
			
			CategoryBalladeDAO BalladeDAO = new CategoryBalladeDAO();
			int result = BalladeDAO.write(ballade.getBalladeTitle(), u_ID, ballade.getBalladeContent());
			
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글쓰기에 실패했습니다')");
				script.println("history.back()");
				script.println("</script>");
				
			} else {
				
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글쓰기에 성공')");
				script.println("location.href='boardBallade.jsp'");
				script.println("</script>");
			}
		}
	}
	
	

%>
</body>
</html>
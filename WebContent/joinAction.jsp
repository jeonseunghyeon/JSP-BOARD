<%@page import="sec.ex.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:useBean id="user" class="sec.ex.UserBean" scope ="page"/>
    <jsp:setProperty name="user" property="u_ID"/>
    <jsp:setProperty name="user" property="u_Password"/>
    <jsp:setProperty name="user" property="u_name"/>
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
	
	// 이미 로그인했으면 회원가입을 할 수 없게 한다
	if(u_ID != null){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인이 되어 있습니다')");
		script.println("location.href='main.jsp'");
		script.println("</script>");
	}
	
	
	if(user.getU_ID() == null || user.getU_Password() == null || user.getU_name() == null){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 부분이 있습니다')");
		script.println("history.back()");
		script.println("</script>");
	} else {
		
		UserDAO userdao = new UserDAO();
		
		int result = userdao.join(user);
		
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하는 회원입니다.')");
			script.println("history.back()");
			script.println("</script>");
			
		}else{
			session.setAttribute("u_ID",user.getU_ID());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입 성공')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
	}




%>
</body>
</html>
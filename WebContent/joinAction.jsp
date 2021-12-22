<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:useBean id="user" class="user.UserBean" scope ="page"/>
    <jsp:setProperty name="user" property="u_ID"/>
    <jsp:setProperty name="user" property="u_Password"/>
    <jsp:setProperty name="user" property="u_Password2"/>
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
	}/*  else if(user.getU_ID() == null ){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('아이디를 입력해 주세요')");
		script.println("history.back()");
		script.println("</script>");
	}else if(user.getU_Password() == null ){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호를 입력해 주세요')");
		script.println("history.back()");
		script.println("</script>");
	}else if(user.getU_Password2() == null ){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호 확인란을 입력해 주세요')");
		script.println("history.back()");
		script.println("</script>");
	}if(user.getU_Password() != user.getU_Password2()){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('두 비밀번호가 맞지 않습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}else if(user.getU_name() == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이름을 입력해 주세요')");
		script.println("history.back()");
		script.println("</script>"); 
	}*/else{
		
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
<!-- <script type="text/javascript">
function checkPassword(u_ID, u_Password, u_Password2) {
    //비밀번호가 입력되었는지 확인하기
    if (!checkExistData(u_Password1, "비밀번호를"))
        return false;
    //비밀번호 확인이 입력되었는지 확인하기
    if (!checkExistData(u_Password2, "비밀번호 확인을"))
        return false;

    var password1RegExp = /^[a-zA-z0-9]{4,12}$/; //비밀번호 유효성 검사
    if (!password1RegExp.test(password1)) {
        alert("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
        form.u_Password1.value = "";
        form.u_Password1.focus();
        return false;
    }
    //비밀번호와 비밀번호 확인이 맞지 않다면..
    if (u_Password1 != u_Password2) {
        alert("두 비밀번호가 맞지 않습니다.");
        form.u_Password1.value = "";
        form.u_Password2.value = "";
        form.u_Password2.focus();
        return false;
    }

    //아이디와 비밀번호가 같을 때..
    if (id == u_Password1) {
        alert("아이디와 비밀번호는 같을 수 없습니다!");
        form.u_Password1.value = "";
        form.u_Password2.value = "";
        form.u_Password2.focus();
        return false;
    }
    return true; //확인이 완료되었을 때
}
</script> -->
</body>
</html>
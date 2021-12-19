<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import = "Board.BoardDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
    
    <jsp:useBean id="board" class="Board.BoardBean" scope ="page"/>
    <jsp:setProperty name="board" property="boardTitle"/>
    <jsp:setProperty name="board" property="boardContent"/>
    
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
	
	int boID = 0;
	if(request.getParameter("boID") != null){
			boID = Integer.parseInt(request.getParameter("boardID"));
	}
	
	String realFolder="";
	String saveFolder = "bbsUpload";		//사진을 저장할 경로
	String encType = "utf-8";				//변환형식
	int maxSize=5*1024*1024;				//사진의 size
	
	
	ServletContext context = this.getServletContext();		//절대경로를 얻는다
	realFolder = context.getRealPath(saveFolder);			//saveFolder의 절대경로를 얻음
			
	
	MultipartRequest multi = null;
	
	
	multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());
	String fileName = multi.getFilesystemName("fileName");
	String boardTitle = multi.getParameter("boardTitle");
	String boardContent = multi.getParameter("boardContent");
	board.setBoardTitle(boardTitle);
	board.setBoardContent(boardContent);

	
	//로그인을 한 사람만 글을 쓸 수 있도록 코드를 수정한다
	if(u_ID == null){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
		
	} else {
		
		if(board.getBoardTitle() == null || board.getBoardContent() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다')");
			script.println("history.back()");
			script.println("</script>");
		}else {
			
			BoardDAO boardDAO = new BoardDAO();
			int result = boardDAO.write(boID,board.getBoardTitle(), u_ID, board.getBoardContent());
			
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글쓰기에 실패했습니다')");
				script.println("history.back()");
				script.println("</script>");
				
			} else {
				
				PrintWriter script = response.getWriter();
				if(fileName != null){
					File oldFile = new File(realFolder+"\\"+fileName);
					File newFile = new File(realFolder+"\\"+(result-1)+"사진.jpg");
					oldFile.renameTo(newFile);
				}
				script.println("<script>");
				script.println("alert('글쓰기에 성공')");
				script.println("location.href='board.jsp'");
				script.println("</script>");
			}
		}
	}
	
	

%>
</body>
</html>
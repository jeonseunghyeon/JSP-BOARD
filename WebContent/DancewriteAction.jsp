<%@page import="user.UserDAO"%>
<%@page import="file.FileDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import = "BoardCategory.CategoryDanceDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
    
    <jsp:useBean id="dance" class="BoardCategory.CategoryDanceBean" scope ="page"/>
    <jsp:setProperty name="dance" property="danceTitle"/>
    <jsp:setProperty name="dance" property="danceContent"/>
    
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
	
	CategoryDanceDAO DanceDAO = new CategoryDanceDAO();
	
	
	dance.setDanceID(DanceDAO.getNewNext());
	int danceID = dance.getDanceID();
	String directory = application.getRealPath("/boardUpload/"+danceID+"/");
	
	File targetDir = new File(directory);
	if(!targetDir.exists()){
		targetDir.mkdirs();
	}
	
	int maxSize = 1024*1024*500;
	String encoding = "utf-8";
	
	MultipartRequest multipartRequest
	= new MultipartRequest(request, directory, maxSize, encoding,
					new DefaultFileRenamePolicy());
	
	String fileName = multipartRequest.getOriginalFileName("file");
	String fileRealName = multipartRequest.getFilesystemName("file");
	
	String danceTitle = multipartRequest.getParameter("danceTitle");
	String danceContent =  multipartRequest.getParameter("danceContent");
	dance.setDanceTitle(danceTitle);
	dance.setDanceContent(danceContent);
		
	
	//로그인을 한 사람만 글을 쓸 수 있도록 코드를 수정한다
	if(u_ID == null){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
		
	} else {
		
		if(dance.getDanceTitle() == null || dance.getDanceContent() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다')");
			script.println("history.back()");
			script.println("</script>");
		}else {
			
			
			int result = DanceDAO.write(dance.getDanceTitle(), u_ID, dance.getDanceContent());
			
			new FileDAO().upload(fileName,fileRealName,dance.getDanceID());
			
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
				script.println("location.href='boardDance.jsp'");
				script.println("</script>");
			}
		}
	}
	
	

%>
</body>
</html>
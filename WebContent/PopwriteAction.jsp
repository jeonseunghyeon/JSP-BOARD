<%@page import="file.FileDAO"%>
<%@page import="BoardCategory.CategoryPopBean"%>
<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import = "BoardCategory.CategoryPopDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
    
    <jsp:useBean id="pop" class="BoardCategory.CategoryPopBean" scope ="page"/>
    <jsp:setProperty name="pop" property="popTitle"/>
    <jsp:setProperty name="pop" property="popContent"/>
    
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
	
	CategoryPopDAO popDAO = new CategoryPopDAO();
	pop.setPopID(popDAO.getNewNext());
	int popID = pop.getPopID();
	String directory = application.getRealPath("/boardUpload/"+popID+"/");
	
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
	
	String popTitle = multipartRequest.getParameter("popTitle");
	String popContent =  multipartRequest.getParameter("popContent");
	pop.setPopTitle(popTitle);
	pop.setPopContent(popContent);
		
		
	
	//로그인을 한 사람만 글을 쓸 수 있도록 코드를 수정한다
	if(u_ID == null){
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 하세요')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
		
	} else {
		
		
		System.out.println("write action : check board parameter" + pop.getPopTitle());
		
		if(pop.getPopTitle() == null || pop.getPopContent() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다')");
			script.println("history.back()");
			script.println("</script>");
		}else {
			
			System.out.println("getNewNext before popDAO.write :" + pop.getPopID());
			int result = popDAO.write(pop.getPopTitle(), u_ID, pop.getPopContent());
			
			new FileDAO().upload(fileName,fileRealName,pop .getPopID());
			
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
				script.println("location.href='boardPop.jsp'");
				script.println("</script>");
			}
		}
	}
	
	

%>
</body>
</html>
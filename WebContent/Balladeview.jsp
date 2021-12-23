<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@page import="java.io.File"%>
 <%@ page import="java.io.PrintWriter" %>
 <%@ page import="BoardCategory.CategoryBalladeDAO" %>
  <%@ page import="BoardCategory.CategoryBalladeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 화면 최적화 -->
<meta name="viewport" content="width-device-width", initial-scale="1">
<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<%
	String u_ID = null;
	if(session.getAttribute("u_ID") != null){
		u_ID = (String)session.getAttribute("u_ID");
	}
	
	int balladeID =0;
	if(request.getParameter("balladeID") != null){
		balladeID = Integer.parseInt(request.getParameter("balladeID"));
	}
	
	if(balladeID == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다')");
		script.println("location.href='boardBallade.jsp'");
		script.println("</script>");
	}
	
	CategoryBalladeBean balladebean = new CategoryBalladeDAO().getBallade(balladeID);
	
%>
<title><%= balladebean.getBalladeTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></title>
</head>
<body>

	<nav class="navbar navbar-default"> <!-- 네비게이션 -->
		<div class="navbar-header"> 	<!-- 네비게이션 상단 부분 -->
			<!-- 네비게이션 상단 박스 영역 -->
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<!-- 이 삼줄 버튼은 화면이 좁아지면 우측에 나타난다 -->
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<!-- 상단 바에 제목이 나타나고 클릭하면 main 페이지로 이동한다 -->
			<a class="navbar-brand" href="main.jsp">Music Player</a>
		</div>
		<!-- 게시판 제목 이름 옆에 나타나는 메뉴 영역 -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="board.jsp">게시판</a></li>
				<li><li class="active"><a href="boardBallade.jsp">발라드</a></li>
				<li><a href="boardDance.jsp">댄스</a></li>
				<li><a href="boardEdm.jsp">EDM</a></li>
				<li><a href="boardHiphop.jsp">Hip Hop</a></li>
				<li><a href="boardPop.jsp">POP</a></li>
				<li><a href="boardRock.jsp">ROCK</a></li>
			</ul>
			<%
				if(u_ID == null){
			%>
			<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<!-- 드랍다운 아이템 영역 -->	
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				}else{
			%>
			<!-- 헤더 우측에 나타나는 드랍다운 영역 -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">회원관리<span class="caret"></span></a>
					<!-- 드랍다운 아이템 영역 -->	
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
<!-- 게시판 글 보기 양식 영역 시작 -->
	<div class="container">
		<div class="row">
			<table class="table table" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th class="bg-primary"colspan="2" style="text-align: center;"><%= balladebean.getBalladeTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%=  balladebean.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%= balladebean.getBalladeDate().substring(0, 11) + balladebean.getBalladeDate().substring(11, 13) + "시"
								+ balladebean.getBalladeDate().substring(14, 16) + "분" %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="height: 200px; text-align: left;"><%= balladebean.getBalladeContent().replaceAll(" ", "&nbsp;")
							.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %>
							
								
							<%
							
							String directory = application.getRealPath("/boardUpload/"+balladeID+"/");
							
							File targetDir = new File(directory);
								if(!targetDir.exists()){
									targetDir.mkdirs();
										}
										
								String files[] = new File(directory).list();	
											
								for(String file : files){
												
								out.write("<a href=\"" + request.getContextPath() + "/downloadAction?boardID="+balladeID+"&file="+
									java.net.URLEncoder.encode(file,"UTF-8") + "\">" + file + "</a><br>");
										}
							
							
							
							%>	
							
							
							</td>
					</tr>
				</tbody>
			</table>
				<a href="boardBallade.jsp" class="btn btn-primary">목록</a>	
				<%
					if(u_ID != null && u_ID.equals(balladebean.getUserID())){

				%>
						<a href="update.jsp?BalladeID=<%=balladeID %>" class="btn btn-primary">수정</a>
						<a href="deleteAction.jsp?BalladeID=<%=balladeID %>" class="btn btn-primary">삭제</a>
				<%
					}
				%>
		</div>
	</div>
	<!-- 게시판 글쓰기 양식 영역 끝 -->
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.0/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
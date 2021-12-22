<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 화면 최적화 -->
<meta name="viewport" content="width-device-width", initial-scale="1">
<!-- 루트 폴더에 부트스트랩을 참조하는 링크 -->
<!-- <link rel="stylesheet" href="css/Reset.css"> -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">


<title>게시판</title>
</head>
<body>
<%
	String u_ID = null;
	if(session.getAttribute("u_ID") != null){
		u_ID = (String)session.getAttribute("u_ID");
	}
%>
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
			<a class="navbar-brand" href="main.jsp"title="Music Player">Music Player</a>
		</div>
		<!-- 게시판 제목 이름 옆에 나타나는 메뉴 영역 -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="main.jsp">메인</a></li>
				<li><a href="board.jsp">자유 게시판</a></li>
				<li><a href="boardBallade.jsp">발라드</a></li>
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
<!-- 메인 페이지 영역 시작 -->
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>Music Player</h1>
				<p>다양한 장르의 음악들을 듣는 회원들이 서로 자신이 좋아하는 음악을 공유하며 소통할 수 있는 게시판입니다.</p>
			</div>
		</div>
	</div>
	<!-- content -->
	<div class="container pt-3">
		<div class="row">
			<!-- left content -->
			<div class="col-sm-4">
				<!-- side menu (link) -->
				<h3>사이드 메뉴</h3>
				<p>오늘의 아티스트!</p>
				<ul class="list-group">
					<li class="list-group-item list-group-item-action"><a href="https://www.melon.com/artist/timeline.htm?artistId=751611"title="클릭시 멜론 아티스트페이지로 이동합니다.">헤이즈</a></li>
					<li class="list-group-item list-group-item-action"><a href="https://www.melon.com/artist/timeline.htm?artistId=585492"title="클릭시 멜론 아티스트페이지로 이동합니다.">기리보이</a></li>
					<li class="list-group-item list-group-item-action"><a href="https://www.melon.com/artist/timeline.htm?artistId=2622030"title="클릭시 멜론 아티스트페이지로 이동합니다." >ITZY</a></li>
				</ul>
			</div>
			<!-- right content -->
			<div class="col-sm-8">
				<div>
				<h2>폴블랑코</h2>
				<p>요즘 핫한 아티스트</p>
				<img src="img/paul.jpg" class="img-fluid">
				<p>지난 4월 캐나다에서 날아든 앨범 Lake of Fire는 단숨에 국내 리스너들 사이에서 화제가 됐다. 앨범을 만든 장본인은 토론토 출신의 힙합 아티스트, 폴 블랑코. 그는 한국말보다 영어로 가사를 쓰는 것이 편하지만, 두루치기와 소주를 좋아한다고 말한다.</p>
				</div>
				<div>
				<h2>저스틴버버</h2>
				<p>10월 23일 2번째 싱글 'Sorry'를 발매</p>
				<img src="img/biber.jpg" class="img-fluid">
				</div>
				<hr>
				<!-- side menu (link) -->
				<h3>오늘의 인터뷰!</h3>
				<p>있지 "선배들 노력있기에…더 완벽하고파"(인터뷰)</p>
				<p>지난 2019년 한 해 신인 걸그룹 중 ITZY(있지)의 적수는 없었다. 데뷔와 동시에 글로벌한 주목을 받았고, 통통 튀는 음악으로 음악성과 무대 위 매력까지 인정받았다.
				   데뷔한지 1년이 지난 지금, 있지는 이미 톱 반열에 올라선 '하이클래스' 걸그룹이 됐다. 있지는 데뷔 후 신인상 9관왕을 차지하며 그야말로 '괴물 신인'다운 행보를 걷는 중. 있지는 데뷔곡 
				   '달라달라'로 K팝 걸그룹 기준 최단기간 지상파 음악방송 1위를 차지하기도 했으며 신인상을 휩쓰는 등 1년 간의 활발한 활동을 기록을 증명했다.</p>
				<div class="text-center">
					<img src="img/itzy.jpg" class="img-fluid rounded mb-3">
				</div>
				<p>지난해 말 뉴스1이 가요 관계자들 21인에게 설문한 결과, 있지는 21표 중 무려 16표를 받는 압도적 지지 속에 '2019년 최고 신예 걸그룹'으로 뽑힌 바 있다. 
				   설문에 참여한 전문가들은 "데뷔와 동시에 대중을 사로잡았다" 
				   "멤버 각각 다 매력적" "음방 10관왕, 돋보이는 음원 차트 등 이례적 성적으로 두각을 나타냈다" "탄탄한 팀워크와 실력을 가짐" 등의 의견을 보였다.</p>
				<p>특히 있지는 이미 수준급의 실력을 가진 '완성형 그룹'이라는 점이 높이 평가받았다. 한 관계자는 "신인답지 않은 파워풀한 안무와 퍼포먼스, 화려한 무대 매너가 압도적"이라며 
					"'신인'이 아닌 올해의 가수라고 해도 지나침이 없다"라고 평했다. 다른 관계자는 
					"올해 가장 두드러진 활약을 보여줬다"라며 "이미 '완성형'인 그룹으로 앞으로가 더 기대된다"라고 귀띔했다.</p>
				<!--test -->
			</div>
		</div>
	</div>
	<!-- 부트스트랩 참조 영역 -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.0/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
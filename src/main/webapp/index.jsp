<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>와이파이 정보 구하기</title>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="inc_menu.jsp"/>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/index.js"></script>
</head>
<body>
	<h1>
		<button type="button" onclick="location.href='history.jsp' ">내 위치 가져오기</button>
		<button type="button" onclick="location.href='load-wifi.jsp' ">근처 Wifi 정보 보기</button>
	</h1>
</body>
</html>
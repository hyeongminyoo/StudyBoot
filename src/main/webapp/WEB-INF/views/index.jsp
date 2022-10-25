<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">
</head>
<body>
	<h1>Index Page</h1>
	<img src="/images/puppy.jpg">
	<a href="./qna/list">QNA</a>
	<div>
		<c:choose>
			<c:when test="${empty sessionScope.member}">
				<a href="/member/login">로그인</a>
				<a href="/member/join">회원가입</a>
			</c:when>
			<c:otherwise>
				<a href="/member/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>	
	</div>
	<div>
		<img alt="" src="/file/qna/eaabffaa-6669-4e86-845c-bd03c3d0d9f8_그레이하운드.jpg">
		<img alt="" src="/file/notice/파피용.jpg">
		<a href="/fileDown/qna?fileNum=2">Qna Download</a>
		<a href="/fileDown/notice?fileNum=2">Notice Download</a>
		
	</div>
</body>
</html>
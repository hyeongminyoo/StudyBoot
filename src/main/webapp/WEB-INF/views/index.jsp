<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">
<script defer src="/js/test.js"></script>
</head>
<body>
	<h1>Index Page</h1>
	<h1><spring:message code="hi" var="h"></spring:message> </h1>
	<h1><spring:message code="test" text="code가 없을 때 출력하는 메세지"></spring:message> </h1>
	
	<img src="/images/puppy.jpg">
	<a href="./qna/list">QNA</a>
	<div>
		<c:choose>
			<c:when test="${empty sessionScope.member}">
				<a href="/member/login">로그인</a>
				<a href="/member/join">회원가입</a>
			</c:when>
			<c:otherwise>
				<h3><spring:message code="welcome" arguments="${member.name}"></spring:message> </h3>
				<h3><spring:message code="welcome2" arguments="${member.id},${member.name}" argumentSeparator=","></spring:message> </h3>
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
	
	<button id="btn">CLICK</button>

	<button class="buttons">BTN1</button>
	<button class="buttons">BTN2</button>
	<button class="buttons">BTN3</button>

	<div id="test">
		
	</div>
	<h1>${h}</h1>
	<h1>${h}</h1>
	
</body>
</html>
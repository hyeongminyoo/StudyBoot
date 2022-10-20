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
		<img alt="" src="/file/qna/eaabffaa-6669-4e86-845c-bd03c3d0d9f8_그레이하운드.jpg">
		<a href="/fileDown/qna?fileNum=2">Download</a>
	</div>
</body>
</html>
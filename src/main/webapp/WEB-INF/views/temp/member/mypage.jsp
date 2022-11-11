<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>마이페이지</h3>
	<sec:authentication property="Principal" var="user"/>
	<h3>ID : ${user.id}</h3>
	
	<h3>NAME : <sec:authentication property="Principal.name"/></h3>
	
	<%-- <h3>ID : <sec:authentication property="name"/></h3> --%>
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<h1>List Page</h1>
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Num</th><th>Writer</th><th>Title</th><th>REGDATE</th><th>HIT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.num}</td>
						<td>${dto.writer}</td>
						<td>${dto.title}</td>
						<td>${dto.regDate}</td>
						<td>${dto.hit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div>
		<a class="btn btn-danger" href="/qna/write">WRITE</a>
	</div>
	
	
	<script type="text/javascript">
		let result = '${param.result}';
		if(result != ""){
			if(result == '1'){
				alert("등록 성공");
			}else{
				alert("등록 실패");
			}
		}
	</script>
		
</body>
</html>
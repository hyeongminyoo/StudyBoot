<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<div class="container">
	<h3>Detail</h3>
		<div class="row">
			<div class="row">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>작성자</th><th>제목</th><th>작성일</th><th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${vo.writer}</td>
							<td>${vo.title}</td>
							<td>${vo.regDate}</td>
							<td>${vo.hit}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<textarea rows="10" cols="10" id="contents"></textarea>
			</div>
			<div class="row">
				<c:forEach items="${vo.qnaFileVOs}" var="file">
					<p>
						<img src="C:\RHM\result\upload\qna\${file.fileName}">
					</p>
				</c:forEach>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$('#contents').summernote('pasteHTML', '${vo.contents}');
	$('#contents').next().find(".note-editable").attr("contenteditable", false);
	
	const toolbar = document.getElementsByClassName("note-toolbar");
	toolbar[0].setAttribute("style","display : none;");
	</script>
</body>
</html>
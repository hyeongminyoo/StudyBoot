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
	<h1>Board Write Page</h1>
		<div class="d-flex justify-content-between align-items-center">
			<form action="write" method="post" enctype="multipart/form-data">
				<div class="mb-3">
				  <label for="writer" class="form-label">작성자</label>
				  <input type="text" class="form-control" id="writer" name="writer" placeholder="작성자를 입력하세요.">
				</div>
				<div class="mb-3">
				  <label for="title" class="form-label">제목</label>
				  <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요.">
				</div>
				<div class="mb-3">
			  		<label for="contents" class="form-label">내용</label>
			  		<textarea class="form-control" id="contents" rows="3" name="contents"></textarea>
				</div>
				
				<div class="mb-3">
			  		<label for="file" class="form-label">File</label>
			  		<input type="file" name="files">
				</div>
				
				<div class="mb-3">
			  		<label for="file" class="form-label">File</label>
			  		<input type="file" name="files">
				</div>
				
				<button type="submit" class="btn btn-outline-danger">Write</button>
			</form>
		</div>
	</div>



<script type="text/javascript">
$("#contents").summernote({
    height : 400,
    lang : "ko-KR",
    minHeight : null,
    maxHeight : null,
    focus : true,
});
</script>
</body>
</html>
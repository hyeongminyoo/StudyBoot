<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="../temp/boot.jsp"></c:import>
<script type="text/javascript" defer src="/js/fileManager.js"></script>
</head>
<body>
	
	<div class="container">
	<h1>QNA Update Page</h1>
		<div class="d-flex justify-content-between align-items-center">
			<form action="update" method="post" enctype="multipart/form-data">
				<input type="hidden" name="num" value="${vo.num}">
				<div class="mb-3">
				  <label for="writer" class="form-label">작성자</label>
				  <input type="text" class="form-control" id="writer" name="writer" readonly value="${vo.writer}">
				</div>
				<div class="mb-3">
				  <label for="title" class="form-label">제목</label>
				  <input type="text" class="form-control" id="title" name="title" value="${vo.title}">
				</div>
				<div class="mb-3">
			  		<label for="contents" class="form-label">내용</label>
			  		<textarea class="form-control" id="contents" rows="3" name="contents"></textarea>
				</div>
				
				<div class="row row-cols-3">
				</div>
				
				
				
				<div class="mb-3" id="files">
					<c:forEach items="${vo.qnaFileVOs}" var="file">
						<div>
							<h5>${file.oriName}</h5>
							<img src="/file/qna/${file.fileName}" style="height: 200px; width: 200px;">
							<button type="button" class="deleteFile" data-fileNum="${file.fileNum}">X</button>
						</div>
					</c:forEach>
				</div>
				
				<div class="mb-3">
					<button type="button" id="fileAdd">FileAdd</button>
				</div>
				
				
				<!-- <div class="mb-3">
			  		<label for="file" class="form-label">File</label>
			  		<input type="file" name="files">
				</div>
				
				<div class="mb-3">
			  		<label for="file" class="form-label">File</label>
			  		<input type="file" name="files">
				</div> -->
				
				<button type="submit" class="btn btn-outline-danger">Write</button>
			</form>
		</div>
	</div>



<script type="text/javascript">
let con = '${vo.contents}';
$("#contents").summernote({
    height : 400,
    lang : "ko-KR",
    minHeight : null,
    maxHeight : null,
    focus : true,
});

$("#contents").summernote('code','${vo.contents}');

let len ='${fn:length(vo.qnaFileVOs)}'; 
</script>
</body>
</html>
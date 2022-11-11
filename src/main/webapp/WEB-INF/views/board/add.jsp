<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
	<h1>Board Write Page</h1>
		<div class="d-flex justify-content-between align-items-center">
			<sec:csrfInput/>
			<form:form method="post" modelAttribute="qnaVO" enctype="multipart/form-data">
	
				<div class="mb-3">
				  <label for="writer" class="form-label">작성자</label>
				  <form:input path="writer" id="writer" cssClass="form-control"/>
				  <form:errors path="writer"></form:errors>
				</div>
				<div class="mb-3">
				  <label for="title" class="form-label">제목</label>
				  <form:input path="title" cssClass="form-control" id="title"/>
				  <form:errors path="title"></form:errors>
				</div>
				<div class="mb-3">
			  		<label for="contents" class="form-label">내용</label>
			  		<form:textarea path="contents" id="contents" cssClass="form-control"/>
			  		<form:errors path="contents"></form:errors>
				</div>
				
				<div class="mb-3" id="files">
					
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
			</form:form>
		</div>
	</div>



<script type="text/javascript">
$("#contents").summernote({
    height : 400,
    lang : "ko-KR",
    minHeight : null,
    maxHeight : null,
    callbacks : {
    	onImageUpload:function(file){
			console.log("ImageUpload");
			//ajax file server upload 후 경로를 받아서 사용
			uploadFile(file);
		},
		onMediaDelete:function(file){
			console.log("Delete Media");
			console.log(file);
			deleteFile(file);
			console.log("Delete File");
		}
    }
});

let summerFiles = [];

//ajax upload 함수
function uploadFile(file){
	console.log("file :",file);
	console.log("filename : ",file[0].name);
	summerFiles.push(filename[0].name);
	//<form> 태그 준비
	const formData = new FormData();
	//<input type="file">
	formData.append('files',file[0]);
	

	$.ajax({
		type:"POST",
		url:"summerFile",
		data:formData,
		//header
		cache:false,
		processData:false,
		contentType:false,
		enctype:'multipart/form-data',
		success:function(img){
			console.log("Image =>",img);
			// img = '<img src='+""+img+""+'>'
			// $('#contents').summernote('pastHTML', img);
			$('#contents').summernote('insertImage', img, 'test.jpg');
		},
		error:function(e){
			console.log('img업로드 실패');
		}

	})

}

function deleteFile(file){
	console.log(file.attr("src"));
	$.post("./summerFileDelete", {fileName:file.attr("src")}, function(result){
		console.log("result => ",result);
		summerFiles.pop(file[0].name);
	})
}

</script>
</body>
</html>
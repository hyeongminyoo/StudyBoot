<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<script defer src="/js/memberAdd.js"></script>
</head>
<body>

	<div class="container-fluid">
    	<div class="row mt-5 justify-content-center">
    		<div class="col-lg-6">    		
    			<h1 class="text-center">Join page</h1>
    		</div>
    	</div>
    
    	<div class="row justify-content-center mt-5">
    		<div class="col-lg-6">
        	<form action="./join" id="joinForm" method="post" enctype="application/x-www-form-urlencoded">
			  <div class="row mb-3">
			    <label for="inputUserName" class="col-sm-2 col-form-label">ID</label>
			    <div class="col-sm-10">
			      <input type="text" name="id" class="form-control" id="inputId" placeholder="ID 입력">
				  <div id="inputUserNameResult"></div>
			    </div>
			  </div>
			  <div class="row mb-3">
			    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="inputPw" placeholder="Password 입력">
				  <div id="inputPasswordResult"></div>
			    </div>
			  </div>

			  <div class="row mb-3">
			    <label for="inputPassword" class="col-sm-2 col-form-label">Password 확인</label>
			    <div class="col-sm-10">
			      <input type="password" name="pw" class="form-control" id="inputPwCh" placeholder="Password 입력">
				  <div id="inputPasswordCheckResult"></div>
			    </div>
			  </div>

			  <div class="row mb-3">
			    <label for="inputName" class="col-sm-2 col-form-label">Name</label>
			    <div class="col-sm-10">
			      <input type="text" name="name" class="form-control" id="inputName" placeholder="이름 입력">
				  <div id="inputNameResult"></div>
			    </div>
			  </div>
			  
			  <div class="row mb-3">
			    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
			    <div class="col-sm-10">
			      <input type="email" required name="email" class="form-control" id="inputEmail" placeholder="Email 입력">
				  <div id="inputEmailResult"></div>
			    </div>
			  </div>
			 <!--  <div class="row mb-3">
			    <label for="inputPhone" class="col-sm-2 col-form-label">Phone</label>
			    <div class="col-sm-10">
			      <input type="text" name="phone" class="form-control" id="inputPhone" placeholder="전화번호 입력">
				  <div></div>
			    </div>
			  </div> -->
			  
			 <!--  <div class="row mb-3">
			    <label for="files" class="col-sm-2 col-form-label">Photo</label>
			    <div class="col-sm-10">
			      <input type="file" name="photo" class="form-control" id="files" placeholder="전화번호 입력">
			    </div>
			  </div> -->
			  
			  <!-- <div>
			  	관리자 <input type="checkbox" name="nums" value="1">
			  	매니저 <input type="checkbox" name="nums" value="2">
			  	일반 <input type="checkbox" name="nums" value="3">
			  </div> -->
			  
			  <!-- 약관동의 Test -->
			  <div class="row mt-5 justify-content-center">
				  <div class="row mt-5 justify-content-center">
					  ALL <input type="checkbox" id="all">
				  </div>
				  <div class="row mt-5 justify-content-center">
					  동의1 <input type="checkbox" class="check" name="" id="">
					  <div class="row mt-5 justify-content-center">
						  약관1
					  </div>
				  </div class="row mt-5 justify-content-center">
				  <div class="row mt-5 justify-content-center">
					  동의2 <input type="checkbox" class="check" name="" id="">
					  <div class="row mt-5 justify-content-center">
						  약관2
					  </div>
				  </div>
				  <div class="row mt-5 justify-content-center">
					  동의3 <input type="checkbox" class="check" name="" id="">
					  <div class="row mt-5 justify-content-center">
						  약관3
					  </div>
				  </div>
			  </div>
			 
			 
			 
			 
			 
			 
			  <div class="row justify-content-end">
			  	<div >
			  		<button type="button" id="joinButton" class="btn btn-primary mb-3">Sign in</button>
			  	</div>
			  </div>
			</form>
        	</div>
        
        </div>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        	<form:form modelAttribute="memberVO" method="post">
        	
			  <div class="row mb-3">
			    <label for="inputUserName" class="col-sm-2 col-form-label">ID</label>
			    <div class="col-sm-10">
			      <form:input path="id" cssClass="form-control" id="inputId"/>
				  <div id="inputUserNameResult">
				  	<form:errors path="id"></form:errors>
				  </div>
			    </div>
			  </div>
			  <div class="row mb-3">
			    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
			    <div class="col-sm-10">
			    	<form:password path="pw" cssClass="form-control" id="inputPw"/>
			    	<form:errors path="pw"></form:errors>
				  <div id="inputPasswordResult"></div>
			    </div>
			  </div>

			  <div class="row mb-3">
			    <label for="inputPassword" class="col-sm-2 col-form-label">Password 확인</label>
			    <div class="col-sm-10">
			    	<form:password path="pwCheck" cssClass="form-control" id="inputPwCh"/>
			    	<form:errors path="pwCheck"></form:errors>
				  <div id="inputPasswordCheckResult"></div>
			    </div>
			  </div>

			  <div class="row mb-3">
			    <label for="inputName" class="col-sm-2 col-form-label">Name</label>
			    <div class="col-sm-10">
				    <form:input path="name" cssClass="form-control" id="inputName"/>
				    <%-- <form:errors path="name"></form:errors> --%>
				  <div id="inputNameResult">
				  	${name}
				  </div>
			    </div>
			  </div>
			  
			  <div class="row mb-3">
			    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
			    <div class="col-sm-10">
			    	<form:input path="email" cssClass="form-control" id="inputEmail"/>
			    	<form:errors path="email"></form:errors>
				  <div id="inputEmailResult"></div>
			    </div>
			  </div>
			  <div class="row mb-3">
			    <label for="inputAge" class="col-sm-2 col-form-label">Age</label>
			    <div class="col-sm-10">
			    	<form:input path="age" cssClass="form-control" id="inputAge"/>
			    	<form:errors path="age"></form:errors>
				  <div id="inputAgeResult"></div>
			    </div>
			  </div>
			  
			  
			  <div class="row mb-3">
			    <label for="inputBirth" class="col-sm-2 col-form-label">Birth</label>
			    <div class="col-sm-10">
			    	<form:input path="birth" cssClass="form-control" id="inputBirth"/>
			    	<form:errors path="birth"></form:errors>
				  <div id="inputbirthResult"></div>
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
			  		<%-- type : submit <form:button></form:button> --%>
			  		<button type="submit" id="joinButton" class="btn btn-primary mb-3">Sign in</button>
			  	</div>
			  </div>
			  
			</form:form>
			
        	</div>
        
        </div>
    </div>
</body>
</html>
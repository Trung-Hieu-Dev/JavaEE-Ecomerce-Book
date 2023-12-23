<%@include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<%@include file="navigation.jsp" %>
	<c:if test="${message != null}">
		<input type="hidden" id="notification" value="${message}" />
	</c:if>
	

	<div class="container py-5">
		<c:if test="${theUser == null}">
			<c:url var="actionLink" value="manage_user">
				<c:param name="command" value="INSERT"/>
			</c:url>
			<h1 class="text-center mb-4">Create New User</h1>
		</c:if>
		
		<c:if test="${theUser != null}">
			<c:url var="actionLink" value="manage_user">
				<c:param name="command" value="UPDATE"/>
			</c:url>
			<h1 class="text-center mb-4">Update User</h1>
		</c:if>
		<hr class="mx-auto" style="width:50%;">

		<h2 class="text-center">Quick action</h2>
		<div class="d-flex justify-content-center">
			<a href="#" class="me-4">New User</a>
		</div>
		<hr class="mx-auto" style="width:50%;">
		
		
		<div class="d-flex flex-column align-items-center py-5">
			<form id="userForm" action="${actionLink}" method="post" 
				style="width:350px;" 
			>
				<div class="form-floating mb-3">
				  <input name="email" 
				  		type="email" class="form-control" 
				  		id="inputUserEmail" 
				  		placeholder="name@example.com" 
				  		value="${theUser.email}"
				  />
				  <label for="inputUserEmail">Email address</label>
				  <div class="invalid-feedback">Please input valid email</div>
				</div>
				<div class="form-floating mb-3">
				  <input name="fullName" 
				  		type="text" 
				  		class="form-control" 
				  		id="inputUserFullname" 
				  		placeholder="Full name" 
				  		value="${theUser.fullName}" 
				  />
				  <label for="inputUserFullname">Full name</label>
				  <div class="invalid-feedback">Please input valid full name</div>
				</div>
				<div class="form-floating mb-3">
				  <input name="password" 
				  		type="password" 
				  		class="form-control" 
				  		id="inputUserPassword" 
				  		placeholder="Password" 
				  		value="${theUser.password}" 
				  />
				  <label for="inputUserPassword">Password</label>
				  <div class="invalid-feedback">Please input valid password</div>
				</div>
				<c:if test="${theUser != null}">
					<input type="hidden" name="userId" value="${theUser.userId}" />
				</c:if>
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary mt-4 w-50">Submit</button>
				</div>
			</form>
		</div>
	</div>

<%@include file="footer.jsp" %>

<!-- AlertifyJS -->
<script type="text/javascript">
	var notification = document.getElementById("notification");
	
	if(notification != null && notification.value.length > 0) {
		alertify.error(notification.value);
	}
</script>


<!-- JQuery Validation -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#userForm").validate({
			rules: {
			    email: {required: true, email:true},
			    fullName: {required: true, minlength: 2},
			    password: {required: true, minlength: 3},
			},
			errorPlacement: function(error, element) {}, // remove default label element
			highlight: function (element, errorClass, validClass) { // in case in-valid
				$(element).addClass("is-invalid").removeClass("is-valid"); //add and remove classes to input tag
			},
			unhighlight: function (element, errorClass, validClass) { // in case valid
				$(element).addClass("is-valid").removeClass("is-invalid"); //add and remove classes to input tag
			},
		});
	})
</script>

<!-- <script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("inputUserEmail");
 		var fieldFullname = document.getElementById("inputUserFullname");
 		var fieldPassword = document.getElementById("inputUserPassword");
 		
 		if (fieldEmail.value.length == 0) {
 			fieldEmail.classList.add("is-invalid");
 			return false;
 		} else {
 			fieldEmail.classList.remove("is-invalid");
 			fieldEmail.classList.add("is-valid");
 		}
 		
 		if (fieldFullname.value.length == 0) {
 			fieldFullname.classList.add("is-invalid");
 			return false;
 		} else {
 			fieldFullname.classList.remove("is-invalid");
 			fieldFullname.classList.add("is-valid");
 		}
 		
 		if (fieldPassword.value.length == 0) {
 			fieldPassword.classList.add("is-invalid");
 			return false;
 		} else {
 			fieldPassword.classList.remove("is-invalid");
 			fieldPassword.classList.add("is-valid");
 		}
 		
 		return true;
	}
</script> -->



<%@include file="scripts.jsp" %>
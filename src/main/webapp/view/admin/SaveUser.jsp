<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.button{
margin-top: 10px;
height: 30px;
width: 70px;
}
.textField{
height: 20px;
margin-top: 10px;
}
form{
margin-top: 100px;
margin-left: 150px;
}</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="client-manager" method="POST" name="userForm">
	<input type = "hidden" name = "userAction">
	<input type = "hidden" name = "id">
	<input type = "text" name = "name" placeholder = "Enter Name" class="textField"><br>
	<input type = "email" name = "email" placeholder = "Enter Email" class="textField"><br>
	<input type = "text" name = "mobile" placeholder="Enter Mobile" class="textField"><br>
	<input type = "password" name = "mobile" placeholder="Enter Password" class="textField"><br>
	<input type = "button" value = "Save" onclick="saveUser()" class="button">
</form>

<script type="text/javascript">

function saveUser(){
	if(document.userForm.name.value==""){
		alert("Please Enter Name");
		return;
	}
	if(document.userForm.email.value==""){
		alert("Please Enter Email");
		return;
	}
	if(document.userForm.mobile.value==""){
		alert("Please Enter Mobile");
		return;
	}
	document.userForm.userAction.value=1
	document.userForm.submit();
}
</script>
</body>
</html>
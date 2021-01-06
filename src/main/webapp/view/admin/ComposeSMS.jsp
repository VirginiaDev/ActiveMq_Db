
<%@page import="com.example.demo.dto.User"%>
<%User user = (User)session.getAttribute("user"); 
%>
<%@page import="com.example.demo.DemoApplication"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action = "client-manager?userAction=4" method="POST">
 <input type="hidden" name="user_id" value="<%=user.getId()%>">
 <input type="text" name="sender_Id" placeholder="Enter Sender Id"><br>
 <input type="text" name="contact" placeholder="Enter Contact"><br>
 <input type="text" name="message" placeholder="Enter Message"><br>
 
 <input type="submit" value="Submit">
 </form>
  
</body>
</html>
<%@page import="com.example.demo.dto.Clients"%>
<%@page import="java.util.List"%>
<%
List<Clients> list = (List<Clients>)request.getAttribute("list");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<th>ID</th>
	<th>Sender Details</th>
	<th>Message</th>
	<th>Status</th>
	<th>Action</th>
	<%if(list.size()>0){
		for(int i=0; i<list.size(); i++){
			Clients c = list.get(i);%>
			<tr>
				<td><%=c.getId() %></td>  
				<td><%=c.getSender_details()%></td>  
				<td><%=c.getMessage()%></td>  
				<%
				String status="";
				if(c.getStatus().equals("1")){
					status="Undeliver";
				}else{
					status="Deliever";
				}%>
				<td><%=c.getStatus() %></td>  
				<td><a href="">Edit</a></td>
			</tr>
		<%}
	}
	%>

</table>
</body>
</html>
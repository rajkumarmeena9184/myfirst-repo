<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome View</title>
</head>
<body><h1><%=request.getAttribute("a") %></h1>
	<h1>${message}</h1>
	<form action="/project0/TestCtl/send" method="post">
		<center>Welcome View</center>
		<input type="submit" value="submit">
	</form>
</body>
</html>
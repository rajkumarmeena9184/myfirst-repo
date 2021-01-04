<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.footer {
	position: fixed;
	right: 0;
	bottom: 0;
	margin-top:150px;
	color: gainsboro;
	left: 0;
	display: block;
	box-shadow: 0px 0px 0px 0px #244a4a;
	text-align: center;
}
</style>
<title>Footer view</title>
<title>Footer</title>
</head>
<body>
	<div class="container-fluid">
		<div class="footer" style="background-color: rgb(125, 125, 125);">
			<p style="color: white;" class="text-center">
				&copy CopyRight<%=Calendar.getInstance().getWeekYear()%>:Rays
				Technology
			</p>
		</div>
	</div> 

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-image: url("/Project0/resources/img/fuwHKJ.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

.btn:hover {
	color: red;
}
</style>
</head>
<body>
	<div class="containe-fluid"></div>
	<div class="row">
		<div class="col col-sm-4"></div>
		<div class="col col-sm-4">
			<br> <a href='<c:url value="/welcome"></c:url>'
				class="img-circle btn btn-link"> <img
				class="img-circle,img-fluid"
				src='<c:url value="/resources/img/logo.jpg"></c:url>' width="400px"
				height="200px"></a> <br> <br> <br> <a
				class="btn shadow" style="font-size: 40px; text-align: center;"
				href='<c:url value="/welcome"></c:url>'>Online Result System</a>
		</div>
		<div class="col col-sm-4"></div>
	</div>
</body>
</html>

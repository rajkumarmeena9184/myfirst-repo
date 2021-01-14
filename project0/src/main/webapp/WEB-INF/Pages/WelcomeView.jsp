<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
body {
	background-image: url("/Project0/resources/img/background.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
</style>
<title>WelcomeView</title>
</head>
<body class="img-fluid">
	<div class="container-fluid">
		<div style="margin-top: 100px;" align="center">
			<font size="10"><span style="color: orange;"><s:message
						code="label.welcomeors" /></span></font>
		</div>
	</div>
</body>
</html>
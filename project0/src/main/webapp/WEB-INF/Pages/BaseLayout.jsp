<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<title>Rays Technologies</title>
<link rel="shortcut icon" href="/Project0/resources/img/icon.jpg"
	type="image/x-icon" class="img-circle" />
<title>BaseLayout</title>
</head>
<tiles:insertAttribute name="header"></tiles:insertAttribute>
<body>
	<br>
	<br>
	<div class="container-fluid">
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
		<div class="row " id="AllBody">
			<div class=""></div>
			<div class="col-sm-12">
				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
		</div>

	</div>
</body>
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</html>
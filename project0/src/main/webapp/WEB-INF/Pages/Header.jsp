<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">


<%-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css"> --%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value=""/>"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/Checkbox11.js"/>"></script>
<style type="text/css">
.navbar-collapse.collapse.show {
	max-height: 50%;
	overflow-y: auto;
}

.btn-group {
	position: fixed;
	z-index: 1;
}

.nav-item:hover {
	background-color: black;
}
</style>


<title>Header</title>
</head>
<body>
	<!-- navbar -->
	<nav class="navbar navbar-expand-sm navbar-light fixed-top"
		style="background-color: rgb(26, 82, 118);">
		<a href=""><img
			src="<c:url value=" /Project0/resources/img/logo.jpg"/>"
			height="30px" width="80px;"></a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">

				<li class="nav-item active"><a class="nav-link text-light"
					href="<c:url value="/welcome"/>"
					style="font-size: 20px; color: white;"><i class="fas fa-home"></i></a></li>
				<c:if test="${not empty sessionScope.user }">

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/User/AddUser"/>"><i
							class="fas fa-user" style="color: orange;"></i><span><s:message
									code="label.adduser"></s:message> </span></a></li>

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/Faculty/AddFaculty"/>"><i
							class="fas fa-chalkboard-teacher" style="color: orange;"></i><span><s:message
									code="label.addfaculty"></s:message> </span></a></li>

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/Student/AddStudent"/>"><i
							class="fas fa-user-circle" style="color: orange;"></i><span><s:message
									code="label.addstudent"></s:message> </span></a></li>

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/Role/AddRole"/>"><i
							class="fas fa-male" style="color: orange;"></i><span><s:message
									code="label.addrole"></s:message> </span></a></li>


					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/College/AddCollege"/>"><i
							class="fas fa-university" style="color: orange;"></i><span><s:message
									code="label.addcollege"></s:message> </span></a></li>

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/Course/AddCourse"/>"><i
							class="fas fa-book" style="color: orange;"></i><span><s:message
									code="label.addcourse"></s:message> </span></a></li>

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/Subject/AddSubject"/>"><i
							class="fas fa-user" style="color: orange;"></i><span><s:message
									code="label.addsubject"></s:message> </span></a></li>

					<li class="nav-item text-nowrap"><a
						class="nav-link text-light" style="font-size: 14px;"
						href="<c:url value="/ctl/TimeTable/AddTimeTable"/>"><i
							class="fas fa-server" style="color: orange;"></i><span><s:message
									code="label.addtimetable"></s:message> </span></a></li>

					<li class="nav-item dropdown text-nowrap"><a
						class="nav-link dropdown-toggle" data-toggle="dropdown" href=""
						role="button" aria-haspopup="true" aria-expanded="false"
						style="color: white;"><span><i
								class="fa fa-address-book"
								style="color: orange; font-size: 14px;"></i></span>&nbsp;<span
							.	style="color: white; font-size: 14px;"><s:message
									code="label.marksheet"></s:message></span></a>
						<div class="dropdown-menu dropdown-menu-right"
							style="background-color: #cceeff;">
							<a class="dropdown-item"
								href="<c:url value="/ctl/Marksheet/AddMarksheet"/>"><i
								class="fa fa-arrow-circle-right" area-hidden="true"></i>&nbsp;&nbsp;
								<span style="color: #8A2BE2;"><s:message
										code="label.addmarksheet"></s:message></span></a> <a
								class="dropdown-item"
								href="<c:url value="/ctl/Marksheet/GetMarksheet"/>"><i
								class="fa fa-arrow-circle-right" area-hidden="true"></i>&nbsp;&nbsp;
								<span style="color: #8A2BE2;"><s:message
										code="label.getmarksheet"></s:message></span></a><a class="dropdown-item"
								href="<c:url value="/ctl/Marksheet/MarksheetMeritListCtl"/>"><i
								class="fa fa-user-circle" area-hidden="true"
								style="color: #0f6810;"></i>&nbsp;&nbsp; <span
								style="color: #8A2BE2;"><s:message
										code="label.marksheetmeritlist"></s:message></span></a>
						</div></li>

				</c:if>
				<li class="nav-item dropdown"></li>
			</ul>
			<div
				class="navbar nav-item navbar-nav dropdown text-nowrap dropdown-menu-left  collapse">

				<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
					style="font-size: 15px; color: white;" role="button"
					aria-haspopup="true" aria-expanded="false"> <span><i
						class="fa fa-user-circle" style="color: orange;"></i></span><span>
						<c:if test="${not empty sessionScope.user }">
							<c:set var="name" value="${sessionScope.user.firstName}"></c:set>
							<c:set var="role" value="${sessionScope.user.roleName}"></c:set>
							<c:out value="${name}(${role})"></c:out>
						</c:if> <c:if test="${empty sessionScope.user }">
							<s:message code="label.hiGuest"></s:message>
						</c:if>
				</span></a>

				<div class="dropdown-menu dropdown-menu-right"
					style="font-size: 15px; background-color: e6f7ff">
					<c:if test="${ not empty sessionScope.user}">

						<a class="dropdown-item"
							href="<c:url value="/ctl/User/MyProfileCtl"/>"><i
							class="fa fa-user-circle" aria-hidden="true"
							style="color: #0f6810;"></i><span style="color: #8A2BE2;"><s:message
									code="label.myprofile"></s:message></span></a>

						<a class="dropdown-item"
							href="<c:url value="/ctl/User/ChangePasswordCtl"/>"><i
							class="fa fa-random" aria-hidden="true" style="color: #0f6810;"></i><span
							style="color: #8A2BE2;"><s:message
									code="label.changepassword"></s:message></span></a>

						<a class="dropdown-item" target="blank" href="<c:url value="/resources/javadoc/index.html"/>"><i
							class="fas fa-caret-square-right" aria-hidden="true"
							style="color: #0f6810;"></i><span style="color: #8A2BE2;"><s:message
									code="label.javadoc"></s:message></span></a>
						<a class="dropdown-item"
							href="<c:url value="/LoginCtl"><c:param name="signout" value="Logout"></c:param> </c:url>"><i
							class="fas fa-caret-square-left" aria-hidden="true"
							style="color: #0f6810;"></i><span style="color: #8A2BE2;"><s:message
									code="label.logout"></s:message></span></a>
					</c:if>

					<c:if test="${ empty sessionScope.user}">

						<a class="dropdown-item" href="<c:url value="/LoginCtl"></c:url>"><i
							class="fa fa-arrow-circle-right" aria-hidden="true"
							style="color: #0f6810;"></i><span style="color: #8A2BE2;"><s:message
									code="label.login"></s:message></span></a>
						<a class="dropdown-item"
							href="<c:url value="/Registration"></c:url>"><i
							class="fa fa-arrow-circle-right" aria-hidden="true"
							style="color: #0f6810;"></i><span style="color: #8A2BE2;"><s:message
									code="label.signup"></s:message></span></a>
					</c:if>
				</div>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<br>
	<div class="btn-group" role="group" style="font-size: 10px;">
		<a class="btn btn-outline-primary" href="<c:url value="?lang=en"/>"
			style="font-size: 10px;"><strong>English</strong></a> <a
			class="btn btn-outline-primary" href="<c:url value="?lang=hi"/>"
			style="font-size: 10px;"><strong>हिन्दी</strong></a>
	</div>
</body>
</html>
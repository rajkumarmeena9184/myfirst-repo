<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LoginView</title>

<style type="text/css">
body {
	background-image: url("/Project0/resources/img/background.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}

.button2 {
	background-color:;
	color: black;
	border: 2px solid #257b0e;
}

.button2:hover {
	background-color: #257b0e;
	color: white;
}
</style>
</head>
<body>
	<sf:form method="POST" modelAttribute="form">
	<sf:hidden path="uri" value="${URI}" />
		<div class="container-fluid" align="center">
			<aside class="col-sm-4">
				<div class="card" style="background-color: rgb(255, 255, 255);">
					<article class="card-body">

						<h4 class="card-title text-center mb-4 mt-1"
							style="color: #C71585;">
							<s:message code="label.login"></s:message>
						</h4>

						<hr>
						<c:if test="${success != null }">
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							<b>${success}</b>
							</div>
						</c:if>
						<c:if test="${error != null }">
							<div class="alert alert-danger alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							<b>${error}</b>
							</div>
						</c:if>
						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.email"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="fas fa-envelope"></i></span>
								</div>
								<sf:input path="emailId" class="form-control"
									placeholder="${email }" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="emailId"></sf:errors>
								</font>
							</div>
						</div>

						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.password"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-lock"></i></span>
								</div>
								<sf:input type="password" path="password" class="form-control"
									placeholder="${password }" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="password"></sf:errors>
								</font>
							</div>
						</div>
						<button type="submit" name="operation" value="SignIn"
							class="btn btn-block button2" title="this is for login">
							<i class="fa fa-paper-plane " style="color:" aria-hidden="true"></i>
							<s:message code="label.login"></s:message>
						</button>
						<br>
						<p class="text-left">
							<a href="<c:url value="/ForgotPassword" />"
								class="btn btn-outline-success"><s:message
									code="label.forgetpassword"></s:message>? </a> <a
								href="<c:url value="/Registration"/>"
								class="float-right btn btn-outline-success"><s:message
									code="label.userRegistration"></s:message></a>
						</p>
				</div>
				</article>
			</aside>
		</div>
	</sf:form>
</body>
</html>
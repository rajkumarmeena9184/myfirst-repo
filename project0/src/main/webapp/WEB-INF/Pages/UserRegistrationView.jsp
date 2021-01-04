<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<html>
<head>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'mm/dd/yy',
			yearRange : "-34:-18",
			defaultDate : "01/01/2000",
		});
	});
</script>

<style type="text/css">
body {
	background-image: url("/Project0/resources/img/background.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
/* 
.button2 {
	background-color:;
	color: black;
	border: 2px solid #257b0e;
}

.button2:hover {
	background-color: #257b0e;
	color: white;
}
 */
</style>
</head>
<body>
	<sf:form method="POST" modelAttribute="form">
		<div class="container-fluid" align="center">
			<aside class="col-sm-4">
				<div class="card" style="background-color: rgb(255, 255, 255);">
					<article class="card-body">

						<h4 class="card-title text-center mb-4 mt-1"
							style="color: #C71585;">
							<s:message code="label.userRegistration"></s:message>
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
						<sf:hidden path="id" />
						<sf:hidden path="createdBy" />
						<sf:hidden path="modifiedBy" />
						<sf:hidden path="createdDateTime" />
						<sf:hidden path="modifiedDateTime" />

						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.firstName"></s:message>
									<s:message code="label.firstName" var="firstName"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-user"></i></span>
								</div>
								<sf:input path="firstName" class="form-control"
									placeholder="${firstName }" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="firstName"></sf:errors>
								</font>
							</div>
						</div>

						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.lastName"></s:message>
									<s:message code="label.lastName" var="lastName"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-user"></i></span>
								</div>
								<sf:input path="lastName" class="form-control"
									placeholder="${lastName }" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="lastName"></sf:errors>
								</font>
							</div>
						</div>

						<div class="form-group">
							<%-- <s:message code="label.mobileNo" var="abc"></s:message> --%>
							<div align="left" style="color: #8A2BE2;">
								<s:message code="label.gender"></s:message>
								<span style="color: red">*</span>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class='fas fa-venus-double'></i></span>
								</div>
								<sf:select path="gender" class="form-control">
									<sf:option value="">
										<s:message code="label.selectgender"></s:message>
									</sf:option>
									<sf:options items="${genderList }" />
								</sf:select>
							</div>
							<div align="left">
								<font color="red"><sf:errors path="gender" /></font>
							</div>
						</div>



						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.dob"></s:message>
									<s:message code="label.dob" var="date"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="fas fa-calendar-alt"></i></span>
								</div>
								<sf:input readonly="true" id="datepicker" path="dob"
									class="form-control" placeholder="${date}" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="dob"></sf:errors>
								</font>
							</div>
						</div>

						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.emailId"></s:message>
									<s:message code="label.emailId" var="emailId"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="fas fa-envelope"></i></span>
								</div>
								<sf:input path="emailId" class="form-control"
									placeholder="${emailId }" />
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
									<s:message code="label.password" var="password"></s:message>
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
						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.confirmpassword"></s:message>
									<s:message code="label.confirmpassword" var="confirmPassword"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fa fa-lock"></i></span>
								</div>
								<sf:input type="password" path="confirmPassword"
									class="form-control" placeholder="${confirmPassword }" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors
										path="confirmPassword"></sf:errors> </font>
							</div>
						</div>
						<div class="form-group">
							<div align="left" style="color: #8A2BE2;"
								for="validationDefault02">
								<sf:label path="">
									<s:message code="label.mobileNo"></s:message>
									<s:message code="label.mobileNo" var="mobileNo"></s:message>
									<span style="color: red">*</span>
								</sf:label>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i
										class="fas fa-mobile-alt"></i></span>
								</div>
								<sf:input type="text" path="mobileNo" class="form-control"
									placeholder="${mobileNo }" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="mobileNo"></sf:errors>
								</font>
							</div>
						</div>
						<button type="submit" name="operation"
							style="float: left; width: 100px; margin-left: 50px;"
							value="SignUp" class="btn btn-primary btn-block button2 msgBtn">
							<span class="fas fa-check-circle"> </span>
							<s:message code="label.signup" />
						</button>


						<button type="submit" name="operation"
							style="width: 100px; margin-left: 150px;" value="Reset"
							class="btn btn-warning btn-block button2">
							<span class="fas fa-refresh"> </span>
							<s:message code="label.reset" />
						</button>
				</div>
				</article>
				<br> <br>
			</aside>
		</div>
	</sf:form>
</body>
</html>
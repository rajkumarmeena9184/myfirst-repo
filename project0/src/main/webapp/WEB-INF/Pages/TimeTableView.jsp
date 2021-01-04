<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title>TimeTableView</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script type="text/javascript">
	function DisableSunday(date) {
		var day = date.getDay();

		if (day == 0) {
			return [ false ];

		} else {
			return [ true ];
		}

	}
	$(function() {
		$("#datepicker").datepicker({
			beforeShowDay : DisableSunday,
			changeMonth : true,
			changeYear : true,
			yearRange : '2020:2021',
			/* dateFormat : 'dd-mm-yy',*/
			minDate : +1
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
						<c:choose>
							<c:when test="${form.id==1}">
								<h4 class="card-title text-center mb-4 mt-1"
									style="color: #C71585;">
									<s:message code="label.updatetimetable"></s:message>
								</h4>
							</c:when>
							<c:otherwise>
								<h4 class="card-title text-center mb-4 mt-1"
									style="color: #C71585;">
									<s:message code="label.addtimetable"></s:message>
								</h4>
							</c:otherwise>
						</c:choose>

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
							<%-- <s:message code="label.mobileNo" var="abc"></s:message> --%>
							<div align="left" style="color: #8A2BE2;">
								<s:message code="label.courseName"></s:message>
								<span style="color: red">*</span>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class='fas fa-venus-double'></i></span>
								</div>
								<sf:select path="courseId" class="form-control">
									<sf:option value="0">
										<s:message code="label.selectcourse"></s:message>
									</sf:option>
									<sf:options items="${courseList }" itemValue="id"
										itemLabel="name" />
								</sf:select>
							</div>
							<div align="left">
								<font color="red"><sf:errors path="courseId" /></font>
							</div>
						</div>


						<div class="form-group">
							<%-- <s:message code="label.mobileNo" var="abc"></s:message> --%>
							<div align="left" style="color: #8A2BE2;">
								<s:message code="label.subjectname"></s:message>
								<span style="color: red">*</span>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class='fas fa-venus-double'></i></span>
								</div>
								<sf:select path="subjectId" class="form-control">
									<sf:option value="0">
										<s:message code="label.selectsubject"></s:message>
									</sf:option>
									<sf:options items="${subjectList }" itemValue="id"
										itemLabel="subjectName" />
								</sf:select>
							</div>
							<div align="left">
								<font color="red"><sf:errors path="subjectId" /></font>
							</div>
						</div>


						<div class="form-group">
							<%-- <s:message code="label.mobileNo" var="abc"></s:message> --%>
							<div align="left" style="color: #8A2BE2;">
								<s:message code="label.examtime"></s:message>
								<span style="color: red">*</span>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class='fas fa-venus-double'></i></span>
								</div>
								<sf:select path="exameTime" class="form-control">
									<sf:option value="">
										<s:message code="label.selecttime"></s:message>
									</sf:option>

									<sf:option value="09:00 AM to 12:00 PM"
										label="09:00 PM to 03:00 PM"></sf:option>
									<sf:option value="10:00 PM to 01:00 PM"
										label="10:00 PM to 01:00 PM"></sf:option>
									<sf:option value="12:00 PM to 03:00 PM"
										label="12:00 PM to 03:00 PM"></sf:option>
									<sf:option value="01:00 PM to 04:00 PM"
										label="02:00 PM to 05:00 PM"></sf:option>

									<%-- <sf:options items="${timeTableList }" /> --%>
								</sf:select>
							</div>
							<div align="left">

								<font color="red"><sf:errors path="exameTime" /></font>
							</div>
						</div>

						<div class="form-group">
							<%-- <s:message code="label.mobileNo" var="abc"></s:message> --%>
							<div align="left" style="color: #8A2BE2;">
								<s:message code="label.semester"></s:message>
								<span style="color: red">*</span>
							</div>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"> <i
										class='fas fa-venus-double'></i></span>
								</div>
								<sf:select path="semester" class="form-control">
									<sf:option value="">
										<s:message code="label.selectsemester"></s:message>
									</sf:option>
									<sf:option value="1st" label="I"></sf:option>
									<sf:option value="2nd" label="II"></sf:option>
									<sf:option value="3rd" label="III"></sf:option>
									<sf:option value="4th" label="IV"></sf:option>
									<sf:option value="5th" label="V"></sf:option>
									<sf:option value="6th" label="VI"></sf:option>
									<sf:option value="7th" label="VII"></sf:option>
									<sf:option value="8th" label="VIII"></sf:option>
									<%-- <sf:options items="${semesterList }" /> --%>
								</sf:select>
							</div>
							<div align="left">

								<font color="red"><sf:errors path="semester" /></font>
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
								<sf:input readonly="true" id="datepicker" path="exameDate"
									class="form-control" placeholder="${date}" />
							</div>
							<div align="left">
								<font style="color: red"><sf:errors path="exameDate"></sf:errors>
								</font>
							</div>
						</div>
						<div></div>
						<c:choose>
							<c:when test="${form.id==0 }">
								<button type="submit" name="operation"
									style="float: left; width: 100px; margin-left: 50px;"
									value="Save" class="btn btn-primary btn-block button2 msgBtn">
									<span class="fas fa-check-circle"> </span>
									<s:message code="label.save" />
								</button>
								<button type="submit" name="operation"
									style="width: 100px; margin-left: 150px;" value="Reset"
									class="btn btn-warning btn-block button2">
									<span class="fas fa-refresh"> </span>
									<s:message code="label.reset" />
								</button>
							</c:when>
							<c:otherwise>
								<button type="submit" name="operation"
									style="float: left; width: 100px; margin-left: 50px;"
									value="Save" class="btn btn-success btn-block button2 msgBtn">
									<span class="fas fa-check-circle"> </span>
									<s:message code="label.update" />
								</button>
								<button type="submit" name="operation"
									style="width: 100px; margin-left: 150px;" value="Cancel"
									class="btn btn-warning btn-block button2">
									<span class="fas fa-refresh"> </span>
									<s:message code="label.cancel" />
								</button>
							</c:otherwise>
						</c:choose>
				</div>
				</article>
				<br> <br> <br>
			</aside>
		</div>
	</sf:form>
</body>
</html>
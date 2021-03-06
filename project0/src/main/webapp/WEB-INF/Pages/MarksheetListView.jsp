<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
	background-image: url("/Project0/resources/img/background.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
</style>
<title>MarksheetListView</title>
</head>
<body class="img-fluid">

	<c:set value="${list}" var="total"></c:set>
	<div class="row d-flex justify-content-center h-100">
		<div class="col-lg-12 col-sm-12 ml-auto mr-auto">
			<div class="card card-table" style="background-color: #B6D5E5;">
				<br>
				<h3 class="w-100 text-center under-over-line">
					<s:message code="label.marksheetlist"></s:message>
				</h3>
				<br>
				<!-- Error -->
				<c:if test="${error != null}">
					<div class="alert alert-danger alert-with-icon text-center"
						data-notify="container" align="center">
						<div class="container">
							<div class="alert-wrapper">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<div class="message">
									<i class="nc-icon nc-bell-55"></i>${error}
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!-- //Error -->
				<!-- Success -->
				<c:if test="${success != null}">
					<div class="alert alert-success alert-with-icon"
						data-notify="container" align="center">
						<div class="container">
							<div class="alert-wrapper">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<div class="message">
									<i class="nc-icon nc-bell-55"></i>${success}
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!-- //Success -->
				<sf:form class="register-form" method="post" modelAttribute="form">
					<c:set value="${((form.pageNo-1)*form.pageSize)+1}" var="index"></c:set>
					<sf:hidden path="pageNo" />
					<sf:hidden path="pageSize" />
					<c:if test="${!empty list}">
						<div class="row p-2 d-flex justify-content-center">
							<div class="col-lg-3">
								<sf:select path="studentId" class="form-control">
									<sf:option value="0">
										<s:message code="label.selectstudent"></s:message>

									</sf:option>
									<sf:options items="${studentList}" itemValue="id"
										itemLabel="firstName" />
								</sf:select>
							</div>
							<%-- <div class="col-lg-3">
								<s:message code="label.studentName" var="enterStudentName" />
								<sf:input path="name" cssClass="form-control border-left-right"
									placeholder="${enterStudentName}" />
							</div> --%>
							<div class="col-lg-3">
								<s:message code="label.enterrollno" var="enterRollNumber" />
								<sf:input path="rollNo"
									cssClass="form-control border-left-right"
									placeholder="${enterRollNumber}" />
							</div>
							<%-- <div class="col-lg-3">
								<s:message code="label.lastName" var="enterLastName" />
								<sf:input path="lastName"
									cssClass="form-control border-left-right"
									placeholder="${enterLastName}" />
							</div> --%>
							<div class="col-lg-3">
								<div class="row">
									<div class="col-6">
										<button type="submit"
											class="btn btn-outline-success btn-block btn-round w-100"
											name="operation" value="Search">
											<s:message code="label.search" />
										</button>
									</div>
									<div class="col-6">
										<button type="submit"
											class="btn btn-outline-danger btn-block btn-round w-100"
											name="operation" value="Reset">
											<s:message code="label.reset" />
										</button>
									</div>
								</div>
							</div>
						</div>
						<br>
						<br>
						<div class="row d-flex justify-content-center">
							<div class="col-lg-12 table-responsive">
								<table
									class="table table-hover table-striped border border-secondary">
									<thead class="bg-dark">
										<tr>
											<th scope="col" class="text-center text-white"><input
												type="checkbox" id="select_all" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.SNo" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.student" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.rollNo" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.physics" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.chemistry" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.maths" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.total" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.percentage" /></th>
											<th scope="col" class="text-center text-white"><s:message
													code="label.edit" /></th>
										</tr>
									</thead>
									<tbody style="background-color: white;">
										<c:forEach items="${list}" var="marksheet" varStatus="ct">
											<c:set var="percentage">
												<fmt:formatNumber type="number" minFractionDigits="2"
													maxFractionDigits="2"
													value="${(marksheet.physics+marksheet.chemistry+marksheet.maths)/3}"></fmt:formatNumber>
											</c:set>
											<c:set var="totalmarks"
												value="${marksheet.physics+marksheet.chemistry+marksheet.maths }"></c:set>
											<tr>
												<td class="text-center"><input class="checkbox"
													name="chk_1" type="checkbox" value="${marksheet.id}"></td>
												<td class="text-center"><c:out
														value="${(form.pageSize * (form.pageNo-1))+ct.index+1}" />
													<c:set var="index"
														value="${(form.pageSize * (form.pageNo-1))+ct.index+1}" />
												</td>
												<td class="text-center">${marksheet.studentName}</td>
												<td class="text-center">${marksheet.rollnumber}</td>
												<td class="text-center">${marksheet.physics}</td>
												<td class="text-center">${marksheet.chemistry}</td>
												<td class="text-center">${marksheet.maths}</td>
												<td class="text-center">${totalmarks}</td>
												<td class="text-center">${percentage}%</td>
												<%-- <td><fmt:formatDate value="${faculty.dob}" type="date"
														pattern="dd-MM-yyyy" /></td> --%>
												<td class="text-center"><c:url var="editUrl"
														value="/ctl/Marksheet/AddMarksheet?id=" /> <a
													class="btn btn-outline-primary btn-block btn-round w-100"
													style="border-color: #67EAE4; color: #67EAE4;"
													name="operation" href="${editUrl}${marksheet.id}"><i
														class="fa fa-edit" aria-hidden="true"></i> <s:message
															code="label.edit" /></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row d-flex justify-content-center">
							<div class="col-lg-2 col-3">
								<c:choose>
									<c:when test="${form.pageNo > 1}">
										<button type="submit"
											class="btn btn-outline-primary btn-block btn-round w-100"
											name="operation" value="Previous">
											<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
											<s:message code="label.previous"></s:message>
										</button>
									</c:when>
									<c:otherwise>
										<button type="submit"
											class="btn btn-outline-secondary btn-block btn-round w-100"
											name="operation" value="Previous" disabled>
											<i class="fa fa-arrow-circle-left" aria-hidden="true"></i>
											<s:message code="label.previous"></s:message>
										</button>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-lg-2 col-3">
								<button type="submit"
									class="btn btn-outline-success btn-block btn-round w-100"
									name="operation" value="New">
									<i class="fa fa-plus" aria-hidden="true"></i>
									<s:message code="label.new"></s:message>
								</button>
							</div>
							<div class="col-lg-2 col-3">
								<button type="submit"
									class="btn btn-outline-danger btn-block btn-round w-100"
									name="operation" value="Delete">
									<i class="fa fa-trash" aria-hidden="true"></i>
									<s:message code="label.delete"></s:message>
								</button>
							</div>
							<div class="col-lg-2 col-3">
									<c:choose>
									<c:when test="${nextlist != 0}">
										<button type="submit"
											class="btn btn-outline-primary btn-block btn-round w-100"
											name="operation" value="Next">
											<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
											<s:message code="label.next"></s:message>
										</button>
									</c:when>
									<c:otherwise>
										<button type="submit"
											class="btn btn-outline-secondary btn-block btn-round w-100"
											name="operation" value="Next" disabled>
											<i class="fa fa-arrow-circle-right" aria-hidden="true"></i>
											<s:message code="label.next"></s:message>
										</button>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</c:if>
					<br>
					<c:if test="${ empty list}">
						<div class="row d-flex justify-content-center">
							<div class="col-lg-2 col-6">
								<button type="submit"
									class="btn btn-outline-warning btn-block btn-round w-100"
									name="operation" value="Back">
									<i class="fa fa-reply" aria-hidden="true"></i>
									<s:message code="label.back"></s:message>
								</button>
							</div>
						</div>
					</c:if>
					<br>
				</sf:form>
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>
</body>
</html>
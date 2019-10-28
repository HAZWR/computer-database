<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script type="text/javascript">
console.log("tew")
	$(function() {

		$("#form").validate({
			rules : {
				computerName : {
					required : true,
					minlength : 5
				},
				introduced : {
					required : false,
					date:true,
				},
				discontinued : {
					required : false,
					date:true,
				}	
			},
		   onchange:function(){
				if(moment($('#introduced').val()).isAfter($('#discontinued').val())==true)
			 		alert("veuillez saisir une date d'introduction inférieure à la date discontinued");
				 else
			        alert("sasie des dates adaptées");
		   }
		});
		$('#form input').on('keyup', function() {
			if ($('#form').valid()) {
				$('#add').prop('disabled', false);
			} else {
				$('#add"').prop('disabled', 'disabled');
			}
		});
     });
});
</script>
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="menu"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form id="form" action="" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="computerName"
									id="computerName" placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" name="introduced"
									id="introduced" placeholder="Introduced date"
									required="required">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" name="discontinued"
									id="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach var="company" items="${listCompanies}">
										<option value="${company.id}"><c:out
												value="${company.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" id="add" class="btn btn-primary">
							or <a href="dashboard.jsp" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
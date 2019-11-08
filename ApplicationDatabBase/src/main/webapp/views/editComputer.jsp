<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="ressources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="ressources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="ressources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="menu"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${ordi.id}"/>
                    </div>
                    <h1>Edit Computer</h1>

                    <form:form action="editComputer" method="POST" modelAttribute="computer">
                        <form:input type="hidden" path="id" id="id" name="id"/> 
                        <fieldset>
                            <div class="form-group">
                                <form:label path="name" for="computerName">Computer name</form:label>
                                <form:input path="name" type="text" class="form-control" id="computerName" name="nom" placeholder="Computer name" />
                            </div>
                            <div class="form-group">
                                <form:label path="introduced" for="introduced">Introduced date</form:label>
                                <form:input path="introduced" type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date" />
                            </div>
                            <div class="form-group">
                                <form:label path="discontinued" for="discontinued">Discontinued date</form:label>
                                <form:input path="discontinued" type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date" />
                            </div>
                            <div class="form-group">
                                <form:label path="company" for="companyId">Company</form:label>
                                <form:select path="company" class="form-control" id="companyId" name="companyId">
                                   <c:forEach var="company"  items="${listCompanies}">
                                    	<option value="${company.name}"><c:out value="${company.name}"/></option>
                                   </c:forEach>
                                </form:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="menu" class="btn btn-default">Cancel</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
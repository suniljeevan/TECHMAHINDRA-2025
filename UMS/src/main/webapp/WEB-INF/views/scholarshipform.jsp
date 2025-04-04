<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Scholarship Form</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Add New Scholarship</h2>
    <form:form method="post" action="save" modelAttribute="scholarship">
        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="name" id="name" class="form-control" />
        </div>
        <div class="form-group">
            <label for="amount">Amount</label>
            <form:input path="amount" id="amount" class="form-control" />
        </div>
        <div class="form-group">
            <label for="applicationDeadLine">Application Deadline</label>
            <form:input path="applicationDeadLine" id="applicationDeadLine" type="date" class="form-control" />
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </form:form>
</div>
</body>
</html>

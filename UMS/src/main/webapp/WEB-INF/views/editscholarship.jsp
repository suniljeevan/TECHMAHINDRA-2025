<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Scholarship</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Edit Scholarship</h2>
         <form:form method="post" action="http://localhost:8080/ums/scholarship/editsave" modelAttribute="scholarship">
            
            <div class="mb-3">
        <label for="id" class="form-label">ID</label>
        <form:input path="id" id="id" class="form-control" readonly="readonly"/>
    </div>
    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <form:input path="name" id="name" class="form-control"/>
    </div>
    <div class="mb-3">
        <label for="amount" class="form-label">Amount</label>
        <form:input path="amount" id="amount" class="form-control"/>
    </div>
    <div class="mb-3">
        <label for="applicationDeadLine" class="form-label">Application Deadline</label>
        <form:input path="applicationDeadLine" id="applicationDeadLine" type="date" class="form-control"/>
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form:form>
    </div>
</body>
</html>

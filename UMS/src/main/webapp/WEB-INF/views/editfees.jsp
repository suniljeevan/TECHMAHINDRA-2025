<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Fees</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Edit Fees</h2>
        
        
         <form:form method="post" action="http://localhost:8080/ums/fees/editsave" modelAttribute="fees">
            
            <div class="mb-3">
                <label for="feeID" class="form-label">Fee ID</label>
                <form:input path="feeID" id="feeID" class="form-control" readonly="readonly"/>
            </div>
            <div class="mb-3">
                <label for="courseID" class="form-label">Course</label>
                <form:select path="courseID.id" id="courseID" class="form-control">
                    <form:options items="${courses}" itemValue="courseID" itemLabel="courseName" />
                </form:select>
            </div>
            <div class="mb-3">
                <label for="amount" class="form-label">Amount</label>
                <form:input path="amount" id="amount" class="form-control" />
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form:form>
    </div>
</body>
</html>

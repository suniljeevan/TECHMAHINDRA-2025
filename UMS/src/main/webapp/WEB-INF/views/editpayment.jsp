<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Payment</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Edit Payment</h2>
   <form:form method="post" action="http://localhost:8080/ums/payment/editsave" modelAttribute="payment">
        <div class="form-group">
            <label for="paymentID">Payment ID</label>
            <form:input path="paymentID" id="paymentID" class="form-control" readonly="true" />
        </div>
        <div class="form-group">
            <label for="studentID">Student ID</label>
            <form:input path="studentID.id" id="studentID" class="form-control" />
        </div>
        <div class="form-group">
            <label for="feeID">Fee ID</label>
            <form:input path="feeID.feeID" id="feeID" class="form-control" />
        </div>
        <div class="form-group">
            <label for="amount">Amount</label>
            <form:input path="amount" id="amount" class="form-control" />
        </div>
        <div class="form-group">
            <label for="paymentDate">Payment Date</label>
            <form:input path="paymentDate" id="paymentDate" type="date" class="form-control" />
        </div>
        <button type="submit" class="btn btn-primary mt-3">Save Changes</button>
        <a href="<c:url value='/payment/viewpayments' />" class="btn btn-secondary mt-3">Cancel</a>
    </form:form>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Payments</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>View Payments</h2>
    <a class="btn btn-primary" href="paymentform">+ Add New Payment</a>
    <br><br>
    <table class="table table-striped border">
        <thead>
            <tr>
                <th scope="col">Payment ID</th>
                <th scope="col">Student ID</th>
                <th scope="col">Fee ID</th>
                <th scope="col">Amount</th>
                <th scope="col">Payment Date</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="payment" items="${list}">
                <tr>
                    <th scope="row">${payment.paymentID}</th>
                    <td>${payment.studentID.id}</td>
                    <td>${payment.feeID.feeID}</td>
                    <td>${payment.amount}</td>
                    <td>${payment.paymentDate}</td>
                    <td>
                        <a class="btn btn-sm btn-outline-primary" href="editpayment/${payment.paymentID}">Edit</a>
                        <a class="btn btn-sm btn-outline-danger" href="deletepayment/${payment.paymentID}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

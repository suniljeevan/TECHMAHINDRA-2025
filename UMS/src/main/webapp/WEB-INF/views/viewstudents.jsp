<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Students | Book Store</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>View Students</h2>
    <a class="btn btn-primary" href="studentform">+ Add New Student</a>
    <br><br>
    <table class="table table-striped border">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${list}">
                <tr>
                    <th scope="row">${student.id}</th>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
              
                    <td>
                        <a class="btn btn-sm btn-outline-primary" href="editstudent/${student.id}">Edit</a>
                        <a class="btn btn-sm btn-outline-danger" href="deletestudent/${student.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

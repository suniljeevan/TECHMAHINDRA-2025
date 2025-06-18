<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Courses</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>View Courses</h2>
    <a class="btn btn-primary" href="courseform">+ Add New Course</a>
    <br><br>
    <table class="table table-striped border">
        <thead>
            <tr>
                <th scope="col">Course ID</th>
                <th scope="col">Course Name</th>
                <th scope="col">Description</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${list}">
                <tr>
                    <th scope="row">${course.courseID}</th>
                    <td>${course.courseName}</td>
                    <td>${course.description}</td>
                   
                    <td>
                        <a class="btn btn-sm btn-outline-primary" href="editcourse/${course.courseID}">Edit</a>
                        <a class="btn btn-sm btn-outline-danger" href="deletecourse/${course.courseID}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student | Book Store</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Edit Student</h2>
        <form:form method="post" action="http://localhost:8080/ums/student/editsave" modelAttribute="student">
            <div class="mb-3">
                <label for="studentID" class="form-label">Student ID</label>
                <form:input path="id" id="studentID" class="form-control" readonly="true" />
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <form:input path="name" id="name" class="form-control" />
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <form:input path="email" id="email" class="form-control" />
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <form:input path="password" id="password" class="form-control" />
            </div>
            <div class="mb-3">
                <label for="program" class="form-label">Program</label>
                <form:input path="program" id="program" class="form-control" />
            </div>
            <div class="mb-3">
                <label for="enrollmentDate" class="form-label">Enrollment Date</label>
                <form:input path="enrollmentDate" id="enrollmentDate" type="date" class="form-control" />
            </div>
            <div class="mb-3">
                <label for="graduationYear" class="form-label">Graduation Year</label>
                <form:input path="graduationYear" id="graduationYear" class="form-control" />
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form:form>
    </div>
</body>
</html>
  
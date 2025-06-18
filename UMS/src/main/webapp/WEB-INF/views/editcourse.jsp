 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Course</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Edit Course</h2>
         <form:form method="post" action="http://localhost:8080/ums/course/editsave" modelAttribute="command">
            <div class="mb-3">
                <label for="courseID" class="form-label">Course ID</label>
                <input type="text" class="form-control" id="courseID" name="courseID" value="${course.courseID}" readonly>
            </div>
            <div class="mb-3">
                <label for="courseName" class="form-label">Course Name</label>
                <input type="text" class="form-control" id="courseName" name="courseName" value="${course.courseName}">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input type="text" class="form-control" id="description" name="description" value="${course.description}">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
       </form:form>
    </div>
</body>
</html>

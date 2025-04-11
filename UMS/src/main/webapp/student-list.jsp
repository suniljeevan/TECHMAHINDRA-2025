<!-- student-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import = "org.cms.MODEL.Student" %>
<%
HttpSession oldsession=request.getSession(false);
    List<Student> students = (List<Student>) oldsession.getAttribute("xyz");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Student List</h2>
    <a href="add-student.jsp" class="btn btn-success mb-3">Add New Student</a>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% if (students != null) {
            for (Student student : students) {
        %>
        <tr>
            <td><%= student.getSid() %></td>
            <td><%= student.getSname() %></td>
            <td><%= student.getEmail() %></td>
            
            <td>
                <a href="update-student?id=<%= student.getSid() %>" class="btn btn-sm btn-primary">Edit</a>
                <a href="delete-student?id=<%= student.getSid() %>" class="btn btn-sm btn-danger">Delete</a>
            </td>
        </tr>
        <%  }
        } else {
        %>
        <tr><td colspan="5" class="text-center">No students found</td></tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Add New Student</h2>
    <form action="RC" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">ID</label>
            <input type="text" class="form-control" id="id" name="id" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Email</label>
            <input type="text" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">address</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Year</label>
            <input type="text" class="form-control" id="year" name="year" required>
        </div>
        <button type="submit" class="btn btn-success">Add Student</button>
        <a href="student-list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>

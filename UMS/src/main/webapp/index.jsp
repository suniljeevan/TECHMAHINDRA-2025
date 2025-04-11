<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>University Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <style>
        body {
            background: linear-gradient(to right, #e3f2fd, #ffffff);
            min-height: 100vh;
        }
        .card {
            transition: transform 0.2s ease;
        }
        .card:hover {
            transform: scale(1.03);
        }
        footer {
            background-color: #f8f9fa;
            padding: 10px 0;
        }
    </style>
</head>
<body data-bs-theme="light">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">UMS</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
            </ul>
            <div class="d-flex align-items-center">
                <button class="btn btn-outline-light me-3" onclick="toggleTheme()">Toggle Dark Mode</button>
                <a class="btn btn-danger" href="logout.jsp">Logout</a>
            </div>
        </div>
    </div>
</nav>

<!-- Main Content -->
<div class="container py-5">
    <div class="text-center mb-5">
        <h1 class="display-4 text-primary fw-bold">University Management System</h1>
        <p class="lead">Manage Students, Courses, and Enrollments with ease</p>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">

        <!-- Student Card -->
        <div class="col">
            <div class="card shadow-sm border-primary">
                <div class="card-body text-center">
                    <h5 class="card-title">Student Management</h5>
                    <p class="card-text">Add, update, view, or delete students</p>
                    <a href="Student" class="btn btn-outline-primary">Manage Students</a>
                </div>
            </div>
        </div>

        <!-- Course Card -->
        <div class="col">
            <div class="card shadow-sm border-success">
                <div class="card-body text-center">
                    <h5 class="card-title">Course Management</h5>
                    <p class="card-text">Add, update, view, or delete courses</p>
                    <a href="Course" class="btn btn-outline-success">Manage Courses</a>
                </div>
            </div>
        </div>

        <!-- Enrollment Card -->
        <div class="col">
            <div class="card shadow-sm border-warning">
                <div class="card-body text-center">
                    <h5 class="card-title">Enrollment Management</h5>
                    <p class="card-text">Enroll students in courses</p>
                    <a href="Enrollment" class="btn btn-outline-warning">Manage Enrollments</a>
                </div>
            </div>
        </div>

        <!-- Placeholder for future modules -->
        <div class="col">
            <div class="card shadow-sm border-secondary">
                <div class="card-body text-center">
                    <h5 class="card-title">Reports & Analytics</h5>
                    <p class="card-text">Generate and view academic reports</p>
                    <a href="#" class="btn btn-outline-secondary disabled">Coming Soon</a>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- Footer -->
<footer class="text-center mt-5">
    <div class="container">
        <p class="mb-0">&copy; 2025 University Management System | Designed by <strong>Sunil Kumar Sahoo</strong></p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function toggleTheme() {
        const body = document.body;
        const currentTheme = body.getAttribute("data-bs-theme");
        body.setAttribute("data-bs-theme", currentTheme === "light" ? "dark" : "light");
    }
</script>
</body>
</html>

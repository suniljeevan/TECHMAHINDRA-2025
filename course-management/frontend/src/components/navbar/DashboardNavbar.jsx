import React, { useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "./DashboardNavbar.css";
import axios from "axios";
import { useTheme } from "../../context/ThemeContext";

const DashboardNavbar = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const { darkMode, toggleTheme } = useTheme();

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      axios.get(`http://localhost:8080/students/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
  });

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };
  const userRole = localStorage.getItem("role").toLocaleLowerCase();
  const userId = localStorage.getItem("id");

  return (
    <nav className="navbar navbar-expand-lg navbar-dark sticky-top shadow">
      <div className="container-fluid">
        <Link
          className="navbar-brand"
          to={
            userRole === "admin"
              ? "/admin/dashboard"
              : `/student/dashboard/${userId}`
          }
        >
          <i className="bi bi-book-half me-2"></i>
          <span>EduSync</span>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div
          className="collapse navbar-collapse justify-content-end"
          id="navbarNav"
        >
          <ul className="navbar-nav align-items-center">
            {userRole === "admin" && (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/admin/dashboard">
                    Dashboard
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/admin/courses">
                    Manage Courses
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/admin/add-course">
                    Add Course
                  </Link>
                </li>
              </>
            )}

            {userRole === "student" && (
              <>
                <li className="nav-item">
                  <Link
                    className="nav-link"
                    to={`/student/dashboard/${userId}`}
                  >
                    Dashboard
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to={`/student/courses/${userId}`}>
                    Browse Courses
                  </Link>
                </li>
                <li className="nav-item">
                  <Link
                    className="nav-link"
                    to={`/student/enrollments/${userId}`}
                  >
                    My Enrollments
                  </Link>
                </li>
              </>
            )}

            <li className="nav-item">
              <button
                onClick={toggleTheme}
                className="btn btn-link nav-link border-0 p-0 mx-3"
                aria-label="Toggle theme"
              >
                <i
                  className={`bi bi-${darkMode ? "sun" : "moon"}-fill fs-5`}
                ></i>
              </button>
            </li>

            <li className="nav-item">
              <button
                onClick={handleLogout}
                className="btn btn-danger btn-sm rounded px-4 text-light"
              >
                Logout
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default DashboardNavbar;

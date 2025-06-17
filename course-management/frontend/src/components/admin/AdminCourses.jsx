import React, { useState, useEffect } from "react";
import axios from "axios";
import DashboardNavbar from "../navbar/DashboardNavbar";
import { toast } from "react-toastify";
import { Link, useNavigate } from "react-router-dom";
import "./AdminCourses.css";

const AdminCourses = () => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const BASE_URL = import.meta.env.VITE_BASE_URL;
  const navigate = useNavigate();

  const fetchCourses = React.useCallback(async () => {
    try {
      setLoading(true);
      const token = localStorage.getItem("token");
      const response = await axios.get(`${BASE_URL}/api/courses`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setCourses(response.data);
    } catch (error) {
      console.error("Failed to fetch courses:", error);
      toast.error(error.response?.data?.message || "Failed to fetch courses");
    } finally {
      setLoading(false);
    }
  }, [BASE_URL]);

  useEffect(() => {
    fetchCourses();
  }, [fetchCourses]);

  if (loading) {
    return (
      <div className="admin-courses-page">
        <DashboardNavbar />
        <div className="container my-5 text-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="admin-courses-page">
      <DashboardNavbar />
      <div className="container my-5">
        <div className="d-flex justify-content-between align-items-center fit-content mb-4">
          <h2 className="page-title m-0">Course Management</h2>
          <Link
            to="/admin/add-course"
            className="btn btn-primary add-course-btn"
          >
            <i className="bi bi-plus-lg me-2"></i>Add New Course
          </Link>
        </div>

        {courses.length === 0 ? (
          <div className="text-center mt-5">
            <p className="text-muted">
              No courses available. Add your first course!
            </p>
          </div>
        ) : (
          <div className="table-responsive">
            <table className="table table-hover">
              <thead className="table-dark">
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Description</th>
                  <th>Credits</th>
                  <th>Type</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {courses.map((course) => (
                  <tr
                    key={course.courseId}
                    onClick={() =>
                      navigate(`/admin/courses/${course.courseId}`)
                    }
                    style={{ cursor: "pointer" }}
                    className="course-row"
                  >
                    <td>{course.courseId}</td>
                    <td>{course.courseName}</td>
                    <td>{course.courseDescription}</td>
                    <td>{course.courseCredits}</td>
                    <td>{course.courseType}</td>
                    <td onClick={(e) => e.stopPropagation()}>
                      <div className="btn-group">
                        <Link
                          to={`/admin/courses/${course.courseId}`}
                          className="btn btn-primary btn-sm"
                        >
                          View Details
                        </Link>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminCourses;

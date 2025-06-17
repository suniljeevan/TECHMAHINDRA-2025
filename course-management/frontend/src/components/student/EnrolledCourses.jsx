import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import DashboardNavbar from "../navbar/DashboardNavbar";
import "./EnrolledCourses.css";

const EnrolledCourses = () => {
  const { id } = useParams();
  const [enrolledCourses, setEnrolledCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const BASE_URL = import.meta.env.VITE_BASE_URL;

  const fetchEnrolledCourses = React.useCallback(async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(
        `${BASE_URL}/enrollments/student/${id}`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setEnrolledCourses(response.data);
      setLoading(false);
    } catch {
      toast.error("Failed to fetch enrolled courses");
      setLoading(false);
    }
  }, [BASE_URL, id]);

  useEffect(() => {
    fetchEnrolledCourses();
  }, [fetchEnrolledCourses]);

  if (loading) {
    return (
      <div className="enrolled-courses-page">
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
    <div className="enrolled-courses-page">
      <DashboardNavbar />
      <div className="container my-5">
        <h2 className="page-title mb-4">My Enrolled Courses</h2>

        {enrolledCourses.length === 0 ? (
          <div className="text-center mt-5">
            <p className="text-muted">
              You haven't enrolled in any courses yet.
            </p>
            <Link
              to={`/student/courses/${id}`}
              className="btn btn-primary mt-3"
            >
              Browse Courses
            </Link>
          </div>
        ) : (
          <div className="row g-4">
            {enrolledCourses.map((course) => (
              <div key={course.courseId} className="col-md-6 col-lg-4">
                <Link
                  to={`/student/enrollments/${id}/course/${course.courseId}`}
                  className="text-decoration-none"
                >
                  <div className="course-card">
                    <div className="course-type-badge">{course.courseType}</div>
                    <h3 className="course-title">{course.courseName}</h3>
                    <p className="course-description">
                      {course.courseDescription}
                    </p>
                    <div className="course-footer">
                      <span className="course-credits">
                        <i className="bi bi-star-fill me-2"></i>
                        {course.courseCredits} Credits
                      </span>
                    </div>
                  </div>
                </Link>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default EnrolledCourses;

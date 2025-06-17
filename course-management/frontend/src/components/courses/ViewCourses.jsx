import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "./ViewCourses.css";
import DashboardNavbar from "../navbar/DashboardNavbar";
import Navbar from "../navbar/Navbar";
import "bootstrap-icons/font/bootstrap-icons.css";

const ViewCourses = () => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [enrolling, setEnrolling] = useState(null);
  const { id } = useParams();
  const BASE_URL = import.meta.env.VITE_BASE_URL;
  const navigate = useNavigate();
  const isAuthenticated = !!localStorage.getItem("token");

  const fetchCourses = useCallback(async () => {
    try {
      setLoading(true);
      const response = await axios.get(`${BASE_URL}/api/courses`);
      setCourses(response.data);
    } catch (err) {
      toast.error(
        "Failed to fetch courses: " +
          (err.response?.data?.message || err.message)
      );
    } finally {
      setLoading(false);
    }
  }, [BASE_URL]);

  useEffect(() => {
    fetchCourses();
  }, [fetchCourses]);

  const handleEnroll = async (courseId) => {
    if (!isAuthenticated) {
      // Store the intended course ID in sessionStorage before redirecting
      sessionStorage.setItem("intendedCourseId", courseId);
      toast.info("Please login to enroll in this course");
      navigate("/login");
      return;
    }

    try {
      const token = localStorage.getItem("token");

      // Check if the student is already enrolled in the course
      const response = await axios.get(
        `${BASE_URL}/enrollments/check?studentId=${id}&courseId=${courseId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.data.isEnrolled) {
        // If already enrolled, notify the user
        toast.info("You are already enrolled in this course");
        return; // Stop further enrollment logic
      }

      // If not enrolled, proceed with enrollment
      setEnrolling(courseId);
      await axios.post(
        `${BASE_URL}/enrollments/enroll?studentId=${id}&courseId=${courseId}`,
        null,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      toast.success("Successfully enrolled in the course!");
      // After successful enrollment, redirect to student dashboard
      if (id) {
        navigate(`/student/dashboard/${id}`);
      }
    } catch (error) {
      toast.error(
        error.response?.data?.message || "Failed to enroll in the course"
      );
    } finally {
      setEnrolling(null);
    }
  };

  if (loading) {
    return (
      <div className="courses-page">
        {isAuthenticated ? <DashboardNavbar /> : <Navbar />}
        <div className="container my-5 text-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="courses-page section-padding">
      {isAuthenticated ? <DashboardNavbar /> : <Navbar />}
      <div className="container my-5">
        <h2 className="text-center page-title">Available Courses</h2>
        {courses.length === 0 ? (
          <div className="text-center mt-5">
            <p className="text-muted">No courses available at the moment.</p>
          </div>
        ) : (
          <div className="row g-4">
            {courses.map((course) => (
              <div key={course.courseId} className="col-md-6 col-lg-4">
                <div className="card course-card h-100">
                  <div className="card-body d-flex flex-column fit-content">
                    <div className="d-flex mb-2 fit-content">
                      <div className="course-type-badge">
                        {course.courseType}
                      </div>
                    </div>
                    <h5 className="card-title text-center mb-3">
                      {course.courseName}
                    </h5>
                    <p className="card-text justify">{course.courseDescription}</p>
                    <div className="mt-auto">
                      <div className="course-info">
                        <span className="credits">
                          <i className="bi bi-star-fill me-2"></i>
                          {course.courseCredits} Credits
                        </span>
                        <span className="course-id">
                          <i className="bi bi-hash me-2"></i>
                          {course.courseId}
                        </span>
                      </div>
                      <button
                        className="btn btn-primary enroll-btn w-100"
                        onClick={() => handleEnroll(course.courseId)}
                        disabled={enrolling === course.courseId}
                      >
                        {enrolling === course.courseId ? (
                          <>
                            <span
                              className="spinner-border spinner-border-sm me-2"
                              role="status"
                              aria-hidden="true"
                            ></span>
                            Enrolling...
                          </>
                        ) : (
                          "Enroll Now"
                        )}
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default ViewCourses;

import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import DashboardNavbar from "../navbar/DashboardNavbar";
import "./EnrolledCourseDetails.css";

const EnrolledCourseDetails = () => {
  const { id, courseId } = useParams();
  const [course, setCourse] = useState(null);
  const [loading, setLoading] = useState(true);
  const [unenrolling, setUnenrolling] = useState(false);
  const navigate = useNavigate();
  const BASE_URL = import.meta.env.VITE_BASE_URL;

  const fetchCourseDetails = React.useCallback(async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(`${BASE_URL}/api/courses/${courseId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setCourse(response.data);
      setLoading(false);
    } catch (error) {
      toast.error("Failed to fetch course details");
      console.error("Error fetching course details:", error);
      setLoading(false);
    }
  }, [courseId, BASE_URL]);

  useEffect(() => {
    fetchCourseDetails();
  }, [fetchCourseDetails]);

  const handleUnenroll = async () => {
    if (
      !window.confirm("Are you sure you want to unenroll from this course?")
    ) {
      return;
    }

    try {
      setUnenrolling(true);
      const token = localStorage.getItem("token");
      await axios.delete(
        `${BASE_URL}/enrollments/unenroll?studentId=${id}&courseId=${courseId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      toast.success("Successfully unenrolled from the course");
      navigate(`/student/enrollments/${id}`);
    } catch (error) {
      toast.error(
        error.response?.data?.message || "Failed to unenroll from course"
      );
    } finally {
      setUnenrolling(false);
    }
  };

  if (loading) {
    return (
      <div className="course-details-page">
        <DashboardNavbar />
        <div className="container my-3 my-md-5 text-center">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="course-details-page d-flex flex-column min-vh-100">
      <DashboardNavbar />
      <div className="container my-3 my-md-5">
        <div className="row justify-content-center g-0">
          <div className="col-12 col-lg-10">
            <div className="course-details-card shadow">
              <div className="card-header bg-primary text-white d-flex justify-content-between align-items-center fit-content">
                <div className="pe-2">
                  <h2 className="m-0 fw-bold">{course.courseName}</h2>
                  <p className="m-0 mt-2 text-white-50">
                    Course ID: {course.courseId}
                  </p>
                </div>
                <button
                  className="btn btn-danger ms-2"
                  onClick={handleUnenroll}
                  disabled={unenrolling}
                >
                  {unenrolling ? (
                    <>
                      <span
                        className="spinner-border spinner-border-sm me-2"
                        role="status"
                        aria-hidden="true"
                      ></span>
                      <span className="d-none d-sm-inline">Unenrolling...</span>
                      <span className="d-inline d-sm-none">...</span>
                    </>
                  ) : (
                    <>
                      <i className="bi bi-box-arrow-left me-1 me-sm-2"></i>
                      <span className="d-none d-sm-inline">Unenroll</span>
                      <span className="d-inline d-sm-none">Exit</span>
                    </>
                  )}
                </button>
              </div>
              <div className="card-body">
                <div className="row g-3">
                  <div className="col-12 col-md-6">
                    <div className="course-info-card h-100 p-3 border rounded">
                      <h4 className="text-primary mb-3 fs-5">
                        <i className="bi bi-bookmark-fill me-2"></i>
                        Course Type
                      </h4>
                      <p className="fs-6 mb-0">{course.courseType}</p>
                    </div>
                  </div>
                  <div className="col-12 col-md-6">
                    <div className="course-info-card h-100 p-3 border rounded">
                      <h4 className="text-primary mb-3 fs-5">
                        <i className="bi bi-award-fill me-2"></i>
                        Credits
                      </h4>
                      <p className="fs-6 mb-0">{course.courseCredits}</p>
                    </div>
                  </div>
                </div>
                <div className="course-description mt-4">
                  <h4 className="text-primary mb-3 fs-5">
                    <i className="bi bi-info-circle-fill me-2"></i>
                    Description
                  </h4>
                  <div className="p-3 border rounded d-flex fit-content">
                    <p className="mb-0 fs-6">{course.courseDescription}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EnrolledCourseDetails;

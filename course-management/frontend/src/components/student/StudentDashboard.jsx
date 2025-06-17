import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import DashboardNavbar from "../navbar/DashboardNavbar";
import { FaGraduationCap, FaBook } from "react-icons/fa";
import "./StudentDashboard.css";

const StudentDashboard = () => {
  const { id } = useParams();
  const [enrolledCourses, setEnrolledCourses] = useState([]);
  const [studentInfo, setStudentInfo] = useState(null);
  const BASE_URL = import.meta.env.VITE_BASE_URL;

  const fetchStudentData = React.useCallback(async () => {
    try {
      const token = localStorage.getItem("token");
      if (!token) {
        toast.error("Token is missing");
        return;
      }

      const response = await axios.get(`${BASE_URL}/students/${id}`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      setStudentInfo(response.data);
    } catch (error) {
      toast.error("Failed to fetch student information");
      console.error("Error fetching student data:", error);
    }
  }, [id, BASE_URL]);

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
    } catch (error) {
      toast.error("Failed to fetch enrolled courses");
      console.error("Error fetching enrollments:", error);
    }
  }, [id, BASE_URL]);

  useEffect(() => {
    fetchStudentData();
    fetchEnrolledCourses();
  }, [fetchStudentData, fetchEnrolledCourses]);

  return (
    <div className="student-dashboard section-padding">
      <DashboardNavbar />
      <div className="container my-5">
        <div className="welcome-section mb-5">
          <h2 className="page-title">Welcome, {studentInfo?.firstName}!</h2>
          <p className="">Student ID: {id}</p>
        </div>

        <div className="row g-4">
          <div className="col-md-6">
            <Link
              to={`/student/courses/${id}`}
              className="text-decoration-none"
            >
              <div className="dashboard-card">
                <div className="card-icon">
                  <FaBook />
                </div>
                <h3>Browse Courses</h3>
                <p>Explore and enroll in available courses</p>
              </div>
            </Link>
          </div>

          <div className="col-md-6">
            <Link
              to={`/student/enrollments/${id}`}
              className="text-decoration-none"
            >
              <div className="dashboard-card">
                <div className="card-icon">
                  <FaGraduationCap />
                </div>
                <h3>My Enrollments</h3>
                <div className="enrolled-courses-summary">
                  <p className="enrollment-count">
                    {enrolledCourses.length}{" "}
                    {enrolledCourses.length === 1 ? "Course" : "Courses"}{" "}
                    Enrolled
                  </p>
                  <p className="">Click to view details</p>
                </div>
              </div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentDashboard;

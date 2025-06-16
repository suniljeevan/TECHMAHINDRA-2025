import React, { useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import DashboardNavbar from "../navbar/DashboardNavbar";
import "./AddCourse.css";

const AddCourse = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [courseData, setCourseData] = useState({
    courseId: "",
    courseName: "",
    courseDescription: "",
    courseCredits: "",
    courseType: "Theory", // Set default value to Theory
  });
  const [errors, setErrors] = useState({});

  const BASE_URL = import.meta.env.VITE_BASE_URL;

  const validateForm = () => {
    const newErrors = {};
    if (!courseData.courseName.trim()) {
      newErrors.courseName = "Course name is required";
    }
    if (!courseData.courseDescription.trim()) {
      newErrors.courseDescription = "Description is required";
    }
    if (!courseData.courseCredits) {
      newErrors.courseCredits = "Credits are required";
    } else if (courseData.courseCredits < 1 || courseData.courseCredits > 10) {
      newErrors.courseCredits = "Credits must be between 1 and 10";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCourseData((prev) => ({
      ...prev,
      [name]: value,
    }));
    // Clear error when user starts typing
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: undefined,
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      setLoading(true);
      const token = localStorage.getItem("token"); // Or sessionStorage

      await axios.post(
        `${BASE_URL}/api/courses`,
        {
          ...courseData,
          courseCredits: parseInt(courseData.courseCredits, 10),
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      toast.success("Course added successfully!");
      navigate("/admin/courses");
    } catch (error) {
      console.error("Failed to add course:", error);
      toast.error(error.response?.data?.message || "Failed to add course");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="add-course-page d-flex flex-column min-vh-100">
      <DashboardNavbar />
      <div className="container my-5">
        <div className="row justify-content-center">
          <div className="col-md-8 col-lg-6">
            <div className="card shadow">
              <div className="card-body">
                <h2 className="text-center mb-4">Add New Course</h2>
                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label htmlFor="courseId" className="form-label">
                      Course ID
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.name ? "is-invalid" : ""
                      }`}
                      id="courseId"
                      name="courseId"
                      value={courseData.courseId}
                      onChange={handleChange}
                      disabled={loading}
                    />
                    {errors.courseId && (
                      <div className="invalid-feedback">{errors.courseId}</div>
                    )}
                  </div>

                  <div className="mb-3">
                    <label htmlFor="courseName" className="form-label">
                      Course Name
                    </label>
                    <input
                      type="text"
                      className={`form-control ${
                        errors.name ? "is-invalid" : ""
                      }`}
                      id="courseName"
                      name="courseName"
                      value={courseData.courseName}
                      onChange={handleChange}
                      disabled={loading}
                    />
                    {errors.courseName && (
                      <div className="invalid-feedback">
                        {errors.courseName}
                      </div>
                    )}
                  </div>

                  <div className="mb-3">
                    <label htmlFor="courseDescription" className="form-label">
                      Description
                    </label>
                    <textarea
                      className={`form-control ${
                        errors.courseDescription ? "is-invalid" : ""
                      }`}
                      id="courseDescription"
                      name="courseDescription"
                      value={courseData.courseDescription}
                      onChange={handleChange}
                      rows="4"
                      disabled={loading}
                    />
                    {errors.courseDescription && (
                      <div className="invalid-feedback">
                        {errors.courseDescription}
                      </div>
                    )}
                  </div>

                  <div className="mb-3">
                    <label htmlFor="courseCredits" className="form-label">
                      Credits
                    </label>
                    <input
                      type="number"
                      className={`form-control ${
                        errors.courseCredits ? "is-invalid" : ""
                      }`}
                      id="courseCredits"
                      name="courseCredits"
                      value={courseData.courseCredits}
                      onChange={handleChange}
                      min="1"
                      max="10"
                      disabled={loading}
                    />
                    {errors.courseCredits && (
                      <div className="invalid-feedback">
                        {errors.courseCredits}
                      </div>
                    )}
                  </div>

                  <div className="mb-3">
                    <label htmlFor="courseType" className="form-label">
                      Course Type
                    </label>
                    <select
                      className="form-select form-control"
                      id="courseType"
                      name="courseType"
                      value={courseData.courseType}
                      onChange={handleChange}
                      required
                      disabled={loading}
                    >
                      <option value="Theory">Theory</option>
                      <option value="Lab">Lab</option>
                      <option value="Integrated">Integrated</option>
                    </select>
                  </div>

                  <div className="d-grid">
                    <button
                      type="submit"
                      className="btn btn-primary add-btn"
                      disabled={loading}
                    >
                      {loading ? (
                        <>
                          <span
                            className="spinner-border spinner-border-sm me-2"
                            role="status"
                            aria-hidden="true"
                          ></span>
                          Adding Course...
                        </>
                      ) : (
                        "Add Course"
                      )}
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddCourse;

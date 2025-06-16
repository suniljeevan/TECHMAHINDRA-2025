import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import { toast } from "react-toastify";
import DashboardNavbar from "../navbar/DashboardNavbar";
import "./CourseDetails.css";

const CourseDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [course, setCourse] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editingCourse, setEditingCourse] = useState(null);
  const [saving, setSaving] = useState(false);
  const [deleting, setDeleting] = useState(false);
  const BASE_URL = import.meta.env.VITE_BASE_URL;

  const fetchCourseDetails = React.useCallback(async () => {
    try {
      setLoading(true);
      const token = localStorage.getItem("token");
      const response = await axios.get(
        `${BASE_URL}/enrollments/courses/${id}/details`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setCourse(response.data);
    } catch (error) {
      console.error("Failed to fetch course details:", error);
      toast.error(
        error.response?.data?.message || "Failed to fetch course details"
      );
      navigate("/admin/courses");
    } finally {
      setLoading(false);
    }
  }, [id, navigate, BASE_URL]);

  useEffect(() => {
    fetchCourseDetails();
  }, [fetchCourseDetails]);

  const handleEdit = () => {
    setEditingCourse({ ...course });
  };

  const handleDelete = async () => {
    if (window.confirm("Are you sure you want to delete this course?")) {
      try {
        setDeleting(true);
        const token = localStorage.getItem("token");
        await axios.delete(`${BASE_URL}/api/courses/${id}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        toast.success("Course deleted successfully");
        navigate("/admin/courses");
      } catch (error) {
        console.error("Failed to delete course:", error);
        toast.error(error.response?.data?.message || "Failed to delete course");
      } finally {
        setDeleting(false);
      }
    }
  };

  const handleUpdate = async () => {
    try {
      setSaving(true);
      const token = localStorage.getItem("token");
      await axios.put(`${BASE_URL}/api/courses/${id}`, editingCourse, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Course updated successfully");
      setEditingCourse(null);
      fetchCourseDetails();
    } catch (error) {
      console.error("Failed to update course:", error);
      toast.error(error.response?.data?.message || "Failed to update course");
    } finally {
      setSaving(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEditingCourse((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleModalBackdropClick = (e) => {
    // Close modal only if clicking the backdrop (not the modal content)
    if (e.target.classList.contains("modal")) {
      setEditingCourse(null);
    }
  };

  if (loading) {
    return (
      <div className="course-details-page">
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
    <div className="course-details-page d-flex flex-column min-vh-100">
      <DashboardNavbar />
      <div className="container my-5 min-vh-100 pt-5">
        <div className="row justify-content-center">
          <div className="col-lg-10">
            <div className="course-details-card shadow">
              <div className="card-header bg-primary text-white d-flex justify-content-between align-items-center p-4 fit-content">
                <div>
                  <h2 className="m-0 fw-bold">{course.courseName}</h2>
                  <p className="m-0 mt-2 text-white-50">
                    Course ID: {course.courseId}
                  </p>
                </div>
                <div className="d-flex gap-2 fit-content justify-content-end">
                  <button className="btn btn-light" onClick={handleEdit}>
                    <i className="bi bi-pencil-fill me-2"></i>
                    Edit Course
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={handleDelete}
                    disabled={deleting}
                  >
                    {deleting ? (
                      <>
                        <span
                          className="spinner-border spinner-border-sm me-2"
                          role="status"
                          aria-hidden="true"
                        ></span>
                        Deleting...
                      </>
                    ) : (
                      <>
                        <i className="bi bi-trash-fill me-2"></i>
                        Delete Course
                      </>
                    )}
                  </button>
                </div>
              </div>
              <div className="card-body p-4">
                <div className="row">
                  <div className="col-md-6">
                    <div className="course-info-card mb-4 p-3 border rounded">
                      <h4 className="text-primary mb-3">
                        <i className="bi bi-bookmark-fill me-2"></i>
                        Course Type
                      </h4>
                      <p className="fs-5 mb-0">{course.courseType}</p>
                    </div>
                  </div>
                  <div className="col-md-6">
                    <div className="course-info-card mb-4 p-3 border rounded">
                      <h4 className="text-primary mb-3">
                        <i className="bi bi-award-fill me-2"></i>
                        Credits
                      </h4>
                      <p className="fs-5 mb-0">{course.courseCredits}</p>
                    </div>
                  </div>
                </div>
                <div className="course-info-section mt-4">
                  <h4 className="text-primary mb-3">
                    <i className="bi bi-info-circle-fill me-2"></i>
                    Description
                  </h4>
                  <div className="p-3 border rounded">
                    <p className="mb-0">{course.courseDescription}</p>
                  </div>
                </div>
                <div className="enrolled-students mt-4">
                  <h4 className="text-primary mb-3">
                    <i className="bi bi-people-fill me-2"></i>
                    Enrolled Students
                  </h4>
                  <div className="table-responsive">
                    <table className="table table-hover">
                      <thead className="">
                        <tr>
                          <th style={{ width: "5%", minWidth: "50px" }}>
                            S.No.
                          </th>
                          <th style={{ width: "30%", wordWrap: "break-word" }}>
                            Student ID
                          </th>
                          <th style={{ width: "30%", wordWrap: "break-word" }}>
                            Name
                          </th>
                          <th style={{ width: "35%", wordWrap: "break-word" }}>
                            Email
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        {course.enrolledStudents?.length > 0 ? (
                          course.enrolledStudents.map((student, index) => (
                            <tr key={student.id}>
                              <td style={{ width: "5%" }}>{index + 1}</td>
                              <td
                                style={{
                                  width: "30%",
                                  wordBreak: "break-word",
                                }}
                              >
                                {student.id}
                              </td>
                              <td
                                style={{
                                  width: "30%",
                                  wordBreak: "break-word",
                                }}
                              >
                                {student.firstName} {student.lastName}
                              </td>
                              <td
                                style={{
                                  width: "35%",
                                  wordBreak: "break-word",
                                }}
                              >
                                {student.email}
                              </td>
                            </tr>
                          ))
                        ) : (
                          <tr>
                            <td colSpan="4" className="text-center">
                              No students enrolled yet
                            </td>
                          </tr>
                        )}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Edit Course Modal */}
      {editingCourse && (
        <div
          className="modal fit-content"
          onClick={handleModalBackdropClick}
          style={{ display: "flex", backgroundColor: "rgba(0,0,0,0.5)" }}
        >
          <div className="modal-dialog modal-dialog-centered w-50">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Edit Course</h5>
                <button
                  type="button"
                  className="btn-close"
                  onClick={() => setEditingCourse(null)}
                ></button>
              </div>
              <div className="modal-body">
                <form>
                  <div className="mb-3">
                    <label htmlFor="courseName" className="form-label">
                      Course Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="courseName"
                      name="courseName"
                      value={editingCourse.courseName}
                      onChange={handleChange}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="courseType" className="form-label">
                      Course Type
                    </label>
                    <select
                      className="form-select form-control"
                      id="courseType"
                      name="courseType"
                      value={editingCourse.courseType}
                      onChange={handleChange}
                      required
                    >
                      <option value="Theory">Theory</option>
                      <option value="Lab">Lab</option>
                      <option value="Integrated">Integrated</option>
                    </select>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="courseCredits" className="form-label">
                      Credits
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="courseCredits"
                      name="courseCredits"
                      value={editingCourse.courseCredits}
                      onChange={handleChange}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="courseDescription" className="form-label">
                      Description
                    </label>
                    <textarea
                      className="form-control"
                      id="courseDescription"
                      name="courseDescription"
                      value={editingCourse.courseDescription}
                      onChange={handleChange}
                      rows="3"
                    ></textarea>
                  </div>
                </form>
              </div>
              <div className="modal-footer d-flex justify-content-end fit-content">
                <button
                  type="button"
                  className="btn btn-secondary"
                  onClick={() => setEditingCourse(null)}
                >
                  Cancel
                </button>
                <button
                  type="button"
                  className="btn btn-primary m-0"
                  style={{ width: "fit-content" }}
                  onClick={handleUpdate}
                  disabled={saving}
                >
                  {saving ? (
                    <>
                      <span
                        className="spinner-border spinner-border-sm me-2"
                        role="status"
                        aria-hidden="true"
                      ></span>
                      Saving...
                    </>
                  ) : (
                    "Save Changes"
                  )}
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CourseDetails;

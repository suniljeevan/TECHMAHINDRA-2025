import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Login.css";
import Navbar from "../navbar/Navbar";
import axios from "axios";

const Login = () => {
  const BASE_URL = import.meta.env.VITE_BASE_URL;
  const [user, setUser] = useState({
    email: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const res = await axios.post(`${BASE_URL}/api/auth/login`, user);

      localStorage.setItem("token", res.data.token);
      localStorage.setItem("role", res.data.role);
      localStorage.setItem("id", res.data.id);

      // Check if there was a course enrollment attempt
      const intendedCourseId = sessionStorage.getItem("intendedCourseId");

      if (intendedCourseId && res.data.role.toLowerCase() === "student") {
        // Clear the stored course ID
        sessionStorage.removeItem("intendedCourseId");
        // Redirect to enroll in the course
        try {
          const token = localStorage.getItem("token");
          // Check if already enrolled
          try {
            const checkResponse = await axios.get(
              `${BASE_URL}/enrollments/check?studentId=${res.data.id}&courseId=${intendedCourseId}`,
              {
                headers: {
                  Authorization: `Bearer ${token}`,
                },
              }
            );

            if (checkResponse.data.isEnrolled) {
              toast.info("You are already enrolled in this course");
            } else {
              await axios.post(
                `${BASE_URL}/enrollments/enroll?studentId=${res.data.id}&courseId=${intendedCourseId}`,
                {},
                {
                  headers: {
                    Authorization: `Bearer ${token}`,
                  },
                }
              );
              toast.success("Successfully enrolled in the course!");
            }
          } catch (error) {
            console.error("Enrollment check/enroll failed:", error);
            toast.error("Something went wrong while processing enrollment.");
          }

          navigate(`/student/dashboard/${res.data.id}`);
        } catch (error) {
          console.error("Enrollment failed:", error);
          toast.error("Failed to enroll in the course");
          navigate(`/student/dashboard/${res.data.id}`);
        }
      } else {
        // Normal login redirect
        if (res.data.role.toLowerCase() === "admin") {
          navigate("/admin/dashboard");
        } else if (res.data.role.toLowerCase() === "student") {
          navigate(`/student/dashboard/${res.data.id}`);
        } else {
          alert("Unknown role, cannot proceed.");
        }
      }
    } catch (error) {
      console.log(
        "Login Error",
        error.response?.data?.message || error.message
      );
      setError("Login failed! Please check your email and password.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <Navbar />
      <div className="d-flex justify-content-center align-items-center">
        <div className="row justify-content-center align-items-center w-100 fit-content">
          <div className="col-lg-6 col-md-8 col-12">
            <div className="card shadow-lg border-0 rounded-lg">
              <div className="card-body p-5">
                <h3 className="text-center mb-4">Welcome Back</h3>

                <form onSubmit={handleSubmit}>
                  {error && <div className="alert alert-danger">{error}</div>}

                  <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                      Email Address
                    </label>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      placeholder="Enter your email"
                      required
                      value={user.email}
                      onChange={(e) =>
                        setUser({ ...user, email: e.target.value })
                      }
                    />
                  </div>

                  <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                      Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="password"
                      placeholder="Enter your password"
                      required
                      value={user.password}
                      onChange={(e) =>
                        setUser({ ...user, password: e.target.value })
                      }
                    />
                  </div>

                  <div className="d-grid">
                    <button
                      type="submit"
                      className="btn btn-primary btn-lg"
                      disabled={loading}
                    >
                      {loading ? "Logging in..." : "Login"}
                    </button>
                  </div>

                  <p className="text-center mt-4">
                    Don't have an account?{" "}
                    <a
                      href="/register"
                      className="text-decoration-none text-primary"
                    >
                      Register here
                    </a>
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;

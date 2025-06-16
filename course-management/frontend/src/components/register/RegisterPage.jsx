import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "../navbar/Navbar";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./RegisterPage.css";

const RegisterPage = () => {
  const BASE_URL = import.meta.env.VITE_BASE_URL;
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    id: "",
    phone: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      toast.error("Passwords do not match.");
      return;
    }

    const submitData = {
      ...formData,
      phone: formData.phone.toString(),
    };

    try {
      const response = await axios.post(`${BASE_URL}/register`, submitData);

      // Save token, role, and id in localStorage
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("role", response.data.role);
      localStorage.setItem("id", response.data.user.id);
      localStorage.removeItem("intendedCourseId");

      toast.success("Registration successful! Redirecting...");

      // Redirect based on role
      if (response.data.role.toLowerCase() === "admin") {
        navigate("/admin/dashboard");
      } else if (response.data.role.toLowerCase() === "student") {
        navigate(`/student/dashboard/${response.data.user.id}`);
      } else {
        toast.error("Unknown role. Cannot redirect.");
      }
    } catch (error) {
      const errorMsg = error.response?.data;

      if (errorMsg === "User ID already registered! Please login.") {
        toast.error("User ID already registered! Redirecting to login...");
        setTimeout(() => {
          navigate("/login");
        }, 3000);
      } else if (
        errorMsg === "Email address already registered! Please login."
      ) {
        toast.error("Email already registered! Redirecting to login...");
        setTimeout(() => {
          navigate("/login");
        }, 3000);
      } else {
        toast.error(errorMsg || "Registration failed! Please try again.");
      }
    }
  };

  return (
    <div className="register-page d-flex justify-content-center align-items-center w-100">
      <Navbar />
      <div className="" style={{ height: "fit-content" }}>
        <div className="row justify-content-center align-items-center w-100 my-auto">
          <div className="col-lg-6 col-md-8 col-12 w-100">
            <div className="card shadow-lg border-0 rounded-lg">
              <div className="card-body p-5">
                <h4 className="text-center mb-4">Create Your Account</h4>
                <form onSubmit={handleSubmit}>
                  {/* Name Input Row */}
                  <div className="row mb-3">
                    <div className="col-md-6">
                      <label htmlFor="firstName" className="form-label">
                        First Name
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="firstName"
                        name="firstName"
                        value={formData.firstName}
                        onChange={handleChange}
                        placeholder="Enter your first name"
                        required
                      />
                    </div>
                    <div className="col-md-6">
                      <label htmlFor="lastName" className="form-label">
                        Last Name
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="lastName"
                        name="lastName"
                        value={formData.lastName}
                        onChange={handleChange}
                        placeholder="Enter your last name"
                        required
                      />
                    </div>
                  </div>

                  {/* ID Input */}
                  <div className="mb-3">
                    <label htmlFor="id" className="form-label">
                      ID
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="id"
                      name="id"
                      value={formData.id}
                      onChange={handleChange}
                      placeholder="Enter your ID"
                      required
                    />
                  </div>

                  {/* Phone Number Input */}
                  <div className="mb-3">
                    <label htmlFor="phone" className="form-label">
                      Phone Number
                    </label>
                    <input
                      type="tel" // Changed to tel type
                      className="form-control"
                      id="phone"
                      name="phone" // Fixed the name attribute to match state
                      value={formData.phone}
                      onChange={handleChange}
                      placeholder="Enter your phone number"
                      pattern="[0-9]{10}" // Added pattern for 10 digits
                      maxLength="10" // Added maxLength
                      required
                    />
                  </div>

                  {/* Email Input */}
                  <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                      Email Address
                    </label>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      name="email"
                      value={formData.email}
                      onChange={handleChange}
                      placeholder="Enter your email"
                      required
                    />
                  </div>

                  {/* Password Input */}
                  <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                      Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="password"
                      name="password"
                      value={formData.password}
                      onChange={handleChange}
                      placeholder="Create a password"
                      required
                    />
                  </div>

                  {/* Confirm Password Input */}
                  <div className="mb-3">
                    <label htmlFor="confirmPassword" className="form-label">
                      Confirm Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="confirmPassword"
                      name="confirmPassword"
                      value={formData.confirmPassword}
                      onChange={handleChange}
                      placeholder="Confirm your password"
                      required
                    />
                  </div>

                  {/* Register Button */}
                  <div className="d-grid">
                    <button type="submit" className="btn btn-primary btn-lg">
                      Register
                    </button>
                  </div>

                  {/* Link to Login */}
                  <p className="text-center mt-4">
                    Already have an account?{" "}
                    <a
                      href="/login"
                      className="text-decoration-none text-primary"
                    >
                      Login here
                    </a>
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* ToastContainer to render the notifications */}
      <ToastContainer position="top-center" autoClose={3000} />
    </div>
  );
};

export default RegisterPage;

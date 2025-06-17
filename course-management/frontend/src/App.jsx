import React, { useEffect } from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  useNavigate,
} from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import LandingPage from "./components/LandingPage";
import ViewCourses from "./components/courses/ViewCourses";
import RegisterPage from "./components/register/RegisterPage";
import Login from "./components/login/Login";
import AdminDashboard from "./components/admin/AdminDashboard";
import StudentDashboard from "./components/student/StudentDashboard";
import AdminCourses from "./components/admin/AdminCourses";
import AddCourse from "./components/admin/AddCourse";
import CourseDetails from "./components/admin/CourseDetails";
import EnrolledCourses from "./components/student/EnrolledCourses";
import EnrolledCourseDetails from "./components/student/EnrolledCourseDetails";
import ProtectedRoute from "./components/ProtectedRoute";
import AboutPage from "./components/about/AboutPage";
import { jwtDecode } from "jwt-decode";
import { ThemeProvider } from "./context/ThemeContext";

function AppWrapper() {
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    const user = JSON.parse(localStorage.getItem("user"));

    if (token) {
      try {
        const decoded = jwtDecode(token);
        const now = Date.now() / 1000;
        if (decoded.exp && decoded.exp > now) {
          if (user?.role === "admin") {
            navigate("/admin/dashboard");
          } else if (user?.role === "student") {
            navigate(`/student/dashboard/${user.id}`);
          }
        } else {
          localStorage.removeItem("token");
          localStorage.removeItem("user");
          navigate("/login");
        }
      } catch (error) {
        console.error("Invalid token", error);
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        navigate("/login");
      }
    }
  }, [navigate]);

  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/about" element={<AboutPage />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/courses" element={<ViewCourses />} />

      {/* Student Routes */}
      <Route
        path="/student/dashboard/:id"
        element={
          <ProtectedRoute>
            <StudentDashboard />
          </ProtectedRoute>
        }
      />
      <Route
        path="/student/courses/:id"
        element={
          <ProtectedRoute>
            <ViewCourses />
          </ProtectedRoute>
        }
      />
      <Route
        path="/student/enrollments/:id"
        element={
          <ProtectedRoute>
            <EnrolledCourses />
          </ProtectedRoute>
        }
      />
      <Route
        path="/student/enrollments/:id/course/:courseId"
        element={
          <ProtectedRoute>
            <EnrolledCourseDetails />
          </ProtectedRoute>
        }
      />

      {/* Admin Routes */}
      <Route
        path="/admin/dashboard"
        element={
          <ProtectedRoute>
            <AdminDashboard />
          </ProtectedRoute>
        }
      />
      <Route
        path="/admin/courses"
        element={
          <ProtectedRoute>
            <AdminCourses />
          </ProtectedRoute>
        }
      />
      <Route
        path="/admin/courses/:id"
        element={
          <ProtectedRoute>
            <CourseDetails />
          </ProtectedRoute>
        }
      />
      <Route
        path="/admin/add-course"
        element={
          <ProtectedRoute>
            <AddCourse />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}

function App() {
  return (
    <Router>
      <ThemeProvider>
        <AppWrapper />
        <ToastContainer
          position="top-right"
          autoClose={3000}
          hideProgressBar={false}
          newestOnTop
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="light"
        />
      </ThemeProvider>
    </Router>
  );
}

export default App;

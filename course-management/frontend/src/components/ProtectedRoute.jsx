import React from "react";
import { Navigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

const ProtectedRoute = ({ children }) => {
  const token = localStorage.getItem("token");

  if (!token) {
    return <Navigate to="/login" />;
  }

  try {
    const decoded = jwtDecode(token);
    const now = Date.now() / 1000;

    if (decoded.exp && decoded.exp < now) {
      // Token expired
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      return <Navigate to="/login" />;
    }
    // Token valid
    return children;
  } catch (error) {
    console.error("Invalid token", error);
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return <Navigate to="/login" />;
  }
};

export default ProtectedRoute;

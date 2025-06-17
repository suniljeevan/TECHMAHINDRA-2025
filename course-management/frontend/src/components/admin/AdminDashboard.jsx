import React from "react";
import { Link } from "react-router-dom";
import DashboardNavbar from "../navbar/DashboardNavbar";
import { FaBook, FaPlus } from "react-icons/fa";
import "./AdminDashboard.css";

const AdminDashboard = () => {
  return (
    <div className="admin-dashboard section-padding">
      <DashboardNavbar />
      <div className="container my-5">
        <h2 className="text-center page-title mb-5">Admin Dashboard</h2>

        <div className="row g-4">
          <div className="col-md-6">
            <Link to="/admin/courses" className="text-decoration-none">
              <div className="dashboard-card">
                <div className="card-icon">
                  <FaBook />
                </div>
                <h3>Manage Courses</h3>
                <p>View, edit, and delete existing courses</p>
              </div>
            </Link>
          </div>

          <div className="col-md-6">
            <Link to="/admin/add-course" className="text-decoration-none">
              <div className="dashboard-card">
                <div className="card-icon">
                  <FaPlus />
                </div>
                <h3>Add New Course</h3>
                <p>Create and publish new courses</p>
              </div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;

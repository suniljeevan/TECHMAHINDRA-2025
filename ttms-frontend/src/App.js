// src/App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginSign from "./Components/LoginSign/LoginSign";
import AdminDashboard from "./Components/AdminDashboard/AdminDashboard";
import Timetable from "./Components/Timetable/Timetable";
import Navbar from "./Components/Navbar/Navbar";
import AddSemester from "./Components/Semester/AddSemester";
import ViewSemester from "./Components/Semester/ViewSemester";
import AddCourse from "./Components/Course/AddCourse";
import ViewCourses from "./Components/Course/ViewCourses";
import AddSection from "./Components/Section/AddSection";
import ViewSections from "./Components/Section/ViewSections";
import RegisterFaculty from "./Components/Faculty/RegisterFaculty";
import ViewFaculty from "./Components/Faculty/ViewFaculty";
import AddStudent from "./Components/Student/AddStudent";
import ViewStudents from "./Components/Student/ViewStudents";
import FacultyDashboard from "./Components/FacultyDashboard/FacultyDashboard";
import TransferSectionFaculty from "./Components/Section/TransferSectionFaculty";
import NavbarFaculty from "./Components/Navbar/NavbarFaculty";
import DeactivateSectionFaculty from "./Components/Section/DeactivateSectionFaculty";
import AddStudentFaculty from "./Components/Student/AddStudentFaculty";
import ViewStudentsFaculty from "./Components/Student/ViewStudentsFaculty";
import NavbarStudent from "./Components/Navbar/NavbarStudent";
import ViewTimetableStudent from "./Components/Timetable/ViewTimetableStudent";
import ViewTimetableFaculty from "./Components/Timetable/ViewTimetableFaculty";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginSign />} />

        {/* Admin Section */}
        <Route
          path="/admin-dashboard"
          element={
            <Navbar>
              <AdminDashboard />
            </Navbar>
          }
        />
        <Route
          path="/faculty-dashboard"
          element={
            <Navbar>
              <FacultyDashboard />
            </Navbar>
          }
        />
        <Route
          path="/view-timetable"
          element={
            <Navbar>
              <Timetable />
            </Navbar>
          }
        />

        <Route
          path="/add-semester"
          element={
            <Navbar>
              <AddSemester />
            </Navbar>
          }
        />
        <Route
          path="/view-semester"
          element={
            <Navbar>
              <ViewSemester />
            </Navbar>
          }
        />
        <Route
          path="/add-course"
          element={
            <Navbar>
              <AddCourse />
            </Navbar>
          }
        />
        <Route
          path="/view-courses"
          element={
            <Navbar>
              <ViewCourses />
            </Navbar>
          }
        />
        <Route
          path="/add-section"
          element={
            <Navbar>
              <AddSection />
            </Navbar>
          }
        />
        <Route
          path="/view-sections"
          element={
            <Navbar>
              <ViewSections />
            </Navbar>
          }
        />
        <Route
          path="/register-faculty"
          element={
            <Navbar>
              <RegisterFaculty />
            </Navbar>
          }
        />
        <Route
          path="/view-faculty"
          element={
            <Navbar>
              <ViewFaculty />
            </Navbar>
          }
        />
        <Route
          path="/add-student"
          element={
            <Navbar>
              <AddStudent />
            </Navbar>
          }
        />
        <Route
          path="/view-students"
          element={
            <Navbar>
              <ViewStudents />
            </Navbar>
          }
        />

        {/* Faculty section */}
        <Route
          path="/add-timetable-faculty"
          element={
            <NavbarFaculty>
              <ViewTimetableFaculty />
            </NavbarFaculty>
          }
        />
        <Route
          path="/transfer-section-faculty"
          element={
            <NavbarFaculty>
              <TransferSectionFaculty />
            </NavbarFaculty>
          }
        />
        <Route
          path="/deactivate-section-faculty"
          element={
            <NavbarFaculty>
              <DeactivateSectionFaculty />
            </NavbarFaculty>
          }
        />
        <Route
          path="/add-student-faculty"
          element={
            <NavbarFaculty>
              <AddStudentFaculty />
            </NavbarFaculty>
          }
        />
        <Route
          path="/view-students-faculty"
          element={
            <NavbarFaculty>
              <ViewStudentsFaculty />
            </NavbarFaculty>
          }
        />

        {/* Student Section */}
        <Route
          path="/view-timetable-student"
          element={
            <NavbarStudent>
              <ViewTimetableStudent />
            </NavbarStudent>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
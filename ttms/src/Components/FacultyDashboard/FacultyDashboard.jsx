import React from "react";
import NavbarFaculty from "../Navbar/NavbarFaculty";
import { Typography, Box, Paper, Container } from "@mui/material";
import "./FacultyDashboard.css";

const FacultyDashboard = () => {
  return (
    <>
      <NavbarFaculty />
      <Container maxWidth="md" className="dashboard-container">
        <Paper className="dashboard-paper">
          <Typography
            variant="h4"
            color="primary"
            className="dashboard-title"
          >
            Effortless schedules, organized learning.
          </Typography>

          <Typography variant="body1" className="dashboard-description">
            Elevate your education journey with our Timetable Management System.
          </Typography>

          <Typography variant="h6" className="explore-text">
            🚀 Explore now!
          </Typography>

          <Box className="image-container">
            <img
              src="/loginTTMS.png"
              alt="Timetable illustration"
              className="dashboard-image"
            />
            <Box className="overlay"></Box>
          </Box>
        </Paper>
      </Container>
    </>
  );
};

export default FacultyDashboard;
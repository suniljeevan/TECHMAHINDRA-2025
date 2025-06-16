import React from "react";
import { Typography, Box, Paper, Container } from "@mui/material";

const AdminDashboard = () => {
  return (
    <Container maxWidth="md" sx={{ py: 8 }}>
      <Paper
        elevation={8}
        sx={{
          p: 4,
          borderRadius: "16px",
          background: "#ffffff",
          boxShadow: "0 15px 40px rgba(0, 0, 0, 0.1)",
          textAlign: "center",
          animation: "fadeInUp 1s ease-out",
        }}
      >
        <Typography
          variant="h4"
          color="primary"
          fontWeight={700}
          sx={{ mb: 2, letterSpacing: 1.2 }}
        >
          Effortless schedules, organized learning.
        </Typography>

        <Typography
          variant="body1"
          sx={{
            color: "#374151",
            fontSize: "1.125rem",
            lineHeight: 1.6,
            mb: 3,
            letterSpacing: 0.5,
          }}
        >
          Elevate your education journey with our Timetable Management System.
        </Typography>

        <Typography
          variant="h6"
          color="secondary"
          sx={{
            fontSize: "1.2rem",
            fontWeight: 600,
            letterSpacing: 0.8,
            mb: 3,
            opacity: 0.85,
            transition: "opacity 0.3s ease",
            "&:hover": {
              opacity: 1,
            },
          }}
        >
          🚀 Explore now!
        </Typography>

        <Box sx={{ mt: 4, position: "relative" }}>
          <img
            src="/loginTTMS.png"
            alt="Timetable illustration"
            style={{
              maxWidth: "100%",
              height: "auto",
              borderRadius: "12px",
              boxShadow: "0 10px 30px rgba(0, 0, 0, 0.1)",
              transition: "transform 0.3s ease, box-shadow 0.3s ease",
            }}
          />
          <Box
            sx={{
              position: "absolute",
              top: 0,
              left: 0,
              right: 0,
              bottom: 0,
              borderRadius: "12px",
              background: "rgba(0, 0, 0, 0.1)",
              opacity: 0,
              transition: "opacity 0.3s ease",
              "&:hover": {
                opacity: 0.2,
              },
            }}
          />
        </Box>
      </Paper>
    </Container>
  );
};

export default AdminDashboard;

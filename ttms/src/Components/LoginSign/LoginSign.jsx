import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { FormControl, InputLabel, MenuItem, Select, Snackbar, Alert } from "@mui/material";
import axios from "axios";
import "./LoginSign.css";

const LoginSign = () => {
  const [role, setRole] = useState("student");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    localStorage.setItem("userRole", role);

    if (role === "admin") {
      try {
        const response = await axios.post("http://localhost:8080/admin/login", {
          userRole: "Admin",
          adminEmail: email,
          adminPassword: password,
        });

        if (response.status === 200) {
          const token = response.data.split(" ")[1]; // Get the token from the response
          localStorage.setItem("jwtToken", token); // Store the JWT token in localStorage
          setSnackbarMessage("Login successful");
          setSnackbarSeverity("success");
          setOpenSnackbar(true);
          setTimeout(() => {
            navigate("/admin-dashboard");
          }, 1000);
        }
      } catch (error) {
        setSnackbarMessage("Invalid credentials");
        setSnackbarSeverity("error");
        setOpenSnackbar(true);
      }
    } else if (role === "faculty") {
      try {
        const response = await axios.post("http://localhost:8080/faculty/login", {
          userRole: "Faculty",
          facultyEmail: email,
          facultyPassword: password,
        });

        if (response.status === 200) {
          const token = response.data.split(" ")[1]; // Get the token from the response
          localStorage.setItem("jwtToken", token); // Store the JWT token in localStorage
          setSnackbarMessage("Login successful");
          setSnackbarSeverity("success");
          setOpenSnackbar(true);
          setTimeout(() => {
            navigate("/faculty-dashboard");
          }, 1000);
        }
      } catch (error) {
        setSnackbarMessage("Invalid credentials");
        setSnackbarSeverity("error");
        setOpenSnackbar(true);
      }
    } else if (role === "student") {
      try {
        const response = await axios.post("http://localhost:8080/student/login", {
          userRole: "Student",
          studentEmail: email,
          studentPassword: password,
        });

        if (response.status === 200) {
          const token = response.data.split(" ")[1]; // Get the token from the response
          localStorage.setItem("jwtToken", token); // Store the JWT token in localStorage
          setSnackbarMessage("Login successful");
          setSnackbarSeverity("success");
          setOpenSnackbar(true);
          setTimeout(() => {
            navigate("/view-timetable-student");
          }, 1000);
        }
      } catch (error) {
        setSnackbarMessage("Invalid credentials");
        setSnackbarSeverity("error");
        setOpenSnackbar(true);
      }
    }
  };

  return (
    <div className="login-container">
      <Snackbar
        open={openSnackbar}
        autoHideDuration={10000}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: "bottom", horizontal: 'center' }}
      >
        <Alert onClose={() => setOpenSnackbar(false)} severity={snackbarSeverity} sx={{ width: "100%" }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>

      <div className="login-left">
        <img src="/loginTTMS.png" alt="Timetable illustration" className="full-illustration" />
      </div>

      <div className="login-right">
        <div className="form-box">
          <h1 className="title">Timetable Management System</h1>
          <p className="subtitle">Effortless planning for academic excellence</p>

          <h2>Login</h2>
          <form onSubmit={handleSubmit}>
            <FormControl fullWidth style={{ marginBottom: "18px", borderRadius: '12px' }}>
              <InputLabel>User Role</InputLabel>
              <Select
                value={role}
                label="User Role"
                onChange={(e) => setRole(e.target.value)}
                required
              >
                <MenuItem value="student">Student</MenuItem>
                <MenuItem value="faculty">Faculty</MenuItem>
                <MenuItem value="admin">Admin</MenuItem>
              </Select>
            </FormControl>

            <input type="email" placeholder="Enter Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
            <input type="password" placeholder="Enter Password" value={password} onChange={(e) => setPassword(e.target.value)} required />

            <button type="submit">Login</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default LoginSign;
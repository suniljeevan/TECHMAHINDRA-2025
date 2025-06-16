import React, { useState } from "react";
import {
  Container,
  TextField,
  Typography,
  Button,
  Box,
  Paper,
  Grid,
  Snackbar,
  Alert,
} from "@mui/material";
import axios from "axios";
import "./RegisterFaculty.css";
import {isValidEmail,isValidContact} from "../InputValidation/InputValidation"

const RegisterTeacher = () => {
  const [formData, setFormData] = useState({
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    contactNo: "",
    address: "",
  });

  const [snackbar, setSnackbar] = useState({
    open: false,
    message: "",
    severity: "success",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSnackbarClose = () => {
    setSnackbar({ ...snackbar, open: false });
  };

  const handleRegister = async () => {
    const payload = {
      userRole: "Faculty",
      facultyEmail: formData.email,
      facultyPassword: formData.password,
      facultyFirstName: formData.firstname,
      facultyLastName: formData.lastname,
      facultyContact: formData.contactNo,
      facultyAddress: formData.address,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/faculty/register",
        payload
      );
      setSnackbar({
        open: true,
        message: response.data || "Faculty registered successfully!",
        severity: "success",
      });
      setFormData({
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        contactNo: "",
        address: "",
      });
    } catch (error) {
      console.error("Error while registering faculty:", error);
      setSnackbar({
        open: true,
        message: "Failed to register faculty",
        severity: "error",
      });
    }
  };

  const isFormValid =
    formData.firstname !== "" &&
    formData.lastname !== "" &&
    isValidEmail(formData.email) &&
    formData.password !== "" &&
    isValidContact(formData.contactNo) &&
    formData.address !== "";


  return (
    <>
      <Container maxWidth="sm" className="register-teacher-container">
        <Paper elevation={6} className="register-teacher-paper">
          <Typography variant="h4" className="form-title">
            🎓 Register New Faculty
          </Typography>
          <Typography variant="subtitle1" className="form-subtitle">
            Fill in the details to register a new faculty
          </Typography>
          <form noValidate autoComplete="off">
            {/* First Name and Last Name */}
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  required
                  label="First Name"
                  variant="outlined"
                  margin="normal"
                  name="firstname"
                  value={formData.firstname}
                  onChange={handleChange}
                  className="input-field"
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  required
                  label="Last Name"
                  variant="outlined"
                  margin="normal"
                  name="lastname"
                  value={formData.lastname}
                  onChange={handleChange}
                  className="input-field"
                />
              </Grid>
            </Grid>

            {/* Email and Password */}
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                required
                type="email"
                label="Email ID"
                variant="outlined"
                margin="normal"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="input-field"
                error={formData.email !== "" && !isValidEmail(formData.email)}
                helperText={
                  formData.email !== "" && !isValidEmail(formData.email)
                    ? "Enter a valid email address"
                    : ""
                }
              />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Password"
                  variant="outlined"
                  margin="normal"
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  className="input-field"
                />
              </Grid>
            </Grid>

            {/* Contact No and Address */}
            <Grid container spacing={3}>
              <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="Contact No"
                variant="outlined"
                margin="normal"
                name="contactNo"
                value={formData.contactNo}
                onChange={handleChange}
                className="input-field"
                error={formData.contactNo !== "" && !isValidContact(formData.contactNo)}
                helperText={
                  formData.contactNo !== "" && !isValidContact(formData.contactNo)
                    ? "Contact number must be 10 digits"
                    : ""
                }
              />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  fullWidth
                  label="Address"
                  variant="outlined"
                  margin="normal"
                  name="address"
                  value={formData.address}
                  onChange={handleChange}
                  className="input-field"
                />
              </Grid>
            </Grid>

            <Box mt={3} textAlign="center">
              <Button
                variant="contained"
                color="primary"
                onClick={handleRegister}
                className="register-btn"
                disabled={!isFormValid}
              >
                Register Faculty
              </Button>
            </Box>
          </form>
        </Paper>
      </Container>

      {/* Snackbar */}
      <Snackbar
        open={snackbar.open}
        autoHideDuration={3000}
        onClose={handleSnackbarClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert onClose={handleSnackbarClose} severity={snackbar.severity} sx={{ width: "100%" }}>
          {snackbar.message}
        </Alert>
      </Snackbar>
    </>
  );
};

export default RegisterTeacher;
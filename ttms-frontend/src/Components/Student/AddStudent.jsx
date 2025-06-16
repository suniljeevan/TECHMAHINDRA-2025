import React, { useState, useEffect } from "react";
import axios from "axios";
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
  Select,
  MenuItem,
  InputLabel,
  FormControl,
  TableRow,
  TableCell
} from "@mui/material";
import "./AddStudent.css";
import {isValidEmail,isValidContact} from "../InputValidation/InputValidation"

const AddStudent = () => {
  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [contact, setContact] = useState("");
  const [address, setAddress] = useState("");
  const [semester, setSemester] = useState("");
  const [section, setSection] = useState("");
  const [semesters, setSemesters] = useState([]);
  const [sections, setSections] = useState([]);

  // Snackbar state
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  // Fetch Semesters and Sections
  useEffect(() => {
    const fetchData = async () => {
      try {
        const semesterResponse = await axios.get("http://localhost:8080/semester/all-semesters");
        setSemesters(semesterResponse.data);
        
        const sectionResponse = await axios.get("http://localhost:8080/section/all-sections");
        setSections(sectionResponse.data);
      } catch (error) {
        console.error("Error fetching data:", error);
        showSnackbar("Failed to fetch data.", "error");
      }
    };

    fetchData();
  }, []);

  // Handle student registration
  const handleAddRegisterStudent = async () => {
    // Ensure that semester and section are selected
    if (!semester || !section) {
      showSnackbar("Please select both semester and section.", "error");
      return;
    }

    const newStudent = {
      studentFirstName: firstname,
      studentLastName: lastname,
      studentEmail: email,
      studentPassword: password,
      studentContactNo: contact,
      studentAddress: address,
      userRole: "Student",
      studentSemester: semester,
      studentSection: section,
    };

    try {
      const response = await axios.post("http://localhost:8080/student/register", newStudent);
      console.log("Student added successfully:", response.data);
      showSnackbar("Student added successfully!", "success");
      
      // Clear form after successful registration
      setFirstName("");
      setLastName("");
      setEmail("");
      setPassword("");
      setContact("");
      setAddress("");
      setSemester("");
      setSection("");
    } catch (error) {
      console.error("Error adding student:", error);
      showSnackbar("Failed to add student.", "error");
    }
  };

  const showSnackbar = (message, severity = "success") => {
    setSnackbarMsg(message);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  const handleSnackbarClose = () => {
    setSnackbarOpen(false);
  };

  const isFormValid = 
    firstname !== "" && lastname !== "" &&
    isValidEmail(email) && password !== "" &&
    isValidContact(contact) && address !== "" &&
    semester!== "" && section!== "";

  return (
    <Container maxWidth="sm" className="register-student-container">
      <Paper elevation={6} className="register-student-paper">
        <Typography variant="h4" className="form-title">
          🎓 Register New Student
        </Typography>
        <Typography variant="subtitle1" className="form-subtitle">
          Fill in the details to register a new student
        </Typography>

        <form
          noValidate
          autoComplete="off"
          onSubmit={(e) => {
            e.preventDefault();
            handleAddRegisterStudent();
          }}
        >
          {/* First Name and Last Name */}
          <Grid container spacing={3}>
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="First Name"
                variant="outlined"
                margin="normal"
                name="firstname"
                value={firstname}
                onChange={(e) => setFirstName(e.target.value)}
                className="input-field"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                fullWidth
                label="Last Name"
                variant="outlined"
                margin="normal"
                name="lastname"
                value={lastname}
                onChange={(e) => setLastName(e.target.value)}
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
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input-field"
              error={email !== "" && !isValidEmail(email)}
              helperText={
                email !== "" && !isValidEmail(email)
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
                value={password}
                onChange={(e) => setPassword(e.target.value)}
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
              value={contact}
              onChange={(e) => setContact(e.target.value)}
              className="input-field"
              error={contact !== "" && !isValidContact(contact)}
              helperText={
                contact !== "" && !isValidContact(contact)
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
                name="studentAddress"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                className="input-field"
              />
            </Grid>
          </Grid>

          {/* Semester and Section */}
          <FormControl fullWidth margin="normal">
            <InputLabel id="semester-label">Select Semester</InputLabel>
            <Select
              labelId="semester-label"
              value={semester}
              onChange={(e) => setSemester(e.target.value)}
              label="Select Semester"
              className="input-field"
            >
              {semesters.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={10} align="center">
                    <Typography variant="h6" color="textSecondary">
                      No Semester's available
                    </Typography>
                  </TableCell>
                </TableRow>
              ) : (
                semesters.map((sem, index) => (
                  <MenuItem key={index} value={sem.semName}>
                    {sem.semName} {/* Render the semName here */}
                  </MenuItem>
                ))
              )}
            </Select>
          </FormControl>

          <FormControl fullWidth margin="normal">
            <InputLabel id="section-label">Select Section</InputLabel>
            <Select
              labelId="section-label"
              value={section}
              onChange={(e) => setSection(e.target.value)}
              label="Select Section"
            >
              {sections.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={10} align="center">
                    <Typography variant="h6" color="textSecondary">
                      No Section's available
                    </Typography>
                  </TableCell>
                </TableRow>
              ) : (
                sections.map((sec, index) => (
                  <MenuItem key={index} value={sec.sectionName}>
                    {sec.sectionName}
                  </MenuItem>
                ))
                )}
            </Select>
          </FormControl>

          <Box mt={3} textAlign="center">
            <Button
              variant="contained"
              color="primary"
              type="submit"
              className="register-btn"
              disabled={!isFormValid}
            >
              Register Student
            </Button>
          </Box>
        </form>
      </Paper>

      {/* Snackbar */}
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={3000}
        onClose={handleSnackbarClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert
          severity={snackbarSeverity}
          onClose={handleSnackbarClose}
          sx={{ width: "100%" }}
        >
          {snackbarMsg}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default AddStudent;
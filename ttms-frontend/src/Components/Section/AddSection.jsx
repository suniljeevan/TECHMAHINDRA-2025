import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Container,
  TextField,
  Typography,
  Button,
  Box,
  Paper,
  MenuItem,
  Select,
  InputLabel,
  FormControl,
  Snackbar,
  Alert,
  TableRow,
  TableCell
} from "@mui/material";
import "./AddSection.css";

const AddSection = () => {
  const [section, setSection] = useState("");
  const [description, setDescription] = useState("");
  const [semester, setSemester] = useState("");
  const [semesters, setSemesters] = useState([]);

  // Snackbar state
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  const [faculty, setFaculty] = useState("");
  const [faculties, setfaculties] = useState([]);

  // Fetch semesters for dropdown
  useEffect(() => {
    const fetchSemesters = async () => {
      try {
        const response = await axios.get("http://localhost:8080/semester/all-semesters");
        const semesterNames = response.data.map((sem) => sem.semName);
        setSemesters(semesterNames);
      } catch (error) {
        console.error("Error fetching semesters:", error);
        showSnackbar("Failed to fetch semesters.", "error");
      }
    };

    const fetchFaculty = async () => {
      try {
        const response = await axios.get("http://localhost:8080/faculty/all-faculty");
        const facultyNames = response.data.map((faculty) => faculty.facultyFirstName);
        setfaculties(facultyNames);
      } catch (error) {
        console.error("Error fetching faculty:", error);
        showSnackbar("Failed to fetch faculty.", "error");
      }
    };

    fetchSemesters();
    fetchFaculty();
  }, []);

  // Handle POST API for adding section
  const handleAddSection = async () => {
    const newSection = {
      sectionName: section,
      sectionDescription: description,
      semName: semester,
      facultyFirstName: faculty,
    };

    try {
      const response = await axios.post("http://localhost:8080/section/create-section", newSection);
      console.log("Section added successfully:", response.data);
      showSnackbar("Section added successfully!", "success");
      setSection("");
      setDescription("");
      setSemester("");
      setFaculty("");
    } catch (error) {
      console.error("Error adding section:", error);
      showSnackbar("Failed to add section.", "error");
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

  // ✅ Form validation — returns true if both fields are filled
  const isFormValid = section !== "" && description!== "" && semester!== "" && faculty!== "";

  return (
    <Container maxWidth="sm" className="add-section-container">
      <Paper elevation={6} className="add-section-paper">
        <Typography variant="h4" className="form-title">
          🎓 Add New Section
        </Typography>
        <Typography variant="subtitle1" className="form-subtitle">
          Plan your academic Section with ease!
        </Typography>
        <form
          noValidate
          autoComplete="off"
          onSubmit={(e) => {
            e.preventDefault();
            handleAddSection();
          }}
        >
          <TextField
            fullWidth
            label="Enter Section"
            variant="outlined"
            margin="normal"
            value={section}
            onChange={(e) => setSection(e.target.value)}
            className="input-field"
          />
          <TextField
            fullWidth
            label="Enter No. of Students"
            variant="outlined"
            margin="normal"
            multiline
            rows={4}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="input-field"
          />

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
                semesters.map((semName, index) => (
                  <MenuItem key={index} value={semName}>
                    {semName}
                  </MenuItem>
                ))
              )}
            </Select>
          </FormControl>

          <FormControl fullWidth margin="normal">
            <InputLabel id="faculty-label">Select Faculty</InputLabel>
            <Select
              labelId="faculty-label"
              value={faculty}
              onChange={(e) => setFaculty(e.target.value)}
              label="Select Faculty"
              className="input-field"
            >
              {faculties.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={10} align="center">
                    <Typography variant="h6" color="textSecondary">
                      No Faculty's available
                    </Typography>
                  </TableCell>
                </TableRow>
              ) : (
                faculties.map((facultyFirstName, index) => (
                  <MenuItem key={index} value={facultyFirstName}>
                    {facultyFirstName}
                  </MenuItem>
                ))
              )}
            </Select>
          </FormControl>

          <Box mt={3} textAlign="center">
            <Button
              variant="contained"
              color="secondary"
              type="submit"
              className="add-btn"
              disabled={!isFormValid}
            >
              Add Section
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

export default AddSection;
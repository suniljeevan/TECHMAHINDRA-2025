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
import "./AddCourse.css";

const AddCourse = () => {
  const [course, setCourse] = useState("");
  const [description, setDescription] = useState("");
  const [semester, setSemester] = useState("");
  const [semesters, setSemesters] = useState([]);

  // Snackbar state
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

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

    fetchSemesters();
  }, []);

  const handleAddCourse = async () => {
    const newCourse = {
      courseName: course,
      courseDescription: description,
      semName: semester,
    };

    try {
      const response = await axios.post("http://localhost:8080/course/create-course", newCourse);
      console.log("Course added successfully:", response.data);
      showSnackbar("Course added successfully!", "success");
      setCourse("");
      setDescription("");
      setSemester("");
    } catch (error) {
      console.error("Error adding course:", error);
      showSnackbar("Failed to add course.", "error");
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
  const isFormValid = course !== "" && description!== "" && semester!== "";

  return (
    <Container maxWidth="sm" className="add-course-container">
      <Paper elevation={6} className="add-course-paper">
        <Typography variant="h4" className="form-title">
          🎓 Add New Course
        </Typography>
        <Typography variant="subtitle1" className="form-subtitle">
          Plan your academic Course with ease!
        </Typography>
        <form
          noValidate
          autoComplete="off"
          onSubmit={(e) => {
            e.preventDefault();
            handleAddCourse();
          }}
        >
          <TextField
            fullWidth
            label="Enter Course"
            variant="outlined"
            margin="normal"
            value={course}
            onChange={(e) => setCourse(e.target.value)}
            className="input-field"
          />
          <TextField
            fullWidth
            label="Enter Course Description"
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
                      No Semester's present
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

          <Box mt={3} textAlign="center">
            <Button
              variant="contained"
              color="secondary"
              type="submit"
              className="add-btn"
              disabled={!isFormValid}
            >
              Add Course
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
        <Alert severity={snackbarSeverity} onClose={handleSnackbarClose} sx={{ width: "100%" }}>
          {snackbarMsg}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default AddCourse;
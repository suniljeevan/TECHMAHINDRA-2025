import React, { useEffect, useState } from "react";
import {
  Container,
  Paper,
  Typography,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Button,
  Box,
  Modal,
  TextField,
  Snackbar,
  Alert,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import axios from "axios";
import "./ViewCourses.css";

const ViewCourses = () => {
  const [courses, setCourses] = useState([]);
  const [semNames, setSemNames] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState({
    cid: "",
    courseName: "",
    courseDescription: "",
    semName: "",
  });
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  useEffect(() => {
    fetchCourses();
    fetchSemesters();
  }, []);

  const fetchCourses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/course/all-courses");
      setCourses(response.data);
    } catch (error) {
      console.error("Error fetching courses:", error);
    }
  };

  const fetchSemesters = async () => {
    try {
      const response = await axios.get("http://localhost:8080/semester/all-semesters");
      setSemNames(response.data.map((sem) => sem.semName));
    } catch (error) {
      console.error("Error fetching semesters:", error);
    }
  };

  const handleOpenModal = (course) => {
    setSelectedCourse(course);
    setOpen(true);
  };

  const handleCloseModal = () => setOpen(false);

  const handleChange = (e) => {
    setSelectedCourse({ ...selectedCourse, [e.target.name]: e.target.value });
  };

  const handleUpdate = async () => {
    if (!semNames.includes(selectedCourse.semName)) {
      showSnackbar("Invalid semester name.", "error");
      return;
    }
    try {
      await axios.put("http://localhost:8080/course/update-course", selectedCourse);
      fetchCourses();
      setOpen(false);
      showSnackbar("Course updated successfully!", "success");
    } catch (error) {
      console.error("Error updating course:", error);
      showSnackbar("Failed to update course.", "error");
    }
  };

  const handleDelete = async (cid) => {
    try {
      await axios.delete(`http://localhost:8080/course/delete/${cid}`);
      fetchCourses();
      showSnackbar("Course deleted successfully!", "success");
    } catch (error) {
      console.error("Error deleting course:", error);
      showSnackbar("Failed to delete course.", "error");
    }
  };

  const showSnackbar = (msg, severity = "success") => {
    setSnackbarMsg(msg);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  return (
    <div className="view-courses-wrapper">
      <Container maxWidth="md" className="course-container">
        <Paper className="course-paper" elevation={4}>
          <Box textAlign="center" mb={3}>
            <Typography variant="h4" className="course-title">
              📚 All Course's
            </Typography>
            <Typography variant="subtitle1" className="course-subtitle">
              Manage, edit, and review available course offerings.
            </Typography>
          </Box>

          <Box sx={{ overflowX: "auto" }}>
            <Table className="course-table">
              <TableHead>
                <TableRow className="course-header">
                  <TableCell>ID</TableCell>
                  <TableCell>Name</TableCell>
                  <TableCell>Description</TableCell>
                  <TableCell>Semester</TableCell>
                  <TableCell align="center">Actions</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {courses.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={5} align="center">
                      <Typography variant="h6" color="textSecondary">
                        No courses available
                      </Typography>
                    </TableCell>
                  </TableRow>
                ) : (
                  courses.map((course, index) => (
                    <TableRow
                      key={course.cid}
                      className={index % 2 === 0 ? "course-row" : "course-row-alt"}
                    >
                      <TableCell>{index + 1}</TableCell>
                      <TableCell>{course.courseName}</TableCell>
                      <TableCell>{course.courseDescription}</TableCell>
                      <TableCell>{course.semName}</TableCell>
                      <TableCell align="center">
                        <Box display="flex" justifyContent="center" gap={1}>
                          <Button
                            size="small"
                            startIcon={<EditIcon />}
                            className="action-btn update"
                            onClick={() => handleOpenModal(course)}
                          >
                            Update
                          </Button>
                          <Button
                            size="small"
                            startIcon={<DeleteIcon />}
                            className="action-btn delete"
                            onClick={() => handleDelete(course.cid)}
                          >
                            Delete
                          </Button>
                        </Box>
                      </TableCell>
                    </TableRow>
                  ))
                )}
              </TableBody>
            </Table>
          </Box>
        </Paper>
      </Container>

      <Modal open={open} onClose={handleCloseModal}>
        <Box className="modal-box">
          <Typography variant="h6" fontWeight="bold" mb={2}>
            Update Course
          </Typography>
          <TextField
            fullWidth
            label="Name"
            name="courseName"
            value={selectedCourse.courseName}
            onChange={handleChange}
            margin="normal"
          />
          <TextField
            fullWidth
            label="Description"
            name="courseDescription"
            value={selectedCourse.courseDescription}
            onChange={handleChange}
            margin="normal"
          />
          <TextField
            select
            fullWidth
            label="Semester"
            name="semName"
            value={selectedCourse.semName}
            onChange={handleChange}
            margin="normal"
            SelectProps={{ native: true }}
          >
            {semNames.map((name, idx) => (
              <option key={idx} value={name}>
                {name}
              </option>
            ))}
          </TextField>
          <Box mt={3} display="flex" justifyContent="flex-end" gap={1}>
            <Button variant="outlined" onClick={handleCloseModal}>
              Cancel
            </Button>
            <Button variant="contained" onClick={handleUpdate}>
              Save
            </Button>
          </Box>
        </Box>
      </Modal>

      <Snackbar
        open={snackbarOpen}
        autoHideDuration={3000}
        onClose={() => setSnackbarOpen(false)}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert severity={snackbarSeverity} onClose={() => setSnackbarOpen(false)}>
          {snackbarMsg}
        </Alert>
      </Snackbar>
    </div>
  );
};

export default ViewCourses;
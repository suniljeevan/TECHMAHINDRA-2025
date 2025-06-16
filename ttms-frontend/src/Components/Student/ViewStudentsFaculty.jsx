import React, { useState, useEffect } from "react";
import axios from "axios";
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
  Alert
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import "./ViewStudentsFaculty.css";

const ViewStudentsFaculty = () => {
  const [students, setStudents] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedStudent, setSelectedStudent] = useState({
    studentid: "",
    studentFirstName: "",
    studentLastName: "",
    studentEmail: "",
    studentPassword: "",
    studentContactNo: "",
    studentAddress: "",
  });
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  const fetchStudents = async () => {
    try {
      const response = await axios.get("http://localhost:8080/student/all-students");
      setStudents(response.data);
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleOpenModal = (student) => {
    setSelectedStudent(student);
    setOpen(true);
  };

  const handleCloseModal = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setSelectedStudent({ ...selectedStudent, [e.target.name]: e.target.value });
  };

  const handleUpdate = async () => {

    try {
      await axios.put("http://localhost:8080/student/update-student", selectedStudent);
      fetchStudents();
      setOpen(false);
      showSnackbar("Student updated successfully!", "success");
    } catch (error) {
      console.error("Error updating student:", error);
      showSnackbar("Failed to update student.", "error");
    }
  };

  const handleDelete = async (studentid) => {
    try {
      await axios.delete(`http://localhost:8080/student/delete/${studentid}`);
      fetchStudents();
      showSnackbar("Student deleted successfully!", "success");
    } catch (error) {
      console.error("Error deleting student:", error);
      showSnackbar("Failed to delete student.", "error");
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

  return (
    <Container maxWidth="lg" className="student-container">
      <Paper className="student-paper" elevation={6}>
        <Box textAlign="center" mb={3}>
          <Typography variant="h4" className="student-title">
            📘 All Students
          </Typography>
          <Typography variant="subtitle1" className="course-subtitle">
            Manage, edit, and review available student offerings.
          </Typography>
        </Box>

        <Box sx={{ overflowX: "auto", marginTop: 2 }}>
          <Table className="student-table">
            <TableHead>
              <TableRow className="student-header">
                <TableCell><strong>ID</strong></TableCell>
                <TableCell><strong>First Name</strong></TableCell>
                <TableCell><strong>Last Name</strong></TableCell>
                <TableCell><strong>Email</strong></TableCell>
                <TableCell><strong>Password</strong></TableCell>
                <TableCell><strong>Contact</strong></TableCell>
                <TableCell><strong>Address</strong></TableCell>
                <TableCell><strong>Semester</strong></TableCell>
                <TableCell><strong>Section</strong></TableCell>
                <TableCell align="center"><strong>Actions</strong></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {students.map((student, index) => (
                <TableRow
                  key={student.studentid}
                  className={index % 2 === 0 ? "student-row" : "student-row-alt"}
                >
                  <TableCell>{index + 1}</TableCell>
                  <TableCell>{student.studentFirstName}</TableCell>
                  <TableCell>{student.studentLastName}</TableCell>
                  <TableCell>{student.studentEmail}</TableCell>
                  <TableCell>{student.studentPassword}</TableCell>
                  <TableCell>{student.studentContactNo}</TableCell>
                  <TableCell>{student.studentAddress}</TableCell>
                  <TableCell>{student.studentSemester}</TableCell>
                  <TableCell>{student.studentSection}</TableCell>
                  <TableCell align="center">
                    <Box display="flex" justifyContent="center" gap={1}>
                      <Button
                        variant="contained"
                        size="small"
                        startIcon={<EditIcon />}
                        className="action-btn update"
                        onClick={() => handleOpenModal(student)}
                      >
                        Update
                      </Button>
                      <Button
                        variant="contained"
                        size="small"
                        startIcon={<DeleteIcon />}
                        className="action-btn delete"
                        onClick={() => handleDelete(student.studentid)}
                      >
                        Delete
                      </Button>
                    </Box>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </Box>
      </Paper>

      <Modal open={open} onClose={handleCloseModal}>
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "background.paper",
            boxShadow: 24,
            p: 4,
            borderRadius: 2
          }}
        >
          <Typography variant="h6" sx={{ color: 'black', fontWeight: 'bold' }} mb={4}>Update Student</Typography>
          <TextField
            fullWidth
            label="Firstname"
            name="studentFirstName"
            margin="normal"
            value={selectedStudent.studentFirstName}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Lastname"
            name="studentLastName"
            margin="normal"
            value={selectedStudent.studentLastName}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Email"
            name="studentEmail"
            margin="normal"
            value={selectedStudent.studentEmail}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="password"
            name="studentPassword"
            margin="normal"
            value={selectedStudent.studentPassword}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Contact"
            name="studentContactNo"
            margin="normal"
            value={selectedStudent.studentContactNo}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Addres"
            name="studentAddress"
            margin="normal"
            value={selectedStudent.studentAddress}
            onChange={handleChange}
          />

          <Box mt={2} display="flex" justifyContent="flex-end" gap={1}>
            <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} onClick={handleCloseModal}>Cancel</Button>
            <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} variant="contained" onClick={handleUpdate}>Save</Button>
          </Box>
        </Box>
      </Modal>

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

export default ViewStudentsFaculty;
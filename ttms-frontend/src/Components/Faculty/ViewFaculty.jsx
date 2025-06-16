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
import "./ViewFaculty.css";

const ViewFaculty = () => {
  const [teachers, setTeachers] = useState([]);
  const [open, setOpen] = useState(false);
  const [currentTeacher, setCurrentTeacher] = useState({
    facultyid: "",
    facultyFirstName: "",
    facultyLastName: "",
    facultyEmail: "",
    facultyContact: "",
    facultyAddress: "",
  });

  const [snackbar, setSnackbar] = useState({
    open: false,
    message: "",
    severity: "success",
  });

  useEffect(() => {
    fetchTeachers();
  }, []);

  const fetchTeachers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/faculty/all-faculty");
      setTeachers(response.data);
    } catch (error) {
      console.error("Error fetching faculty:", error);
    }
  };

  const handleUpdateClick = (teacher) => {
    setCurrentTeacher(teacher);
    setOpen(true);
  };

  const handleCloseModal = () => {
    setOpen(false);
    setCurrentTeacher({
      facultyid: "",
      facultyFirstName: "",
      facultyLastName: "",
      facultyEmail: "",
      facultyContact: "",
      facultyAddress: "",
    });
  };

  const handleChange = (e) => {
    setCurrentTeacher({ ...currentTeacher, [e.target.name]: e.target.value });
  };

  const handleSaveUpdate = async () => {
    try {
      await axios.put("http://localhost:8080/faculty/update-faculty", currentTeacher);
      setTeachers((prev) =>
        prev.map((t) =>
          t.facultyid === currentTeacher.facultyid ? currentTeacher : t
        )
      );
      handleCloseModal();
      showSnackbar("Faculty updated successfully", "success");
    } catch (error) {
      console.error("Error updating faculty:", error);
      showSnackbar("Failed to update faculty", "error");
    }
  };

  const handleDelete = async (facultyid) => {
    try {
      await axios.delete(`http://localhost:8080/faculty/delete/${facultyid}`);
      setTeachers((prev) => prev.filter((t) => t.facultyid !== facultyid));
      showSnackbar("Faculty deleted successfully", "success");
    } catch (error) {
      console.error("Error deleting faculty:", error);
      showSnackbar("Failed to delete faculty", "error");
    }
  };

  const showSnackbar = (message, severity) => {
    setSnackbar({
      open: true,
      message,
      severity,
    });
  };

  const handleSnackbarClose = () => {
    setSnackbar((prev) => ({ ...prev, open: false }));
  };

  return (
    <Container maxWidth="lg" className="teacher-container">
      <Paper className="teacher-paper" elevation={6}>
        <Box textAlign="center" mb={3}>
        <Typography variant="h4" className="teacher-title">
          👨‍🏫 All Faculty's
        </Typography>
        <Typography variant="subtitle1" className="faculty-subtitle">
              Manage, edit, and review available faculty offerings.
        </Typography>
        </Box>

        <Box sx={{ overflowX: "auto", marginTop: 2 }}>
          <Table className="teacher-table">
            <TableHead>
              <TableRow className="teacher-header">
                <TableCell><strong>First Name</strong></TableCell>
                <TableCell><strong>Last Name</strong></TableCell>
                <TableCell><strong>Email</strong></TableCell>
                <TableCell><strong>Phone No</strong></TableCell>
                <TableCell><strong>Address</strong></TableCell>
                <TableCell align="center"><strong>Actions</strong></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {teachers.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={10} align="center">
                    <Typography variant="h6" color="textSecondary">
                      No Faculty's available
                    </Typography>
                  </TableCell>
                </TableRow>
              ) : (
                teachers.map((teacher, index) => (
                  <TableRow
                    key={teacher.facultyid}
                    className={index % 2 === 0 ? "teacher-row" : "teacher-row-alt"}
                  >
                    <TableCell>{teacher.facultyFirstName}</TableCell>
                    <TableCell>{teacher.facultyLastName}</TableCell>
                    <TableCell>{teacher.facultyEmail}</TableCell>
                    <TableCell>{teacher.facultyContact}</TableCell>
                    <TableCell>{teacher.facultyAddress}</TableCell>
                    <TableCell align="center">
                      <Box display="flex" justifyContent="center" gap={1}>
                        <Button
                          variant="contained"
                          size="small"
                          startIcon={<EditIcon />}
                          className="action-btn update"
                          onClick={() => handleUpdateClick(teacher)}
                        >
                          Update
                        </Button>
                        <Button
                          variant="contained"
                          size="small"
                          startIcon={<DeleteIcon />}
                          className="action-btn delete"
                          onClick={() => handleDelete(teacher.facultyid)}
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
            borderRadius: 2,
          }}
        >
          <Typography variant="h6" sx={{ color: 'black', fontWeight: 'bold' }} mb={3}>
            Update Faculty
          </Typography>
          <TextField
            fullWidth
            label="First Name"
            name="facultyFirstName"
            margin="normal"
            value={currentTeacher.facultyFirstName}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Last Name"
            name="facultyLastName"
            margin="normal"
            value={currentTeacher.facultyLastName}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Email"
            name="facultyEmail"
            margin="normal"
            value={currentTeacher.facultyEmail}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Contact"
            name="facultyContact"
            margin="normal"
            value={currentTeacher.facultyContact}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Address"
            name="facultyAddress"
            margin="normal"
            value={currentTeacher.facultyAddress}
            onChange={handleChange}
          />
          <Box mt={3} display="flex" justifyContent="flex-end" gap={1}>
            <Box mt={2} display="flex" justifyContent="flex-end" gap={1}>
              <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} onClick={handleCloseModal}>Cancel</Button>
              <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} variant="contained" onClick={handleSaveUpdate}>Save</Button>
            </Box>
          </Box>
        </Box>
      </Modal>

      <Snackbar
        open={snackbar.open}
        autoHideDuration={3000}
        onClose={handleSnackbarClose}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert severity={snackbar.severity} onClose={handleSnackbarClose} sx={{ width: "100%" }}>
          {snackbar.message}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default ViewFaculty;
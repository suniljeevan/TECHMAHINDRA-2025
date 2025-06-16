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
  Grid,
  Snackbar,
  Alert
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import "./ViewSemester.css";

const ViewSemester = () => {
  const [semesters, setSemesters] = useState([]);
  const [open, setOpen] = useState(false);
  const [currentSemester, setCurrentSemester] = useState({
    sid: "",
    semName: "",
    semDescription: "",
  });

  // Snackbar state
  const [snackbar, setSnackbar] = useState({
    open: false,
    message: "",
    severity: "success",
  });

  useEffect(() => {
    fetchSemesters();
  }, []);

  const fetchSemesters = async () => {
    try {
      const response = await axios.get("http://localhost:8080/semester/all-semesters");
      setSemesters(response.data);
    } catch (error) {
      console.error("Error fetching semesters:", error);
    }
  };

  const handleDelete = async (sid) => {
    try {
      await axios.delete(`http://localhost:8080/semester/delete/${sid}`);
      setSemesters((prev) => prev.filter((sem) => sem.sid !== sid));
      showSnackbar("Semester deleted successfully", "success");
    } catch (error) {
      console.error("Error deleting semester:", error);
      showSnackbar("Failed to delete semester", "error");
    }
  };

  const handleUpdateClick = (semester) => {
    setCurrentSemester(semester);
    setOpen(true);
  };

  const handleUpdateClose = () => {
    setOpen(false);
    setCurrentSemester({ sid: "", semName: "", semDescription: "" });
  };

  const handleSaveUpdate = async () => {
    try {
      const updatedSemester = { ...currentSemester };
      await axios.put("http://localhost:8080/semester/update-semester", updatedSemester);
      setSemesters((prev) =>
        prev.map((semester) =>
          semester.sid === updatedSemester.sid ? updatedSemester : semester
        )
      );
      handleUpdateClose();
      showSnackbar("Semester updated successfully", "success");
    } catch (error) {
      console.error("Error updating semester:", error);
      showSnackbar("Failed to update semester", "error");
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
    setSnackbar({ ...snackbar, open: false });
  };

  return (
    <Container maxWidth="lg" className="semester-container">
      <Paper elevation={6} className="semester-paper">
        <Box textAlign="center" mb={3}>
          <Typography variant="h4" className="semester-title">
            🎓 All Semester's
          </Typography>
          <Typography variant="subtitle1" className="semester-subtitle">
                Manage, edit, and review available semester offerings.
          </Typography>
        </Box>

        <Box sx={{ overflowX: "auto" }}>
          <Table className="semester-table">
            <TableHead>
              <TableRow className="semester-header">
                <TableCell><strong>Semester ID</strong></TableCell>
                <TableCell><strong>Name</strong></TableCell>
                <TableCell><strong>Description</strong></TableCell>
                <TableCell align="center"><strong>Actions</strong></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {semesters.length === 0 ? (
                <TableRow>
                  <TableCell colSpan={10} align="center">
                    <Typography variant="h6" color="textSecondary">
                      No Semester's available
                    </Typography>
                  </TableCell>
                </TableRow>
              ) : (
                semesters.map((semester, index) => (
                  <TableRow
                    key={semester.sid}
                    className={index % 2 === 0 ? "semester-row" : "semester-row-alt"}
                  >
                    <TableCell>{index + 1}</TableCell>
                    <TableCell>{semester.semName}</TableCell>
                    <TableCell>{semester.semDescription}</TableCell>
                    <TableCell align="center">
                      <Box display="flex" flexWrap="wrap" justifyContent="center" gap={1}>
                        <Button
                          variant="contained"
                          size="small"
                          className="action-btn update"
                          startIcon={<EditIcon />}
                          onClick={() => handleUpdateClick(semester)}
                        >
                          Update
                        </Button>

                        <Button
                          variant="contained"
                          size="small"
                          className="action-btn delete"
                          startIcon={<DeleteIcon />}
                          onClick={() => handleDelete(semester.sid)}
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

      {/* Modal for Updating Semester */}
      <Modal open={open} onClose={handleUpdateClose}>
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "background.paper",
            borderRadius: 2,
            boxShadow: 24,
            p: 4,
          }}
        >
          <Typography variant="h6" sx={{color: 'black', fontWeight: 'bold'}} mb={4}>Update Semester</Typography>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                label="Semester Name"
                variant="outlined"
                fullWidth
                value={currentSemester.semName}
                onChange={(e) => setCurrentSemester({ ...currentSemester, semName: e.target.value })}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                label="Semester Description"
                variant="outlined"
                fullWidth
                value={currentSemester.semDescription}
                onChange={(e) => setCurrentSemester({ ...currentSemester, semDescription: e.target.value })}
              />
            </Grid>
          </Grid>
          <Box mt={2} display="flex" justifyContent="space-between">
            <Button sx={{backgroundcolor: '#4f46e5color', color: '#ffffff'}} onClick={handleUpdateClose}>
              Cancel
            </Button>
            <Button sx={{backgroundcolor: '#4f46e5color', color: '#ffffff'}} onClick={handleSaveUpdate}>
              Update
            </Button>
          </Box>
        </Box>
      </Modal>

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
    </Container>
  );
};

export default ViewSemester;
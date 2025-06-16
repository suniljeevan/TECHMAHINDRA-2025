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
  Snackbar,
  Alert
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import axios from "axios";
import "./DeactivateSectionFaculty.css";

const DeactivateSectionFaculty = () => {
  const [sections, setSections] = useState([]);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  useEffect(() => {
    fetchSections();
  }, []);

  const fetchSections = async () => {
    try {
      const response = await axios.get("http://localhost:8080/section/all-sections");
      setSections(response.data);
    } catch (error) {
      console.error("Error fetching sections:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/section/delete/${id}`);
      fetchSections();
      showSnackbar("Section deleted successfully!", "success");
    } catch (error) {
      console.error("Error deleting section:", error);
      showSnackbar("Failed to delete section.", "error");
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
    <Container maxWidth="lg" className="section-container">
      <Paper className="section-paper" elevation={6}>
        <Box textAlign="center" mb={3}>
          <Typography variant="h4" className="section-title">🧾 Deactivate Sections</Typography>
          <Typography variant="subtitle1" className="course-subtitle">
            Deactivate the available section offerings.
          </Typography>
        </Box>

        <Box sx={{ overflowX: "auto", marginTop: 2 }}>
          <Table className="section-table">
            <TableHead>
              <TableRow className="section-header">
                <TableCell><strong>ID</strong></TableCell>
                <TableCell><strong>Section Name</strong></TableCell>
                <TableCell><strong>Description</strong></TableCell>
                <TableCell><strong>Semester</strong></TableCell>
                <TableCell><strong>Faculty</strong></TableCell>
                <TableCell align="center"><strong>Actions</strong></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {sections.map((section, index) => (
                <TableRow key={section.secid} className={index % 2 === 0 ? "section-row" : "section-row-alt"}>
                  <TableCell>{index + 1}</TableCell>
                  <TableCell>{section.sectionName}</TableCell>
                  <TableCell>{section.sectionDescription}</TableCell>
                  <TableCell>{section.semName}</TableCell>
                  <TableCell>{section.facultyFirstName}</TableCell>
                  <TableCell align="center">
                    <Box display="flex" flexWrap="wrap" justifyContent="center" gap={1}>
                      <Button
                        variant="contained"
                        size="small"
                        startIcon={<DeleteIcon />}
                        className="action-btn delete"
                        onClick={() => handleDelete(section.secid)}
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

export default DeactivateSectionFaculty;
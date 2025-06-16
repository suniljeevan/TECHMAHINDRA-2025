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
  Alert
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import axios from "axios";
import "./TransferSectionFaculty.css";

const TransferSectionsFaculty = () => {
  const [sections, setSections] = useState([]);
  const [semNames, setSemNames] = useState([]);
  const [open, setOpen] = useState(false);
  const [faculties, setFaculties] = useState([]);

  const [selectedSection, setSelectedSection] = useState({
    secid: "",
    sectionName: "",
    sectionDescription: "",
    semName: "",
    facultyFirstName: ""
  });

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  // Fetch faculties
  const fetchFaculties = async () => {
    try {
      const response = await axios.get("http://localhost:8080/faculty/all-faculty");
      setFaculties(response.data);
    } catch (error) {
      console.error("Error fetching faculties:", error);
    }
  };

  // Fetch sections
  const fetchSections = async () => {
    try {
      const response = await axios.get("http://localhost:8080/section/all-sections");
      setSections(response.data);
    } catch (error) {
      console.error("Error fetching sections:", error);
    }
  };

  // Fetch semester names
  const fetchSemesters = async () => {
    try {
      const response = await axios.get("http://localhost:8080/semester/all-semesters");
      const semesterNames = response.data.map(sem => sem.semName);
      setSemNames(semesterNames);
    } catch (error) {
      console.error("Error fetching semesters:", error);
    }
  };

  useEffect(() => {
    fetchSections();
    fetchSemesters();
    fetchFaculties();
  }, []);

  const handleOpenModal = (section) => {
    setSelectedSection(section);
    setOpen(true);
  };

  const handleCloseModal = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setSelectedSection({ ...selectedSection, [e.target.name]: e.target.value });
  };

  const handleTransferSection = async () => {
    if (!semNames.includes(selectedSection.semName)) {
      showSnackbar("Invalid semester name. Please select a valid one.", "error");
      return;
    }

    try {
      await axios.put("http://localhost:8080/section/update-section", selectedSection);
      fetchSections();
      setOpen(false);
      showSnackbar("Section updated successfully!", "success");
    } catch (error) {
      console.error("Error updating section:", error);
      showSnackbar("Failed to update section.", "error");
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
          <Typography variant="h4" className="section-title">🧾 Transfer Sections</Typography>
          <Typography variant="subtitle1" className="course-subtitle">
            Transfer the available section offerings.
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
              {sections.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={10} align="center">
                      <Typography variant="h6" color="textSecondary">
                        No Section's available
                      </Typography>
                    </TableCell>
                  </TableRow>
                ) : (
                sections.map((section, index) => (
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
                          startIcon={<EditIcon />}
                          className="action-btn update"
                          onClick={() => handleOpenModal(section)}
                        >
                          Transfer Section
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

      {/* Update Section Modal */}
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
          <Typography variant="h6" sx={{ color: 'black', fontWeight: 'bold' }} mb={4}>Update Section</Typography>
          <TextField
            select
            fullWidth
            label="Faculty"
            name="facultyFirstName"
            margin="normal"
            value={selectedSection.facultyFirstName}
            onChange={handleChange}
            SelectProps={{ native: true }}
          >
            <option value="">Select Faculty</option>
            {faculties.map((faculty) => (
              <option key={faculty.facultyid} value={faculty.facultyFirstName}>
                {faculty.facultyFirstName}
              </option>
            ))}
          </TextField>

          <Box mt={2} display="flex" justifyContent="flex-end" gap={1}>
            <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} onClick={handleCloseModal}>Cancel</Button>
            <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} variant="contained" onClick={handleTransferSection}>Save</Button>
          </Box>
        </Box>
      </Modal>

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

export default TransferSectionsFaculty;
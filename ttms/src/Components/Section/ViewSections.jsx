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
import DeleteIcon from "@mui/icons-material/Delete";
import AddIcon from '@mui/icons-material/Add';
import FastfoodIcon from '@mui/icons-material/Fastfood';
import axios from "axios";
import "./ViewSections.css";

const ViewSections = () => {
  const [sections, setSections] = useState([]);
  const [semNames, setSemNames] = useState([]);
  const [open, setOpen] = useState(false);
  const [ttOpen, setttOpen] = useState(false);
  const [selectedSection, setSelectedSection] = useState({
    secid: "",
    sectionName: "",
    sectionDescription: "",
    semName: "",
    facultyFirstName: ""
  });
  const [courses, setCourses] = useState([]);
  const [timetable, setTimetable] = useState({});

  // Fetch Courses
  const fetchCourses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/course/all-courses");
      setCourses(response.data);
    } catch (error) {
      console.error("Error fetching courses:", error);
    }
  };

  useEffect(() => {
    fetchSections();
    fetchSemesters();
    fetchCourses();
  }, []);

  // Initialize timetable structure when opening modal
  const handleOpenttModal = (section) => {
    const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
    const hours = ["9AM", "10AM", "11AM", "12PM","Break", "1PM", "2PM", "3PM", "4PM"];
    const initialTimetable = {};
    days.forEach((day) => {
      initialTimetable[day] = {};
      hours.forEach((time) => {
        initialTimetable[day][time] = "";
      });
    });
    setTimetable(initialTimetable);
    setSelectedSection(section); 
    setttOpen(true);
  };

  // Handle Course Selection
  const handleTimetableChange = (day, time, value) => {
    setTimetable((prev) => ({
      ...prev,
      [day]: {
        ...prev[day],
        [time]: value,
      },
    }));
  };

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMsg, setSnackbarMsg] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  // Fetch sections from backend
  const fetchSections = async () => {
    try {
      const response = await axios.get("http://localhost:8080/section/all-sections");
      setSections(response.data);
    } catch (error) {
      console.error("Error fetching sections:", error);
    }
  };

  // Fetch semester names from backend
  const fetchSemesters = async () => {
    try {
      const response = await axios.get("http://localhost:8080/semester/all-semesters");
      const semesterNames = response.data.map(sem => sem.semName);
      setSemNames(semesterNames);
    } catch (error) {
      console.error("Error fetching semesters:", error);
    }
  };

  const handleOpenModal = (section) => {
    setSelectedSection(section);
    setOpen(true);
  };

  const handleCloseModal = () => {
    setOpen(false);
  };

  const handleClosettModal = () => {
    setttOpen(false);
  };

  const handleChange = (e) => {
    setSelectedSection({ ...selectedSection, [e.target.name]: e.target.value });
  };

  const handleUpdate = async () => {
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

  const handleSaveTimetable = async () => {
    // Prepare timetableData for submission (flatten the object)
    const timetableData = [];
    const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
    const hours = ["9AM", "10AM", "11AM", "12PM", "Break", "1PM", "2PM", "3PM", "4PM"];
  
    days.forEach((day) => {
      hours.forEach((time) => {
        const course = timetable[day]?.[time] || ""; // Get the course for the given time and day
        timetableData.push(course);
      });
    });
  
    const timetablePayload = {
      sectionName: selectedSection.sectionName,
      semName: selectedSection.semName,
      facultyFirstName: selectedSection.facultyFirstName,
      timetableData: timetableData
    };
  
    // Make POST API call to save the timetable
    try {
      await axios.post("http://localhost:8080/timetable/create-timetable", timetablePayload);
      showSnackbar("Timetable saved successfully!", "success");
      handleClosettModal();
    } catch (error) {
      console.error("Error saving timetable:", error);
      showSnackbar("Failed to save timetable.", "error");
    }
  };
  
  const generateRandomTimetable = () => {
    const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
    const hours = ["9AM", "10AM", "11AM", "12PM", "Break", "1PM", "2PM", "3PM", "4PM"];
    const newTimetable = {};
  
    days.forEach((day) => {
      newTimetable[day] = {};
      hours.forEach((time) => {
        if (time === "Break") {
          newTimetable[day][time] = "Break";
        } else {
          const randomCourse = courses.length > 0 
            ? courses[Math.floor(Math.random() * courses.length)].courseName
            : "";
          newTimetable[day][time] = randomCourse;
        }
      });
    });
  
    setTimetable(newTimetable);
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
          <Typography variant="h4" className="section-title">🧾 All Section's</Typography>
          <Typography variant="subtitle1" className="course-subtitle">
            Manage, edit, and review available section offerings.
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
                <TableCell><strong>Time table</strong></TableCell>
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
                    <TableCell>
                      <Button
                        variant="contained"
                        size="small"
                        startIcon={<AddIcon />}
                        className="action-btn upload"
                        onClick={() => handleOpenttModal(section)}
                      >
                        Add Timetable
                      </Button>
                    </TableCell>
                    <TableCell align="center">
                      <Box display="flex" flexWrap="wrap" justifyContent="center" gap={1}>
                        <Button
                          variant="contained"
                          size="small"
                          startIcon={<EditIcon />}
                          className="action-btn update"
                          onClick={() => handleOpenModal(section)}
                        >
                          Update
                        </Button>
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
            fullWidth
            label="Name"
            name="sectionName"
            margin="normal"
            value={selectedSection.sectionName}
            onChange={handleChange}
          />
          <TextField
            fullWidth
            label="Description"
            name="sectionDescription"
            margin="normal"
            value={selectedSection.sectionDescription}
            onChange={handleChange}
          />
          <TextField
            select
            fullWidth
            label="Semester"
            name="semName"
            margin="normal"
            value={selectedSection.semName}
            onChange={handleChange}
            SelectProps={{ native: true }}
          >
            {semNames.map((name, idx) => (
              <option key={idx} value={name}>{name}</option>
            ))}
          </TextField>
          <Box mt={2} display="flex" justifyContent="flex-end" gap={1}>
            <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} onClick={handleCloseModal}>Cancel</Button>
            <Button sx={{ backgroundColor: '#4f46e5', color: '#ffffff' }} variant="contained" onClick={handleUpdate}>Save</Button>
          </Box>
        </Box>
      </Modal>

      {/* Add time table modal */}
      <Modal open={ttOpen} onClose={handleClosettModal}>
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: "95%",
            maxWidth: 1000,
            bgcolor: "background.paper",
            boxShadow: 24,
            p: 4,
            borderRadius: 2,
            overflowY: "auto",
            maxHeight: "90vh",
          }}
        >
          <Typography variant="h5" mb={2} sx={{ fontWeight: "bold", color: "#4f46e5" }}>
            📅 Add Timetable
          </Typography>

          {/* Display sectionName and semName inside the modal */}
          <Typography variant="h6" mb={2} sx={{ fontWeight: "bold", color: "black" }}>Section: {selectedSection.sectionName}</Typography>
          <Typography variant="h6" mb={2} sx={{ fontWeight: "bold", color: "black" }}>Semester: {selectedSection.semName}</Typography>

          <Box sx={{ overflowX: "auto" }}>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell sx={{ fontWeight: "bold" }}>Day / Time</TableCell>
                  {["9AM", "10AM", "11AM", "12PM", "Break", "1PM", "2PM", "3PM", "4PM"].map((time) => (
                  <TableCell key={time} sx={{ fontWeight: "bold" }}>{time}</TableCell>
                ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"].map((day) => (
                  <TableRow key={day}>
                    <TableCell sx={{ fontWeight: "bold" }}>{day}</TableCell>
                    {["9AM", "10AM", "11AM", "12PM", "Break", "1PM", "2PM", "3PM", "4PM"].map((time) => (
                    <TableCell key={time}>
                      {time === "Break" ? (
                        <Typography align="center" sx={{ fontWeight: "bold", color: "#4f46e5" }}>
                          <FastfoodIcon sx={{color: 'red'}}/>
                        </Typography>
                      ) : (
                        <TextField
                          select
                          size="small"
                          value={timetable[day]?.[time] || ""}
                          onChange={(e) => handleTimetableChange(day, time, e.target.value)}
                          SelectProps={{ native: true }}
                          fullWidth
                        >
                          <option value="">--</option>
                          {courses.map((course) => (
                            <option key={course.courseid} value={course.courseName}>
                              {course.courseName}
                            </option>
                          ))}
                        </TextField>
                      )}
                    </TableCell>
                  ))}
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </Box>

          <Box mt={3} display="flex" justifyContent="flex-end" gap={1}>
            <Button
              onClick={generateRandomTimetable}
              sx={{ backgroundColor: "#6b7280", color: "#fff" }}
            >
              Random Timetable
            </Button>
            <Button
              onClick={handleClosettModal}
              sx={{ backgroundColor: "#6b7280", color: "#fff" }}
            >
              Cancel
            </Button>
            <Button
              sx={{ backgroundColor: "#4f46e5", color: "#ffffff" }}
              variant="contained"
              onClick={handleSaveTimetable}
            >
              Save
            </Button>
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

export default ViewSections;
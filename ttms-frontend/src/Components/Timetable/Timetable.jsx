import React, { useEffect, useState } from "react";
import {
  Container,
  Typography,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Box,
  Paper,
  Button,
  TableContainer,
  Snackbar,
  Alert
} from "@mui/material";
import axios from "axios";
import "./Timetable.css";

const Timetable = () => {
  const [semNames, setSemNames] = useState([]);
  const [sections, setSections] = useState([]);
  const [selectedSem, setSelectedSem] = useState("");
  const [selectedSection, setSelectedSection] = useState("");
  const [facultyName, setFacultyName] = useState("");
  const [timetable, setTimetable] = useState(null);

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState("");
  const [snackbarSeverity, setSnackbarSeverity] = useState("success");

  const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
  const hours = ["9AM", "10AM", "11AM", "12PM", "Break", "1PM", "2PM", "3PM", "4PM"];

  useEffect(() => {
    const fetchSemesters = async () => {
      try {
        const response = await axios.get("http://localhost:8080/semester/all-semesters");
        setSemNames(response.data.map((sem) => sem.semName));
      } catch (error) {
        console.error("Error fetching semesters:", error);
        showSnackbar("Error fetching semesters.", "error");
      }
    };
  
    const fetchSections = async () => {
      try {
        const response = await axios.get("http://localhost:8080/section/all-sections");
        setSections(response.data.map((sec) => sec.sectionName));
      } catch (error) {
        console.error("Error fetching sections:", error);
        showSnackbar("Error fetching sections.", "error");
      }
    };
  
    fetchSemesters();
    fetchSections();
  }, []);
  

  const fetchTimetable = async () => {
    if (!selectedSem || !selectedSection) {
      showSnackbar("Please select both Semester and Section.", "warning");
      return;
    }
    try {
      const response = await axios.get(
        `http://localhost:8080/timetable/get-timetable/${selectedSem}/${selectedSection}`
      );

      const timetableData = response.data.timetableData;
      const facultyName = response.data.facultyFirstName;

      const formattedTimetable = {};
      let index = 0;
      for (let day of days) {
        formattedTimetable[day] = {};
        for (let time of hours) {
          formattedTimetable[day][time] = timetableData[index] || "-";
          index++;
        }
      }
      setTimetable(formattedTimetable);
      setFacultyName(facultyName);
      showSnackbar("Timetable fetched successfully!", "success");
    } catch (error) {
      console.error("Error fetching timetable:", error);
      setTimetable(null);
      showSnackbar("No timetable found for the selected Semester and Section.", "error");
    }
  };

  const showSnackbar = (message, severity) => {
    setSnackbarMessage(message);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };

  return (
    <Container maxWidth="lg" sx={{ mt: 4 }}>
      <Paper elevation={3} sx={{ p: 4, borderRadius: 4 }}>
      <Typography variant="h4" className="timetable-title" sx={{color: '#4f46e5', fontSize: '28px', fontWeight: '700', textAlign: 'center', marginBottom: '20px'}}>
          📋 View Timetable
      </Typography>

        <Box
          sx={{
            display: "flex",
            flexDirection: { xs: "column", sm: "row" },
            gap: 3,
            mb: 3
          }}
        >
          <FormControl sx={{ minWidth: 200 }}>
            <InputLabel>Semester</InputLabel>
            <Select
              value={selectedSem}
              onChange={(e) => setSelectedSem(e.target.value)}
              label="Semester"
            >
              {semNames.map((sem, index) => (
                <MenuItem key={index} value={sem}>
                  {sem}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <FormControl sx={{ minWidth: 200 }}>
            <InputLabel>Section</InputLabel>
            <Select
              value={selectedSection}
              onChange={(e) => setSelectedSection(e.target.value)}
              label="Section"
            >
              {sections.map((sec, index) => (
                <MenuItem key={index} value={sec}>
                  {sec}
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <Button
            variant="contained"
            color="primary"
            onClick={fetchTimetable}
            sx={{
              height: "56px",
              borderRadius: 2,
              px: 4
            }}
          >
            Load Timetable
          </Button>
        </Box>

        {timetable && (
          <TableContainer sx={{ overflowX: "auto" }}>
            {facultyName && (
              <Typography variant="h6" gutterBottom sx={{color: "black"}}>
                Faculty In-charge: {facultyName}
              </Typography>
            )}
            <Table size="small" className="timetable-table">
              <TableHead>
                <TableRow>
                  <TableCell className="day-column">Day / Time</TableCell>
                  {hours.map((time) => (
                    <TableCell key={time} align="center">
                      {time}
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {days.map((day) => (
                  <TableRow key={day}>
                    <TableCell className="day-column">{day}</TableCell>
                    {hours.map((time) => (
                      <TableCell key={time} align="center">
                        {timetable[day][time]}
                      </TableCell>
                    ))}
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </Paper>

      {/* Snackbar for notifications */}
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={4000}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      >
        <Alert
          onClose={handleCloseSnackbar}
          severity={snackbarSeverity}
          sx={{ width: "100%" }}
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default Timetable;
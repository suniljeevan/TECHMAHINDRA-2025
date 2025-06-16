import React, { useState } from "react";
import {
  Container,
  TextField,
  Typography,
  Button,
  Box,
  Paper,
  Snackbar,
  Alert,
  Select,
  MenuItem,
  InputLabel,
  FormControl
} from "@mui/material";
import "./AddSemester.css";
import axios from "axios";

const AddSemester = () => {
  const [semester, setSemester] = useState("");
  const [description, setDescription] = useState("");
  const [snackbar, setSnackbar] = useState({ open: false, message: "", severity: "success" });

  const handleSnackbarClose = () => {
    setSnackbar({ ...snackbar, open: false });
  };

  const handleAddSemester = async () => {
    const payload = {
      semName: semester,
      semDescription: description,
    };

    try {
      const response = await axios.post("http://localhost:8080/semester/create-semester", payload);
      setSnackbar({ open: true, message: response.data || "Semester added successfully!", severity: "success" });
      setSemester("");
      setDescription("");
    } catch (error) {
      console.error("Error while creating semester");
      setSnackbar({ open: true, message: "Failed to create Semester", severity: "error" });
    }
  };

  // ✅ Form validation — returns true if both fields are filled
  const isFormValid = semester !== "" && description.trim() !== "";

  return (
    <>
      <Container maxWidth="sm" className="add-semester-container">
        <Paper elevation={6} className="add-semester-paper">
          <Typography variant="h4" className="form-title">
            🎓 Add New Semester
          </Typography>
          <Typography variant="subtitle1" className="form-subtitle">
            Plan your academic Semester with ease!
          </Typography>
          <form noValidate autoComplete="off">

            <FormControl fullWidth margin="normal">
              <InputLabel>Select Semester</InputLabel>
              <Select
                required
                value={semester}
                onChange={(e) => setSemester(e.target.value)}
                label="Select Semester"
              >
                {[...Array(8)].map((_, index) => (
                  <MenuItem key={index + 1} value={`B Tech Semester ${index + 1}`}>
                    B Tech Semester {index + 1}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>

            <TextField
              fullWidth
              label="Enter Semester Description"
              variant="outlined"
              margin="normal"
              multiline
              rows={4}
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="input-field"
            />

            <Box mt={3} textAlign="center">
              <Button
                variant="contained"
                color="secondary"
                onClick={handleAddSemester}
                className="add-btn"
                disabled={!isFormValid}
              >
                Add Semester
              </Button>
            </Box>
          </form>
        </Paper>
      </Container>

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
    </>
  );
};

export default AddSemester;
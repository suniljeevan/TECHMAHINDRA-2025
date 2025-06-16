import React, { useState } from "react";
import axios from "axios";
import {
  AppBar,
  Toolbar,
  Typography,
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  IconButton,
  Box,
  Divider,
  CssBaseline,
  Menu,
  MenuItem,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { useNavigate } from "react-router-dom";
import FitbitIcon from "@mui/icons-material/Fitbit";
import "./Navbar.css";

const drawerWidth = 240;

const menuItems = [
  { text: "View Timetable", icon: <CalendarTodayIcon />, path: "/view-timetable-student" },
  { text: "Logout", icon: <ExitToAppIcon />, path: "/" },
];

const NavbarStudent = ({ children }) => {
  const [mobileOpen, setMobileOpen] = useState(false);
  const [anchorEl, setAnchorEl] = useState(null);
  const [studentDetails, setStudentDetails] = useState({});
  const navigate = useNavigate();

  const handleDrawerToggle = () => setMobileOpen(!mobileOpen);
  const handleNavigation = (path) => path && navigate(path);

  const handleProfileClick = (event) => {
    setAnchorEl(event.currentTarget);
    fetchStudentDetails();
  };

  const handleProfileClose = () => setAnchorEl(null);

  const handleLogout = () => {
    handleProfileClose();
    localStorage.removeItem("studentId"); // optionally clear studentId on logout
    navigate("/");
  };

  const handleLogoClick = () => {
    navigate("/view-timetable-student");
  };

  const fetchStudentDetails = async () => {
    try {
      const studentId = localStorage.getItem('studentId');
      if (!studentId) {
        console.error('No studentId found in localStorage.');
        return;
      }
      const response = await axios.get(`http://localhost:8080/student/${studentId}`);
      setStudentDetails(response.data);
    } catch (error) {
      console.error('Failed to fetch student details:', error);
    }
  };

  const drawer = (
    <Box>
      <Toolbar>
        <Box sx={{ width: "100%", display: "flex", justifyContent: "center" }}>
          <IconButton onClick={handleLogoClick}>
            <FitbitIcon fontSize="large" sx={{ color: 'white' }} />
          </IconButton>
        </Box>
      </Toolbar>
      <Divider sx={{ backgroundColor: "rgba(255,255,255,0.3)" }} />
      <List>
        {menuItems.map((item, index) => (
          <ListItem
            button
            key={index}
            onClick={() => handleNavigation(item.path)}
            className="menu-item"
          >
            <ListItemIcon sx={{ color: "#fff" }}>{item.icon}</ListItemIcon>
            <ListItemText primary={item.text} />
          </ListItem>
        ))}
      </List>
    </Box>
  );

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{
          backgroundColor: "#1976d2",
          width: { sm: `calc(100% - ${drawerWidth}px)` },
          ml: { sm: `${drawerWidth}px` },
        }}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: "none" } }}
          >
            <MenuIcon />
          </IconButton>

          <Box sx={{ flexGrow: 1, textAlign: "center" }}>
            <Typography
              component="div"
              sx={{ fontWeight: 600, color: 'white', fontSize: '20px' }}
              onClick={handleLogoClick}
            >
              Student Dashboard
            </Typography>
          </Box>

          <IconButton color="inherit" onClick={handleProfileClick}>
            <AccountCircleIcon fontSize="large" />
          </IconButton>

          <Menu
            anchorEl={anchorEl}
            open={Boolean(anchorEl)}
            onClose={handleProfileClose}
          >
            <MenuItem disabled>
              Logged in as: {studentDetails.userRole || "Student"}
            </MenuItem>
            <Divider />
            <MenuItem onClick={handleLogout}>
              <ExitToAppIcon fontSize="small" sx={{ mr: 1 }} />
              Logout
            </MenuItem>
          </Menu>
        </Toolbar>
      </AppBar>

      <Box
        component="nav"
        sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}
        aria-label="menu"
      >
        <Drawer
          variant="temporary"
          open={mobileOpen}
          onClose={handleDrawerToggle}
          ModalProps={{ keepMounted: true }}
          classes={{ paper: "drawer-paper" }}
          sx={{ display: { xs: "block", sm: "none" } }}
        >
          {drawer}
        </Drawer>
        <Drawer
          variant="permanent"
          open
          classes={{ paper: "drawer-paper" }}
          sx={{ display: { xs: "none", sm: "block" }, cursor: "pointer" }}
        >
          {drawer}
        </Drawer>
      </Box>

      <Box
        component="main"
        className="main-content"
        sx={{ width: { sm: `calc(100% - ${drawerWidth}px)` } }}
      >
        <Toolbar />
        {children}
      </Box>
    </Box>
  );
};

export default NavbarStudent;
import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { Link } from "react-router-dom";
import "./Navbar.css";
import { useTheme } from "../../context/ThemeContext";

const Navbar = () => {
  const { darkMode, toggleTheme } = useTheme();

  return (
    <nav
      className="navbar navbar-expand-lg sticky-top shadow"
    >
      <div className="container-fluid">
        <Link className="navbar-brand text-light" to="/">
          <i className="bi bi-book-half me-2"></i>
          <span>EduSync</span>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto align-items-center">
            {[
              { path: "/", label: "Home" },
              { path: "/about", label: "About" },
              { path: "/courses", label: "Courses" },
              { path: "/login", label: "Login" },
              { path: "/register", label: "Register" },
            ].map((item) => (
              <li className="nav-item" key={item.path}>
                <Link
                  className="nav-link text-light"
                  to={item.path}
                >
                  {item.label}
                </Link>
              </li>
            ))}
            <li className="nav-item ms-2">
              <button
                onClick={toggleTheme}
                className="btn btn-link nav-link border-0 p-0"
                aria-label="Toggle theme"
              >
                <i
                  className={`bi text-light bi-${darkMode ? "sun" : "moon"}-fill fs-5`}
                ></i>
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;

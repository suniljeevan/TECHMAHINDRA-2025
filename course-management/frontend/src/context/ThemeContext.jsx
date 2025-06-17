import React, { createContext, useState, useContext, useEffect } from "react";

const ThemeContext = createContext();

const lightTheme = {
  background: "#ffffff",
  text: "#000000",
  primary: "#2575fc",
  secondary: "#6e8efb",
};

const darkTheme = {
  background: "#212429",
  text: "#ffffff",
  primary: "#1a60d5",
  secondary: "#2575fc",
};

export const ThemeProvider = ({ children }) => {
  const [darkMode, setDarkMode] = useState(() => {
    const savedTheme = localStorage.getItem("theme");
    return savedTheme === "dark";
  });

  const theme = darkMode ? darkTheme : lightTheme;

  useEffect(() => {
    localStorage.setItem("theme", darkMode ? "dark" : "light");
    document.documentElement.setAttribute(
      "data-theme",
      darkMode ? "dark" : "light"
    );
  }, [darkMode]);

  const toggleTheme = () => {
    setDarkMode(!darkMode);
  };

  return (
    <ThemeContext.Provider value={{ darkMode, toggleTheme, theme }}>
      {children}
    </ThemeContext.Provider>
  );
};

export const useTheme = () => {
  const context = useContext(ThemeContext);
  if (context === undefined) {
    throw new Error("useTheme must be used within a ThemeProvider");
  }
  return context;
};

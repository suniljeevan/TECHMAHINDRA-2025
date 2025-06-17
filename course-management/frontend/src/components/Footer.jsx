import React from "react";

const Footer = () => {
  return (
    <footer className="text-light py-responsive text-center py-3">
      <p className="body-small mb-0">
        &copy; {new Date().getFullYear()} EduSync. All Rights Reserved.
      </p>
    </footer>
  );
};

export default Footer;

import React from "react";
import Navbar from "../navbar/Navbar";
import {
  FaUsers,
  FaLightbulb,
  FaUniversalAccess,
  FaStar,
  FaCode,
  FaDatabase,
  FaPalette,
  FaTools,
  FaClipboardCheck,
} from "react-icons/fa";
import "./AboutPage.css";
import Footer from "../Footer";

const AboutPage = () => {
  const teamMembers = [
    {
      name: "M Grishma",
      role: "Frontend Developer",
      icon: FaCode,
      colorClass: "frontend",
    },
    {
      name: "Dilip D",
      role: "Backend Developer",
      icon: FaDatabase,
      colorClass: "backend",
    },
    {
      name: "Sanjana B",
      role: "UI/UX Designer",
      icon: FaPalette,
      colorClass: "design",
    },
    {
      name: "Harshini Vijendra Kumar",
      role: "Full Stack Developer",
      icon: FaTools,
      colorClass: "fullstack",
    },
    {
      name: "Bhavith Mourya R",
      role: "Quality Assurance",
      icon: FaClipboardCheck,
      colorClass: "qa",
    },
  ];

  return (
    <>
      <Navbar />
      <div className="about-page">
        <section className="about-hero">
          <div className="container">
            <div className="row justify-content-center w-100">
              <div className="col-lg-8 text-center">
                <h1 className="display-1">About EduSync</h1>
                <p className="lead mt-4">
                  Empowering learners through innovative course management and
                  personalized education
                </p>
              </div>
            </div>
          </div>
        </section>

        <section className="mission-section">
          <div className="container">
            <div className="row justify-content-center">
              <div className="col-lg-8 col-md-10 text-center">
                <h2 className="h1 fw-bold mb-4">Our Mission</h2>
                <p className="lead">
                  At EduSync, we're dedicated to transforming education through
                  technology. Our platform provides a seamless experience for
                  both students and administrators, making course management and
                  learning more accessible and efficient than ever before.
                </p>
              </div>
            </div>
          </div>
        </section>

        <section className="team-section d-flex align-items-center justify-content-center">
          <div className="container-fluid px-4">
            <div className="row justify-content-center">
              <div className="col-12 text-center">
                <h2 className="h1 fw-bold mb-4">Meet Our Team</h2>
                <div className="team-grid">
                  {teamMembers.map((member, index) => (
                    <div
                      key={index}
                      className={`team-card ${member.colorClass}`}
                    >
                      <div className="team-card-content">
                        <div className="team-icon">
                          <member.icon />
                        </div>
                        <h3 className="h4 mb-2">{member.name}</h3>
                        <p className="mb-0">{member.role}</p>
                        <div className="card-overlay"></div>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </section>

        <section className="values-section d-flex align-items-center justify-content-center">
          <div className="container">
            <div className="row justify-content-center">
              <div className="col-lg-10 text-center">
                <h2 className="h1 fw-bold mb-4">Our Values</h2>
                <div className="values-grid">
                  <div className="d-flex justify-content-center align-items-stretch gap-4 fit-content">
                    <div className="value-card innovation">
                      <div className="icon-wrapper">
                        <FaLightbulb />
                      </div>
                      <h3 className="h4">Innovation</h3>
                      <p className="mb-0">
                        Continuously improving with cutting-edge technology
                      </p>
                      <div className="card-overlay"></div>
                    </div>
                    <div className="value-card accessibility">
                      <div className="icon-wrapper">
                        <FaUniversalAccess />
                      </div>
                      <h3 className="h4">Accessibility</h3>
                      <p className="mb-0">
                        Making education accessible to everyone
                      </p>
                      <div className="card-overlay"></div>
                    </div>
                    <div className="value-card excellence">
                      <div className="icon-wrapper">
                        <FaStar />
                      </div>
                      <h3 className="h4">Excellence</h3>
                      <p className="mb-0">
                        Maintaining high standards in everything
                      </p>
                      <div className="card-overlay"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>

      <Footer />
    </>
  );
};

export default AboutPage;

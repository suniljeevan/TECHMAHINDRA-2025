import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { FaGraduationCap, FaChartLine, FaHeadset } from "react-icons/fa";
import Navbar from "./navbar/Navbar";
import "./LandingPage.css";
import Footer from "./Footer";

const LandingPage = () => {
  return (
    <>
      <Navbar />

      {/* Hero Section */}
      <section className="hero-section section-padding d-flex align-items-center justify-content-center">
        <div className="hero-content">
          <div className="container text-center">
            <h1 className="hero-title display-1">
              Transform Your Future with EduSync
            </h1>
            <p className="body-large mb-responsive">
              Unlock your potential with cutting-edge courses and personalized
              learning
            </p>
            <div className="flex-responsive justify-content-center">
              <a
                href="/register"
                className="btn btn-custom btn-custom-primary btn-responsive"
              >
                Start Learning
              </a>
              <a
                href="/courses"
                className="btn btn-custom btn-custom-light btn-responsive"
              >
                Explore Courses
              </a>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
<section className="features-section section-padding d-flex align-items-center justify-content-center">
  <div className="container text-center">
    <h2 className="fw-bold mb-responsive h1">
      Manage Courses Seamlessly
    </h2>
    <div className="row justify-content-center">
      <div className="col-md-6 col-lg-3 mb-4">
        <div className="feature-card responsive-card mx-auto">
          <div className="feature-icon">
            <FaGraduationCap />
          </div>
          <h4 className="h3">Add Courses</h4>
          <p className="body">
            Quickly add new courses with relevant details like title, code, and description.
          </p>
        </div>
      </div>
      <div className="col-md-6 col-lg-3 mb-4">
        <div className="feature-card responsive-card mx-auto">
          <div className="feature-icon">
            <FaChartLine />
          </div>
          <h4 className="h3">Edit & Update</h4>
          <p className="body">
            Modify existing course information anytime with a simple and intuitive interface.
          </p>
        </div>
      </div>
      <div className="col-md-6 col-lg-3 mb-4">
        <div className="feature-card responsive-card mx-auto">
          <div className="feature-icon">
            <FaHeadset />
          </div>
          <h4 className="h3">View & Organize</h4>
          <p className="body">
            Easily browse all available courses and keep them organized for quick access.
          </p>
        </div>
      </div>
      <div className="col-md-6 col-lg-3 mb-4">
        <div className="feature-card responsive-card mx-auto">
          <div className="feature-icon">
            <FaGraduationCap />
          </div>
          <h4 className="h3">Delete with Control</h4>
          <p className="body">
            Remove outdated or incorrect courses safely, ensuring your system stays up-to-date.
          </p>
        </div>
      </div>
    </div>
  </div>
</section>


      {/* CTA Section */}
      <section className="cta-section section-padding text-center d-flex align-items-center justify-content-center">
        <div className="container">
          <h2 className="fw-bold mb-responsive h1">
            Ready to Begin Your Journey?
          </h2>
          <p className="body-large mb-responsive">
            Join our community of learners today
          </p>
          <div className="flex-responsive justify-content-center">
            <a
              href="/register"
              className="btn btn-custom btn-custom-primary btn-responsive"
            >
              Get Started
            </a>
            <a
              href="/login"
              className="btn btn-custom btn-custom-light btn-responsive"
            >
              Sign In
            </a>
          </div>
        </div>
      </section>

      {/* Footer */}
      <Footer />
      <div className="hero-animation"></div>
    </>
  );
};

export default LandingPage;

import axios from 'axios';
import React, { useEffect, useState } from 'react';
import SideBar from './SideBar';
import Header from './Header';
import Footer from './Footer';
import "../../src/assets/css/profilepage.css";

const ProfilePage = () => {
  const [profileInfo, setProfileInfo] = useState(null);
  const [loading, setLoading] = useState(true);  // <-- Add loading state

  useEffect(() => {
    fetchProfileInfo();
  }, []);

  const fetchProfileInfo = async () => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get("http://localhost:8083/user/get-profile", {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      console.log('Profile API Response:', response.data);
      setProfileInfo(response.data.users);
    } catch (error) {
      console.error("Error fetching profile info:", error);
      if (error.response && error.response.status === 401) {
        console.log("Unauthorized access. Please log in again.");
        // Redirect to login
        window.location.href = '/login';
      } else {
        console.log("An error occurred while fetching profile info.");
      }
    } finally {
      setLoading(false); // <-- Always set loading to false
    }
  };

  if (loading) {
    return <div>Loading...</div>;  // <-- Show loading while fetching
  }

  if (!profileInfo) {
    // If after loading, still no profile info (meaning unauthorized or invalid user)
    return window.location.href = '/login';
  }

  return (
    <>
      <SideBar />
      <Header />
      <div className="profile-container">
        <h1>Profile Page</h1>
        <div className="profile-info">
          <p>Name: {profileInfo.name}</p>
          <p>Email: {profileInfo.email}</p>
          <p>Role: {profileInfo.role}</p>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default ProfilePage;

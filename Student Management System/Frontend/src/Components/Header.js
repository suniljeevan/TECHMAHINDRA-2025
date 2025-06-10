import React, { useEffect, useState } from 'react';
import "../assets/css/header.css"
import axios from 'axios';


const Header = () => {
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
    <header className='header'>
      <div class="hamburgers">
        <label class="hamburger">
          <input type="checkbox" />
          <span class="bar"></span>
          <span class="bar"></span>
          <span class="bar"></span>
        </label>
      </div>
      <div className='header-right'>
        <span>Welcome, {profileInfo.name}</span>
      </div>
    </header>
  )
}

export default Header
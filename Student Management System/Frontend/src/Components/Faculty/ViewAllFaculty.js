import React, { useState, useEffect } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import '../../assets/css/viewAllFaculty.css';
import axios from 'axios';

const ViewAllFaculty = () => {
    const [faculty, setFaculty] = useState([]);

    const viewAllFaculty = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await axios.get("http://localhost:8083/faculty/get-all", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(response.data);
            setFaculty(response.data.facultyList);
        } catch (error) {
            console.error("Error fetching faculty:", error);
        }
    };

    useEffect(() => {
        viewAllFaculty();
    }, []);

    return (
        <>
            <SideBar />
            <Header />
            <div className="view-all-faculty">
                <h1>View All Faculty</h1>
                <div className="notifications-cards">
                    {faculty.map((facultyMember) => (
                        <div className="notification" key={facultyMember.facultyId}>
                            <div className="notiglow"></div>
                            <div className="notiborderglow"></div>
                            <div className="notititle">{facultyMember.name}</div>
                            <div className="notibody">Designation: {facultyMember.designation}</div>
                            <div className="notibody">Department: {facultyMember.department}</div>
                            <div className="notibody">Email: {facultyMember.email}</div>
                            <div className="notibody">Phone No: {facultyMember.phone}</div>
                        </div>
                    ))}
                </div>
            </div>
            <Footer />
        </>
    );
};

export default ViewAllFaculty;

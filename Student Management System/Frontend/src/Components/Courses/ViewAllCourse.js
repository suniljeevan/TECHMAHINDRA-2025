import React, { useEffect, useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import axios from 'axios';
import '../../assets/css/viewAllStudents.css';
import '../../assets/css/style.css';

const ViewAllCourses = () => {
    const [courses, setCourses] = useState([]);

    const fetchAllCourses = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await axios.get("http://localhost:8083/course/all", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            setCourses(response.data.courseList || []); // Safely access courseList
        } catch (error) {
            console.error("Error fetching courses:", error);
        }
    };

    useEffect(() => {
        fetchAllCourses();
    }, []);

    return (
        <>
            <SideBar />
            <Header />
            <div className="main-content">
                {courses.map((course) => (
                    <div className="card" key={course.courseId}>
                        <div className="container">
                            <div className="right">
                                <div className="text-wrap">
                                    <p className="text-content">
                                        <strong>{course.courseName}</strong> ({course.courseCode})
                                    </p>
                                    <p className="time">{course.courseDescription}</p>
                                    <p className="time">
                                        Duration: {course.courseDuration} hrs | Credits: {course.courseCredits}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <Footer />
        </>
    );
};

export default ViewAllCourses;

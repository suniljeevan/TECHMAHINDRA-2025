import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import Footer from '../Footer';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import '../../assets/css/manageCourse.css';

const ManageCourse = () => {
    const [courses, setCourses] = useState([]);
    const navigate = useNavigate();

    const fetchCourses = async () => {
        try {
            const token = localStorage.getItem('token');
            const response = await axios.get('http://localhost:8083/course/all', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setCourses(response.data.courseList || []);
        } catch (error) {
            toast.error("Failed to fetch courses");
        }
    };

    const deleteCourse = async (id) => {
        try {
            const token = localStorage.getItem('token');
            await axios.delete(`http://localhost:8083/course/delete/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            toast.success("Course deleted successfully");
            fetchCourses();
        } catch (error) {
            toast.error("Failed to delete course");
        }
    };

    useEffect(() => {
        fetchCourses();
    }, []);

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="manage-course-container">
                <h2>Manage Courses</h2>
                <div className="cards">
                    {courses.map(course => (
                        <div key={course.courseId} className="card">
                            <div className="container">
                                <div className="right">
                                    <p><strong>{course.courseName}</strong> ({course.courseCode})</p>
                                    <p>{course.courseDescription}</p>
                                    <p>Duration: {course.courseDuration} hrs | Credits: {course.courseCredits}</p>
                                    <div className="button-wrap">
                                        <button className="primary-cta" onClick={() => navigate(`/course/updateCourse/${course.courseId}`)}>Edit</button>
                                        <button className="secondary-cta" onClick={() => deleteCourse(course.courseId)}>Delete</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
            <Footer />
        </>
    );
};

export default ManageCourse;

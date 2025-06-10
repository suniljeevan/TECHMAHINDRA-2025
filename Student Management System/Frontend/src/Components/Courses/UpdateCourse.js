import React, { useEffect, useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';

const UpdateCourse = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [course, setCourse] = useState({
        name: '',
        code: '',
        description: '',
        duration: '',
        credits: ''
    });

    const fetchCourse = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await axios.get(`http://localhost:8083/course/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            const data = response.data.course;
            setCourse({
                name: data.courseName,
                code: data.courseCode,
                description: data.courseDescription,
                duration: data.courseDuration,
                credits: data.courseCredits
            });
        } catch (error) {
            toast.error("Failed to load course details");
        }
    };

    const handleUpdate = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem("token");
            await axios.put(`http://localhost:8083/course/update/${id}`, {
                courseId: parseInt(id),
                ...course
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            });
            toast.success("Course updated successfully");
            navigate('/course/manageCourse');
        } catch (error) {
            toast.error("Failed to update course");
        }
    };

    useEffect(() => {
        fetchCourse();
    }, []);

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="add-course-container">
                <h2 className="form-title">Update Course</h2>
                <form className="course-form-grid" onSubmit={handleUpdate}>
                    <div className="form-group">
                        <label>Course Name</label>
                        <input type="text" value={course.name} onChange={(e) => setCourse({ ...course, name: e.target.value })} required />
                    </div>
                    <div className="form-group">
                        <label>Course Code</label>
                        <input type="text" value={course.code} onChange={(e) => setCourse({ ...course, code: e.target.value })} required />
                    </div>
                    <div className="form-group">
                        <label>Course Description</label>
                        <textarea value={course.description} onChange={(e) => setCourse({ ...course, description: e.target.value })} required />
                    </div>
                    <div className="form-group">
                        <label>Course Duration (in hours)</label>
                        <input type="number" value={course.duration} onChange={(e) => setCourse({ ...course, duration: e.target.value })} required />
                    </div>
                    <div className="form-group">
                        <label>Course Credits</label>
                        <input type="number" value={course.credits} onChange={(e) => setCourse({ ...course, credits: e.target.value })} required />
                    </div>
                    <div className="form-group full-width">
                        <button type="submit" className="submit-btn">Update Course</button>
                    </div>
                </form>
            </div>
            <Footer />
        </>
    );
};

export default UpdateCourse;

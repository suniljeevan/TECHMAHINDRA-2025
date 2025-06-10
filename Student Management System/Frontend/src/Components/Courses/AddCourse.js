import React, { useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import '../../assets/css/addCourse.css';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';

const AddCourse = () => {
    const [name, setName] = useState('');
    const [code, setCode] = useState('');
    const [description, setDescription] = useState('');
    const [duration, setDuration] = useState('');
    const [credits, setCredits] = useState('');

    const handleAddCourse = async (e) => {
        e.preventDefault();

        try {
            const token = localStorage.getItem("token");

            if (!token) {
                toast.error("Unauthorized: No token found");
                return;
            }

            const response = await axios.post(
                "http://localhost:8083/course/add",
                {
                    name,
                    code,
                    description,
                    duration,
                    credits
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json"
                    }
                }
            );

            toast.success(response.data.message || "Course added successfully");

        } catch (error) {
            console.error(error);
            const errMsg = error.response?.data?.message || "Something went wrong";
            toast.error(errMsg);
        }
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="add-course-container">
                <h2 className="form-title">Add Course</h2>
                <form className="course-form-grid" onSubmit={handleAddCourse}>
                    <div className="form-group">
                        <label>Course Name</label>
                        <input type="text" placeholder="Enter course name" required onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Course Code</label>
                        <input type="text" placeholder="Enter course code" required onChange={(e) => setCode(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Course Description</label>
                        <textarea placeholder="Enter course description" required onChange={(e) => setDescription(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Course Duration (in hours)</label>
                        <input type="number" placeholder="Enter duration" required onChange={(e) => setDuration(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Course Credits</label>
                        <input type="number" placeholder="Enter credits" required onChange={(e) => setCredits(e.target.value)} />
                    </div>
                    <div className="form-group full-width">
                        <button type="submit" className="submit-btn">Add Course</button>
                    </div>
                </form>
            </div>
            <Footer />
        </>
    );
};

export default AddCourse;

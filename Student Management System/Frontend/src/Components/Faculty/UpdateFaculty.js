import React, { useState, useEffect } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import { toast, ToastContainer } from 'react-toastify';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../../assets/css/addFaculty.css';

const UpdateFaculty = () => {
    const { facultyId } = useParams(); // Make sure the route is like /faculty/update/:facultyId

    const [facultyName, setFacultyName] = useState("");
    const [facultyEmail, setFacultyEmail] = useState("");
    const [facultyPassword, setFacultyPassword] = useState("");
    const [facultyPhone, setFacultyPhone] = useState("");
    const [facultyGender, setFacultyGender] = useState("");
    const [facultyDepartment, setFacultyDepartment] = useState("");
    const [facultyDesignation, setFacultyDesignation] = useState("");
    const [facultyRole, setFacultyRole] = useState("");

    useEffect(() => {
        const fetchFaculty = async () => {
            try {
                const token = localStorage.getItem("token");
                const response = await axios.get(`http://localhost:8083/faculty/get/${facultyId}`, {
                    headers: { Authorization: `Bearer ${token}` }
                });
    
                const faculty = response.data.faculty;
                setFacultyName(faculty.name);
                setFacultyEmail(faculty.email);
                setFacultyPhone(faculty.phone);
                setFacultyGender(faculty.gender || ""); // fallback to "" if gender is not returned
                setFacultyDepartment(faculty.department);
                setFacultyDesignation(faculty.designation);
                setFacultyRole(faculty.role);
            } catch (error) {
                toast.error("Failed to fetch faculty details");
            }
        };
    
        fetchFaculty();
    }, [facultyId]);

    const handleUpdateSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem("token");

            const response = await axios.put(
                `http://localhost:8083/faculty/update/${facultyId}`,
                {
                    facultyName,
                    facultyEmail,
                    facultyPassword: facultyPassword || undefined, // only send if not empty
                    facultyPhone,
                    facultyGender,
                    facultyDepartment,
                    facultyDesignation,
                    facultyRole
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json"
                    }
                }
            );

            if (response.data.statusCode === 200) {
                toast.success("Faculty updated successfully");
            } else {
                toast.error(response.data.message || "Update failed");
            }
        } catch (error) {
            console.error(error);
            toast.error(error.response?.data?.message || "Error updating faculty");
        }
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="add-faculty-container">
                <h2 className="form-title">Update Faculty</h2>
                <form className="student-form-grid" onSubmit={handleUpdateSubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input
                            type="text"
                            required
                            value={facultyName}
                            onChange={(e) => setFacultyName(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            required
                            value={facultyEmail}
                            onChange={(e) => setFacultyEmail(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            value={facultyPassword}
                            onChange={(e) => setFacultyPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Phone</label>
                        <input
                            type="tel"
                            required
                            value={facultyPhone}
                            onChange={(e) => setFacultyPhone(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Gender</label>
                        <select
                            required
                            value={facultyGender}
                            onChange={(e) => setFacultyGender(e.target.value)}
                        >
                            <option value="">Select Gender</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Department</label>
                        <input
                            type="text"
                            required
                            value={facultyDepartment}
                            onChange={(e) => setFacultyDepartment(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Designation</label>
                        <input
                            type="text"
                            required
                            value={facultyDesignation}
                            onChange={(e) => setFacultyDesignation(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Role</label>
                        <select
                            required
                            value={facultyRole}
                            onChange={(e) => setFacultyRole(e.target.value)}
                        >
                            <option value="">Select Role</option>
                            <option value="admin">Admin</option>
                            <option value="faculty">Faculty</option>
                            <option value="student">Student</option>
                            <option value="mentor">Mentor</option>
                            <option value="parents">Parents</option>
                        </select>
                    </div>
                    <div className="form-group full-width">
                        <button type="submit" className="submit-btn">Update</button>
                    </div>
                </form>
            </div>
            <Footer />
        </>
    );
};

export default UpdateFaculty;

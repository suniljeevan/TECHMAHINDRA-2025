import React, { useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import { toast, ToastContainer } from 'react-toastify';
import axios from 'axios';
import '../../assets/css/addFaculty.css';


const AddFaculty = () => {
    const [facultyName, setFacultyName] = useState("");
    const [facultyEmail, setFacultyEmail] = useState("");
    const [facultyPassword, setFacultyPassword] = useState("");
    const [facultyPhone, setFacultyPhone] = useState("");
    const [facultyGender, setFacultyGender] = useState("");
    const [facultyDepartment, setFacultyDepartment] = useState("");
    const [facultyDesignation, setFacultyDesignation] = useState("");
    const [role, setRole] = useState("");

    const handelFacultySubmit = async (e) => {
        e.preventDefault();
        console.log(facultyName, facultyEmail, facultyPassword, facultyPhone, facultyGender, facultyDepartment, facultyDesignation, role);

        try {
            const token = localStorage.getItem("token");

            if (!token) {
                toast.error("Unauthorized: No token found");
                return;
            }

            const response = await axios.post(
                "http://localhost:8083/faculty/register",
                {
                    facultyName: facultyName,
                    facultyEmail: facultyEmail,
                    facultyPassword: facultyPassword,
                    facultyPhone: facultyPhone,
                    facultyGender: facultyGender, // optional, if not used in backend, remove it
                    facultyDepartment: facultyDepartment,
                    facultyDesignation: facultyDesignation,
                    facultyRole: role // <-- 'role' should be renamed to 'facultyRole'
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json"
                    }
                }
            );

            console.log(response.data);
            if (response.data.statusCode === 200) {
                toast.success(response.data.message || "Faculty registered successfully");
            }

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
            <div className="add-faculty-container">
                <h2 className="form-title">Add Faculty</h2>
                <form className="student-form-grid" onSubmit={handelFacultySubmit}>
                    <div className="form-group">
                        <label>Name</label>
                        <input
                            type="text"
                            placeholder="Enter full name"
                            required
                            value={facultyName}
                            onChange={(e) => setFacultyName(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            placeholder="Enter email"
                            required
                            value={facultyEmail}
                            onChange={(e) => setFacultyEmail(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Enter password"
                            required
                            value={facultyPassword}
                            onChange={(e) => setFacultyPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Phone</label>
                        <input
                            type="tel"
                            placeholder="Enter phone number"
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
                            placeholder="Enter department"
                            required
                            value={facultyDepartment}
                            onChange={(e) => setFacultyDepartment(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Designation</label>
                        <input
                            type="text"
                            placeholder="Enter program"
                            required
                            value={facultyDesignation}
                            onChange={(e) => setFacultyDesignation(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Role</label>
                        <select
                            required
                            value={role}
                            onChange={(e) => setRole(e.target.value)}
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
                        <button type="submit" className="submit-btn">Submit</button>
                    </div>
                </form>
            </div>
            <Footer />
        </>
    );
};

export default AddFaculty;

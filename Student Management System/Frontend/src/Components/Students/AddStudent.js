import React, { useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import '../../assets/css/addStudent.css'; // CSS import
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';

const AddStudent = () => {
    //name, email, password, phone, gender, rollNumber, department, program, batch, role
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phone, setPhone] = useState("");
    const [gender, setGender] = useState("");
    const [rollNumber, setRollNumber] = useState("");
    const [department, setDepartment] = useState("");
    const [program, setProgram] = useState("");
    const [batch, setBatch] = useState("");
    const [role, setRole] = useState("");




    const handelAddStudent = async (e) => {
        e.preventDefault();

        console.log(name, email, password, phone, gender, rollNumber, department, program, batch, role);

        try {
            // Get the token from localStorage (or wherever you stored it after login)
            const token = localStorage.getItem("token");

            // Make sure token exists
            if (!token) {
                toast.error("Unauthorized: No token found");
                return;
            }

            // Send POST request with Authorization header
            const response = await axios.post(
                "http://localhost:8083/student/register",
                {
                    name,
                    email,
                    password,
                    phone,
                    gender,
                    rollNumber,
                    department,
                    program,
                    batch,
                    role
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json"
                    }
                }
            );

            console.log(response.data);
            console.log(response.data.statusCode);

            if (response.data.statusCode === 200) {
                toast.success(response.data.message || "Student registered successfully");
            }
        } catch (error) {
            console.log(error);
            const errMsg = error.response?.data?.message || "Something went wrong";
            toast.error(errMsg);
        }
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="add-student-container">
                <h2 className="form-title">Add Student</h2>
                <form className="student-form-grid" onSubmit={handelAddStudent}>
                    <div className="form-group">
                        <label>Name</label>
                        <input type="text" placeholder="Enter full name" required onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" placeholder="Enter email" required onChange={(e) => setEmail(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" placeholder="Enter password" required onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Phone</label>
                        <input type="tel" placeholder="Enter phone number" required onChange={(e) => setPhone(e.target.value)} />
                    </div>
                    <div className="form-group" onChange={(e) => setGender(e.target.value)}>
                        <label>Gender</label>
                        <select required>
                            <option value="">Select Gender</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Roll Number</label>
                        <input type="text" placeholder="Enter roll number" required onChange={(e) => setRollNumber(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Department</label>
                        <input type="text" placeholder="Enter department" required onChange={(e) => setDepartment(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Program</label>
                        <input type="text" placeholder="Enter program" required onChange={(e) => setProgram(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Batch</label>
                        <input type="text" placeholder="Enter batch (e.g., 2021-2025)" required onChange={(e) => setBatch(e.target.value)} />
                    </div>
                    <div className="form-group" onChange={(e) => setRole(e.target.value)}>
                        <label>Role</label>
                        <select required>
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

export default AddStudent;
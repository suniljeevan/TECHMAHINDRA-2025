import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import '../../assets/css/addStudent.css';

const UpdateStudents = () => {
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

    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchStudent = async () => {
            try {
                const token = localStorage.getItem("token");
                if (!token) {
                    toast.error("Unauthorized: No token found");
                    return;
                }

                const response = await axios.get(
                    `http://localhost:8083/student/get/${id}`,
                    {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    }
                );

                const student = response?.data?.student;

                setName(student.name);
                setEmail(student.email);
                setPassword(student.password);
                setPhone(student.phone);
                setGender(student.gender);
                setRollNumber(student.rollNumber);
                setDepartment(student.department);
                setProgram(student.program);
                setBatch(student.batch);
                setRole(student.role);
            } catch (err) {
                console.error(err);
                toast.error("Failed to load student data");
            }
        };

        if (id) fetchStudent();
    }, [id]);

    const handleUpdateStudents = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem("token");
            const response = await axios.put(
                `http://localhost:8083/student/update/${id}`,
                {
                    id,
                    name, email, password, phone, gender,
                    rollNumber, department, program, batch, role
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (response.data.statusCode === 200) {
                toast.success(response.data.message);
                setTimeout(() => navigate("/student/manageStudents"), 2000);
            } else {
                toast.error(response.data.message);
            }
        } catch (error) {
            console.error(error);
            toast.error(error?.response?.data?.message || "Failed to update student");
        }
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="add-student-container">
                <h2 className="form-title">Update Student</h2>
                <form className="student-form-grid" onSubmit={handleUpdateStudents}>
                    <div className="form-group">
                        <label>Name</label>
                        <input type="text" required value={name} onChange={(e) => setName(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" required value={email} onChange={(e) => setEmail(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" required value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Phone</label>
                        <input type="tel" required value={phone} onChange={(e) => setPhone(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Gender</label>
                        <select required value={gender} onChange={(e) => setGender(e.target.value)}>
                            <option value="">Select Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Roll Number</label>
                        <input type="text" required value={rollNumber} onChange={(e) => setRollNumber(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Department</label>
                        <input type="text" required value={department} onChange={(e) => setDepartment(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Program</label>
                        <input type="text" required value={program} onChange={(e) => setProgram(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Batch</label>
                        <input type="text" required value={batch} onChange={(e) => setBatch(e.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Role</label>
                        <select required value={role} onChange={(e) => setRole(e.target.value)}>
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

export default UpdateStudents;

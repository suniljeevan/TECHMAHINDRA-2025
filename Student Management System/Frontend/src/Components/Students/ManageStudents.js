import React, { useEffect, useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const ManageStudents = () => {
    const [students, setStudents] = useState([]);
    const navigate = useNavigate();

    const getAllStudents = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await axios.get("http://localhost:8083/student/get-all", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(response.data);
            setStudents(response.data.studentList); // Ensure key matches backend JSON
        } catch (error) {
            console.error("Failed to fetch students:", error);
        }
    };

    useEffect(() => {
        getAllStudents();
    }, []);

    const handleEdit = (id) => {
        navigate(`/student/updateStudent/${id}`); // ✅ Fixed route
    };

    const handleDelete = async (id) => {
        try {
            const token = localStorage.getItem("token");
            if (!token) {
                toast.error("Unauthorized: No token found");
                return;
            }
    
            const response = await axios.delete(
                `http://localhost:8083/student/delete/${id}`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
    
            if (response.data.statusCode === 200) {
                toast.success(response.data.message);
                getAllStudents();
            }
        } catch (error) {
            console.error("Failed to delete student:", error);
            toast.error("Failed to delete student");
        }
    };

    return (
        <>
        <SideBar />
        <Header />
        <ToastContainer />
        <table className="table-primary">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Student Phone</th>
                    <th>Gender</th>
                    <th>Roll Number</th>
                    <th>Department</th>
                    <th>Program</th>
                    <th>Batch</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {students.map((student) => (
                    <tr key={student.id}>
                        <td>{student.id}</td>
                        <td>{student.name || "No Name Provided"}</td>
                        <td>{student.email || "No Email Provided"}</td>
                        <td>{student.phone || "No Phone Provided"}</td>
                        <td>{student.gender || "No Gender Provided"}</td>
                        <td>{student.rollNumber || "No Roll Number Provided"}</td>
                        <td>{student.department || "No Department Provided"}</td>
                        <td>{student.program || "No Program Provided"}</td>
                        <td>{student.batch || "No Batch Provided"}</td>
                        <td>{student.role || "No Role Provided"}</td>
                        <td>
                            <button className="btn btn-outline-warning" onClick={() => handleEdit(student.id)}>Edit</button>&nbsp;
                            <button className="btn btn-outline-danger" onClick={() => handleDelete(student.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
        
        <Footer />
    </>
    );
};

export default ManageStudents;

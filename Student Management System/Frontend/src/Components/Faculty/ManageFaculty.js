import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import Footer from '../Footer';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import '../../assets/css/manageFaculty.css';

const ManageFaculty = () => {
    const [facultyList, setFacultyList] = useState([]);
    const navigate = useNavigate();

    const fetchFaculties = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await axios.get("http://localhost:8083/faculty/get-all", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            setFacultyList(response.data.facultyList || []);
        } catch (err) {
            console.error(err);
            toast.error("Failed to fetch faculty");
        }
    }; 

    useEffect(() => {
        fetchFaculties();
    }, []);

    const handleEdit = (id) => {
        navigate(`/faculty/updateFaculty/${id}`);
    };

    const handleDelete = async (id) => {
        if (!id || isNaN(id)) {
            toast.error("Invalid faculty ID");
            console.error("Invalid faculty ID:", id);
            return;
        }
    
        try {
            const token = localStorage.getItem("token");
            const response = await axios.delete(`http://localhost:8083/faculty/delete/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            toast.success(response.data.message);
            fetchFaculties();
        } catch (error) {
            console.error(error);
            toast.error("Failed to delete faculty");
        }
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="table-responsive p-4">
                <h2>Manage Faculty</h2>
                <table className="table table-striped table-bordered">
                    <thead className="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Department</th>
                            <th>Designation</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {facultyList.map((faculty) => (
                            <tr key={faculty.facultyId}>
                                <td>{faculty.facultyId}</td>
                                <td>{faculty.name}</td>
                                <td>{faculty.email}</td>
                                <td>{faculty.phone}</td>
                                <td>{faculty.department}</td>
                                <td>{faculty.designation}</td>
                                <td>{faculty.role}</td>
                                <td>
                                    <button className="btn btn-outline-warning me-2" onClick={() => handleEdit(faculty.facultyId)}>Edit</button>
                                    <button className="btn btn-outline-danger" onClick={() => handleDelete(faculty.facultyId)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <Footer />
        </>
    );
};

export default ManageFaculty;

import React, { useEffect, useState } from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import Footer from '../Footer'
import axios from 'axios'
import '../../assets/css/viewAllStudents.css'
import '../../assets/css/style.css'

const ViewAllStudents = () => {
    const [students, setStudents] = useState([])

    const viewAllStudents = async () => {
        try {
            const token = localStorage.getItem("token");
            const response = await axios.get("http://localhost:8083/student/get-all", {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(response.data);
            setStudents(response.data.studentList);
        } catch (error) {
            console.error("Error fetching faculty:", error);
        }
    };

    useEffect(() => {
        viewAllStudents()
    }, [])

    return (
        <>
            <SideBar />
            <Header />
            <div className="main-content">
                {students.map((student) => (
                    <div className="card" key={student.id}>
                    <div className="container">
                        <div className="right">
                            <div className="text-wrap">
                                <p className="text-content">
                                    <a className="text-link" href="#">{student.name}</a>
                                </p>
                                <p className="time">{student.rollNumber}</p>
                            </div>
                            <div className="button-wrap">
                                <button className="primary-cta">View Profile</button>
                                <button className="secondary-cta">Mark as read</button>
                            </div>
                        </div>
                    </div>
                </div>
                ))}
            </div>
            <Footer />
        </>
    )
}

export default ViewAllStudents

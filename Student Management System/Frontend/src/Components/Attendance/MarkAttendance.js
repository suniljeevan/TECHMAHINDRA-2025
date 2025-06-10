import React, { useState } from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import Footer from '../Footer'
import '../../assets/css/markAttendance.css'
import Select from 'react-select'
import { toast, ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import axios from 'axios'

const MarkAttendance = () => {
    const [branchOptionsPicked, setBranchOptionsPicked] = useState(null)
    const [sectionOptionsPicked, setSectionOptionsPicked] = useState(null)
    const [subjectOptionsPicked, setSubjectOptionsPicked] = useState(null)
    const [dateOptionsPicked, setDateOptionsPicked] = useState("")
    const [students, setStudents] = useState([])

    const branchOptions = [
        { value: 'CSD', label: 'CSD' }
    ]

    const sectionOptions = [
        { value: '', label: '' },
        { value: '8CSD01', label: '8CSD01' },
        { value: '8CSD2', label: '8CSD02' },
        { value: '8CSD3', label: '8CSD03' },
        { value: '8CSD04', label: '8CSD04' }
    ]

    const subjectOptions = [
        { value: '', label: '' },
        { value: 'DSA', label: 'DSA' },
        { value: 'DBMS', label: 'DBMS' },
        { value: 'OS', label: 'OS' },
        { value: 'CN', label: 'CN' },
        { value: 'SE', label: 'SE' }
    ]

    const customStyles = {
        control: (provided) => ({
            ...provided,
            border: '1px solid #ccc',
            borderRadius: '4px',
            boxShadow: 'none',
            '&:hover': {
                borderColor: '#888',
            },
        }),
        option: (provided, state) => ({
            ...provided,
            backgroundColor: state.isSelected ? '#007bff' : 'white',
            color: state.isSelected ? 'white' : 'black',
            '&:hover': {
                backgroundColor: state.isSelected ? '#007bff' : '#f5f5f5',
            },
        }),
    }

    const handleSearch = async (e) => {
        e.preventDefault()

        if (!branchOptionsPicked || !sectionOptionsPicked || !subjectOptionsPicked === "") {
            toast.warning("Please select all fields!")
            return
        }

        const url = `http://localhost:8083/attendance/markAttendance/${branchOptionsPicked.value}/${sectionOptionsPicked.value}/${subjectOptionsPicked.value}`
        try {
            const response = await axios.get(url)
            if (response.status === 200) {
                if (response.data.length === 0) {
                    toast.warning("No students found!")
                } else {

                    toast.success("Students fetched successfully!")
                    setStudents(response.data)
                }
            }
        } catch (error) {
            toast.error("Failed to fetch students!")
            console.error(error)
        }
    }

    const handelSaveAttendance = async (e) => {
        e.preventDefault()
        try {
            if (!dateOptionsPicked) {
                toast.warning("Please select date!")
            } else {

                toast.success("Attendance saved successfully!")
            }

        } catch (error) {
            toast.error("Failed to save attendance!")
            console.error(error)

        }
    }




    return (
        <>
            <SideBar />
            <ToastContainer />
            <Header />
            <div className="mark-attendance-container">
                <h1>Mark Attendance</h1>
                <div className="select-container">
                    <Select placeholder="Select Branch" options={branchOptions} styles={customStyles} onChange={(option) => setBranchOptionsPicked(option)} />
                    <Select placeholder="Select Section" options={sectionOptions} styles={customStyles} onChange={(option) => setSectionOptionsPicked(option)} />
                    <Select placeholder="Select Subject" options={subjectOptions} styles={customStyles} onChange={(option) => setSubjectOptionsPicked(option)} />
                    <button onClick={handleSearch}>Search</button>
                </div>

                <p className="selected-labels">
                    Mark Attendance for Branch: {branchOptionsPicked?.label} |
                    Section: {sectionOptionsPicked?.label} |
                    Subject: {subjectOptionsPicked?.label} |
                    <input
                        className="attendanceDatePicker"
                        type="date"
                        onChange={(e) => setDateOptionsPicked(e.target.value)}
                    />
                    <button onClick={handelSaveAttendance}>Save</button>
                </p>

                <div className="attendance-container">
                    {students.map((student) => (
                        <label key={student.attendanceId} className="card-wrapper">
                            <input
                                type="checkbox"
                                className="attendance-checkbox"
                                name={`student-${student.attendanceId}`}
                            />
                            <div className="card">
                                <div className="container">
                                    <div className="right">
                                        <div className="text-wrap">
                                            <p className="text-content">
                                                <a className="text-link" href="/">{student.name}</a>
                                            </p>
                                            <p className="time">{student.rollNumber}</p>
                                        </div>
                                        <div className="button-wrap">
                                            <button className="primary-cta">View Profile</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </label>
                    ))}
                </div>

            </div>
            <Footer />
        </>
    )
}

export default MarkAttendance

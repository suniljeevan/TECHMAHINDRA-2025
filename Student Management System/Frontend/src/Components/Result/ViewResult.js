import React from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import Footer from '../Footer'
import '../../assets/css/viewResult.css'
import { useState } from 'react'
import UMSER from '../../assets/img/University_Management_System_ER.png'
import Iframe from 'react-iframe'


const ViewResult = () => {

    const [rollNo, setRollNo] = useState("");
    const [semester, setSemester] = useState("");
    const URL = `https://www.presidencyexams.com/results/2025-01-ET/${rollNo}.pdf`
    const [RESULT, setResult] = useState(URL);
    const [showResult, setShowResult] = useState(false);

    const handelResultSubmit = async (e) => {
        e.preventDefault();
        console.log(rollNo, semester);

        const RESULT = `https://www.presidencyexams.com/results/2025-01-ET/${rollNo}.pdf`
        setResult(RESULT);
        setShowResult(true);

    }

    return (
        <>
            <SideBar />
            <Header />
            <div className="view-result-container">
                <h1>View Result</h1>
                <div className="container">
                    <form className="student-form-grid" onSubmit={handelResultSubmit}>
                        <div className="form-group">
                            <label>Enter Roll no.</label>
                            <input type="text" placeholder="Enter Roll no." required onChange={(e) => setRollNo(e.target.value)} />
                        </div>
                        <div className="form-group" onChange={(e) => setSemester(e.target.value)}>
                            <label>Semester</label>
                            <select required>
                                <option value="">Select Semester</option>
                                <option value="sem1">Sem 1</option>
                                <option value="sem2">Sem 2</option>
                                <option value="sem3">Sem 3</option>
                                <option value="sem4">Sem 4</option>
                                <option value="sem5">Sem 5</option>
                                <option value="sem6">Sem 6</option>
                                <option value="sem7">Sem 7</option>
                                <option value="sem8">Sem 8</option>
                            </select>
                        </div>
                        <div className="form-group full-width">
                            <button type="submit" className="submit-btn">Submit</button>
                        </div>
                    </form>  
                </div>
                <Iframe url={RESULT} id='resultFrame'
                        className="iframe"
                        display="block"
                        position="relative" />
            </div>
            <Footer />
        </>
    )
}

export default ViewResult
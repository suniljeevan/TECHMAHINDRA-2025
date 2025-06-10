import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import SideBar from '../SideBar';
import Header from '../Header';
import '../../assets/css/viewAllQuestion.css';
import { useNavigate } from 'react-router-dom';


const ViewAllQuestions = () => {
    const [questions, setQuestions] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchQuestions = async () => {
            try {
                const response = await axios.get("http://localhost:8082/question/allQuestions");
                setQuestions(response.data);
            } catch (error) {
                console.error("Error fetching questions:", error);
                toast.error("Failed to fetch questions");
            }
        };

        fetchQuestions();
    }, []);

    const handleDelete = async (id) => {
        try {
            const response = await axios.delete(`http://localhost:8082/question/delete/${id}`);
            if (response.status === 200) {
                toast.success("Question deleted successfully");
                setQuestions(questions.filter((q) => q.id !== id)); // Remove the deleted question from state
            }
        } catch (error) {
            console.error("Error deleting question:", error);
            toast.error("Failed to delete question");
        }
    };

    const handleUpdate = async (id) => {
        navigate(`/question/update/${id}`);
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="questions-container">
                <h2>All Questions</h2>
                <ul>
                    {questions.map((q, index) => (
                        <li key={index}>
                            <strong>{q.questionTitle}</strong><br />
                            A. {q.option1}, B. {q.option2}, C. {q.option3}, D. {q.option4}<br />
                            <em>Answer: {q.rightAnswer} | Level: {q.difficultylevel} | Category: {q.category}</em><br />
                            <button className='update-btn' onClick={() => handleUpdate(q.id)}>Update</button>
                            <button className='delete-btn' onClick={() => handleDelete(q.id)}>Delete</button>
                        </li>
                    ))}
                </ul>
            </div>
        </>
    );
};

export default ViewAllQuestions;

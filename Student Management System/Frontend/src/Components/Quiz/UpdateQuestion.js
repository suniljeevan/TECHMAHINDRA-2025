import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import { useParams, useNavigate } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';
import SideBar from '../SideBar';
import Header from '../Header';
import '../../assets/css/addQuestion.css';
import Footer from '../Footer';

const UpdateQuestion = () => {
    const { id } = useParams(); // Get question ID from URL
    const navigate = useNavigate();

    const [question, setQuestion] = useState({
        questionTitle: '',
        option1: '',
        option2: '',
        option3: '',
        option4: '',
        rightAnswer: '',
        difficultylevel: '',
        category: ''
    });

    useEffect(() => {
        const fetchQuestion = async () => {
            try {
                const res = await axios.get(`http://localhost:8082/question/getQuestion/${id}`);
                setQuestion(res.data);
            } catch (error) {
                toast.error("Failed to load question");
            }
        };

        fetchQuestion();
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setQuestion((prev) => ({ ...prev, [name]: value }));
    };

    const handleUpdate = async (e) => {
        e.preventDefault();
        try {
            const res = await axios.put(`http://localhost:8082/question/update/${id}`, {
                ...question,
                id: parseInt(id)
            });

            if (res.status === 200) {
                toast.success("Question updated successfully");
                setTimeout(() => navigate("/quiz/manageQuestion"), 1500);
            }
        } catch (error) {
            toast.error("Failed to update question");
        }
    };

    return (
        <>
            <SideBar />
            <Header />
            <ToastContainer />
            <div className="add-question-container">
                <h2 className="form-title">Update Question</h2>
                <form className="question-form" onSubmit={handleUpdate}>
                    <div className="form-group full-width">
                        <label>Question Title</label>
                        <input
                            type="text"
                            name="questionTitle"
                            value={question.questionTitle}
                            onChange={handleChange}
                            required
                        />
                    </div>

                    <div className="input-grid">
                        {['option1', 'option2', 'option3', 'option4', 'rightAnswer'].map((field) => (
                            <div className="form-group" key={field}>
                                <label>{field.replace('option', 'Option ').replace('rightAnswer', 'Right Answer')}</label>
                                <input
                                    type="text"
                                    name={field}
                                    value={question[field]}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                        ))}

                        <div className="form-group">
                            <label>Difficulty Level</label>
                            <select
                                name="difficultylevel"
                                value={question.difficultylevel}
                                onChange={handleChange}
                                required
                            >
                                <option value="">Select Difficulty</option>
                                <option value="Easy">Easy</option>
                                <option value="Medium">Medium</option>
                                <option value="Hard">Hard</option>
                            </select>
                        </div>

                        <div className="form-group full-width">
                            <label>Category</label>
                            <input
                                type="text"
                                name="category"
                                value={question.category}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    <button className="submit-btn" type="submit">Update Question</button>
                </form>
            </div>
            <Footer />
        </>
    );
};

export default UpdateQuestion;

import React, { useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import SideBar from '../SideBar';
import Header from '../Header';
import Footer from '../Footer';
import '../../assets/css/addQuestion.css';

const AddQuestion = () => {
    const [questionTitle, setQuestionTitle] = useState("");
    const [option1, setOption1] = useState("");
    const [option2, setOption2] = useState("");
    const [option3, setOption3] = useState("");
    const [option4, setOption4] = useState("");
    const [rightAnswer, setRightAnswer] = useState("");
    const [difficultyLevel, setDifficultyLevel] = useState("");
    const [category, setCategory] = useState("");

    const handleAddQuestion = async (e) => {
        e.preventDefault();

        try {

            const response = await axios.post(
                "http://localhost:8082/question/add",
                {
                    questionTitle,
                    option1,
                    option2,
                    option3,
                    option4,
                    rightAnswer,
                    difficultylevel: difficultyLevel,
                    category
                }
            );

            if (response.status === 201 && response.data === "success") {
                toast.success("Question added successfully");
                // Clear form fields
                setQuestionTitle("");
                setOption1("");
                setOption2("");
                setOption3("");
                setOption4("");
                setRightAnswer("");
                setDifficultyLevel("");
                setCategory("");
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
            <div className="add-question-container">
                <h2 className="form-title">Add Question</h2>
                <form className="question-form" onSubmit={handleAddQuestion}>
                    <div className="form-group full-width">
                        <label>Question Title</label>
                        <input
                            type="text"
                            placeholder="Enter question"
                            value={questionTitle}
                            onChange={(e) => setQuestionTitle(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-grid">
                        <div className="form-group">
                            <label>Option 1</label>
                            <input
                                type="text"
                                value={option1}
                                onChange={(e) => setOption1(e.target.value)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Option 2</label>
                            <input
                                type="text"
                                value={option2}
                                onChange={(e) => setOption2(e.target.value)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Option 3</label>
                            <input
                                type="text"
                                value={option3}
                                onChange={(e) => setOption3(e.target.value)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Option 4</label>
                            <input
                                type="text"
                                value={option4}
                                onChange={(e) => setOption4(e.target.value)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Right Answer</label>
                            <input
                                type="text"
                                value={rightAnswer}
                                onChange={(e) => setRightAnswer(e.target.value)}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Difficulty Level</label>
                            <select
                                value={difficultyLevel}
                                onChange={(e) => setDifficultyLevel(e.target.value)}
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
                                placeholder="e.g., Chemistry, Physics"
                                value={category}
                                onChange={(e) => setCategory(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    <button className="submit-btn" type="submit">Add Question</button>
                </form>
            </div>
            <Footer />
        </>
    );
};

export default AddQuestion;

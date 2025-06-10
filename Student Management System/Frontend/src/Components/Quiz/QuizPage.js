import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SideBar from '../SideBar';
import Header from '../Header';
import '../../assets/css/quizPage.css'

const QuizPage = () => {
  const [questions, setQuestions] = useState([]);
  const [userAnswers, setUserAnswers] = useState({});
  const [score, setScore] = useState(null);
  const [error, setError] = useState(""); // To display error messages

  const fetchQuestions = async () => {
    try {
      const response = await axios.get('http://localhost:8082/question/allQuestions');
      const formattedQuestions = response.data.map((q) => ({
        question: q.questionTitle,
        options: [q.option1, q.option2, q.option3, q.option4],
        answer: q.rightAnswer,
      }));
      setQuestions(formattedQuestions);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchQuestions();
  }, []);

  const handleOptionChange = (questionIndex, selectedOption) => {
    setUserAnswers((prev) => ({
      ...prev,
      [questionIndex]: selectedOption,
    }));
  };

  const normalize = (str) => str?.toString().trim().toLowerCase();

  const handleSubmit = () => {
    // Check if all questions have been answered
    const unansweredQuestions = questions.filter((_, index) => !userAnswers[index]);
    if (unansweredQuestions.length > 0) {
      setError("Please answer all the questions before submitting.");
      return;
    } else {
      setError(""); // Clear error message if all questions are answered
    }

    let score = 0;
    questions.forEach((q, index) => {
      if (normalize(userAnswers[index]) === normalize(q.answer)) {
        score += 1;
      }
    });
    setScore(score);
  };

  const handleReset = () => {
    setUserAnswers({});
    setScore(null);
    setError(""); // Clear error message on reset
  };

  return (
    <>
      <SideBar />
      <Header />
      <div className="quiz-container">
        <h1>Quiz</h1>
        <div className="quiz-info">
          <p>Answer the following questions:</p>
          <p>Choose the correct answer from the options provided.</p>
          <p>Click on the "Submit" button to see your results.</p>
          <p>Click on the "Reset" button to start over.</p>
          <p>Good luck!</p>
        </div>
        <div className="quiz-result">
          <h4>Score: {score !== null ? `${score}/${questions.length}` : "--/--"}</h4>
        </div>
      </div>

      <div className="quiz-questions">
        {questions.map((q, index) => {
          const isCorrect = normalize(userAnswers[index]) === normalize(q.answer);

          return (
            <div className="quiz-question" key={index}>
              <div className="radio-input">
                <div className="info">
                  <span className="question">{q.question}</span>
                  <span className="steps">{index + 1}/{questions.length}</span>
                </div>

                {q.options.map((option, optIndex) => {
                  const normalizedOption = normalize(option);
                  const normalizedAnswer = normalize(q.answer);
                  const isUserChoice = userAnswers[index] === option;

                  let labelClass = '';
                  if (score !== null) {
                    if (normalizedOption === normalizedAnswer) {
                      labelClass = 'correct';
                    } else if (isUserChoice && normalizedOption !== normalizedAnswer) {
                      labelClass = 'wrong';
                    }
                  }

                  return (
                    <React.Fragment key={optIndex}>
                      <input
                        type="radio"
                        id={`q${index}-option-${optIndex}`}
                        name={`question-${index}`}
                        value={option}
                        checked={userAnswers[index] === option}
                        onChange={() => handleOptionChange(index, option)}
                        required
                      />
                      <label
                        htmlFor={`q${index}-option-${optIndex}`}
                        className={labelClass}
                      >
                        {option}
                      </label>
                    </React.Fragment>
                  );
                })}

                {score !== null && (
                  isCorrect ? (
                    <span className="result success">Correct</span>
                  ) : (
                    <span className="result error">Wrong, correct: {q.answer}</span>
                  )
                )}
              </div>
            </div>
          );
        })}

        <div className="quiz-buttons">
          {error && <div className="error-message">{error}</div>}

          <button type="button" className="btn btn-outline-primary" onClick={handleSubmit}>Submit</button>
          <button type="button" className="btn btn-outline-warning" onClick={handleReset}>Reset</button>
        </div>
      </div>
    </>
  );
};

export default QuizPage;

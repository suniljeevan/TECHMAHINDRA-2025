import './App.css';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

// Auth and Common Pages
import Login from './Components/Auth/Login';
import Signup from './Pages/Signup';
import Home from './Pages/Home';
import NotFound from './Pages/NotFound';
import ProfilePage from './Components/ProfilePage';

// Student Components
import AddStudent from './Components/Students/AddStudent';
import ViewAllStudents from './Components/Students/ViewAllStudents';
import ManageStudents from './Components/Students/ManageStudents';
import UpdateStudents from './Components/Students/UpdateStudents';

// Faculty Components
import AddFaculty from './Components/Faculty/AddFaculty';
import ViewAllFaculty from './Components/Faculty/ViewAllFaculty';
import ManageFaculty from './Components/Faculty/ManageFaculty';
import UpdateFaculty from './Components/Faculty/UpdateFaculty';

// Course Components
import AddCourse from './Components/Courses/AddCourse';
import ViewAllCourse from './Components/Courses/ViewAllCourse';
import ManageCourse from './Components/Courses/ManageCourse';
import UpdateCourse from './Components/Courses/UpdateCourse';

// Attendance Components
import MarkAttendance from './Components/Attendance/MarkAttendance';
import AbsenteesReport from './Components/Attendance/AbsenteesReport';
import ManageAttendance from './Components/Attendance/ManageAttendance';

// Result Component
import ViewResult from './Components/Result/ViewResult';

// Route Protection
import PrivateRoute from './Components/Auth/PrivateRoute';
import Register from './Components/Auth/Register';
import QuizPage from './Components/Quiz/QuizPage';
import AddQuestion from './Components/Quiz/AddQuestion';
import ViewAllQuestions from './Components/Quiz/ViewAllQuestion';
import UpdateQuestion from './Components/Quiz/UpdateQuestion';

function App() {
  const getRole = () => localStorage.getItem('role');

  return (
    <BrowserRouter>
      <Routes>
        {/* Public Routes */}
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Register />} />

        {/* Protected Common Routes */}
        <Route path="/home" element={<PrivateRoute><Home /></PrivateRoute>} />
        <Route path="/profile" element={<PrivateRoute><ProfilePage /></PrivateRoute>} />

        {/* Student Routes */}
        <Route path="/student/viewResult" element={
          <PrivateRoute>
            {getRole() === "STUDENT" ? <ViewResult /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />

        {/* Faculty Routes */}
        <Route path="/attendance/markAttendance" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ?  <MarkAttendance /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/attendance/absenteesReport" element={
          <PrivateRoute>
            {getRole() ==="ADMIN" || getRole() === "FACULTY" ?  <AbsenteesReport /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/attendance/manageAttendance" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ?  <ManageAttendance /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />

        {/* Admin Routes */}
        <Route path="/student/addStudent" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ? <AddStudent /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/student/viewAllStudents" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ?  <ViewAllStudents /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/student/manageStudents" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ? <ManageStudents /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/student/updateStudent/:id" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ?  <UpdateStudents /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />

        {/* Faculty Management */}
        <Route path="/faculty/addFaculty" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <AddFaculty /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/faculty/viewAllFaculty" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <ViewAllFaculty /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/faculty/manageFaculty" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <ManageFaculty /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/faculty/updateFaculty/:facultyId" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <UpdateFaculty /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />

        {/* Course Management */}
        <Route path="/course/addCourse" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ?  <AddCourse /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/course/viewAllCourse" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ? <ViewAllCourse /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/course/manageCourse" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ? <ManageCourse /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />
        <Route path="/course/updateCourse/:id" element={
          <PrivateRoute>
            {getRole() === "ADMIN" || getRole() === "FACULTY" ? <UpdateCourse /> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        } />

        <Route path="/quiz/takeQuiz" element={
          <PrivateRoute>
            {getRole() === "STUDENT" || getRole() === "ADMIN" ? <QuizPage/> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        }>
        </Route>
          <Route path="/quiz/addQuestion" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <AddQuestion/> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        }>
        </Route>
          <Route path="/quiz/manageQuestion" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <ViewAllQuestions/> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        }>
        </Route>
          <Route path="/question/update/:id" element={
          <PrivateRoute>
            {getRole() === "ADMIN" ? <UpdateQuestion/> : <Navigate to="/profile" replace />}
          </PrivateRoute>
        }>
        </Route>

        {/* Fallback Route */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

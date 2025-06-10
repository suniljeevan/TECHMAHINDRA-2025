import React, { useState } from 'react'
import '../assets/css/authentication.css'
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const Signup = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate();

    const handelSignup = (e) => {
        e.preventDefault();

        if (!email || !password || !confirmPassword) {
            toast.error('Please fill all the fields');
        } else if (password !== confirmPassword) {
            toast.error('Password and Confirm Password do not match');
        } else {
            console.log(email, password);
            toast.success('Signup successful');
            navigate('/login');
        }
    };


    return (
        <form className="form_container" onSubmit={handelSignup}>
            <ToastContainer />
            <input type="text" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <input type="password" placeholder="Confirm Password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
            <button type="submit">Signup</button>
        </form>

    )
}

export default Signup

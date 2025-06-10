import React from 'react'
import SideBar from '../Components/SideBar'
import MainBody from '../Components/MainBody'
import Header from '../Components/Header'
import '../assets/css/style.css'
import ProfilePage from '../Components/ProfilePage'
import Footer from '../Components/Footer'

const Home = () => {
  return (
    <>
      <SideBar />
      <Header />
      <ProfilePage />
      <Footer />
    </>
  )
}

export default Home

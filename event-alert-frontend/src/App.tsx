import { useState } from 'react';
import './App.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Login from './pages/Login';
import PrivateRoute from './authentication/PrivateRoute';
import Registration from './pages/Registration';
import BlogPost from './components/BlogPost';
import Home from './pages/Home';
import Channels from './pages/Channels';
import Profile from './pages/Profile';


function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/channels" element={<Channels />} />
        <Route path="/create-post" element={<></>} />
        <Route path="/my-profile" element={<Profile />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App

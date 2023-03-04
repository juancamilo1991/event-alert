import React, {ChangeEvent, FormEvent, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import { registerUser } from '../api/users';


const Registration = () => {

const [credentials, setCredentials] = useState({
  username: '',
  email: '',
  password: ''
})

const navigate = useNavigate();

const requestBody = {
        username: credentials.username,
        email: credentials.email,
        password: credentials.password
}

async function onRegister(e: FormEvent<HTMLFormElement>) {
  e.preventDefault();
  try {
    const result = await registerUser(requestBody)
    setCredentials({username: '', email: '', password: ''})
    return navigate('/login');
  } catch (error) {
    
  }
  
}

function onChange(e: ChangeEvent<HTMLInputElement>) {
  setCredentials({...credentials, [e.target.id]: e.target.value});
}


  return (
    <form onSubmit={onRegister}>
      <label htmlFor='username'>username</label>
      <input type='text' id='username' value={credentials.username} onChange={onChange}/>
      <label htmlFor='email'>email</label>
      <input type='text' id='email' value={credentials.email} onChange={onChange}/>
      <label htmlFor='password'>password</label>
      <input type='password' id='password' value={credentials.password} onChange={onChange}/>
      <button type='submit'>register</button>
    </form>
  )
}

export default Registration;
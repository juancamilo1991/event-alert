import React, { useEffect, FormEvent, ChangeEvent } from 'react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useLocalStorage } from '../authentication/utils/useLocalState';
import { loginUser } from '../api-client/users';

const Login = () => {

const [credentials, setCredentials] = useState({
    username: '',
    password: ''
})

const [jwt, setJwt] = useLocalStorage("jwt", "");
const navigate = useNavigate();

const requestBody = {
    username: credentials.username,
    password: credentials.password
}
 

const onLogin = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (jwt !== "") {
       return navigate('/channels')
    }
    loginUser(requestBody)
    .then(res => {
        return setJwt(res.headers['authorization'])
    })
    .then((jwt) => {
        console.log(jwt);
        navigate('/channels')
    })
    .catch((error) => console.log(error))
}

const onChange = (e: ChangeEvent<HTMLInputElement>) => {
        setCredentials({...credentials, [e.target.id]: e.target.value})
}

  return (
        <form onSubmit={onLogin}>
                <label htmlFor='username'>username</label>
                <input id='username' type="text" value={credentials.username} onChange={onChange} />
                <label htmlFor='password'>password</label>
                <input id='password' type="password" value={credentials.password} onChange={onChange} />
                <button type='submit'>log in</button>
        </form>
  )
}

export default Login
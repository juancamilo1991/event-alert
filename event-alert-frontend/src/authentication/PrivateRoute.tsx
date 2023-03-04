import React from 'react'
import { useLocalStorage } from './utils/useLocalState'
import {useNavigate} from 'react-router-dom';

const PrivateRoute= () => {

  const navigate = useNavigate();

  const [jwt, setJwt] = useLocalStorage('', 'jwt')

  return (
    jwt ? navigate('/logout') : navigate('/login')
  )
}

export default PrivateRoute
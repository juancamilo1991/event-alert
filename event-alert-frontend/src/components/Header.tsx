import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useLocalStorage } from '../authentication/utils/useLocalState';
import classes from './styles/Header.module.css';
import { isAuthenticated } from '../authentication/utils/authentication';
import Logout from '../authentication/Logout';
import Login from '../pages/Login';
import { Button } from '@mui/material';


interface HeaderProps {
  logUserOut: (jwt: string) => void;
}

const Header = (props: HeaderProps) => {

  const [jwt, setJwt] = useLocalStorage("jwt", "");


  function logTheUserOut(jwt: string) {
    setJwt(jwt);
    props.logUserOut(jwt);
  }

  // if logged in -> logo | mein Konto -------> logout btn
  // below textarea for posting

  // if NOT logged in -> logo | login
  // below -> anmelden oder Konto eröffnen um beizutragen!

  return (
    <div className={classes.container}>
      <nav className={classes.navbar}>
        <span>Logo</span>
        <span>|</span> {jwt !== "" ?
          <>
            <Link to='/my-profile'><span>mein Konto</span></Link>
            <div className={classes.logoutBtn}>
              <Logout logout={(jwt: string) => logTheUserOut(jwt)} />
            </div>
          </> :
          <Link to='/login'><span>Login</span></Link>}
      </nav>
    </div>
  )
}

export default Header;
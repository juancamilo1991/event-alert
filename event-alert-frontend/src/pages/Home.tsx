import React from 'react'
import classes from './styles/Home.module.css';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

const Home = () => {

  return (
    <div className={classes.container}>
      <div>
        <div>Welcome to event alert!</div>
        <Link to='/channels'>
          <Button variant='contained'>
              click for some magic...
          </Button>
        </Link>
      </div>
    </div>
  )
}

export default Home;
import React from 'react';
import classes from './styles/WritePost.module.css';
import { TextField } from '@mui/material';
import {Link} from 'react-router-dom';

interface WritePostProps {
    isLoggedIn: string;
}

const WritePost = (props: WritePostProps) => {
  return (
    <>
        {props.isLoggedIn !== "" ? 
        <TextField
        id="outlined-multiline-static"
        label="Mein Beitrag"
        multiline
        rows={4}
        defaultValue="Schreibe etwas..."
        className={classes.container}
        /> :
        <p>
            <Link to='/login'>Anmelden</Link> oder <Link to='/register'> registrieren </Link> um Beiträge zu posten!
        </p>
        }
    
        </>
  )
}

export default WritePost;
import React from 'react';
import classes from './styles/Footer.module.css';
import heart from '../assets/images/heart-red.svg';

const Footer = () => {
  return (
    <div className={classes.container}>
        <span>made with</span>
        <img src={heart}/>
    </div>
  )
}

export default Footer;
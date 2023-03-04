import React from 'react';
import classes from './styles/SideBar.module.css';

interface sideBarProps {
    isActive: boolean,
    closeSideNav: () => void
}

const SideBar = (props: sideBarProps) => {

  return (
    <div className={`${classes.container} ${props.isActive ? classes.show_sidebar : ''}`}>
        <span className={classes.closebtn} onClick={props.closeSideNav}>&times;</span>
        <span>category - selection bullets</span>
        <span>newest or oldest - switch bar</span>
        <span>most liked or less - switch bar</span>
    </div>
  )
}

export default SideBar
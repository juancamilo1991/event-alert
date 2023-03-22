import React, { useState } from 'react';
import classes from './styles/SideBar.module.css';
import { getFilteredPosts } from '../api/blogPosts';
import { FormControl, FormLabel, RadioGroup, FormControlLabel, Radio } from '@mui/material';

interface sideBarProps {
    isActive: boolean,
    closeSideNav: () => void
}

const SideBar = (props: sideBarProps) => {

  const [radioValue, setRadioValue] = useState<string>('');

  function getFilteredPostss() {
    getFilteredPosts
  }

  return (
    <div className={`${classes.container} ${props.isActive ? classes.show_sidebar : ''}`}>
        <span className={classes.closebtn} onClick={props.closeSideNav}>&times;</span>
        <span className={classes.radio_group}>
          <FormControl>
            <FormLabel id="category-radio-buttons-group-label">Category</FormLabel>
            <RadioGroup
                aria-labelledby="category-radio-buttons-group-label"
                defaultValue="SUSPICIOUS_BEHAVIOUR"
                name="category-group"
            >
              <FormControlLabel value="SUSPICIOUS_BEHAVIOUR" control={<Radio />} label="SUSPICIOUS_BEHAVIOUR" />
              <FormControlLabel value="STARS" control={<Radio />} label="STARS" />
              <FormControlLabel value="ACCIDENT" control={<Radio />} label="ACCIDENT" />
            </RadioGroup>
          </FormControl>
        </span>
        <span>newest or oldest - switch bar</span>
        <span>most liked or less - switch bar</span>
    </div>
  )
}

export default SideBar
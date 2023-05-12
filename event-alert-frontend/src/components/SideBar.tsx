import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import classes from './styles/SideBar.module.css';
import { FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, Button, TextField, InputLabel } from '@mui/material';
import { FILTERED_POSTS_ENDPOINT } from '../api-client/blogPosts';
import { ChannelPost, SideBarProps } from '../types/types';
import { makeRequest } from '../helpers/api-requests/apiRequests';
import { useHandleApiResponse } from '../customHooks/useHandleApiResponse';


const SideBar = (props: SideBarProps) => {

  const [categoryValue, setCategoryValue] = useState<string>('');
  const [filterValue, setFilterValue] = useState<string>('');
  const [applyFilterBtn, setApplyFilterBtn] = useState<boolean>(true);
  const [area, setArea] = useState<string>('');

  const [handleIncomingPosts] = useHandleApiResponse(props);

  useEffect(() => {
    if (area !== '' && categoryValue !== '') {
      setApplyFilterBtn(false);
    }
    else {
      setApplyFilterBtn(true);
    }
  }, [area, categoryValue])


  function handleRadioChange(event: ChangeEvent<HTMLInputElement>) {
    if (event.target.name === 'category') {
      return setCategoryValue(event.target.value);
    }
      setFilterValue(event.target.value);
  }

  function handleAreaChange(event: ChangeEvent<HTMLInputElement>) {
    setArea(event.target.value);
  }

  function getFilteredBlogPosts(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    makeRequest(FILTERED_POSTS_ENDPOINT, area, props.displayError, handleIncomingPosts, '', categoryValue, filterValue);
  }


  return (
    <div className={`${classes.container} ${props.isActive ? classes.show_sidebar : ''}`}>
        <span className={classes.closebtn} onClick={props.closeSideNav}>&times;</span>
        <span className={classes.radio_group}>
          <form onSubmit={getFilteredBlogPosts}>
            <div>
              <TextField
              id='plz'
              label='Postleizahl'
              value={area}
              onChange={handleAreaChange}/>
            </div>
            <FormControl>

              <FormLabel id="category-group">Kategorien (eine muss ausgewählt sein)</FormLabel>
              <RadioGroup
                  aria-labelledby="category-group"
                  defaultValue="SUSPICIOUS_BEHAVIOUR"
                  name="category"
                  value={categoryValue}
                  onChange={handleRadioChange}
                  >
                <FormControlLabel className={classes.radio_label} value="SUSPICIOUS_BEHAVIOUR" control={<Radio />} label="verdächtig" />
                <FormControlLabel className={classes.radio_label} value="STARS" control={<Radio />} label="VIP" />
                <FormControlLabel className={classes.radio_label} value="ACCIDENT" control={<Radio />} label="Unfall" />
              </RadioGroup>
            </FormControl>

            <FormControl>

              <FormLabel id="filter-group">Filtern nach</FormLabel>
              <RadioGroup
                  aria-labelledby='filter-group'
                  defaultValue="neuster Beitrag"
                  name="filter"
                  value={filterValue}
                  onChange={handleRadioChange}
                  >
                <FormControlLabel value="newest" control={<Radio />} label="neuster Beitrag" />
                <FormControlLabel value="oldest" control={<Radio />} label="ältester Beitrag" />
                <FormControlLabel value="most liked" control={<Radio />} label="beliebtester Beitrag" />
              </RadioGroup>

            </FormControl>
            <Button className={classes.radio_label} type='submit' variant='contained' disabled={applyFilterBtn}>Filter anwenden</Button>
          </form>
        </span>
    </div>
  )
}

export default SideBar
import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import classes from './styles/SearchArea.module.css';
import Button from '@mui/material/Button';
import { ALLPOSTS_ENDPOINT } from '../api-client/blogPosts';
import { makeRequest } from '../helpers/api-requests/apiRequests';
import { useHandleApiResponse } from '../customHooks/useHandleApiResponse';
import { SearchAreaProps } from '../types/types';


const SearchArea = (props: SearchAreaProps) => {

  const [area, setArea] = useState<string>('');
  const [applyFilterBtn, setApplyFilterBtn] = useState<boolean>(true);

  const [handleIncomingPosts] = useHandleApiResponse(props);

  useEffect(() => {
    if (area !== '') {
      setApplyFilterBtn(false);
    }
    else {
      setApplyFilterBtn(true);
    }
  }, [area])

  function getAllPosts(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    makeRequest(ALLPOSTS_ENDPOINT, area, props.displayError, handleIncomingPosts);
  }

  function onChange(e: ChangeEvent<HTMLInputElement>) {
    setArea(e.target.value);
}


  return (
    <section className={classes.search_area}>
        <form onSubmit={getAllPosts}>
            <label htmlFor="search">Search</label>
            <input type="text" id='search' onChange={onChange} />
            <Button disabled={applyFilterBtn} type='submit' variant='contained'>Go!</Button>
        </form>
    </section>    
  )
}

export default SearchArea;

import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import classes from './styles/SearchArea.module.css';
import Button from '@mui/material/Button';
import { ALLPOSTS_ENDPOINT } from '../api-client/blogPosts';
import { ChannelPost, RequestError } from '../types/types';
import { makeRequest } from '../helpers/api-requests/getPosts';

interface SearchAreaProps {
    displayError: (error: RequestError) => void;
    displayPosts: (posts: ChannelPost[]) => void;
}

const SearchArea = (props: SearchAreaProps) => {

  const [area, setArea] = useState<string>('');
  const [incomingPosts, setIncomingPosts] = useState<ChannelPost[]>([]);
  const [applyFilterBtn, setApplyFilterBtn] = useState<boolean>(true);

  useEffect(() => {
    if (area !== '') {
      setApplyFilterBtn(false);
    }
    else {
      setApplyFilterBtn(true);
    }
  }, [area])

  useEffect(() => {
    incomingPosts !== undefined ? props.displayPosts(incomingPosts) : null;
  }, [incomingPosts])

  function handleIncomingPosts(result: ChannelPost[]): void {
    setIncomingPosts(result);
  }

  function getAllPosts(e: FormEvent<HTMLFormElement>) {
    makeRequest(e, ALLPOSTS_ENDPOINT, area, props.displayError, handleIncomingPosts);
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

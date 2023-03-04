import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import classes from './styles/SearchArea.module.css';
import Button from '@mui/material/Button';
import { getAllPosts } from '../api/blogPosts';
import { ChannelPost } from '../types/types';


interface SearchAreaProps {
    displayPosts: (posts: ChannelPost[]) => void;
}

const SearchArea = (props: SearchAreaProps) => {

  const [area, setArea] = useState<number>(0);
  const [blogPosts, setBlogPosts] = useState<ChannelPost[]>([]);

  console.log(area);
  console.log(blogPosts);

  useEffect(() => {
    // trigger when blogposts change
    props.displayPosts(blogPosts);
  }, [blogPosts])

  function getPosts(e: FormEvent<HTMLFormElement>) {
    // api call
    e.preventDefault();
    getAllPosts(area).then(res => setBlogPosts(res));
  }

  function onChange(e: ChangeEvent<HTMLInputElement>) {
    setArea(e.target.valueAsNumber);
}


  return (
    <section className={classes.search_area}>
        <form onSubmit={getPosts}>
            <label htmlFor="search">Search</label>
            <input type="number" id='search' onChange={onChange} />
            <Button type='submit' variant='contained'>Go!</Button>
        </form>
    </section>    
  )
}

export default SearchArea;
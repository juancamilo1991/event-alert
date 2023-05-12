import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import classes from './styles/WritePost.module.css';
import { Button, TextField } from '@mui/material';
import { Link } from 'react-router-dom';
import { makeRequest } from '../helpers/api-requests/apiRequests';
import { POST_BLOG_ENDPOINT } from '../api-client/blogPosts';
import { WritePostProps } from '../types/types';
import { useHandleApiResponse } from '../customHooks/useHandleApiResponse';
import { useLocalStorage } from '../authentication/utils/useLocalState';






const WritePost = (props: WritePostProps) => {

  const [content, setContent] = useState({ title: '', text: '' });
  const [jwt, setJwt] = useLocalStorage("jwt", "");

  useEffect(() => {
    setContent({title: '' , text: ''});
  }, [props.isDeleteInput])

  const headers = {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${jwt}`
  }

  let correctCategoryName = '';

  const [handleIncomingPosts] = useHandleApiResponse(props);

  if (props.category === 'Verdächtig') {
    correctCategoryName = 'SUSPICIOUS_BEHAVIOUR'
  }
  if (props.category === 'VIP') {
    correctCategoryName = 'STARS'
  }
  if (props.category === 'Unfall') {
    correctCategoryName = 'ACCIDENT'
  }



  function sendBlogPost(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    makeRequest(POST_BLOG_ENDPOINT, props.area, props.displayError, handleIncomingPosts, 'POST', correctCategoryName, '', content.title, content.text, headers);
  }

  function onChange(e: ChangeEvent<HTMLInputElement>) {
    setContent({ ...content, [e.target.id]: e.target.value });
  }

  return (
    <>
      {props.isLoggedIn !== "" ?
        <form onSubmit={sendBlogPost}>
          <TextField
            id='title'
            label='Titel'
            placeholder='Titel'
            value={content.title}
            onChange={onChange} />
          <br /> <br />
          <TextField
            id="text"
            label="Mein Beitrag"
            multiline
            rows={4}
            placeholder="Schreibe etwas..."
            value={content.text}
            onChange={onChange}
            className={classes.container}
          /> <br /> <br />
          <Button style={{ marginBottom: '20px' }} type='submit' disabled={content.text === '' || content.title === '' || props.area === '' || props.category === ''} variant='contained'>
            Jetzt posten
          </Button>
        </form> :
        <p>
          <Link to='/login'>Anmelden</Link> oder <Link to='/register'> registrieren </Link> um Beiträge zu posten!
        </p>
      }

    </>
  )
}

export default WritePost;
import React, { FormEvent, useEffect } from 'react'
import { useLocalStorage } from '../authentication/utils/useLocalState'

const BlogPost = () => {

const [jwt, setJwt] = useLocalStorage("", "jwt")

useEffect(() => {
    console.log(jwt)
}, [])

const reqBody = {
   method: "POST",
   headers: {
    "Content-Type": "application/json",
    "Authorization": `Bearer ${jwt}`
    },
}

const createPost = (e: FormEvent<HTMLElement>) => {
    e.preventDefault()
        fetch('/blog/create-post', reqBody)
        .then((response) => response.status === 200 ? response.json() : null)
        .then(data => console.log(data))
}

  return (
    <form onSubmit={createPost}>
        <button type='submit'>create post</button>
    </form>
  )
}

export default BlogPost;
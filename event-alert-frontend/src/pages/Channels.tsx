import React, { useEffect, useState } from 'react'
import classes from './styles/Channels.module.css';
import Header from '../components/Header';
import SideBar from '../components/SideBar';
import Footer from '../components/Footer';
import SearchArea from '../components/SearchArea';
import { ChannelPost, RequestError } from '../types/types';
import { Card, Button } from '@mui/material';
import { useLocalStorage } from '../authentication/utils/useLocalState';
import WritePost from '../components/WritePost';
import RequestErrorCard from '../components/errorHandling/RequestErrorCard';


const Channels = () => {

  const [isNavActive, setNavActive] = useState(false);
  const [isAuthenticated, setAuthenticated] = useState<string>("initial");
  const [channelPosts, setChannelPosts] = useState<ChannelPost[]>([]);
  const [jwt, setJwt] = useLocalStorage("jwt", "");
  const [error, setErrorMessage] = useState<RequestError>();

  function toggleSideNav() {
    setNavActive(!isNavActive);
  }
  
  function logUserOut(jwt: string) {
    setJwt(jwt);
    setAuthenticated(jwt);
  }
  
  function handleIncomingErrorMessage(error: RequestError) {
    setErrorMessage(error);
    setChannelPosts([]);
  }

  return (
    <div className={classes.container}>
      <div className={classes.main}>
        <Header logUserOut={(jwt: string) => logUserOut(jwt)} />
        <div className={classes.grid_container} >
          <section className={classes.popular_areas}>
            <div className={classes.bottom_border_container}>
              <div className={classes.bottom_border}></div>
            </div>
            <p>popular areas right now</p>
            <p>popular areas right now</p>
            <p>popular areas right now</p>
            <p>popular areas right now</p>
          </section>
          <SearchArea
            displayPosts={(posts: ChannelPost[]) => setChannelPosts(posts)}
          />
          <section className={`${classes.channel_posts_container} ${isNavActive ? classes.sidenav_active : ''}`}>
            <div className={classes.left_border_container}>
              <div className={classes.left_border}></div>
            </div>
            <div className={classes.channel_posts}>
              <div className={classes.all_posts_container}>
                {channelPosts.length > 0 ? channelPosts.map((post: ChannelPost) => (
                  <Card className={classes.mui_post_card}>
                    <span>{post.title}</span>
                    <span>{post.text}</span>
                    <span>{post.category}</span>
                    <span>{post.likesCount}</span>
                    <span>{post.publicationDate}</span>
                    <span>{post.user.username}</span>
                  </Card>
                )) : <>
                  {error ? <RequestErrorCard closeCard={(closed) => setErrorMessage(closed)} error={error} /> : <div>provide a query</div>}
                </>}
              </div>
              <WritePost isLoggedIn={jwt} />
            </div>
            <div className={`${classes.open_sidenav} ${isNavActive ? classes.hide_button : ''}`} onClick={toggleSideNav}>filter</div>
          </section>
          <SideBar
            isActive={isNavActive}
            closeSideNav={toggleSideNav}
            displayPosts={(posts: ChannelPost[]) => {setChannelPosts(posts)}}
            displayError={(error: RequestError) => handleIncomingErrorMessage(error)}
          />
        </div>
        <Footer />
      </div>
    </div>
  )
}

export default Channels;
import React, {useEffect, useState} from 'react'
import classes from './styles/Channels.module.css';
import Header from '../components/Header';
import SideBar from '../components/SideBar';
import Footer from '../components/Footer';
import SearchArea from '../components/SearchArea';
import { ChannelPost } from '../types/types';
import {Card, Button} from '@mui/material';
import { useLocalStorage } from '../authentication/utils/useLocalState';
import WritePost from '../components/WritePost';


const Channels = () => {

  const [isNavActive, setNavActive] = useState(false);
  const [isAuthenticated, setAuthenticated] = useState<string>("initial");
  const [channelPosts, setChannelPosts] = useState<ChannelPost[]>([]);
  const [jwt, setJwt] = useLocalStorage("jwt", "");

  function openSideNav() {
    setNavActive(true);
  }

  function closeSideNav() {
    setNavActive(false);
  }

  function logUserOut(jwt: string) {
    setJwt(jwt);
    setAuthenticated(jwt);
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
        <SearchArea displayPosts={(posts) => setChannelPosts(posts)} />
        <section className={`${classes.channel_posts_container} ${isNavActive ? classes.sidenav_active : ''}`}>
          <div className={classes.left_border_container}>
            <div className={classes.left_border}></div>
          </div>
          <div className={classes.channel_posts}>
            <div className={classes.all_posts_container}>
            {channelPosts.map((post: ChannelPost) => (
              <Card className={classes.mui_post_card}>
                <span>{post.title}</span>
                <span>{post.text}</span>
                <span>{post.category}</span>
                <span>{post.likesCount}</span>
                <span>{post.publicationDate}</span>
                <span>{post.user.username}</span>
              </Card>
              ))}
              </div>
              <WritePost isLoggedIn={jwt} />
          </div>          
          <div className={`${classes.open_sidenav} ${isNavActive ? classes.hide_button : ''}`} onClick={openSideNav}>filter</div>
        </section>
        <SideBar isActive={isNavActive} closeSideNav={closeSideNav}/>
      </div>
      <Footer />
    </div>
    </div>
  )
}

export default Channels;
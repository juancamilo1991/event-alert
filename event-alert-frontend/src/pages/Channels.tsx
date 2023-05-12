import React, { ChangeEvent, useEffect, useState, useRef } from 'react'
import classes from './styles/Channels.module.css';
import Header from '../components/Header';
import SideBar from '../components/SideBar';
import Footer from '../components/Footer';
import SearchArea from '../components/SearchArea';
import { ChannelPost, RequestError } from '../types/types';
import { Card, Button, FormControl, InputLabel, MenuItem, Select, SelectChangeEvent, TextField } from '@mui/material';
import { useLocalStorage } from '../authentication/utils/useLocalState';
import WritePost from '../components/WritePost';
import RequestErrorCard from '../components/errorHandling/RequestErrorCard';
import { isValidZipCode } from '../helpers/post-office/getZipCodes';
import MyModal from '../components/MyModal';



const Channels = () => {

  const [isNavActive, setNavActive] = useState(false);
  const [isAuthenticated, setAuthenticated] = useState<string>("initial");
  const [channelPosts, setChannelPosts] = useState<ChannelPost[]>([]);
  const [newPost, setNewPost] = useState<ChannelPost[]>();
  const [modal, setModal] = useState<boolean>(false);
  const [jwt, setJwt] = useLocalStorage("jwt", "");
  const [error, setErrorMessage] = useState<RequestError>();
  const [data, setData] = useState({area: '', category: ''});
  const [deleteInput, setDeleteInput] = useState<boolean>(false);

  useEffect(() => {
    if (newPost !== undefined && !Array.isArray(newPost)) {
      console.log(newPost);
      setModal(true);
      setData({area: '', category: ''});
    }
  }, [newPost]);

  console.log(modal);

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

  function handleChange(e: ChangeEvent<HTMLInputElement>) {
    setData({...data, area: e.target.value});
  }

  function handleSelectChange(e: SelectChangeEvent) {
    setData({...data, category: e.target.value})
  }

  function deleteAllInput() {
    setModal(false);
    setDeleteInput(!deleteInput);
  }

  return (
    <div className={classes.container}>
      <div className={classes.main_container}>
        <div className={classes.main_content}>
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
              displayError={(error: RequestError) => handleIncomingErrorMessage(error)}
            />
            <section className={`${classes.channel_posts_container} ${isNavActive ? classes.sidenav_active : ''}`}>
              <div className={classes.left_border_container}>
                <div className={classes.left_border}></div>
              </div>
              <div className={classes.select_area}>
                <h2>bitte Postleitzahl und Kategorie wählen um Beitrag zu erfassen</h2>
                {jwt !== "" ? (
                  <>
                    <div>
                      <TextField
                        id="plz_1"
                        error={!isValidZipCode(data.area)}
                        label="Poszleitzahl"
                        value={data.area}
                        onChange={handleChange}
                      />
                    </div>
                    <br />
                    <div>
                      <FormControl fullWidth>
                        <InputLabel id="category-select-label">Kategorie</InputLabel>
                        <Select
                          labelId="category-select-label"
                          id="category-select-label"
                          value={data.category}
                          label="Kategorie"
                          onChange={handleSelectChange}
                        >
                          <MenuItem value='Unfall'>Unfall</MenuItem>
                          <MenuItem value='Verdächtig'>Verdacht</MenuItem>
                          <MenuItem value='VIP'>VIP</MenuItem>
                        </Select>
                      </FormControl>
                    </div>
                  </>
                ) : null}
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
                    {error ? <MyModal closeCard={(closed) => setErrorMessage(closed)} error={error} /> : <div>provide a query</div>}
                  </>}
                </div>
                <WritePost
                area={data.area} 
                category={data.category}
                isLoggedIn={jwt}
                isDeleteInput={deleteInput}
                displayError={(error: RequestError) => handleIncomingErrorMessage(error)}
                displayPosts={(posts: ChannelPost[]) => setNewPost(posts)} />
                {modal && <MyModal success={{message: 'Beitrag erfolgreich erstellt'}} closeCard={(close) => deleteAllInput()} />}
              </div>
              <div className={`${classes.open_sidenav} ${isNavActive ? classes.hide_button : ''}`} onClick={toggleSideNav}>filter</div>
            </section>
            <SideBar
              isActive={isNavActive}
              closeSideNav={toggleSideNav}
              displayPosts={(posts: ChannelPost[]) => setChannelPosts(posts)}
              displayError={(error: RequestError) => handleIncomingErrorMessage(error)}
            />
          </div>
        </div>
      </div>
      <Footer />
    </div>
  )
}

export default Channels;
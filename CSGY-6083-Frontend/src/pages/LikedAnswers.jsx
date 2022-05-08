import React, {useState, useEffect} from 'react';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import { Button } from '@mui/material';
import { Link } from 'react-router-dom';

import PrimarySearchAppBar from '../component/Header';
import Typography from '@mui/material/Typography';
import SideList from '../component/SideList';

import axios from 'axios';

import {BASE_URL} from '../config/server';
import AnswerWithTitle from '../component/AnswerWithTitle';

const theme = createTheme();

const LikedAnswers = () => {

  const [myAnswer, setMyAnswer] = useState([]);

  const uid = localStorage.getItem('uid');

  useEffect(() => {
    // get my answers
    if (uid == null) {
      alert('Please login');
      window.localtion = "/signin";
    }
    
    axios.get(BASE_URL + "/answers/like?uid=" + uid)
      .then(res => {
        let data = res.data.data;
        console.log(data);
        setMyAnswer(data);
      })
      .catch(error => {
        alert("Network error!");
      });
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <PrimarySearchAppBar></PrimarySearchAppBar>
      <Grid container>
        <main>
          <br/>
          <Grid container>
            <Grid item xs={2}>
              <SideList></SideList>
            </Grid>
            <Grid item xs={10}>
              {/* self questions and answers */}
              <Grid item xs={10}>
              <Grid>
                <Typography variant="h4" gutterBottom component="div">
                  See answers I liked
                </Typography>
              </Grid>
              <Grid container sx={{mt: 2}}>
                {myAnswer.map((ans) => {
                  return (
                    <AnswerWithTitle
                      answer={ans}
                    /> 
                  )
                })}
              </Grid>
            </Grid>
            </Grid>
          </Grid>         
        </main>
      </Grid>      
    </ThemeProvider>
  );
};

export default LikedAnswers;

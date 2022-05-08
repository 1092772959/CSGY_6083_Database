import React, {useState, useEffect} from 'react';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import { Button } from '@mui/material';
import { Link } from 'react-router-dom';

import postsData from '../data/postsData';
import PostItem from '../component/PostItem';
import PrimarySearchAppBar from '../component/Header';
import Typography from '@mui/material/Typography';
import TopicSelector from '../component/TopicSelector';
import SideList from '../component/SideList';

import axios from 'axios';

import {BASE_URL} from '../config/server';

const theme = createTheme();

const Dashboard = () => {

  const[state, setState]=useState({
    questions: [],
    showedQuestions: [],
  });

  const uid = localStorage.getItem('uid');

  useEffect(() => {
    // get my questions
    if(uid == null || uid === undefined) {
      alert('Please Login');
      window.location = "/signin"; 
    }

    axios.get(BASE_URL + '/questions/user?uid=' + uid)
    .then(res => {
      if (res.data == "session error") {
        alert('Please Login');
        window.location = "/signin";
      }

      let data = res.data.data;
      setState({
        questions: data,
        showedQuestions: data,
      });
    })
    .catch(error => {
      alert('Please Login');
      window.location = "/signin"; 
    });
  }, []);
  
  const handleChange = (parentTopicId, TopicId) => {
    if (parentTopicId === -1) {
      setState({
        questions: state.questions,
        showedQuestions : state.questions,
      });
    } else if (TopicId === -1) {
      var tmpQuestions = [];
      state.questions.forEach((question, i) => {
        if (question.p_topic_id == parentTopicId) {
          tmpQuestions.push(question);
        }
      });
      setState({
        questions: state.questions,
        showedQuestions: tmpQuestions,
      });
    } else {
      var tmpQuestions = [];
      state.questions.forEach((question, i) => {
        if (question.topic_id === TopicId) {
          tmpQuestions.push(question);
        }
      });
      setState({
        questions: state.questions,
        showedQuestions: tmpQuestions,
      });
    }
  };

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
              <Grid container spacing = {3}>
                <Grid item xs = {4}>  
                  <Typography variant="h4" gutterBottom component="div">
                    See my questions
                  </Typography>  
                </Grid>
                <Grid item xs = {5}>
                  <TopicSelector setQuestions={handleChange}></TopicSelector>
                </Grid>
                <Grid item xs = {3}>
                  <div style={{ display: "flex" }}>
                    <Button component={Link} to="/questions/post"
                      variant="contained" 
                      color="primary"
                      size="large"
                      style={{ marginLeft: "auto" }}>
                      Ask a Question
                    </Button>
                  </div>
                </Grid>
              </Grid>
              <Grid container spacing={3} >
                {state.showedQuestions.map((post) => {
                  return (
                    <PostItem post={post}></PostItem>
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

export default Dashboard;

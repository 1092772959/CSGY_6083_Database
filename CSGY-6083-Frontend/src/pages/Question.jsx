import React, {useEffect, useState} from 'react';
import { useParams } from "react-router-dom";

import {Grid, Container, Button, TextField, alertTitleClasses, Alert, Paper, Typography} from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

import postsData from '../data/postsData';
import PostItem from '../component/PostItem';
import Answer from '../component/Answer';
import PrimarySearchAppBar from '../component/Header';

import axios from 'axios';
import { BASE_URL } from '../config/server';

const theme = createTheme();

export default function Question() {

  const [post, setPost] = useState(postsData[0]);
  const [answers, setAnswers] = useState([]);
  const [failSnack, setFailSnack] = React.useState(false);
  const [hasBest, setHasBest] = useState(0);
  const [quid, setQuid] = useState(-1);

  const params = useParams();

  const uid = localStorage.getItem('uid');

  // get question
  useEffect( () => {
    let ques_id = params.quesId;
    axios.get(BASE_URL + '/questions/' + ques_id)
      .then(res => {
        const data = res.data.data;
        console.log(data);
        setPost(data);
        setHasBest(data.hasBest);
        setQuid(data.uid);
      })
      .catch(error => {
        alert(error);
      });
  }, []);

  // get answers
  useEffect( () => {
    let ques_id = params.quesId;
    axios.get(BASE_URL + '/answers/question?ques_id=' + ques_id + '&uid=' + uid)
      .then(res => {
        setAnswers(res.data.data);
      })
      .catch(error => {
        alert(error);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    var bodyFormData = new FormData();
    bodyFormData.append('body', data.get('body'));
    let ques_id = params.quesId;

    const header = {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    };
    
    // request
    axios.post(BASE_URL + '/answers?uid=' + uid + '&ques_id=' + ques_id, bodyFormData, header)
      .then(res => {
        const data = res.data;
        if (data.code == 0) {
          // redirect to dashboard
          window.location = "/questions/" + ques_id; 
        } else { 
          setFailSnack(true);
        }
      }).catch( error => {
        return (
          <Alert severity="error">{error}</Alert>
        );
      });
  };  

  return (
  <ThemeProvider theme={theme}>
    <PrimarySearchAppBar></PrimarySearchAppBar>
    <Container maxWidth="lg">
      <main>
{/* question */}
      <Grid container>
        <Grid item xs={3}>
          <Typography variant="h4" gutterBottom component="div">
            Question
          </Typography>
          <Grid item>
            
          </Grid>
        </Grid>
      </Grid>
      {/* <Grid container spacing={2} > */}
        <Paper elevation={5} >
          <PostItem post={post} />
        </Paper>
      {/* </Grid> */}
      {/* answers */}
      <br/>
      <Grid>
        <Typography variant="h5" gutterBottom component="div">
          Answers
        </Typography>
      </Grid>
      <Grid container spacing={5} sx={{ mt: 1}}>
        {answers.map((ans) => {
          return (
            <Answer 
              answer={ans} 
              quid={quid}
              hasBest={hasBest}
              setHasBest={setHasBest}
            /> 
          )
        })}
        <Grid item xs={12}>
          <Typography variant="h4" gutterBottom component="div">
              Post your Answer
          </Typography>
          <form onSubmit={handleSubmit}>
          <TextField
              id="outlined-multiline-static"
              name="body"
              fullWidth
              label="Option"
              multiline
              rows={20}
              defaultValue="Default Value"
              variant="outlined"
            />
            <br/>
            <br/>
            <div style={{ display: "flex" }}>
                <Button 
                  type="submit"
                  variant="contained" 
                  color="primary"
                  size="large"
                  style={{ marginLeft: "auto" }}>
                  Post
                </Button>
            </div>
            </form>
         </Grid>
        </Grid>
      </main>
    </Container>
      
  </ThemeProvider>
  );
};
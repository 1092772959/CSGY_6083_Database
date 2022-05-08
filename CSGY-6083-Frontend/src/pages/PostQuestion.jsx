import React from 'react';

import { Container, Grid, Typography, TextField, Button, Alert, FormControl, InputLabel, Select, MenuItem, FormHelperText} from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import PrimarySearchAppBar from '../component/Header';

import axios from 'axios';
import {BASE_URL} from '../config/server';
import topics from '../data/topcis';

const theme = createTheme();

export default function Question() {

  const [failSnack, setFailSnack] = React.useState(false);
  const [topic, setTopic] = React.useState(-1);

  const handleTopicChange = (event) => {
    setTopic(event.target.value);
  }
  
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    var bodyFormData = new FormData();
    bodyFormData.append('topic_id', topic);
    bodyFormData.append('title', data.get('title'));
    bodyFormData.append('body', data.get('body'));
    const uid = localStorage.getItem('uid');

    const header = {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    };

    // request
    axios.post(BASE_URL + '/questions/user?uid=' + uid, bodyFormData, header)
      .then(res => {
        const data = res.data;
        if (data.code == 0) {
          // redirect to dashboard
          window.location = "/"; 
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
{/* Ask a question */}
      <br/>
      <Grid container>
        <Grid item xs={3}>
          <Typography variant="h4" gutterBottom component="div">
            Ask a Question
          </Typography>
        </Grid>
        <FormControl>
          <InputLabel>Topic</InputLabel>
            <Select
              id="topic-selector"
              name="topic"
              value={topic}
              label="Topic"
              onChange={handleTopicChange}
            > 
            <MenuItem value={-1}>
              All
            </MenuItem>
            {topics.map((topicItem) => {
              return (
                  <MenuItem id="topic_item" value={topicItem.topic_id}>
                      {topicItem.topic_name}
                  </MenuItem>
              )
            })}
            </Select>
          <FormHelperText>Select preferred topic</FormHelperText>
        </FormControl>
      </Grid>
      <br/>
      <Container>
        <Typography variant="h5" gutterBottom component="div">
          Title
        </Typography>
        <form onSubmit={handleSubmit}>
          <TextField 
            id="standard-full-width"  
            name="title"
            fullWidth
            label="Required" 
            defaultValue="Title" 
            inputProps={{ 
                'aria-label': 'description'
            }}
          />
        <br/>
        <br/>
        <Typography variant="h5" gutterBottom component="div">
          Body
        </Typography>
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
      </Container>
      </main>
      
    </Container>
  </ThemeProvider>
  );
};
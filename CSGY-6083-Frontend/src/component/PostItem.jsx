import React, {Fragment, useState} from 'react';

import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

import Chip from '@mui/material/Chip';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import Typography from '@mui/material/Typography';

import Card from '@mui/material/Card';
import CardActionArea from '@mui/material/CardActionArea';
import CardContent from '@mui/material/CardContent';

import axios from 'axios';

import {BASE_URL} from '../config/server';

import './PostItem.scss';


const PostItem = ({
  post: {
    ques_id,
    uid,
    username,
    topic_id,
    date,
    title,
    ques_body,
    answerCount,
    tags,
    isSolved,
    hasBest,
  },
}) => {

  const curUId = parseInt(localStorage.getItem('uid'));

  const [solved, setSolved] = useState(isSolved);

  const handleMarkChange = () => {
    let newSolved;
    if (solved === true) {
      setSolved(false);
      newSolved = false;
    } else {
      setSolved(true);
      newSolved = true;
    }
    // send request to server
    axios.post(BASE_URL + '/questions/solved?id=' + ques_id + '&solved=' + newSolved)
    .then(res => {
      alert('Solved Mark updated');
    })
    .catch(error => {
      alert('Network error');
    });
  }

  return (
    <Grid item xs={12} >
      <Card sx={{ display: 'flex' }}>
            <CardContent sx={{ flex: 1 }}>
            <CardActionArea>
              <Grid container alignItems='center'>
                <Grid item xs={11}>
                  <Typography variant="subtitle1" gutterBottom component="div">
                    <Link to={`/questions/${ques_id}`}>{title}</Link>
                  </Typography>
                </Grid>
              </Grid>
              </CardActionArea>
      
              {/* <br/> */}
              <Grid container >
                <Typography variant="subtitle1" color="text.secondary">
                  {date.toLocaleString()} 
                </Typography>

                <Typography variant="subtitle1" color="text.first">
                  &nbsp;by&nbsp;
                </Typography>
                <Link to={`/user/${uid}`}>
                  <Typography variant="subtitle1" color="text.first">
                    {username}
                  </Typography>
                </Link>
              </Grid>
              
              <Typography variant="subtitle1" paragraph>
                {ques_body.substring(0, 200)}
              </Typography>
                <Grid container justifyContent="flex-end">
                  <Grid item>
                    <Stack direction="row" spacing={1}>
                    {tags.map((tag, index) => (
                      <Chip label={tag} />
                    ))}
                    </Stack>
                  </Grid>
                  <Grid sx={{ flexGrow: 1 }} />
                  
                  {(curUId === uid)? 
                      <Grid item xs={3}>
                        <Button variant="outlined" 
                          color="primary"
                          onClick={handleMarkChange}>
                          {solved === true ? "Mark as UnSolved" : "Mark as Solved"} 
                          
                        </Button>
                      </Grid> : ''}
                    {/* <IconButton 
                      size="large"
                      edge="start"
                      color="inherit"
                      aria-label="open drawer"
                      
                      sx={{ mr: 2 }}>
                      <CheckCircleOutlineIcon />
                    </IconButton> */}
                    <Grid item xs={1}>
                      {solved === true? 
                        <Chip label="Solved" color="primary" />
                      : ''}
                    </Grid>  
                  
                </Grid>
              {/* <Typography variant="subtitle1" color="primary">
                Continue reading...
              </Typography> */}
            </CardContent>
            <CardContent>
              <Typography>
                {answerCount}
              </Typography>
              <Typography>
                answers
              </Typography>
              {/* <div className='vote'>
                <span className='vote-count'>{answerCount}</span>
                <div className='count-text'>answers</div>
              </div> */}
            </CardContent>
          </Card>
    </Grid>
  );
};

export default PostItem;
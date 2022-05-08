import React, {useState} from 'react';

import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

import Chip from '@mui/material/Chip';
import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

import Card from '@mui/material/Card';
import CardActionArea from '@mui/material/CardActionArea';
import CardContent from '@mui/material/CardContent';

import './PostItem.scss';

import Divider from '@mui/material/Divider';
import Paper from '@mui/material/Paper';
import IconButton from '@mui/material/IconButton';
import ThumbUpOffAltIcon from '@mui/icons-material/ThumbUpOffAlt';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import { styled } from '@mui/material/styles';

import axios from 'axios';

import {BASE_URL} from '../config/server';

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));


const Answer = ({
  answer: {
    ans_id,
    ques_id,
    uid,
    username,
    date,
    ans_body,
    isBest,
    thumb_ups,
    likedByUser,
  },
  quid,
  hasBest,
  setHasBest,
}) => {

  const curUId = parseInt(localStorage.getItem('uid'));
  
  const [liked, setLiked] = useState(likedByUser);
  const [curLikes, setCurLikes] = useState(thumb_ups);
  const [best, setBest] = useState(isBest);

  const handleLike = () => {
    if (liked === 1) {
      setLiked(0);
      // delete request
      axios.delete(BASE_URL + '/likes/ans?uid=' + curUId + "&ans_id=" + ans_id)
        .then(res => {
          let answer = res.data.data;
          setCurLikes(answer.thumb_ups);
          alert('Unlike succeed!');
        })
        .catch(error => {
          alert("Network error!");
        });
    } else {
      setLiked(1);
      // like request
      axios.post(BASE_URL + '/likes/ans?uid=' + curUId + "&ans_id=" + ans_id)
        .then(res => {
          let answer = res.data.data;
          setCurLikes(answer.thumb_ups);
          alert('Like succeed!');
        })
        .catch(error => {
          alert("Network error!");
        })
    }
  };

  const handleMarkBest = () => {
    if (hasBest == 0 && best == 0) {
      axios.post(BASE_URL + "/answers/best?ans_id=" + ans_id + "&is_best=1")
        .then(res => {
          if (res.data.code == 0) {      
            setBest(1);
            setHasBest(1); 
          } else {
            console.log(res.data);
          }
        })
        .catch(err => {
          alert("Network error!");
        });
    } else if (hasBest == 1 && best == 1) {
      axios.post(BASE_URL + "/answers/best?ans_id=" + ans_id + "&is_best=0")
        .then(res => {
          if (res.data.code == 0) {      
            setBest(0);
            setHasBest(0); 
          } else {
            console.log(res.data);
          }
        })
        .catch(err => {
          alert("Network error!");
        });
    } else {
      alert("Set best answer error!");
    }
  };

  return (
    <Grid item xs={12} >
      <Card sx={{ display: 'flex' }}>
          <CardContent sx={{ flex: 1 }}>
              <Grid container >
                <Typography variant="subtitle1" color="text.secondary">
                  {date} 
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
              <Grid container>
                <Typography variant="subtitle1" paragraph>
                    {ans_body}
                  </Typography>
              </Grid>
              <Divider />
              <br />
              <Grid container >
                <Grid item xs={2}>
                  <Grid container>
                  <Grid item xs={2} sx={{ml: 1}}>
                  <IconButton
                      // size="small"
                      edge="start"
                      color="inherit"
                      aria-label="open drawer"
                      onClick={handleLike}
                      >
                        {liked === 1? <ThumbUpAltIcon/> : <ThumbUpOffAltIcon/>}
                    </IconButton>
                  </Grid>
                  <Grid item xs={1}>
                    {/* <br/> */}
                    <Grid sx={{ p: 1 }}>

                      <Typography >{curLikes}</Typography>
                    </Grid>
                  </Grid>
                  </Grid>
                </Grid>
                <Grid sx={{ flexGrow: 1 }} />
                  {(curUId === quid && (hasBest && best || !hasBest))? 
                    <Grid item xs={3}>
                      <Button variant="outlined" 
                        color="success"
                        onClick={handleMarkBest}>
                        {best == 0 ? "Mark Best Answer" : "UnMark"} 
                      </Button>
                  </Grid> : ''}
                 
                  <Grid item xs={2}>
                    {(hasBest && best) ?
                      <Chip label="Best Answer" color="success" />
                    : null}
                  </Grid>
              </Grid>
          </CardContent>
      </Card>
    </Grid>
  );
};


Answer.propTypes = {
  setHasBest: PropTypes.func.isRequired,
};

export default Answer;
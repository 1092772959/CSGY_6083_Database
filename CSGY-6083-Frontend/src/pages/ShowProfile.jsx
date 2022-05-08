import React, {useState, useEffect} from 'react';

import { Container, List, Typography, ListItem, ListItemText, Box, ListItemButton, Divider} from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import PrimarySearchAppBar from '../component/Header';
import axios from 'axios';
import {BASE_URL} from '../config/server';

const theme = createTheme();

export default function ShowProfile() {
  
  const [failSnack, setFailSnack] = React.useState(false);
  const[user, setUser] = useState({
    username: "",
    email: "",
    level: "",
    profile: "",
    city: "",
    state: "",
    country: "",
  });

  useEffect(()=> {
    const uid = localStorage.getItem('uid');
    if(uid == null || uid === undefined) {
      alert('Please Login');
      window.location = "/signin"; 
      return;
    }

    axios.get(BASE_URL + "/users/" + uid)
      .then(res => {
        console.log(res.data.data);
        setUser(res.data.data);
      })
      .catch(error => {
        alert("Network error!");
      });
  }, []);

  return (
  <ThemeProvider theme={theme}>
    <PrimarySearchAppBar></PrimarySearchAppBar>
    <Container maxWidth="xs">
      <main>
{/* Edit the profilez*/}
      <br/>
    <Box
        sx={{
        marginTop: 8,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        }}
    >
    <Typography variant="h4" gutterBottom component="div">
        Profile
    </Typography>
    <Box sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
      <nav aria-label="main mailbox folders">
        <List>
          <ListItem>
            <ListItemButton>
              <ListItemText primary="Username" sx={{width: 500,}}/>
              <ListItemText primary={user.username} />
            </ListItemButton>
          </ListItem>
          <Divider></Divider>
          
          <ListItem>
            <ListItemButton>
              <ListItemText primary="Email" sx={{width: 500,}}/>
              <ListItemText primary={user.email} />
            </ListItemButton>
          </ListItem>
          <Divider></Divider>

          <ListItem>
            <ListItemButton>
            <ListItemText primary="Level" sx={{width: 500,}}/>
              <ListItemText primary={user.level} />
            </ListItemButton>
          </ListItem>
          <Divider></Divider>

          <ListItem>
            <ListItemButton>
            <ListItemText primary="City" sx={{width: 500,}}/>
              <ListItemText primary={user.city} />
            </ListItemButton>
          </ListItem>
          <Divider></Divider>

          <ListItem>
            <ListItemButton>
            <ListItemText primary="State" sx={{width: 500,}}/>
              <ListItemText primary={user.state} />
            </ListItemButton>
          </ListItem>
          <Divider></Divider>

          <ListItem>
            <ListItemButton>
            <ListItemText primary="Country" sx={{width: 500,}}/>
              <ListItemText primary={user.country} />
            </ListItemButton>
          </ListItem>
          <Divider></Divider>


          <ListItem>
            <ListItemButton>
            <ListItemText primary="Profile" sx={{width: 500,}}/>
              <ListItemText primary={user.profile} multiline/>
            </ListItemButton>
          </ListItem>
          <Divider></Divider>
          
        </List>
      </nav>
    </Box>
    </Box>
    </main>
    </Container>
  </ThemeProvider>
  );
};
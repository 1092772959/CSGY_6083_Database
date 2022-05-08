import React, { useEffect } from 'react';

import { Container, Grid, Typography, TextField, Alert, Box, Button} from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import PrimarySearchAppBar from '../component/Header';
import axios from 'axios';
import {BASE_URL} from '../config/server';

const theme = createTheme();

export default function Profile() {
  
  const [failSnack, setFailSnack] = React.useState(false);
  
  useEffect(()=> {
    const uid = localStorage.getItem('uid');
    if(uid == null || uid === undefined) {
      alert('Please Login');
      window.location = "/signin"; 
      return;
    }
  });
  
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const uid = localStorage.getItem('uid');

    var bodyFormData = new FormData();  
    bodyFormData.append('username', data.get('username'));
    bodyFormData.append('password', data.get('password'));
    bodyFormData.append('email', data.get('email'));
    bodyFormData.append('city', data.get('city'));
    bodyFormData.append('state', data.get('state'));
    bodyFormData.append('country', data.get('country'));
    bodyFormData.append('profile', data.get('profile'));

    const header = {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    };

    // request
    axios.post(BASE_URL + '/profile?uid=' + uid, bodyFormData, header)
      .then(res => {
        const data = res.data;
        if (data.code == 0) {
          // redirect to dashboard
          window.location = "/profileDisplay"; 
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
        Edit the Profile
    </Typography>
      <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <TextField
                    autoComplete="given-name"
                    name="username"
                    required
                    fullWidth
                    id="username"
                    label="username"
                    autoFocus
                />
            </Grid>  
            <Grid item xs={12}>
                <TextField
                    required
                    fullWidth
                    id="email"
                    label="email"
                    name="email"
                    autoComplete="email"
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    required
                    fullWidth
                    name="password"
                    label="password"
                    type="password"
                    id="password"
                    autoComplete="new-password"
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    autoComplete="given-name"
                    name="city"
                    required
                    fullWidth
                    id="city"
                    label="city"
                    autoFocus
                />
            </Grid>  
            <Grid item xs={12}>
                <TextField
                    autoComplete="given-name"
                    name="state"
                    required
                    fullWidth
                    id="state"
                    label="state"
                    autoFocus
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    autoComplete="given-name"
                    name="country"
                    required
                    fullWidth
                    id="country"
                    label="country"
                    autoFocus
                />
            </Grid>  
            <Grid item xs={12}>
                <TextField
                    autoComplete="given-name"
                    name="profile"
                    required
                    fullWidth
                    multiline
                    rows={10}
                    id="profile"
                    label="profile"
                    autoFocus
                />
            </Grid>  
        </Grid>
            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                >
                Update
            </Button>
      </Box>
      </Box>
      </main>
    </Container>
  </ThemeProvider>
  );
};
import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Alert from '@mui/material/Alert';
import Snackbar from '@mui/material/Snackbar';

import axios from 'axios';

import {BASE_URL} from '../config/server';

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const theme = createTheme();

export default function SignIn() {

  const [successSnack, setSuccessSnack] = React.useState(false);
  const [failSnack, setFailSnack] = React.useState(false);
  const [loginSuccess, setLoginSuccess] = React.useState(false);
  // const [error, setError] = React.useState(false);
  
  const handleClose1 = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setSuccessSnack(false);
  };

  const handleClose2 = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setFailSnack(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.target);
    console.log(data.value);

    const header = {
      headers : {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    };

    var bodyFormData = new FormData();
    bodyFormData.append('username', data.get('username'));
    bodyFormData.append('password', data.get('password'));

    // request
    axios.post(BASE_URL + '/users/login', bodyFormData, header)
      .then(res => {
        const data = res.data;
        if (data.code == 0) {
          localStorage.setItem('uid', data.data);
          // redirect to home
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
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Snackbar
          open={failSnack}
          autoHideDuration={6000}
          onClose={handleClose2}
          message="Wrong Username or Password!"
          // action={action}
        />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="username"
              label="User Name"
              name="username"
              autoComplete="username"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            {/* <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            /> */}
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              {/* <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid> */}
              <Grid item>
                <Link href="/signup" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
      </Container>
    </ThemeProvider>
  );
}

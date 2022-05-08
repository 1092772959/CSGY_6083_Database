import logo from './logo.svg';
import './App.css';
import Router from './Router'
import { Box } from '@mui/material';

import axios from 'axios';

function App() {

  axios.defaults.withCredentials = true;

  return (
    <div>
      <Router></Router>
    </div>
  );
}

export default App;

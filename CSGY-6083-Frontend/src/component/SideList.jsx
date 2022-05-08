import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import Grid from '@mui/material/Grid';
import ListItemText from '@mui/material/ListItemText';
import ListSubheader from '@mui/material/ListSubheader';
import DashboardIcon from '@mui/icons-material/Dashboard';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import PeopleIcon from '@mui/icons-material/People';
import BarChartIcon from '@mui/icons-material/BarChart';
import LayersIcon from '@mui/icons-material/Layers';

import { Link } from "react-router-dom";

const SideList = () => {

  return (
  <Grid container>
    <Grid item xs={12}>
    <ListItemButton button component={Link} to="/">
      <ListItemIcon>
        <DashboardIcon />
      </ListItemIcon>
      <ListItemText primary="My Questions" />
    </ListItemButton>
    </Grid>
    <Grid item xs={12}>
    <ListItemButton button component={Link} to="/myanswer">
      <ListItemIcon>
        <DashboardIcon />
      </ListItemIcon>
      <ListItemText primary="My Answers" />
    </ListItemButton>
    </Grid>
    <Grid item xs={12}>
    <ListItemButton component={Link} to="/mylikes">
      <ListItemIcon>
        <ShoppingCartIcon />
      </ListItemIcon>
      <ListItemText primary="My Likes" />
    </ListItemButton>
    </Grid>

    <Grid item xs={12}>
    <ListItemButton component={Link} to="/explore">
      <ListItemIcon>
        <ShoppingCartIcon />
      </ListItemIcon>
      <ListItemText primary="Explore" />
    </ListItemButton>
    </Grid>

    <Grid item xs={12}>
    <ListItemButton component={Link} to="/profile">
      <ListItemIcon>
        <PeopleIcon />
      </ListItemIcon>
      <ListItemText primary="Edit Profile" />
    </ListItemButton>
    </Grid>
    
    <Grid item xs={12}>
    <ListItemButton component={Link} to="/profileDisplay">
      <ListItemIcon>
        <PeopleIcon />
      </ListItemIcon>
      <ListItemText primary="Show Profile" />
    </ListItemButton>
    </Grid>
  </Grid>
  )};

export default SideList;
import React from 'react';
import {Routes, Route} from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import SignUp from './pages/SignUp';
import SignIn from './pages/SignIn';
import Question from './pages/Question';
import PostQuestion from './pages/PostQuestion';
import Explore from './pages/Explore';
import Profile from './pages/Profile';
import MyAnswers from './pages/MyAnswer';
import LikedAnswers from './pages/LikedAnswers';
import ShowProfile from './pages/ShowProfile';

const Router = () => {
  return (
    <div>
      <Routes>
        <Route exact path='/' element={<Dashboard />}></Route>
        <Route exact path='/signup' element={<SignUp />}></Route>
        <Route exact path='/signin' element={<SignIn />}></Route>
        <Route exact path='/explore' element={<Explore />}></Route>
        <Route exact path='/questions/:quesId' element={<Question />}></Route>
        <Route exact path='/questions/post' element={<PostQuestion />}></Route>
        <Route exact path='/profile' element={<Profile />}></Route>
        <Route exact path='/myanswer' element={<MyAnswers />}></Route>
        <Route exact path='/mylikes' element={<LikedAnswers />}></Route>
        <Route exact path='/profileDisplay' element={<ShowProfile />}></Route>
      </Routes>
    </div>
  );
};

export default Router;
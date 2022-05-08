import * as React from 'react';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormHelperText from '@mui/material/FormHelperText';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import PropTypes from 'prop-types';

import topics from '../data/topcis';

import axios from 'axios';

import {BASE_URL} from '../config/server';

const TopicSelector = ({ setQuestions }) => {
  
  const [parentTopic, setParentTopic] = React.useState(-1);
  const [topic, setTopic] = React.useState(-1);
  const [topicList, setTopicList] = React.useState([]);

  React.useEffect(() => {
    axios.get(BASE_URL + "/topics")
      .then(res => {
        let topics = res.data.data;
        setTopicList(topics);
      })
      .catch(error => {
        alert("Network error!");
      });
  }, []);

  const handleParentTopicChange = (event) => {
    setParentTopic(event.target.value);
    setQuestions(event.target.value, -1);
  }

  const handleChange = (event) => {
    setTopic(event.target.value);
    setQuestions(parentTopic, event.target.value);
  };

  return (
    <div>
      <FormControl sx={{ m: 1, minWidth: 120 }}>
        <InputLabel id="demo-simple-select-helper-label">Topic</InputLabel>
        <Select
          labelId="demo-simple-select-helper-label"
          id="parent-topic-selectot"
          value={parentTopic}
          label="Parent Topic"
          onChange={handleParentTopicChange}
        > 
          <MenuItem value={-1}>
            {/* <em>None</em> */}
            All
          </MenuItem>
            {topicList.filter(topicItem => topicItem.parent_id == -1)
              .map((topicItem) => {
              return (
                  <MenuItem value={topicItem.topic_id}>
                      {topicItem.topic_name}
                  </MenuItem>
              )
            })}  
        </Select>
        {/* <FormHelperText>Select preferred topic</FormHelperText> */}
      </FormControl>
      <FormControl sx={{ m: 1, minWidth: 120 }}>
        <InputLabel id="demo-simple-select-helper-label2">Sub Topic</InputLabel>
        <Select
          labelId="demo-simple-select-helper-label2"
          id="topic-selector"
          label="Sub Topic"
          value={topic}
          onChange={handleChange}
          displayEmpty
          // inputProps={{ 'aria-label': 'Without label' }}
        >
          <MenuItem value={-1}>
            All
          </MenuItem>
          {topicList.filter(topicItem => parentTopic != -1 ? (topicItem.parent_id == parentTopic) : false)
            .map((topicItem) => {
              return (
                  <MenuItem value={topicItem.topic_id}>
                      {topicItem.topic_name}
                  </MenuItem>
              )
            })}
        </Select>
        {/* <FormHelperText>Without label</FormHelperText> */}
      </FormControl>
    </div>
  );
}

TopicSelector.propTypes = {
  setQuestions: PropTypes.func.isRequired,
};

export default TopicSelector;
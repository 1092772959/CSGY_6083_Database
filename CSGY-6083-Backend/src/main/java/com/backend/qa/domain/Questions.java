package com.backend.qa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("Questions")
public class Questions {
    private Integer ques_id;
    private Integer uid;
    private Integer topic_id;
    private Date date;
    private String title;
    private String ques_body;
    private Boolean isSolved;

    @Override
    public String toString() {
        return "Questions{" +
                "ques_id=" + ques_id +
                ", uid=" + uid +
                ", topic_id=" + topic_id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", ques_body='" + ques_body + '\'' +
                ", isSolved=" + isSolved +
                '}';
    }
}
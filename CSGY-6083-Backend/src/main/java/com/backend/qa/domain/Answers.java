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
@Alias("Answers")
public class Answers {
    private Integer ans_id;
    private Integer uid;
    private Integer ques_id;
    private Date date;
    private String ans_body;
    private Integer thumb_ups;
    private Boolean isBest;
}

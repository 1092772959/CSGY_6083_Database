package com.backend.qa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("Topic")
public class Topic {
    private Integer topic_id;
    private String topic_name;
    private Integer parent_id;

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topic_id +
                ", topic_name='" + topic_name + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }
}

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
@Alias("Like")
public class Like {
    private Integer uid;
    private Integer ans_id;

    @Override
    public String toString() {
        return "Like{" +
                "uid=" + uid +
                ", ans_id=" + ans_id +
                '}';
    }
}

package com.greenpaperuj.wuziqi.pojo;
import java.time.LocalDateTime;

import lombok.Data;


@Data
public class User {
    private String username;
    private String password;
    private int id;
    private int score;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

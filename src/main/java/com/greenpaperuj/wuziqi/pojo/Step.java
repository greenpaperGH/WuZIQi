package com.greenpaperuj.wuziqi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Step {
    private Integer roomId;
    private Integer chessPlayerID;
    private Byte row;
    private Byte column;
}

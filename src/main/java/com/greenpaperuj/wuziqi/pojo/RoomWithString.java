package com.greenpaperuj.wuziqi.pojo;

import com.greenpaperuj.wuziqi.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomWithString {
    private Integer id;
    private Integer playerOneId;
    private Integer playerTwoId;
    private String boardString;
    private StatusEnum status;
    private Integer nextPlayerId;
    private Integer winnerId;
}

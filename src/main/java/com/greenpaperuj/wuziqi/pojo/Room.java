package com.greenpaperuj.wuziqi.pojo;

import com.greenpaperuj.wuziqi.enums.StatusEnum;
import com.greenpaperuj.wuziqi.utils.BoardChangeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Integer id;
    private Integer playerOneId;
    private Integer playerTwoId;
    private Byte[][] checkerboard;//0当空位，-1为玩家一下的棋，1为玩家2下的棋
    private StatusEnum status;
    private Integer nextPlayerId;
    private Integer winnerId;

    public Room(Integer playerOneId, Integer playerTwoId) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.checkerboard = BoardChangeUtils.newBoard();
        this.status = StatusEnum.WAITING;
        this.nextPlayerId = playerOneId;
    }

    public Room(Integer playerOneId) {
        this.playerOneId = playerOneId;
        this.checkerboard = BoardChangeUtils.newBoard();
        this.status = StatusEnum.WAITING;
        this.nextPlayerId = playerOneId;
    }
}
package com.greenpaperuj.wuziqi.pojo;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Room {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private Integer id;
    private Integer playerOneId;
    private Integer playerTwoId;
    private Byte[][] checkerboard;//0当空位，-1为玩家一下的棋，1为玩家2下的棋
    private Status status;

    private enum Status {
        FINISHED, PROCEEDING, WAITING
    }//FINISHED表示棋局已经完成，PROCEEDING表示正在进行中，WAITING表示等待玩家加入

    public Room(Integer playerOneId, Integer playerTwoId) {
        this.id = idCounter.getAndIncrement();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.checkerboard = newBoard();
        this.status = Status.WAITING;
    }

    public static Byte[][] newBoard() {
        Byte[][] board = new Byte[17][17];

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                board[i][j] = 0;
            }
        }

        return board;
    }
}
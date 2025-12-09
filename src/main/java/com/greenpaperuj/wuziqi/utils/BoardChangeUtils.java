package com.greenpaperuj.wuziqi.utils;

import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.Step;

public class BoardChangeUtils {
    public static Room changeByStep(Step step, Room room,byte role) {
        Byte[][] board = room.getCheckerboard();
        board[step.getRow()][step.getColumn()] = role;
        room.setCheckerboard(board);
        return room;
    }
}

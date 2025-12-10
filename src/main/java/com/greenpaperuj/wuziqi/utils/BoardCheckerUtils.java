package com.greenpaperuj.wuziqi.utils;

import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.Step;

public class BoardCheckerUtils {
    public static Boolean legalPositionCheck(Step step, Room room) {
        byte row = step.getRow();
        byte column = step.getColumn();
        Byte[][] checkerboard = room.getCheckerboard();

        if (row < 1 || row > 15 || column < 1 || column > 15) {
            return false;
        }

        return checkerboard[row][column] == 0;
    }

    public static Boolean winCheck(Step step, Room room, byte chess) {
        //chess代表下棋者的棋是哪个数字，规定-1为玩家一，1为玩家二
        byte row = step.getRow();
        byte column = step.getColumn();
        Byte[][] checkerboard = room.getCheckerboard();

        byte verticalCount = (byte) (upVerticalCount(row, column, chess, checkerboard) +
                        downVerticalCount(row, column, chess, checkerboard) - 1);

        byte crosswiseCount = (byte) (leftCrosswiseCount(row, column, chess, checkerboard) +
                rightCrosswiseCount(row, column, chess, checkerboard) - 1);

        byte slopeOneCount = (byte) (leftAndUpCount(row, column, chess, checkerboard) +
                rightAndDownCount(row, column, chess, checkerboard) - 1);

        byte slopeTwoCount = (byte) (leftAndDownCount(row, column, chess, checkerboard) +
                rightAndUpCount(row, column, chess, checkerboard) - 1);

        return verticalCount >= 5 || crosswiseCount >= 5 || slopeOneCount >= 5 || slopeTwoCount >= 5;
    }

    private static byte upVerticalCount(byte row, byte column, byte chess,Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + upVerticalCount((byte) (row + 1), column, chess,checkerboard));
        }
        return 0;
    }

    private static byte downVerticalCount(byte row, byte column, byte chess,Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + downVerticalCount((byte) (row - 1), column, chess,checkerboard));
        }
        return 0;
    }

    private static byte leftCrosswiseCount(byte row, byte column, byte chess,Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + leftCrosswiseCount( row, (byte) (column - 1), chess,checkerboard));
        }
        return 0;
    }

    private static byte rightCrosswiseCount(byte row, byte column, byte chess,Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + rightCrosswiseCount( row, (byte) (column + 1), chess,checkerboard));
        }
        return 0;
    }

    private static byte leftAndUpCount(byte row, byte column, byte chess, Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + leftAndUpCount((byte) (row - 1), (byte) (column - 1), chess,checkerboard));
        }
        return 0;
    }

    private static byte rightAndDownCount(byte row, byte column, byte chess, Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + rightAndDownCount((byte) (row + 1), (byte) (column + 1), chess,checkerboard));
        }
        return 0;
    }

    private static byte leftAndDownCount(byte row, byte column, byte chess, Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + leftAndDownCount((byte) (row + 1), (byte) (column - 1), chess,checkerboard));
        }
        return 0;
    }

    private static byte rightAndUpCount(byte row, byte column, byte chess, Byte[][] checkerboard) {
        if(checkerboard[row][column] == chess) {
            return (byte) (1 + rightAndUpCount((byte) (row - 1), (byte) (column + 1), chess,checkerboard));
        }
        return 0;
    }
}
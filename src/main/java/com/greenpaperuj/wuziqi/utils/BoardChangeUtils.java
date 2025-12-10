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

    public static String changeByStep(Step step, String boardString, byte role) {
        int index = (step.getRow() - 1) * 15 +(step.getColumn() - 1);
        StringBuilder stringBuilder = new StringBuilder(boardString);

        stringBuilder.delete(index, index - 1);
        char chess = switch (role) {
            case -1 -> 'b';
            case 1 -> 'c';
            default -> 'a';
        };
        stringBuilder.insert(index, chess);

        return stringBuilder.toString();
    }

    public static String boardToString(Byte[][] board) {
        StringBuilder boardString = new StringBuilder();

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {
                switch (board[i][j]) {
                    case 0 :
                        boardString.append('a');
                        break;
                    case -1 :
                        boardString.append('b');
                        break;
                    case 1 :
                        boardString.append('c');
                }
            }
        }

        return boardString.toString();
    }

    public static Byte[][] stringToBoard(String boardString) {
        int index;
        Byte[][] board = BoardChangeUtils.newBoard();

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {
                index = (i - 1) * 15 + (j -1);
                byte chess = switch (boardString.charAt(index)) {
                    case 'a' -> 0;
                    case 'b' -> -1;
                    case 'c' -> 1;
                    default -> throw new IllegalStateException("Unexpected value: " + boardString.charAt(index));
                };
                board[i][j] = chess;
            }
        }

        return board;
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

package com.greenpaperuj.wuziqi;

import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import com.greenpaperuj.wuziqi.pojo.Step;
import com.greenpaperuj.wuziqi.service.RoomService;
import com.greenpaperuj.wuziqi.service.impl.RoomServiceImpl;
import com.greenpaperuj.wuziqi.utils.BoardChangeUtils;
import com.greenpaperuj.wuziqi.utils.BoardCheckerUtils;
import com.greenpaperuj.wuziqi.utils.RoomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WuZiQiApplicationTests {

    @Test
    void contextLoads() {
        Step step = new Step(1, (byte) 8, (byte) 8);

        Room roomOne = new Room(100, 101);
        Byte[][] boardOne = BoardChangeUtils.newBoard();
        boardOne[7][7] = 1;
        boardOne[6][6] = 1;
        boardOne[5][5] = 1;
        boardOne[9][9] = 1;
        boardOne[8][8] = 1;
        roomOne.setCheckerboard(boardOne);

        String boardStringOne = BoardChangeUtils.boardToString(boardOne);
        System.out.println(boardStringOne);

        Byte[][] board = BoardChangeUtils.stringToBoard(boardStringOne);

        System.out.println(BoardCheckerUtils.winCheck(step, roomOne, (byte) 1));
    }

    @Test
    void test1() {
        Room room = new Room(100, 101);
        RoomWithString roomWithString = RoomUtils.toRoomWithString(room);

        RoomService roomService = new RoomServiceImpl();
        
        roomService.create(roomWithString);
    }

}

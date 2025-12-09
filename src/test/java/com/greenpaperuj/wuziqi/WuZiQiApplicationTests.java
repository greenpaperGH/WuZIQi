package com.greenpaperuj.wuziqi;

import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.Step;
import com.greenpaperuj.wuziqi.utils.BoardCheckerUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WuZiQiApplicationTests {

    @Test
    void contextLoads() {
        Step step = new Step(1, (byte) 8, (byte) 8);

        Room roomOne = new Room(100, 101);
        Byte[][] boardOne = Room.newBoard();
        boardOne[7][7] = 1;
        boardOne[6][6] = 1;
        boardOne[5][5] = 1;
        boardOne[9][9] = 1;
        boardOne[8][8] = 1;
        roomOne.setCheckerboard(boardOne);

        System.out.println(BoardCheckerUtils.winCheck(step, roomOne, (byte) 1));
    }

}

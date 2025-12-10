package com.greenpaperuj.wuziqi.utils;

import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;

public class RoomUtils {
    public static Room toRoom (RoomWithString roomWithString) {
        return new Room(roomWithString.getId(), roomWithString.getPlayerOneId(), roomWithString.getPlayerTwoId(),
                BoardChangeUtils.stringToBoard(roomWithString.getBoardString()), roomWithString.getStatus());
    }

    public static RoomWithString toRoomWithString (Room room) {
        return new RoomWithString(room.getId(), room.getPlayerOneId(), room.getPlayerTwoId(),
                BoardChangeUtils.boardToString(room.getCheckerboard()), room.getStatus());
    }
}

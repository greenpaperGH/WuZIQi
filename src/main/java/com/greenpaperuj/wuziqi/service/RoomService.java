package com.greenpaperuj.wuziqi.service;

import com.greenpaperuj.wuziqi.pojo.RoomWithString;

import java.util.List;

public interface RoomService {
    List<RoomWithString> list();

    void create(RoomWithString roomWithString);

    void playerJoin(int position, int playerId, int roomId);

    RoomWithString selectedById (Integer id);

    void triggerGameStart(Integer roomId);
}

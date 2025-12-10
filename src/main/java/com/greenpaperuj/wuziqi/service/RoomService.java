package com.greenpaperuj.wuziqi.service;

import com.greenpaperuj.wuziqi.pojo.RoomWithString;

import java.util.List;

public interface RoomService {
    List<RoomWithString> list();

    void create(RoomWithString roomWithString);

    RoomWithString selectedById (int id);

    void playerJoin(int position, int playerId, int roomId);
}

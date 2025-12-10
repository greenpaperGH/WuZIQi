package com.greenpaperuj.wuziqi.service.impl;

import com.greenpaperuj.wuziqi.enums.StatusEnum;
import com.greenpaperuj.wuziqi.mapper.RoomMapper;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import com.greenpaperuj.wuziqi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomMapper roomMapper;

    @Override
    public List<RoomWithString> list() {
        return roomMapper.list();
    }

    @Override
    public void create(RoomWithString roomWithString) {
        roomMapper.create(roomWithString);
    }

    @Override
    public RoomWithString selectedById(Integer id) {
        return roomMapper.selectById(id);
    }

    @Override
    public void triggerGameStart(Integer roomId) {
        RoomWithString room = roomMapper.selectById(roomId);

        if (room.getPlayerOneId() == null || room.getPlayerTwoId() == null) {
            return;
        }

        Random random = new Random();
        Integer startPlayerId = random.nextBoolean() ? room.getPlayerOneId() : room.getPlayerTwoId();

        roomMapper.startGame(StatusEnum.PROCEEDING, startPlayerId, roomId);
    }

    @Override
    public void playerJoin(int position, int playerId, int roomId) {
        if( position == 1 ) {
            roomMapper.playerJoinOne(playerId, roomId);
        } else {
            roomMapper.playerJoinTwo(playerId, roomId);
        }
    }
}

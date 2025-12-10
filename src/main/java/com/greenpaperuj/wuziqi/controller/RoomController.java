package com.greenpaperuj.wuziqi.controller;

import com.greenpaperuj.wuziqi.enums.StatusEnum;
import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import com.greenpaperuj.wuziqi.service.RoomService;
import com.greenpaperuj.wuziqi.utils.JwtUtils;
import com.greenpaperuj.wuziqi.utils.RoomUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/wuziqi/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping
    public Result list() {
        log.info("获取房间列表.");
        List<Room> roomList = new ArrayList<>();
        List<RoomWithString> roomWithStringsList = roomService.list();

        for (RoomWithString roomWithString : roomWithStringsList) {
            roomList.add(RoomUtils.toRoom(roomWithString));
        }

        return Result.success(roomList);
    }

    @PostMapping("/create")
    public Result create(@RequestHeader("token") String jwtToken) {
        log.info("创建房间.");

        Integer playerOneId = JwtUtils.parseToken(jwtToken).get("id", Integer.class);
        Room room = new Room(playerOneId);
        RoomWithString roomWithString = RoomUtils.toRoomWithString(room);

        roomService.create(roomWithString);

        return Result.success(room);
    }

    @PutMapping("/{id}")
    public Result playerJoin(@PathVariable Integer id, @RequestHeader("token") String jwtToken) {
        log.info("玩家加入房间.");
        RoomWithString roomWithString = roomService.selectedById(id);

        if (roomWithString == null) {
            return Result.error("Room not found.");
        }

        Integer roomId = roomWithString.getId();
        Claims claims = JwtUtils.parseToken(jwtToken);
        Integer playerId = claims.get("id", Integer.class);

        if (roomWithString.getStatus() != StatusEnum.WAITING) {
            return Result.error("This room is not available.");
        }

        if (roomWithString.getPlayerOneId() != null) {

            if (roomWithString.getPlayerOneId().equals(playerId)) {
                return Result.error("You are already in this room.");
            }

            roomService.playerJoin(2, playerId, roomId);

            roomService.triggerGameStart(roomId);

            return Result.success("Game Started!");
        }

        roomService.playerJoin(1, playerId, roomId);
        return Result.success();
    }

}

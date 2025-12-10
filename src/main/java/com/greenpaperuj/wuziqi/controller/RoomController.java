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

    @PostMapping
    public Result create(@RequestBody Room room) {
        log.info("创建房间.");
        RoomWithString roomWithString = RoomUtils.toRoomWithString(new Room(room.getPlayerOneId(), room.getPlayerTwoId()));
        roomService.create(roomWithString);

        return Result.success();
    }

    @PutMapping("/{id}")
    public Result playerJoin(@PathVariable Integer id, @RequestHeader("token") String jwtToken) {
        log.info("玩家加入房间.");

        RoomWithString roomWithString = roomService.selectedById(id);
        Integer roomId = roomWithString.getId();
        Claims claims = JwtUtils.parseToken(jwtToken);
        Integer playerId = claims.get("id", Integer.class);

        if (roomWithString.getStatus() != StatusEnum.WAITING) {
            return Result.error("This room is not available.");
        }

        if (roomWithString.getPlayerOneId() != null) {
            roomService.playerJoin(1, playerId, roomId);
            return Result.success();
        }

        roomService.playerJoin(2, playerId, roomId);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectRoomById(@PathVariable Integer id) {
        log.info("使用id查询房间.");

        return Result.success(RoomUtils.toRoom(roomService.selectedById(id)));
    }

}

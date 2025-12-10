package com.greenpaperuj.wuziqi.controller;

import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.pojo.Step;
import com.greenpaperuj.wuziqi.service.GameService;
import com.greenpaperuj.wuziqi.utils.JwtUtils;
import com.greenpaperuj.wuziqi.utils.RoomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wuziqi/game")
public class GameController {
    @Autowired
    GameService gameService;

    @PostMapping("/move")
    public Result move(@RequestBody Step step, @RequestHeader("token") String token) {
        log.info("根据step进行移动.");
        Integer currentUserId = JwtUtils.parseToken(token).get("id", Integer.class);
        step.setChessPlayerID(currentUserId);

        return gameService.processMove(step);
    }

    @GetMapping("/info/{id}")
    public Result selectRoomById(@PathVariable Integer id) {
        log.info("使用id查询房间.");

        return Result.success(RoomUtils.toRoom(gameService.selectedById(id)));
        
    }
}

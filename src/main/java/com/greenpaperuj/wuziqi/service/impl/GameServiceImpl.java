package com.greenpaperuj.wuziqi.service.impl;

import com.greenpaperuj.wuziqi.enums.StatusEnum;
import com.greenpaperuj.wuziqi.mapper.RoomMapper;
import com.greenpaperuj.wuziqi.mapper.UserMapper;
import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.pojo.Room;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import com.greenpaperuj.wuziqi.pojo.Step;
import com.greenpaperuj.wuziqi.service.GameService;
import com.greenpaperuj.wuziqi.utils.BoardChangeUtils;
import com.greenpaperuj.wuziqi.utils.BoardCheckerUtils;
import com.greenpaperuj.wuziqi.utils.RoomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    RoomMapper roomMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public RoomWithString selectedById(Integer id) {
        return roomMapper.selectById(id);
    }

    @Override
    public Result processMove(Step step) {
        RoomWithString roomWithString = roomMapper.selectById(step.getRoomId());
        if (roomWithString == null) {
            return Result.error("No such room.");
        }
        Room room = RoomUtils.toRoom(roomWithString);

        if (roomWithString.getStatus() != StatusEnum.PROCEEDING) {
            return Result.error("This game is not available for playing.");
        }

        if (!roomWithString.getNextPlayerId().equals(step.getChessPlayerID())) {
            return Result.error("Not your turn.");
        }//为什么IDEA提示用这个equal方法

        if (!BoardCheckerUtils.legalPositionCheck(step, room)) {
            return Result.error("illegal position.");
        }

        byte chessColor = step.getChessPlayerID().equals(room.getPlayerOneId()) ? (byte) -1 : (byte) 1;

        BoardChangeUtils.changeByStep(step, room, chessColor);

        boolean isWin = BoardCheckerUtils.winCheck(step, room, chessColor);

        RoomWithString updateData = new RoomWithString();
        updateData.setId(room.getId());
        updateData.setBoardString(BoardChangeUtils.boardToString(room.getCheckerboard()));

        if (isWin) {
            updateData.setStatus(StatusEnum.FINISHED);
            updateData.setWinnerId(step.getChessPlayerID());
            updateData.setNextPlayerId(null);
        } else {
            updateData.setStatus(StatusEnum.PROCEEDING);
            updateData.setWinnerId(null);
            int nextPlayer = step.getChessPlayerID().equals(room.getPlayerOneId()) ? room.getPlayerTwoId() : room.getPlayerOneId();
            updateData.setNextPlayerId(nextPlayer);
        }

        roomMapper.updateGameStatus(updateData);

        if (isWin) {
            Integer userId = step.getChessPlayerID();
            Integer updatedScore = userMapper.selectById(userId).getScore() + 1;
            userMapper.updateScore(updatedScore, userId);
            return Result.success("Win!");
        }
        return Result.success("Successfully placed chess.");

    }
}

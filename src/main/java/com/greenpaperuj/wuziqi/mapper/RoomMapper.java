package com.greenpaperuj.wuziqi.mapper;

import com.greenpaperuj.wuziqi.enums.StatusEnum;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomMapper {
    @Select("select * from room_with_string")
    List<RoomWithString> list();

    @Insert("insert into room_with_string(player_one_id, player_two_id, board_string, status) values " +
            "(#{playerOneId}, #{playerTwoId}, #{boardString}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(RoomWithString roomWithString);

    @Select("select * from room_with_string where id = #{id}")
    RoomWithString selectById(int id);

    @Update("update room_with_string set player_one_id = #{playerId} where id = #{roomId}")
    void playerJoinOne(int playerId, int roomId);

    @Update("update room_with_string set player_two_id = #{playerId} where id = #{roomId}")
    void playerJoinTwo(int playerId, int roomId);

    @Update("update room_with_string set board_string = #{boardString}, next_player_id = #{nextPlayerId}, " +
            "status = #{status}, winner_id = #{winnerId} where id = #{id}")
    void updateGameStatus(RoomWithString roomWithString);

    @Update("update room_with_string set status = #{status}, next_player_id = #{nextPlayerId} where id = #{id}")
    void startGame(StatusEnum status, int nextPlayerId, int id);
}

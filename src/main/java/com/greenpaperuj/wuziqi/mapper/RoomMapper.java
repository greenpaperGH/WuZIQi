package com.greenpaperuj.wuziqi.mapper;

import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoomMapper {
    @Select("select * from room_with_string")
    List<RoomWithString> list();

    @Insert("insert into room_with_string(id, player_one_id, player_two_id, board_string, status) values " +
            "(#{id}, #{playerOneId}, #{playerTwoId}, #{boardString}, #{status})")
    void create(RoomWithString roomWithString);

    @Select("select * from room_with_string where id = #{id}")
    RoomWithString selectById(int id);

    @Update("update room_with_string set player_one_id = #{playerId} where id = #{roomId}")
    void playerJoinOne(int playerId, int roomId);

    @Update("update room_with_string set player_two_id = #{playerId} where id = #{roomId}")
    void playerJoinTwo(int playerId, int roomId);
}

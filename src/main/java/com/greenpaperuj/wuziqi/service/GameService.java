package com.greenpaperuj.wuziqi.service;

import com.greenpaperuj.wuziqi.pojo.Result;
import com.greenpaperuj.wuziqi.pojo.RoomWithString;
import com.greenpaperuj.wuziqi.pojo.Step;

public interface GameService {
    RoomWithString selectedById (Integer id);

    Result processMove(Step step);
}

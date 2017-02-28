package com.shu.db.dao.live;

import com.shu.db.dao.BaseMapper;
import com.shu.db.model.live.TLiveRoom;

public interface TLiveRoomMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TLiveRoom record);

    int insertSelective(TLiveRoom record);

    TLiveRoom selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TLiveRoom record);

    int updateByPrimaryKey(TLiveRoom record);
}
package com.shu.services.live;

import com.shu.db.model.Order;
import com.shu.db.model.Pager;
import com.shu.db.model.live.TLiveRoom;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */
public interface TLiveRoomService {
    public List<TLiveRoom> getLRoomListByParam(TLiveRoom follow, Order order, Pager page);

    public void addLRoom(TLiveRoom follow);

    public void modifyLRoom(TLiveRoom follow);

    public TLiveRoom getLRoomById(String id);
}
